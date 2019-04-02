package io.redgreen.benchpress.counter

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ZzzEffectFunctionTest {
    @Test
    fun `it returns a null effect for 0`() {
        // given
        val effectFunction = fizzBuzzEffectFunction()

        // when
        val effect = effectFunction.invoke(0)

        // then
        assertThat(effect)
            .isNull()
    }
}
