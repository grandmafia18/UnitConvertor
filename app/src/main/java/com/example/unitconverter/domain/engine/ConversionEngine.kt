package com.example.unitconverter.domain.engine

import com.example.unitconverter.domain.UnitId

interface ConversionEngine {
    fun convert(value: Double, from: UnitId, to: UnitId): Double
}
