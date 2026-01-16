package alien42.tuxbrew.utils

import net.sourceforge.tess4j.Tesseract
import java.awt.image.BufferedImage

/**
 * This class parses data using a tesseract OCR parser.
 */
class TesseractOcrParser {
    private val tesseract = Tesseract().apply {
        setDatapath("tessdata")
        setLanguage("eng")
    }

    fun setWhiteList() {
        tesseract.setVariable(
            "tessedit_char_whitelist",
            "0123456789"
        )
    }

    fun generateTextFromImage(image : BufferedImage) : String {
        return tesseract.doOCR(image)
    }
}