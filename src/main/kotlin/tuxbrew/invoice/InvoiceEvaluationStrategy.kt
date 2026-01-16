package alien42.tuxbrew.invoice

import alien42.tuxbrew.utils.Money
import alien42.tuxbrew.utils.TesseractOcrParser
import java.io.InputStream

/**
 * The data of an invoice evaluation entry.
 *
 * @property name       The name of the invoice.
 * @property quantity   The amount of items in this invoice evaluation.
 * @property price      The price per item in this invoice evaluation.
 */
data class InvoiceEvaluationEntry(val name: String, val quantity: UInt, val price: Money)

/**
 * Formats for evaluating invoices.
 */
enum class InvoiceEvaluationFormat {
    /** The default invoice evaluation format. */
    Sauder2025,
}

/**
 * The strategy for evaluating invoices.
 *
 * @property format The format to evaluate the invoice with.
 */
abstract class InvoiceEvaluationStrategy protected constructor(val format: InvoiceEvaluationFormat) {
    /**
     * This function evaluates the given invoice data.
     *
     * @param data  The invoice data to evaluate.
     */
    abstract fun evaluate(data: InputStream): List<InvoiceEvaluationEntry>
}

/**
 * The default implementation for evaluating invoices.
 *
 * @property tax    The tax for this invoice.
 */
class Sauder2025InvoiceEvaluationStrategy(val tax: UInt) : InvoiceEvaluationStrategy(InvoiceEvaluationFormat.Sauder2025) {
    override fun evaluate(data: InputStream): List<InvoiceEvaluationEntry> {
        val parser = TesseractOcrParser()
        TODO("Not yet implemented $parser, ${Money.ofCents(0u).convertNettoToBrutto()}")
    }

    private fun Money.convertNettoToBrutto() = this + this * (tax.toDouble() / 100.0)
}