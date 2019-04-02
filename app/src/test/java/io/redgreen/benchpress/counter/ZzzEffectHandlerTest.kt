package io.redgreen.benchpress.counter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.counter.effectHandler.ZzzEffectHandler
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.test.ImmediateSchedulersProvider
import org.junit.Test

class ZzzEffectHandlerTest {
    private val actions = mock<CounterActions>()
    private val effectHandlerTestCase = EffectHandlerTestCase(ZzzEffectHandler.create(actions, ImmediateSchedulersProvider()))

    @Test
    fun `it can dispatch a fizz action`() {
        // when
        effectHandlerTestCase.dispatchEffect(FizzEffect)

        // then
        effectHandlerTestCase.assertNoOutgoingEvents()

        verify(actions).showFizz()
        verifyNoMoreInteractions(actions)
    }

    @Test
    fun `it can dispatch a buzz effect`() {
        //when
        effectHandlerTestCase.dispatchEffect(BuzzEffect)

        //then
        effectHandlerTestCase.assertNoOutgoingEvents()

        verify(actions).showBuzz()
        verifyNoMoreInteractions(actions)
    }

    @Test
    fun `it can dispatch a fizzBuzz effect`() {
        //when
        effectHandlerTestCase.dispatchEffect(FizzBuzzEffect)

        //then
        effectHandlerTestCase.assertNoOutgoingEvents()

        verify(actions).showFizzBuzz()
        verifyNoMoreInteractions(actions)
    }
}
