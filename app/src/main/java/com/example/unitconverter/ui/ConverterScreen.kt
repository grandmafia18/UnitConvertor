package com.example.unitconverter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.unitconverter.domain.UnitCategory
import com.example.unitconverter.domain.UnitId

@Composable
fun ConverterScreen(vm: ConverterViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Unit Converter", style = MaterialTheme.typography.headlineSmall)

                CategoryDropdown(
                    selected = state.category,
                    onSelect = vm::setCategory,
                    modifier = Modifier.testTag(TestTags.CATEGORY)
                )

                OutlinedTextField(
                    value = state.input,
                    onValueChange = vm::setInput,
                    label = { Text("Value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth().testTag(TestTags.INPUT)
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnitDropdown(
                        label = "From",
                        units = unitsFor(state.category),
                        selected = state.from,
                        onSelect = vm::setFrom,
                        modifier = Modifier.weight(1f).testTag(TestTags.FROM_UNIT)
                    )
                    UnitDropdown(
                        label = "To",
                        units = unitsFor(state.category),
                        selected = state.to,
                        onSelect = vm::setTo,
                        modifier = Modifier.weight(1f).testTag(TestTags.TO_UNIT)
                    )
                }

                Button(
                    onClick = vm::doConvert,
                    modifier = Modifier.testTag(TestTags.CONVERT)
                ) { Text("Convert") }

                state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                Text(
                    text = if (state.output.isBlank()) "Result: —" else "Result: ${state.output}",
                    modifier = Modifier.testTag(TestTags.RESULT)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryDropdown(selected: UnitCategory, onSelect: (UnitCategory) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }, modifier = modifier) {
        TextField(
            value = selected.name,
            onValueChange = {},
            readOnly = true,
            label = { Text("Category") },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            UnitCategory.entries.forEach {
                DropdownMenuItem(text = { Text(it.name) }, onClick = { onSelect(it); expanded = false })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UnitDropdown(
    label: String,
    units: List<UnitId>,
    selected: UnitId,
    onSelect: (UnitId) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }, modifier = modifier) {
        TextField(
            value = "${selected} (${selected.symbol})",
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            units.forEach { u ->
                DropdownMenuItem(
                    text = { Text("${u} (${u.symbol})") },
                    onClick = { onSelect(u); expanded = false }
                )
            }
        }
    }
}

private fun unitsFor(cat: UnitCategory): List<UnitId> = when (cat) {
    UnitCategory.TEMPERATURE -> UnitId.Temperature.entries.toList()
    UnitCategory.LENGTH -> UnitId.Length.entries.toList()
    UnitCategory.WEIGHT -> UnitId.Weight.entries.toList()
}
