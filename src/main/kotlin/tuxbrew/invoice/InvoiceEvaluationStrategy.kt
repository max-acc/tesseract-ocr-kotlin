package alien42.tuxbrew.invoice

import alien42.tuxbrew.utils.Money
import alien42.tuxbrew.utils.TesseractOcrParser
import org.apache.pdfbox.Loader
import org.apache.pdfbox.io.RandomAccessRead
import org.apache.pdfbox.io.RandomAccessReadBuffer
import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.PDFRenderer
import java.awt.image.BufferedImage
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
        parser.setWhiteList()
        //TODO("Not yet implemented $parser, ${Money.ofCents(0u).convertNettoToBrutto()}")

        val images = pdfToImg(data)

        val entry : InvoiceEvaluationEntry = InvoiceEvaluationEntry("test", 5u, Money.ofDouble(1.0).convertNettoToBrutto())
        println(parser.generateTextFromImage(images[0]))
        return listOf(entry)
    }

    private fun Money.convertNettoToBrutto() = this + this * (tax.toDouble() / 100.0)
}

private fun pdfToImg(pdfStream : InputStream) : List<BufferedImage> {
    val randomAccess: RandomAccessRead = RandomAccessReadBuffer(pdfStream)

    Loader.loadPDF(randomAccess).use { document ->
        val renderer = PDFRenderer(document)
        return (0 until document.numberOfPages).map { pageIndex ->
            renderer.renderImageWithDPI(
                pageIndex,
                300f,
                ImageType.GRAY
            )
        }
    }
}