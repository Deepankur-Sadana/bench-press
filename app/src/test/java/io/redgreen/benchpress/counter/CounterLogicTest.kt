package io.redgreen.benchpress.counter

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {
    @Test
    fun `when user taps on plus, increment counter`() {
        val zero = CounterModel.ZERO
        val updateSpec = UpdateSpec<CounterModel, CounterEvent, Nothing>(CounterLogic::update)

        updateSpec
            .given(zero)
            .`when`(IncrementEvent)
            .then(
                assertThatNext(
                    hasModel(zero.increment()),
                    hasNoEffects()
                )
            )
    }
}
