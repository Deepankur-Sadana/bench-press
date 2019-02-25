package io.redgreen.benchpress.bmi

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object BmiLogic : Update<BmiModel, BmiEvent, BMIEffect> {
    override fun update(
        model: BmiModel,
        event: BmiEvent
    ): Next<BmiModel, BMIEffect> {
        return when (event) {
            is HeightChangeEvent -> next(model.changeHeight(event.height))
            is WeightChangeEvent -> next(model.changeWeight(event.weight))
            is MeasurementTypeChangeEvent -> next(model.changeMeasurementType(event.measurementType))
        }
    }
}
