package com.example.unitconverter.di

import com.example.unitconverter.domain.engine.ConversionEngine
import com.example.unitconverter.domain.engine.DefaultConversionEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindEngine(impl: DefaultConversionEngine): ConversionEngine
}
