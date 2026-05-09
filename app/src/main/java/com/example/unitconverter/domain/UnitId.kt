package com.example.unitconverter.domain

sealed interface UnitId {
    val symbol: String

    enum class Temperature(override val symbol: String) : UnitId {
        C("°C"), F("°F"), K("K")
    }

    enum class Length(override val symbol: String) : UnitId {
        M("m"), KM("km"), FT("ft"), MI("mi")
    }

    enum class Weight(override val symbol: String) : UnitId {
        G("g"), KG("kg"), LB("lb")
    }
}
