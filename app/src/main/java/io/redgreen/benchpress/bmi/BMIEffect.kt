package io.redgreen.benchpress.bmi

sealed class BMIEffect

data class InvalidWeightEffect(val weightEffect: Double) :BMIEffect()
