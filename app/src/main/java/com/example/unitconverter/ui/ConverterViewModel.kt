package com.example.unitconverter.ui

import androidx.lifecycle.ViewModel
import com.example.unitconverter.domain.ConvertUseCase
import com.example.unitconverter.domain.UnitCategory
import com.example.unitconverter.domain.UnitId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ConverterUiState(
    val category: UnitCategory = UnitCategory.TEMPERATURE,
    val input: String = "0",
    val from: UnitId = UnitId.Temperature.C,
    val to: UnitId = UnitId.Temperature.F,
    val output: String = "",
    val error: String? = null
)

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convert: ConvertUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ConverterUiState())
    val state: StateFlow<ConverterUiState> = _state

    fun setCategory(cat: UnitCategory) {
        val defaults = when (cat) {
            UnitCategory.TEMPERATURE -> UnitId.Temperature.C to UnitId.Temperature.F
            UnitCategory.LENGTH -> UnitId.Length.M to UnitId.Length.KM
            UnitCategory.WEIGHT -> UnitId.Weight.KG to UnitId.Weight.LB
        }
        _state.update {
            it.copy(category = cat, from = defaults.first, to = defaults.second, output = "", error = null)
        }
    }

    fun setInput(value: String) = _state.update { it.copy(input = value, error = null) }
    fun setFrom(unit: UnitId) = _state.update { it.copy(from = unit, output = "", error = null) }
    fun setTo(unit: UnitId) = _state.update { it.copy(to = unit, output = "", error = null) }

    fun doConvert() {
        val v = _state.value.input.toDoubleOrNull()
        if (v == null) {
            _state.update { it.copy(error = "Invalid number", output = "") }
            return
        }
        runCatching {
            convert.convert(v, _state.value.from, _state.value.to)
        }.onSuccess { out ->
            val formatted = "%.2f".format(out)
            _state.update { it.copy(output = formatted, error = null) }
        }.onFailure { e ->
            _state.update { it.copy(error = e.message ?: "Conversion failed", output = "") }
        }
    }
}
