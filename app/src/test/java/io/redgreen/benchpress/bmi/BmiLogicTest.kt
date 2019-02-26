package io.redgreen.benchpress.bmi

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class BmiLogicTest {
    private val updateSpec = UpdateSpec<BmiModel, BmiEvent, Nothing>(BmiLogic::update)

    @Test
    fun `when can change Height`() {
        val defaultModel = BmiModel.DEFAULT
        val randomHeight = 176.3f;
        updateSpec
            .given(defaultModel)
            .`when`(HeightChangeEvent(randomHeight))
            .then(
                assertThatNext(
                    hasModel(defaultModel.changeHeight(randomHeight)),
                    hasNoEffects()
                )
            )

    }

    @Test
    fun `user can change weight`() {
        val defaultModel = BmiModel.DEFAULT
        val validWeight = 80.5f
        updateSpec.given(defaultModel)
            .`when`(WeightChangeEvent(validWeight))
            .then(
                assertThatNext(
                    hasModel(defaultModel.changeWeight(validWeight)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `user can change measurementType`() {
        val defaultModel = BmiModel.DEFAULT
        val standardUnit = MeasurementUnit.STANDARD
        updateSpec.given(defaultModel)
            .`when`(MeasurementTypeChangeEvent(standardUnit))
            .then(
                assertThatNext(
                    hasModel(defaultModel.changeMeasurementType(standardUnit)),
                    hasNoEffects()
                )
            )
    }


    @Test
    fun `invalid weight has side effect`() {
        val defaultModel = BmiModel.DEFAULT
        val inValidWeight = 1.20f
        updateSpec.given(defaultModel)
            .`when`(HeightChangeEvent(inValidWeight))
            .then(
                assertThatNext(
                    hasModel(defaultModel),
                    hasNoEffects()
                )
            )
    }

}
