package com.jetapps.jettaskboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tracing.trace
import com.jetapps.jettaskboard.JtbNavigationDestination
import com.jetapps.jettaskboard.navigation.TopLevelDestination

@Composable
fun rememberJtbAppState(
  navController: NavHostController = rememberNavController()
): JtbAppState {
  return remember(navController) {
    JtbAppState(navController)
  }
}

@Stable
class JtbAppState(
  val navController: NavHostController
) {
  val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination

  /**
   * UI logic for navigating to a particular destination in the app. The NavigationOptions to
   * navigate with are based on the type of destination, which could be a top level destination or
   * just a regular destination.
   *
   * Top level destinations have only one copy of the destination of the back stack, and save and
   * restore state whenever you navigate to and from it.
   * Regular destinations can have multiple copies in the back stack and state isn't saved nor
   * restored.
   *
   * @param destination: The [JtbNavigationDestination] the app needs to navigate to.
   * @param route: Optional route to navigate to in case the destination contains arguments.
   */
  fun navigate(
    destination: JtbNavigationDestination,
    route: String? = null
  ) {
    trace("Navigation: $destination") {
      if (destination is TopLevelDestination) {
        navController.navigate(route ?: destination.route) {
          // Pop up to the start destination of the graph to
          // avoid building up a large stack of destinations
          // on the back stack as users select items
          popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
          }
          // Avoid multiple copies of the same destination when
          // re-selecting the same item
          launchSingleTop = true
          // Restore state when re-selecting a previously selected item
          restoreState = true
        }
      } else {
        navController.navigate(route ?: destination.route)
      }
    }
  }
}