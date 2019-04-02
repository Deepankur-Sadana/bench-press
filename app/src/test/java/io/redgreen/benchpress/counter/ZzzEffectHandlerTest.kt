package io.redgreen.benchpress.counter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.counter.effectHandler.ZzzEffectHandler
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.test.ImmediateSchedulersProvider
import org.junit.Test

class ZzzEffectHandlerTest {
    @Test
    fun `it can dispatch a fizz action`() {
        // given
        val actions = mock<CounterActions>()
        val effectHandler = ZzzEffectHandler.create(actions, ImmediateSchedulersProvider())
        val effectHandlerTestCase = EffectHandlerTestCase(effectHandler)

        // when
        effectHandlerTestCase.dispatchEffect(FizzEffect)

        // then
        effectHandlerTestCase.assertNoOutgoingEvents()

        verify(actions).showFizz()
        verifyNoMoreInteractions(actions)
    }
}
