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

fun fizzBuzzEffectFunction(): ZzzEffectFunction {
    return {
        when {
            it == 0 -> null
            it % 15 == 0 -> FizzBuzzEffect
            it % 3 == 0 -> FizzEffect
            it % 5 == 0 -> BuzzEffect
            else -> null
        }
    }
}
