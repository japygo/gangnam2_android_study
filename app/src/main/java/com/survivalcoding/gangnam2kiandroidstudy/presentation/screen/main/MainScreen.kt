package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    body: @Composable (modifier: Modifier) -> Unit = {},
    backStack: NavBackStack<NavKey>,
    onFabClick: () -> Unit = {},
) {
    val currentRoute = backStack.lastOrNull()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MainNavigationBar(
                currentRoute = currentRoute,
                onNavigate = {
                    backStack.clear()
                    backStack.add(it)
                },
                onFabClick = onFabClick,
            )
        },
    ) {
        body(Modifier.padding(it))
    }
}

private data class NavItem(
    val route: Route,
    val label: String,
    val icon: Int,
    val selectedIcon: Int,
)

@Composable
private fun MainNavigationBar(
    currentRoute: NavKey?,
    onNavigate: (Route) -> Unit,
    onFabClick: () -> Unit = {},
) {
    val leftNavItems = listOf(
        NavItem(
            Route.Home,
            "Home",
            R.drawable.outline_home,
            R.drawable.select_home,
        ),
        NavItem(
            Route.SavedRecipes,
            "Saved Recipes",
            R.drawable.outline_bookmark,
            R.drawable.select_bookmark,
        ),
    )

    val rightNavItems = listOf(
        NavItem(
            Route.Notification,
            "Notification",
            R.drawable.outline_notification_bing,
            R.drawable.select_notification_bing,
        ),
        NavItem(
            Route.Profile,
            "Profile",
            R.drawable.outline_profile,
            R.drawable.select_profile,
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        NavigationBar(
            containerColor = AppColors.White,
            modifier = Modifier.height(72.dp),
        ) {
            leftNavItems.forEach { navItem ->
                MainNavigationBarItem(currentRoute, navItem, onNavigate)
            }

            Spacer(modifier = Modifier.weight(1f))

            rightNavItems.forEach { navItem ->
                MainNavigationBarItem(currentRoute, navItem, onNavigate)
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(48.dp),
            onClick = onFabClick,
            shape = CircleShape,
            containerColor = AppColors.Primary100,
            contentColor = AppColors.White,
        ) {
            Icon(
                painter = painterResource(R.drawable.fab_plus),
                contentDescription = "Add",
                modifier = Modifier.size(21.dp),
            )
        }
    }
}

@Composable
private fun RowScope.MainNavigationBarItem(
    currentRoute: NavKey?,
    navItem: NavItem,
    onNavigate: (Route) -> Unit,
) {
    val selected = currentRoute == navItem.route
    NavigationBarItem(
        selected = selected,
        onClick = { onNavigate(navItem.route) },
        icon = {
            Icon(
                painter = painterResource(if (selected) navItem.selectedIcon else navItem.icon),
                contentDescription = navItem.label,
                tint = if (selected) Color.Unspecified else AppColors.Gray4,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = AppColors.White,
        ),
    )
}

@Preview
@Composable
fun NavigationBarPreview() {
    MainNavigationBar(currentRoute = Route.Home, onNavigate = {})
}