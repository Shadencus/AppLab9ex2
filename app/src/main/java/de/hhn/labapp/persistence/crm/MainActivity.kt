package de.hhn.labapp.persistence.crm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.hhn.labapp.persistence.crm.components.NavigationDrawerContent
import de.hhn.labapp.persistence.crm.model.DatabaseProvider
import de.hhn.labapp.persistence.crm.ui.theme.Exercise2Theme
import de.hhn.labapp.persistence.crm.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseProvider.init(applicationContext)

        setContent {
            Exercise2Theme {
                // A surface container using the 'background' color from the theme
                ScreenNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = { NavigationDrawerContent(navController, drawerState) },
        content = { Screens(navController, drawerState) },
        drawerState = drawerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screens(
    navController: NavHostController,
    navDrawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CustomersOverview.name,
        enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
        popEnterTransition = { slideInHorizontally { fullWidth -> -fullWidth } },
    ) {
        AppRouter(navController, navDrawerState).build(this)
    }
}
