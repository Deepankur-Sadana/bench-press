package io.redgreen.benchpress.counter.effectHandler

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.counter.CounterActions
import io.redgreen.benchpress.counter.CounterEvent
import io.redgreen.benchpress.counter.FizzEffect
import io.redgreen.benchpress.counter.ZzzEffect

object ZzzEffectHandler {
    fun create(
        actions: CounterActions,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<ZzzEffect, CounterEvent> {
        return RxMobius
            .subtypeEffectHandler<ZzzEffect, CounterEvent>()
            .addAction(FizzEffect::class.java, actions::showFizz, schedulersProvider.ui)
            .build()
    }
}
