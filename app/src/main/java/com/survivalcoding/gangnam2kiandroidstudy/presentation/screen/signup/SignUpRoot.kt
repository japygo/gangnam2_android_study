package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpAction.OnSignInWithGoogle
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpRoot(
    viewModel: SignUpViewModel = koinViewModel(),
    onNavigate: (SignUpNavigation) -> Unit = {},
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                SignUpEvent.NavigateToSignIn -> onNavigate(SignUpNavigation.SignIn)
                SignUpEvent.NavigateToMain -> onNavigate(SignUpNavigation.Main)
                is SignUpEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
                SignUpEvent.RequestGoogleSignIn -> {
                    scope.launch {
                        val idToken = getGoogleIdToken(context)
                        if (idToken != null) {
                            viewModel.onAction(OnSignInWithGoogle(idToken))
                        } else {
                            snackbarHostState.showSnackbar("구글 로그인 실패")
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        SignUpScreen(
            modifier = Modifier.padding(innerPadding),
            onAction = viewModel::onAction,
        )
    }
}

suspend fun getGoogleIdToken(context: Context): String? {
    val credentialManager = CredentialManager.create(context)

    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    return try {
        val result = credentialManager.getCredential(context, request)
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            GoogleIdTokenCredential.createFrom(credential.data).idToken
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}