package io.redgreen.benchpress.bmi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BmiModel(
    var height: Double,
    var weight: Double,
    var bmi: Double,
    var measurementType: MeasurementType,
    var weightCategory: WeightCategory
) : Parcelable {
    companion object {
        val DEFAULT = BmiModel(
            height = 176.0,
            weight = 74.0,
            bmi = 12.3,//// TODO(ns) 25/Feb/19 - fix this
            measurementType = MeasurementType.METRIC,
            weightCategory = WeightCategory.NORMAL_WEIGHT
        )
    }

    fun calculateBMI() {
        bmi = if (measurementType == MeasurementType.METRIC) {
            ((weight / (height * height) * 10000).toDouble())
        } else {
            ((weight / (height * height) * 10000).toDouble()) * 703
        }
    }


    fun changeHeight(height: Double): BmiModel {
        copy(height = height)
        calculateBMI()
        return this;
    }

    fun changeWeight(weight: Double): BmiModel {
        copy(weight = weight)
        calculateBMI()
        return this;
    }

    fun changeMeasurementType(measurementType: MeasurementType): BmiModel =
        copy(measurementType = measurementType)

}

