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
        return if (event is IncrementEvent) {
            next(model.increment())
        } else {
            next(model.decrement())
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
