package com.example.unitconverter.engine

import com.example.unitconverter.domain.UnitId
import com.example.unitconverter.domain.engine.TemperatureConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureConverterTest {

    private val converter = TemperatureConverter()

    @Test fun c_to_f() {
        assertEquals(32.0, converter.convert(0.0, UnitId.Temperature.C, UnitId.Temperature.F), 0.0)
    }

    @Test fun f_to_c() {
        assertEquals(0.0, converter.convert(32.0, UnitId.Temperature.F, UnitId.Temperature.C), 0.0)
    }

    @Test fun c_to_k() {
        assertEquals(273.15, converter.convert(0.0, UnitId.Temperature.C, UnitId.Temperature.K), 0.0)
    }
}
