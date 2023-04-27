package nl.michiel.portfolio.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nl.michiel.friends.view.FriendsScreen
import nl.michiel.trips.view.*

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelNavigation() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val placeDetails = remember { mutableStateOf(PlaceDetails.NONE) }
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    fun showBottomSheet(content: PlaceDetails) {
        coroutineScope.launch {
            placeDetails.value = content
            modalSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = { DetailsFor(placeDetails.value, ::showBottomSheet) },
    ) {
        Scaffold(bottomBar = { AppBottomNavigation(navController) }) { padding ->
            NavHost(
                navController = navController,
                startDestination = Navigation.TRIPS.name,
                Modifier.padding(padding)
            ) {
                composable(Navigation.TRIPS.name) { TripsViewNoMap(::showBottomSheet) }
                composable(Navigation.FRIENDS.name) { FriendsScreen() }
                composable(Navigation.PROFILE.name) { Todo("profile") }
            }
        }
    }
}

@Composable
private fun AppBottomNavigation(navController: NavHostController) {
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


@Composable
fun Todo(name: String) {
    Text(name)
}
