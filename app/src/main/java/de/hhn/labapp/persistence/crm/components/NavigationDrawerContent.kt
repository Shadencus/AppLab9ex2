package de.hhn.labapp.persistence.crm.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.hhn.labapp.persistence.crm.viewmodel.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    navController: NavHostController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    fun onNavigate(route: String) {
        scope.launch { drawerState.close() }
        navController.navigate(route)
    }

    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(0.6f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Navigation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            NavigationDrawerItem(
                label = { Text(text = "Customers") },
                selected = false,
                onClick = { onNavigate(Screen.CustomersOverview.name) },
            )
            NavigationDrawerItem(
                label = { Text(text = "Invoices") },
                selected = false,
                onClick = { onNavigate(Screen.InvoicesOverview.name) },
            )
        }
    }
}
