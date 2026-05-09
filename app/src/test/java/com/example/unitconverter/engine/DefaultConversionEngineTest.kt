package com.example.unitconverter.engine

import com.example.unitconverter.domain.UnitId
import com.example.unitconverter.domain.engine.DefaultConversionEngine
import com.example.unitconverter.domain.engine.LengthWeightConverters
import com.example.unitconverter.domain.engine.TemperatureConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultConversionEngineTest {
    private val engine = DefaultConversionEngine(TemperatureConverter(), LengthWeightConverters())

    @Test fun meters_to_km() {
        assertEquals(1.0, engine.convert(1000.0, UnitId.Length.M, UnitId.Length.KM), 0.0)
    }

    @Test fun kg_to_lb() {
        assertEquals(2.20462262185, engine.convert(1.0, UnitId.Weight.KG, UnitId.Weight.LB), 1e-6)
    }
}
