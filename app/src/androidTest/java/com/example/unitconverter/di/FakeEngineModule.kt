package com.example.unitconverter.di

import com.example.unitconverter.domain.UnitId
import com.example.unitconverter.domain.engine.ConversionEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object FakeEngineModule {

    @Provides
    @Singleton
    fun provideFakeEngine(): ConversionEngine = object : ConversionEngine {
        override fun convert(value: Double, from: UnitId, to: UnitId): Double = 123.456
    }
}
