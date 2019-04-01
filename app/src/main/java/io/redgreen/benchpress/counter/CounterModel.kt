package io.redgreen.benchpress.counter

data class CounterModel(
    val counter: Int
) {
    companion object {
        val ZERO = CounterModel(0)
    }

    fun increment(): CounterModel =
        copy(counter = counter + 1)

    fun decrement(): CounterModel =
        copy(counter = counter - 1)
}
