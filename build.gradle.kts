plugins {
    kotlin("jvm") version "2.1.10"
}

group = "alien42"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //OCR
    implementation("net.sourceforge.tess4j:tess4j:5.13.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // PDF
    implementation("org.apache.pdfbox:pdfbox:3.0.2")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}