package io.redgreen.benchpress.counter

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ZzzEffectFunctionTest {
    private val effectFunction = fizzBuzzEffectFunction()


    @Test
    fun `it returns a null effect for 0`() {
        // when
        val effect = effectFunction.invoke(0)

        // then
        assertThat(effect)
            .isNull()
    }

    @Test
    fun `it returns fizz effect for 3`() {
        // when
        val effect  = effectFunction.invoke(3)

        // then
        assertThat(effect)
            .isEqualTo(FizzEffect)
    }
}
