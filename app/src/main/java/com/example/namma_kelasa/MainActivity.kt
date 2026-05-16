package com.example.namma_kelasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.namma_kelasa.ui.screens.*
import com.example.namma_kelasa.ui.theme.NAMMA_KELASATheme
import com.example.namma_kelasa.data.Worker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NAMMA_KELASATheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    val navigateToBottomTab: (String) -> Unit = { route ->
        navController.navigate(route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onContinue = { navController.navigate("home") })
        }
        composable("home") {
            HomeScreen(
                onWorkerClick = { worker -> navController.navigate("worker_profile/${worker.id}") },
                onSearchClick = { navController.navigate("filter") },
                onNavigate = navigateToBottomTab
            )
        }
        composable("jobs") {
            MyJobsScreen(onNavigate = navigateToBottomTab)
        }
        composable("chats") {
            ChatScreen(onNavigate = navigateToBottomTab)
        }
        composable("profile") {
            UserProfileScreen(onNavigate = navigateToBottomTab)
        }
        composable(
            route = "worker_profile/{workerId}",
            arguments = listOf(navArgument("workerId") { type = NavType.StringType })
        ) { backStackEntry ->
            val workerId = backStackEntry.arguments?.getString("workerId")
            val worker = Worker(
                id = workerId ?: "1",
                name = "Ramesh Painter",
                skill = "Painter",
                location = "Koramangala, Bengaluru",
                rating = 4.8,
                reviewCount = 24,
                dailyRate = "₹800/day"
            )
            WorkerProfileScreen(
                worker = worker,
                onBack = { navController.popBackStack() },
                onHire = { navController.navigate("jobs") }
            )
        }
        composable("filter") {
            FilterResultsScreen(
                onBack = { navController.popBackStack() },
                onApply = { navController.popBackStack() }
            )
        }
    }
}
