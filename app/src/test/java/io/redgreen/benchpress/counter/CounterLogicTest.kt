package io.redgreen.benchpress.counter

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {
    private val updateSpec = UpdateSpec<CounterModel, CounterEvent, ZzzEffect>(CounterLogic::update)

    @Test
    fun `when user taps on plus, increment counter`() {
        val zero = CounterModel.ZERO

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

    @Test
    fun `when user taps on minus, then decrement counter`() {
        val zero = CounterModel.ZERO

        updateSpec
            .given(zero)
            .`when`(DecrementEvent)
            .then(
                assertThatNext(
                    hasModel(zero.decrement()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when counter is a multiple of 3, then show Fizz`() {
        val two = CounterModel.with(2)

        updateSpec
            .given(two)
            .`when`(IncrementEvent)
            .then(
                assertThatNext(
                    hasModel(two.increment()),
                    hasEffects(FizzEffect as ZzzEffect)
                )
            )
    }
}
