package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.redgreen.benchpress.R
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : AppCompatActivity() , CounterActions
{
  override fun showFizz() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showBuzz() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showFizzBuzz() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, CounterActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.counter_activity)
    deleteMe()
  }

  private fun deleteMe() {
    counterTextView.text = 0.toString()
  }
}
