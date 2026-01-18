package alien42.tuxbrew.utils

import net.sourceforge.tess4j.Tesseract
import java.awt.image.BufferedImage

enum class CHARS(val content: String) {
    ALPHANUMERIC("abcdefghijklmnopqrstuvwxyzäöüABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ"),
    NUMERIC("0123456789"),
    SPECIAL(".,€$%"),
}

/**
 * This class parses data using a tesseract OCR parser.
 */
class TesseractOcrParser {
    private val tesseract = Tesseract().apply {
        setDatapath("tessdata")
        setLanguage("eng")
    }

    fun setWhiteList(charSet: String) {
        tesseract.setVariable(
            "tessedit_char_whitelist",
            charSet,
        )
    }

    fun generateTextFromImage(image : BufferedImage) : String {
        return tesseract.doOCR(image)
    }
}