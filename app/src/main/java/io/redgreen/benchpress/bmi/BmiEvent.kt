package io.redgreen.benchpress.bmi

sealed class BmiEvent

data class HeightChangeEvent(val height : Double) : BmiEvent()

data class WeightChangeEvent(val weight : Double) : BmiEvent()

data class MeasurementTypeChangeEvent(val measurementType: MeasurementType):BmiEvent()