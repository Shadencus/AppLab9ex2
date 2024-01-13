package de.hhn.labapp.persistence.crm.components.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ControlButtons(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    onDelete: (() -> Unit)? = null,
    onGenerateRandom: (() -> Unit)? = null,

    isSaveEnabled: Boolean = true,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (onDelete == null) {
            ControlButtonsWithoutDelete(
                onCancel = onCancel,
                onSave = onSave,

                isSaveEnabled = isSaveEnabled,
            )
        } else {
            ControlButtonsWithDelete(
                onCancel = onCancel,
                onSave = onSave,
                onDelete = onDelete,

                isSaveEnabled = isSaveEnabled,
            )
        }

        if (onGenerateRandom != null) {
            GenerateRandomClickableText(onGenerateRandom)
        }
    }
}

@Composable
private fun ControlButtonsWithoutDelete(
    onCancel: () -> Unit,
    onSave: () -> Unit,

    isSaveEnabled: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CancelButton(onCancel)
        SaveButton(onSave, isEnabled = isSaveEnabled)
    }
}

@Composable
private fun ControlButtonsWithDelete(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,

    isSaveEnabled: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CancelButton(onCancel)
        DeleteButton(onDelete)
    }

    SaveButton(onSave, isEnabled = isSaveEnabled)
}

@Composable
private fun SaveButton(onSave: () -> Unit, isEnabled: Boolean) {
    CustomButton(
        text = "Save",
        modifier = Modifier.fillMaxWidth(),
        isEnabled = isEnabled,
        onClick = {
            // Caution: Don't use a function reference here, as this breaks
            // the captured "customer" object
            onSave()
        }
    )
}

@Composable
private fun CancelButton(onCancel: () -> Unit) {
    CustomButton(
        text = "Cancel",
        onClick = {
            // Caution: Don't use a function reference here, as this breaks
            // the captured "customer" object
            onCancel()
        },
        modifier = Modifier.fillMaxWidth(0.5f),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
    )
}

@Composable
private fun DeleteButton(onDelete: () -> Unit) {
    CustomButton(
        text = "Delete",
        onClick = {
            // Caution: Don't use a function reference here, as this breaks
            // the captured "customer" object
            onDelete()
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
    )
}

@Composable
fun GenerateRandomClickableText(onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(
            text = "Generate random",
            spanStyle = SpanStyle(
                color= MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center),
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() },
    )
}

@Composable
fun CustomButton(
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
) {
    Button(
        modifier = modifier.height(48.dp),
        enabled = isEnabled,
        colors = colors,
        onClick = onClick,
    ) {
        Text(text)
    }
}
