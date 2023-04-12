package nl.michiel.portfolio.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Navigation(val icon: ImageVector) {
    TRIPS(Icons.Filled.Place),
    FRIENDS(Icons.Filled.Person),
    PROFILE(Icons.Filled.AccountCircle),
}

val navItems = listOf(
    Navigation.TRIPS,
    Navigation.FRIENDS,
    Navigation.PROFILE
)

@Composable
fun TopLevelNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value
                navItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.name) },
                        selected = currentDestination?.destination?.hierarchy?.any { it.route == screen.name } == true,
                        onClick = {
                            navController.navigate(screen.name) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = Navigation.TRIPS.name, Modifier.padding(padding)) {
            composable(Navigation.TRIPS.name) { Todo("trips") }
            composable(Navigation.FRIENDS.name) { Todo("friends") }
            composable(Navigation.PROFILE.name) { Todo("profile") }
        }
    }
}

@Composable
fun Todo(name: String) {
    Text(name)
}
