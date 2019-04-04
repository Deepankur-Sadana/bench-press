package io.redgreen.benchpress.counter

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {
    private val update = CounterLogic(fizzBuzzEffectFunction(ssshEffect(), fizzBuzzEffect(), fizzEffect(), buzzEffect()))::update
    private val updateSpec = UpdateSpec<CounterModel, CounterEvent, ZzzEffect>(update)

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
    fun `when counter is a multiple of 15, then show fizz buzz`() {
        val fourteen = CounterModel.with(14)

        updateSpec
            .given(fourteen)
            .`when`(IncrementEvent)
            .then(
                assertThatNext(
                    hasModel(fourteen.increment()),
                    hasEffects(FizzBuzzEffect as ZzzEffect)
                )
            )
    }
}
