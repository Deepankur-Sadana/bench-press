package io.redgreen.benchpress.counter

import com.spotify.mobius.Next
import com.spotify.mobius.Update

object CounterLogic : Update<CounterModel, CounterEvent, Nothing> {
    override fun update(
        model: CounterModel,
        event: CounterEvent
    ): Next<CounterModel, Nothing> {
        return Next.next(model.increment())
    }
}
