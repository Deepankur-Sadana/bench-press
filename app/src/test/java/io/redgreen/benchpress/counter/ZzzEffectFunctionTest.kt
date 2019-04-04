package io.redgreen.benchpress.counter

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ZzzEffectFunctionTest {
    private val effectFunction = fizzBuzzEffectFunction(
        ssshEffect(),
        fizzBuzzEffect(),
        fizzEffect(),
        buzzEffect()
    )

    @Test
    fun `it returns a null effect for 0`() {
        // when
        val effect = effectFunction(0)

        // then
        assertThat(effect)
            .isEqualTo(SsshEffect)
    }

    @Test
    fun `it returns fizz effect for 3`() {
        // when
        val effect = effectFunction(3)

        // then
        assertThat(effect)
            .isEqualTo(FizzEffect)
    }

    @Test
    fun `it returns buzz effect for multiple of 5`() {
        //when
        val effect = effectFunction(5)

        //then
        assertThat(effect)
            .isEqualTo(BuzzEffect)
    }

    @Test
    fun `it returns fizz buzz effect for multiples of 15`() {
        //when
        val effect = effectFunction(15)

        //then
        assertThat(effect)
            .isEqualTo(FizzBuzzEffect)
    }
}
