package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification.NotificationScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    NavDisplay(
        modifier = modifier,
        backStack = topLevelBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    onClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
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
                SignUpScreen(
                    onSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                    onSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                )
            }
            entry<Route.Main> {
                val backStack = rememberNavBackStack(Route.Home)

                MainScreen(
                    backStack = backStack,
                    body = { modifier ->
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
                                        onDishClick = { recipeId ->
                                            topLevelBackStack.removeIf { it is Route.RecipeDetails }
                                            topLevelBackStack.add(Route.RecipeDetails(recipeId))
                                        },
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesScreen(
                                        onCardClick = { recipeId ->
                                            topLevelBackStack.removeIf { it is Route.RecipeDetails }
                                            topLevelBackStack.add(Route.RecipeDetails(recipeId))
                                        },
                                    )
                                }
                                entry<Route.Notification> { NotificationScreen() }
                                entry<Route.Profile> { ProfileScreen() }
                            },
                        )
                    },
                )
            }
            entry<Route.RecipeDetails> { key ->
                RecipeDetailsScreen(key.recipeId)
            }
        },
    )
}