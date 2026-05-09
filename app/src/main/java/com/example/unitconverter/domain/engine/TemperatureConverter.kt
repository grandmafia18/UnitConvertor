package com.example.unitconverter.domain.engine

import com.example.unitconverter.domain.UnitId
import javax.inject.Inject
import kotlin.math.round

class TemperatureConverter @Inject constructor() {
    fun convert(v: Double, from: UnitId.Temperature, to: UnitId.Temperature): Double {
        val c = when (from) {
            UnitId.Temperature.C -> v
            UnitId.Temperature.F -> (v - 32) * 5.0 / 9.0
            UnitId.Temperature.K -> v - 273.15
        }
        val out = when (to) {
            UnitId.Temperature.C -> c
            UnitId.Temperature.F -> c * 9.0 / 5.0 + 32
            UnitId.Temperature.K -> c + 273.15
        }
        // keep UI stable (avoid 1.999999 style)
        return round(out * 1_000_000.0) / 1_000_000.0
    }
}
