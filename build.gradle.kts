import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.4.21"
    id("com.google.devtools.ksp") version "2.3.12"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Core orchestration + KSP named builders
    implementation("io.github.damian-rafael-lattenero:kap-core:5.3.0")
    implementation("io.github.damian-rafael-lattenero:kap-ksp-annotations:5.3.0")
    ksp("io.github.damian-rafael-lattenero:kap-ksp:5.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")

    // Optional: resilience patterns (Schedule, CircuitBreaker, Resource, timeoutRace)
    // implementation("io.github.damian-rafael-lattenero:kap-resilience:5.3.0")

    // Optional: Arrow integration (parallel validation with error accumulation)
    // implementation("io.github.damian-rafael-lattenero:kap-arrow:5.3.0")

    // Optional: Ktor server plugin
    // implementation("io.github.damian-rafael-lattenero:kap-ktor:5.3.0")

    // Optional: test matchers
    // testImplementation("io.github.damian-rafael-lattenero:kap-kotest:5.3.0")

    // Optional: the kap doctrine as a detekt gate (ManualWaitLoop, CancellationArmBeforeCatchAll, VolatileReadModifyWrite)
    // detektPlugins("io.github.damian-rafael-lattenero:kap-detekt-rules:5.3.0")
}

kotlin {
    jvmToolchain(25)
}

// Built with the newest LTS, runs on 21+ — the same single-JVM law as kap,
// agent-brain and zcode-bot-kotlin: the toolchain is 25, the BYTECODE stays 21.
val bytecodeTarget = 21
tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(bytecodeTarget.toString()))
        freeCompilerArgs.add("-Xjdk-release=$bytecodeTarget")
    }
}
tasks.withType<JavaCompile>().configureEach { options.release.set(bytecodeTarget) }

application {
    mainClass.set("MainKt")
}
