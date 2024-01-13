package de.hhn.labapp.persistence.crm.components.customers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hhn.labapp.persistence.crm.components.shared.AppTitleBar
import de.hhn.labapp.persistence.crm.factories.CustomerFactory
import de.hhn.labapp.persistence.crm.model.entities.Customer
import de.hhn.labapp.persistence.crm.viewmodel.CustomersOverviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersList(
    navDrawerState: DrawerState,
    customers: Collection<Customer>,
    onCustomerClicked: (Customer) -> Unit,
) {
    val viewModel = CustomersOverviewViewModel()
    LaunchedEffect(viewModel) {
        viewModel.init()
    }

    Column {
        AppTitleBar(
            title = "Customers",
            navDrawerState = navDrawerState,
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 4.dp),
            content = {
                items(customers.sortedBy {
                    it.name
                }) { customer ->
                    CustomerCard(
                        customer = customer,
                        onClick = onCustomerClicked,
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomersListPreview() {
    CustomersList(
        navDrawerState = rememberDrawerState(DrawerValue.Closed),
        customers = CustomerFactory.createRandomCustomers(3),
        onCustomerClicked = {},
    )
}
