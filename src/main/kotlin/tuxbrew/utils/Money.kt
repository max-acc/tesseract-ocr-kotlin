package alien42.tuxbrew.utils

import kotlin.math.roundToInt

/**
 * A money class which represents money as a non-negative [Double] with two-decimal rounding.
 * It is ensured that the amount of money is always >= 0.
 */
@JvmInline
value class Money private constructor(val cents: UInt) : Comparable<Money> {
    companion object {
        /**
         * Creates a Money object from a provided [Double].
         *
         * @param value The monetary value as [Double].
         * @return A money object wrapping the value rounded to two decimal places.
         * @throws [IllegalArgumentException] if value is negative.
         */
        fun ofDouble(value: Double): Money {
            require(value >= 0) { "'value' must be positive (got $value)" }
            return Money((value * 100).roundToInt().toUInt())
        }

        /**
         * Creates a Money object from the provided amount of cents.
         *
         * @param value The monetary amount in cents as [UInt].
         * @return A money object wrapping the value.
         */
        fun ofCents(value: UInt) = Money(value)
    }

    /**
     * Divides the current monetary value by a provided value.
     *
     * @param value The value by which it will be divided.
     * @return A new Money object equal to the original monetary quantity divided by the value.
     * @throws [IllegalArgumentException] if the resulting amount is negative.
     * @throws [ArithmeticException] if the value is zero.
     */
    operator fun div(value: Double) = ofDouble(this.cents.toDouble() / value)

    /**
     * Multiplies the provided value with the current monetary value.
     *
     * @param value The value it will be multiplied with.
     * @return A new Money object equal to the original monetary quantity multiplied by the value.
     * @throws [IllegalArgumentException] if the resulting amount is negative.
     */
    operator fun times(value: Double) = ofDouble(this.cents.toDouble() * value)

    /**
     * Adds the provided value to the current monetary value.
     *
     * @param other The value which will be added.
     * @return A new Money object equal to the original monetary quantity plus the value.
     * @throws [IllegalArgumentException] if the resulting amount is negative.
     */
    operator fun plus(other: Money) = ofCents(this.cents + other.cents)

    /**
     * Subtracts the provided value from the current monetary value.
     *
     * @param other The value which will be subtracted from the current monetary the value.
     * @return A new Money object equal to the original monetary quantity minus the value.
     * @throws [IllegalArgumentException] if the resulting amount is negative.
     */
    operator fun minus(other: Money) = ofCents(this.cents - other.cents)

    override fun compareTo(other: Money) = this.cents.compareTo(other.cents)
}