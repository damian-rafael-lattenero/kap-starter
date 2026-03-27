plugins {
    kotlin("jvm") version "2.3.20"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Core orchestration — the only required module
    implementation("io.github.damian-rafael-lattenero:kap-core:2.4.0")

    // Optional: resilience patterns (Schedule, CircuitBreaker, Resource, timeoutRace)
    // implementation("io.github.damian-rafael-lattenero:kap-resilience:2.4.0")

    // Optional: Arrow integration (parallel validation with error accumulation)
    // implementation("io.github.damian-rafael-lattenero:kap-arrow:2.4.0")

    // Optional: Ktor server plugin
    // implementation("io.github.damian-rafael-lattenero:kap-ktor:2.4.0")

    // Optional: test matchers
    // testImplementation("io.github.damian-rafael-lattenero:kap-kotest:2.4.0")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
