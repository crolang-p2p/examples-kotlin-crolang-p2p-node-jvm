plugins {
    kotlin("jvm") version "2.1.21"
    application
}

group = "io.github.crolang-p2p"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("io.github.crolang-p2p:crolang-p2p-node-jvm:0.6.0-alpha")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}

fun registerExampleRunTask(exampleN: String, node: String) {
    tasks.register<JavaExec>("runEx${exampleN}${node}") {
        group = "application"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("ex_${exampleN}.Ex_${exampleN}_${node}Kt")
    }
}

fun registerSubExampleRunTask(exampleN: String, exampleLetter: String, node: String) {
    tasks.register<JavaExec>("runEx${exampleN}${exampleLetter}${node}") {
        group = "application"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("ex_${exampleN}.Ex_${exampleN}_${exampleLetter}_${node}Kt")
    }
}

registerExampleRunTask("1", "Alice")
registerExampleRunTask("1", "Bob")
registerExampleRunTask("2", "Alice")
registerExampleRunTask("3", "Alice")
registerExampleRunTask("3", "Bob")
registerSubExampleRunTask("4", "A", "Alice")
registerSubExampleRunTask("4", "B", "Alice")
registerSubExampleRunTask("4", "C", "Alice")
registerSubExampleRunTask("4", "D", "Alice")
registerExampleRunTask("4", "Bob")
registerExampleRunTask("4", "Carol")
registerExampleRunTask("5", "Alice")
registerExampleRunTask("5", "Bob")
registerExampleRunTask("6", "Alice")
registerExampleRunTask("6", "Bob")
registerExampleRunTask("6", "Carol")
registerExampleRunTask("7", "Alice")
registerExampleRunTask("7", "Bob")
registerExampleRunTask("8", "Alice")
registerExampleRunTask("8", "Bob")
registerExampleRunTask("9", "Alice")
registerExampleRunTask("9", "Bob")
registerExampleRunTask("9", "Carol")
registerExampleRunTask("10", "Alice")
registerExampleRunTask("10", "Bob")
registerExampleRunTask("11", "Alice")
registerExampleRunTask("12", "Alice")