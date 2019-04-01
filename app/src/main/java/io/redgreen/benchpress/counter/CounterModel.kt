package io.redgreen.benchpress.counter

import android.support.annotation.VisibleForTesting

data class CounterModel(
    val counter: Int
) {
    companion object {
        val ZERO = CounterModel(0)

        @VisibleForTesting
        fun with(counter: Int): CounterModel =
            CounterModel(counter)
    }

    fun increment(): CounterModel =
        copy(counter = counter + 1)

    fun decrement(): CounterModel =
        copy(counter = counter - 1)
}
