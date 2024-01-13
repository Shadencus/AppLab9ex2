package de.hhn.labapp.persistence.crm.components.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.hhn.labapp.persistence.crm.components.shared.ControlButtons
import de.hhn.labapp.persistence.crm.components.shared.PropertyTextField
import de.hhn.labapp.persistence.crm.factories.CustomerFactory
import de.hhn.labapp.persistence.crm.model.entities.Customer
import de.hhn.labapp.persistence.crm.viewmodel.CustomerDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersDetails(
    customer: Customer,
    navController: NavController,
    navDrawerState: DrawerState,
) {
    val viewModel = remember { CustomerDetailsViewModel(customer, navController) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            CustomerPropertyTextFields(viewModel)

            Spacer(modifier = Modifier.height(16.dp))

            ControlButtons(
                onCancel = viewModel::onCancel,
                onSave = viewModel::onSave,
                onGenerateRandom = viewModel::onGenerateRandom,

                isSaveEnabled = viewModel.isSaveEnabled,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "ID: ${customer.id}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
fun CustomerPropertyTextFields(viewModel: CustomerDetailsViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PropertyTextField("Name", viewModel.name) { viewModel.name = it }
        PropertyTextField("Address", viewModel.address) { viewModel.address = it }
        PropertyTextField(
            property = "Postal Code",
            value = viewModel.postalCode,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        ) { viewModel.postalCode = it }
        PropertyTextField("City", viewModel.city) { viewModel.city = it }
        PropertyTextField("Country", viewModel.country) { viewModel.country = it }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomerDetailsPreview() {
    CustomersDetails(
        customer = CustomerFactory.createRandomCustomer(),
        navController = rememberNavController(),
        navDrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
    )
}
