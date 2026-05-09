package com.example.unitconverter.domain

import com.example.unitconverter.domain.engine.ConversionEngine
import javax.inject.Inject

class ConvertUseCase @Inject constructor(
    private val engine: ConversionEngine
) {
    fun convert(value: Double, from: UnitId, to: UnitId): Double =
        engine.convert(value, from, to)
}
