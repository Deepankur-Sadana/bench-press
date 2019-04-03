package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.threading.DefaultSchedulersProvider
import io.redgreen.benchpress.counter.effectHandler.ZzzEffectHandler
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity :
    BaseActivity<CounterModel, CounterEvent, ZzzEffect>(),
    CounterActions {

    private val counterLogic by lazy(LazyThreadSafetyMode.NONE) {
        CounterLogic(fizzBuzzEffectFunction())
    }

    override fun layoutResId(): Int {
        return R.layout.counter_activity
    }

    override fun setup() {
        setupViewEventListeners()
    }

    private fun setupViewEventListeners() {
        incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementEvent) }
        decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementEvent) }

    }

    //todo return zero or from saved state ???
    override fun initialModel(): CounterModel =
        CounterModel.ZERO


    override fun updateFunction(
        model: CounterModel,
        event: CounterEvent
    ): Next<CounterModel, ZzzEffect> {
        return counterLogic.update(model, event)
    }

    override fun render(model: CounterModel) {
        counterTextView.text = model.counter.toString()
    }

    override fun effectHandler(): ObservableTransformer<ZzzEffect, CounterEvent> =
        ZzzEffectHandler.create(this, DefaultSchedulersProvider())


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CounterActivity::class.java))
        }
    }

    override fun showFizz() {
        Toast.makeText(this, "Fizz", Toast.LENGTH_SHORT).show()
    }

    override fun showBuzz() {
        Toast.makeText(this, "Buzz", Toast.LENGTH_SHORT).show()
    }

    override fun showFizzBuzz() {
        Toast.makeText(this, "FizzBuzz", Toast.LENGTH_SHORT).show()
    }
}
