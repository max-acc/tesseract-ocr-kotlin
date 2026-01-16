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
    implementation("net.sourceforge.tess4j:tess4j:4.2.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}