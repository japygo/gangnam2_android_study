package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification.NotificationScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes.SearchRecipeNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkRoute: Route? = null,
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)
    val backStack = rememberNavBackStack(Route.Home)

    LaunchedEffect(deepLinkRoute) {
        if (deepLinkRoute != null) {
            when (deepLinkRoute) {
                is Route.SavedRecipes -> {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main)

                    backStack.clear()
                    backStack.add(Route.SavedRecipes)
                }

                is Route.RecipeDetails -> {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main)

                    backStack.clear()
                    backStack.add(Route.SavedRecipes)

                    topLevelBackStack.removeIf { it is Route.RecipeDetails }
                    topLevelBackStack.add(deepLinkRoute)
                }
                else -> Unit
            }
        }
    }

    NavDisplay(
        modifier = modifier,
        backStack = topLevelBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(
                    onNavigate = { navigation ->
                        when (navigation) {
                            SplashNavigation.SignIn -> {
                                topLevelBackStack.clear()
                                topLevelBackStack.add(Route.SignIn)
                            }
                        }
                    },
                )
            }
            entry<Route.SignIn> {
                SignInScreen(
                    onSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    onSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    },
                )
            }
            entry<Route.SignUp> {
                SignUpRoot(
                    onNavigate = { navigation ->
                        when (navigation) {
                            SignUpNavigation.SignIn -> {
                                topLevelBackStack.clear()
                                topLevelBackStack.add(Route.SignIn)
                            }
                            SignUpNavigation.Main -> {
                                topLevelBackStack.clear()
                                topLevelBackStack.add(Route.Main)
                            }
                        }
                    },
                )
            }
            entry<Route.Main> {
                MainScreen(
                    body = { modifier, snackbarHostState ->
                        NavDisplay(
                            modifier = modifier,
                            backStack = backStack,
                            entryDecorators = listOf(
                                rememberSaveableStateHolderNavEntryDecorator(),
                                rememberViewModelStoreNavEntryDecorator(),
                            ),
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeRoot(
                                        onNavigate = { navigation ->
                                            when (navigation) {
                                                HomeNavigation.SearchRecipes -> {
                                                    topLevelBackStack.removeIf { it is Route.SearchRecipes }
                                                    topLevelBackStack.add(Route.SearchRecipes)
                                                }
                                                is HomeNavigation.RecipeDetails -> {
                                                    topLevelBackStack.removeIf { it is Route.RecipeDetails }
                                                    topLevelBackStack.add(
                                                        Route.RecipeDetails(
                                                            navigation.recipeId,
                                                        ),
                                                    )
                                                }
                                            }
                                        },
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(
                                        snackbarHostState = snackbarHostState,
                                        onNavigate = { navigation ->
                                            when (navigation) {
                                                is SavedRecipesNavigation.RecipeDetails -> {
                                                    topLevelBackStack.removeIf { it is Route.RecipeDetails }
                                                    topLevelBackStack.add(
                                                        Route.RecipeDetails(
                                                            navigation.recipeId,
                                                        ),
                                                    )
                                                }
                                            }
                                        },
                                    )
                                }
                                entry<Route.Notification> { NotificationScreen() }
                                entry<Route.Profile> { ProfileScreen() }
                            },
                        )
                    },
                    onNavigate = {
                        backStack.clear()
                        backStack.add(it)
                    },
                    isSelectedRoute = {
                        val currentRoute = backStack.lastOrNull()

                        it == currentRoute
                    },
                )
            }
            entry<Route.SearchRecipes> { key ->
                SearchRecipesRoot(
                    onNavigate = { navigation ->
                        when (navigation) {
                            is SearchRecipeNavigation.RecipeDetails -> {
                                topLevelBackStack.removeIf { it is Route.RecipeDetails }
                                topLevelBackStack.add(Route.RecipeDetails(navigation.recipeId))
                            }
                            SearchRecipeNavigation.Back -> {
                                topLevelBackStack.remove(key)
                            }
                        }
                    },
                )
            }
            entry<Route.RecipeDetails> { key ->
                RecipeDetailsRoot(
                    id = key.recipeId,
                    onNavigate = { navigation ->
                        when (navigation) {
                            RecipeDetailsNavigation.Back -> {
                                topLevelBackStack.remove(key)
                            }
                            is RecipeDetailsNavigation.Reviews -> {
                                topLevelBackStack.add(Route.Reviews(navigation.recipeId))
                            }
                        }
                    },
                )
            }
        },
    )
}
