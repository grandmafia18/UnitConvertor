package com.example.unitconverter.domain.engine

import com.example.unitconverter.domain.UnitId
import javax.inject.Inject
import kotlin.math.round

class LengthWeightConverters @Inject constructor() {

    private val lengthToMeters = mapOf(
        UnitId.Length.M to 1.0,
        UnitId.Length.KM to 1000.0,
        UnitId.Length.FT to 0.3048,
        UnitId.Length.MI to 1609.344
    )

    private val weightToGrams = mapOf(
        UnitId.Weight.G to 1.0,
        UnitId.Weight.KG to 1000.0,
        UnitId.Weight.LB to 453.59237
    )

    fun convertLength(v: Double, from: UnitId.Length, to: UnitId.Length): Double {
        val meters = v * lengthToMeters.getValue(from)
        val out = meters / lengthToMeters.getValue(to)
        return round(out * 1_000_000.0) / 1_000_000.0
    }

    fun convertWeight(v: Double, from: UnitId.Weight, to: UnitId.Weight): Double {
        val grams = v * weightToGrams.getValue(from)
        val out = grams / weightToGrams.getValue(to)
        return round(out * 1_000_000.0) / 1_000_000.0
    }
}
