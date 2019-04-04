package io.redgreen.benchpress.counter

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

class CounterLogic(
    private val effectFunction: ZzzEffectFunction
) : Update<CounterModel, CounterEvent, ZzzEffect> {
    override fun update(
        model: CounterModel,
        event: CounterEvent
    ): Next<CounterModel, ZzzEffect> {
        val updatedModel = if (event is IncrementEvent) {
            model.increment()
        } else {
            model.decrement()
        }

        val effect = effectFunction(updatedModel.counter)

        return if (effect != null) {
            next(updatedModel, setOf(effect))
        } else {
            next(updatedModel)
        }
    }
}

typealias ZzzEffectFunction = (Int) -> ZzzEffect?

fun fizzBuzzEffectFunction(
    vararg effectFunctions: ZzzEffectFunction
): ZzzEffectFunction {
    return {
        effectFunctions
            .map { effectFunction -> effectFunction(it) }
            .firstOrNull { it != null }
    }
}

fun ssshEffect(): ZzzEffectFunction =
    { if (it == 0) SsshEffect else null }

fun fizzBuzzEffect(): ZzzEffectFunction =
    { if (it % 15 == 0) { FizzBuzzEffect } else { null } }

fun fizzEffect(): ZzzEffectFunction =
    { if (it % 3 == 0) { FizzEffect } else { null } }

fun buzzEffect(): ZzzEffectFunction =
    { if (it % 5 == 0) { BuzzEffect } else { null } }
