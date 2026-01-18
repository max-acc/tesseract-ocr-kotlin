package alien42

import alien42.tuxbrew.invoice.Sauder2025InvoiceEvaluationStrategy
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val path = Path.of("/home/alien/GitHub/tesseract-ocr-kotlin/data/Sauder_2024_11_26-1.pdf")
    // test.pdf")
    // Sauder_2024_11_26.pdf")
    // Sauder_2024_11_26-1.pdf")
    val tax : UInt = 5u
    val eval = Sauder2025InvoiceEvaluationStrategy(tax)
    Files.newInputStream(path).use { pdfStream ->
        eval.evaluate(pdfStream)
    }

    //println(eval.evaluate(inputStream))
}


