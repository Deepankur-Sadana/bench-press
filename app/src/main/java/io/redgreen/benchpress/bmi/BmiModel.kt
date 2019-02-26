package io.redgreen.benchpress.bmi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BmiModel(
    var height: Float,
    var weight: Float,
    var bmi: Float,
    var measurementUnit: MeasurementUnit,
    var weightCategory: WeightCategory
) : Parcelable {
    companion object {
        val DEFAULT = BmiModel(
            height = 176.0f,
            weight = 74.0f,
            bmi = 12.3f,//// TODO(ns) 25/Feb/19 - fix this
            measurementUnit = MeasurementUnit.METRIC,
            weightCategory = WeightCategory.NORMAL_WEIGHT
        )
    }

    fun calculateBmi() {
        if (measurementUnit == MeasurementUnit.METRIC) {
            System.out.println("height  $height " + " weight $weight")
            bmi = ((weight / (Math.pow((height / 100).toDouble(), 2.0))).toFloat())
            System.out.println("new bmi $bmi")
        }
    }


    fun resetWeightCategory(bmi: Float) {
        weightCategory = when {
            bmi <= 18.5 -> WeightCategory.UNDERWEIGHT
            bmi > 18.5 && bmi <= 25 -> WeightCategory.NORMAL_WEIGHT
            bmi > 25 && bmi < 30 -> WeightCategory.OVER_WEIGHT
            else -> WeightCategory.OBESE
        }
    }


    fun changeHeight(height: Float): BmiModel {
        this.height = height
        return this;
    }

    fun changeWeight(weight: Float): BmiModel {
        this.weight = weight
        return this;
    }

    fun changeMeasurementType(measurementUnit1: MeasurementUnit): BmiModel {
        this.measurementUnit = measurementUnit1;
        return this;
    }

    fun reEvaluateValuesOnTogglingUnits(oldMeasurmentUnit: MeasurementUnit, newMeasurementUnit: MeasurementUnit) {
        convertWeightToNewUnit(oldMeasurmentUnit, newMeasurementUnit)
        convertHeightToNewUnit(oldMeasurmentUnit, newMeasurementUnit)
    }

    fun convertHeightToNewUnit(oldUnit: MeasurementUnit, newUnit: MeasurementUnit) {
        if (newUnit == oldUnit) return;
        when (oldUnit) {
            MeasurementUnit.METRIC -> {
                when (newUnit) {
                    MeasurementUnit.STANDARD -> {
                        this.height = height * 0.393701f;
                        System.out.print("convertWeightToNewUnit changing.....to $height  ")
                    }
                    else -> TODO()
                }

            }
            MeasurementUnit.STANDARD -> {
                when (newUnit) {
                    MeasurementUnit.METRIC -> {

                    }
                }

            }
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }

    fun convertWeightToNewUnit(oldUnit: MeasurementUnit, newUnit: MeasurementUnit) {
        System.out.print("convertWeightToNewUnit $oldUnit $newUnit ")
        if (newUnit == oldUnit) return;
        when (oldUnit) {
            MeasurementUnit.METRIC -> {
                when (newUnit) {
                    MeasurementUnit.STANDARD -> {
                        this.weight = weight * 2.2f;
                        System.out.print("convertWeightToNewUnit changing.....to $weight  ")
                    }
                    else -> TODO()
                }

            }
            MeasurementUnit.STANDARD -> {
                when (newUnit) {
                    MeasurementUnit.METRIC -> {

                    }
                }

            }
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }


}

