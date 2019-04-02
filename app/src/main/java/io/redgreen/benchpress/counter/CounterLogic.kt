package io.redgreen.benchpress.counter

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object CounterLogic : Update<CounterModel, CounterEvent, ZzzEffect> {
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
    return { null }
}
