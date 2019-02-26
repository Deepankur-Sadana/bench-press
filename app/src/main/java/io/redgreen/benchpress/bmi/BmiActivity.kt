package io.redgreen.benchpress.bmi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.effecthandlers.NoOpEffectHandler
import kotlinx.android.synthetic.main.bmi_activity.*

class BmiActivity : BaseActivity<BmiModel, BmiEvent, Nothing>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BmiActivity::class.java))
        }
    }

    override fun layoutResId(): Int {
        return R.layout.bmi_activity
    }

    override fun setup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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



    private fun deleteMe() {
        bmiTextView.text = 24.toString()
        bmiCategoryTextView.text = getString(R.string.normal)
        weightTextView.text = getString(R.string.template_weight_si, 0.0)
        heightTextView.text = getString(R.string.template_height_si, 0.0)
    }
}
