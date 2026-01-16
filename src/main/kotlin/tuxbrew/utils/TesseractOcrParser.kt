package alien42.tuxbrew.utils

import net.sourceforge.tess4j.Tesseract
import java.io.InputStream
import javax.imageio.ImageIO

/**
 * This class parses data using a tesseract OCR parser.
 */
class TesseractOcrParser {
    private val tesseract = Tesseract().apply {
        setDatapath("tessdata")
        setLanguage("eng")
    }

    fun generateTextFromImage(imageStream : InputStream) : String {
        val image = ImageIO.read(imageStream)
            ?: throw IllegalStateException("Invalid image input stream") // TODO handle this error

        return tesseract.doOCR(image)
    }
}