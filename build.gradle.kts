val kotlinx_serialization_version: String by project
val kotlinx_coroutines_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.1.20"
    `maven-publish`

    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
    id("com.gradleup.shadow") version "8.3.5"
}

group = "net.mt32.expoll.commons"
version = "4.2.4"

repositories {
    mavenCentral()

    maven {
        url = uri("https://git.mt32.net/api/v4/groups/expoll/expoll-kotlin-common/-/packages/maven")
        name = "GitLab"
        credentials(HttpHeaderCredentials::class) {
            name = "Job-Token"
            value = System.getenv("CI_JOB_TOKEN")
        }
        authentication {
            create("header", HttpHeaderAuthentication::class)
        }
    }


}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${kotlinx_serialization_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinx_coroutines_version}")
    implementation("joda-time:joda-time:2.13.0")

    // Ktor client
    implementation("io.ktor:ktor-client-core:${ktor_version}")
    implementation("io.ktor:ktor-client-jetty:${ktor_version}")
    implementation("io.ktor:ktor-client-java:${ktor_version}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktor_version}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}