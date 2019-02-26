package io.redgreen.benchpress.bmi

import com.google.common.truth.Truth
import org.junit.Test

class BmiModelTest {

    companion object {
        val TAG = ::BmiModelTest.javaClass
    }

    @Test
    fun `when weight is set, then incrementing weight increases BMI`() {
        val defaultModel = BmiModel.DEFAULT
        val someModel = defaultModel.copy()
        someModel.changeWeight(someModel.weight + 5)
        someModel.calculateBmi()
        System.out.println(("$TAG   $defaultModel"))
        System.out.println(("$TAG   $someModel"))

        Truth.assertThat(someModel.bmi)
            .isGreaterThan(defaultModel.bmi)
    }

    @Test
    fun `when height is increased,then BMI decreases`() {
        val defaultModel = BmiModel.DEFAULT
        defaultModel.calculateBmi()
        val overHeightModel = BmiModel(
            defaultModel.height,
            defaultModel.weight,
            defaultModel.bmi,
            defaultModel.measurementUnit,
            defaultModel.weightCategory
        )
        overHeightModel.changeHeight(defaultModel.height + 10)
        overHeightModel.calculateBmi()
        System.out.println(("$TAG   $defaultModel"))
        System.out.println(("$TAG   $overHeightModel"))
        Truth.assertThat(overHeightModel.bmi)
            .isLessThan(defaultModel.bmi)
    }

    @Test
    fun `check if BMI matches for given weight and height`() {

//        Pair<BMI, Pair<HEIGHT, WEIGHT>>
        val sampleData = mutableListOf<Pair<Float, Pair<Float, Float>>>()
//        sampleData.add(Pair(18.5f, Pair(180f, 80f)))
        sampleData.forEach { item ->
            System.out.println((" looping "))
            val defaultModel = BmiModel.DEFAULT
            defaultModel.changeHeight(item.second.first)
            defaultModel.changeWeight(item.second.second)
            Truth.assertThat(defaultModel.bmi)
                .isEqualTo(item.first)
        }
    }

    @Test
    fun `check if weight changes after changing unit from Metric to Standard`() {
        val defaultModel = BmiModel.DEFAULT
        val `height in  cm` = defaultModel.height
        val `weight in  KG` = defaultModel.weight
        val oldMEasureMentUnit = defaultModel.measurementUnit
        defaultModel.changeMeasurementType(MeasurementUnit.STANDARD)
        System.out.println(("$TAG   $defaultModel"))
        defaultModel.reEvaluateValuesOnTogglingUnits(oldMEasureMentUnit, MeasurementUnit.STANDARD)

        Truth.assertThat(`weight in  KG` * 2.2f)
            .isEqualTo(defaultModel.weight)


    }

    @Test
    fun `check if height changes after changing unit from Metric to Standard`(){
        val defaultModel = BmiModel.DEFAULT
        val `height in  cm` = defaultModel.height
        val oldMEasureMentUnit = defaultModel.measurementUnit
        defaultModel.changeMeasurementType(MeasurementUnit.STANDARD)
        defaultModel.reEvaluateValuesOnTogglingUnits(oldMEasureMentUnit, MeasurementUnit.STANDARD)

        Truth.assertThat(`height in  cm` * 0.393701f)
            .isEqualTo(defaultModel.height)
    }
}