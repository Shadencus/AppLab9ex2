package de.hhn.labapp.persistence.crm.components.customers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.hhn.labapp.persistence.crm.components.customers.CustomersList
import de.hhn.labapp.persistence.crm.viewmodel.CustomersOverviewViewModel
import de.hhn.labapp.persistence.crm.viewmodel.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersOverview(
    navController: NavController,
    navDrawerState: DrawerState
) {
    // Managing the state in here so that `CustomersList` can be stateless.
    val viewModel = CustomersOverviewViewModel()
    LaunchedEffect(viewModel) {
        viewModel.init()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { navController.navigate(Screen.CustomerDetails.name) },
                ) {
                    Icon(Icons.Filled.Add, "Floating Action Button")
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
            ) {
                CustomersList(
                    navDrawerState = navDrawerState,
                    customers = viewModel.customers.value,
                    onCustomerClicked = {
                        navController.navigate("${Screen.CustomerDetails.name}/${it.id}")
                    },
                )
            }
        }
    }
}
