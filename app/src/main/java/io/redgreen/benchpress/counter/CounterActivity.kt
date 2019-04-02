package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : BaseActivity<CounterModel, CounterEvent, ZzzEffect>(), CounterActions {
    override fun layoutResId(): Int {
        return R.layout.counter_activity
    }

    override fun setup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initialModel(): CounterModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFunction(model: CounterModel, event: CounterEvent): Next<CounterModel, ZzzEffect> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(model: CounterModel) {
        counterTextView.text = model.counter.toString()
    }

    override fun effectHandler(): ObservableTransformer<ZzzEffect, CounterEvent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CounterActivity::class.java))
        }
    }

    override fun showFizz() {
        Toast.makeText(this,"Fizz",Toast.LENGTH_SHORT).show()
    }

    override fun showBuzz() {
        Toast.makeText(this,"Buzz",Toast.LENGTH_SHORT).show()
    }

    override fun showFizzBuzz() {
        Toast.makeText(this,"FizzBuzz",Toast.LENGTH_SHORT).show()
    }
}
