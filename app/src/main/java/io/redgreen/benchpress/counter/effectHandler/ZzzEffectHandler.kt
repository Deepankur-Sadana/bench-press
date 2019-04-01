package io.redgreen.benchpress.counter.effectHandler

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.counter.*

object ZzzEffectHandler {
    fun create(
        viewActions: CounterActions,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<ZzzEffect, CounterEvent> {
        return RxMobius
            .subtypeEffectHandler<ZzzEffect, CounterEvent>()
            .addAction(FizzEffect::class.java, viewActions::showFizz, schedulersProvider.ui)
            .addAction(BuzzEffect::class.java, viewActions::showBuzz, schedulersProvider.ui)
            .addAction(FizzBuzzEffect::class.java, viewActions::showFizzBuzz, schedulersProvider.ui)
            .build()
    }

}