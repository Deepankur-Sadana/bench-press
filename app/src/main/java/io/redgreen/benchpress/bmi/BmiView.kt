package io.redgreen.benchpress.bmi

interface BmiView {

    fun renderBmi(bmi: Float)

    fun renderWeightCategory(weightCategory: WeightCategory)
}