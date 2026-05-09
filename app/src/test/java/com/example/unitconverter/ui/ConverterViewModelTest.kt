package com.example.unitconverter.ui

import com.example.unitconverter.domain.ConvertUseCase
import com.example.unitconverter.domain.UnitId
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class ConverterViewModelTest {

    private val useCase = mockk<ConvertUseCase>()
    private val vm = ConverterViewModel(useCase)

    @Test fun invalid_input_sets_error() {
        vm.setInput("abc")
        vm.doConvert()
        assertEquals("Invalid number", vm.state.value.error)
        assertTrue(vm.state.value.output.isBlank())
    }

    @Test fun convert_success_updates_output() {
        every { useCase.convert(10.0, any(), any()) } returns 20.0

        vm.setInput("10")
        vm.setFrom(UnitId.Length.M)
        vm.setTo(UnitId.Length.FT)
        vm.doConvert()

        assertNull(vm.state.value.error)
        assertEquals("20.00", vm.state.value.output)
    }
}
