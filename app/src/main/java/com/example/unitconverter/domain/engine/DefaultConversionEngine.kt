package com.example.unitconverter.domain.engine

import com.example.unitconverter.domain.UnitId
import javax.inject.Inject

class DefaultConversionEngine @Inject constructor(
    private val temperature: TemperatureConverter,
    private val lengthWeight: LengthWeightConverters
) : ConversionEngine {

    override fun convert(value: Double, from: UnitId, to: UnitId): Double {
        if (from::class != to::class && from !is UnitId.Temperature && to !is UnitId.Temperature) {
            // Different categories (Length vs Weight) are not convertible.
            throw IllegalArgumentException("Incompatible unit categories: $from -> $to")
        }

        return when (from) {
            is UnitId.Temperature -> temperature.convert(value, from, to as UnitId.Temperature)
            is UnitId.Length -> lengthWeight.convertLength(value, from, to as UnitId.Length)
            is UnitId.Weight -> lengthWeight.convertWeight(value, from, to as UnitId.Weight)
        }
    }
}
