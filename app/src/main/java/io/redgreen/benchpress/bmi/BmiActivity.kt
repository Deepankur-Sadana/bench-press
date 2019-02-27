package io.redgreen.benchpress.bmi

import android.content.Context
import android.content.Intent
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.effecthandlers.NoOpEffectHandler
import kotlinx.android.synthetic.main.bmi_activity.*
import timber.log.Timber


class BmiActivity : BaseActivity<BmiModel, BmiEvent, Nothing>(), BmiView {

    private val MAX_VALUE = 220
    private val MIN_VALUE = 50

    private val renderer by lazy(LazyThreadSafetyMode.NONE) {
        BmiViewRenderer(this)
    }

    companion object {
        val TAG = ::BmiActivity.javaClass.simpleName
        fun start(context: Context) {
            context.startActivity(Intent(context, BmiActivity::class.java))
        }
    }

    override fun layoutResId(): Int {
        return R.layout.bmi_activity
    }

    override fun setup() {
        weightSeekBar.setMax(MAX_VALUE - MIN_VALUE);
        heightSeekBar.setMax(MAX_VALUE - MIN_VALUE);
        weightSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Timber.d(TAG, " ")

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {

                displayValue(heightTextView, (progress + MIN_VALUE).toString() + "KG");
            }
        })

        heightSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Timber.d(TAG, " ")

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                displayValue(heightTextView, (progress + MIN_VALUE).toString() + "cm");

            }
        })
    }


    fun displayValue(textView: TextView, value: String) {
        textView.text = value
    }

    override fun initialModel(): BmiModel {
        return BmiModel.DEFAULT
    }


    override fun updateFunction(
        model: BmiModel,
        event: BmiEvent
    ): Next<BmiModel, Nothing> {
        return BmiLogic.update(model, event)
    }


    override fun render(model: BmiModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun effectHandler(): ObservableTransformer<Nothing, BmiEvent> {
        return NoOpEffectHandler()
    }


    private fun displayResult(bmiModel: BmiModel) {
        bmiTextView.text = bmiModel.bmi.toString()

    }
}
