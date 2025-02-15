plugins {
    `java-library`
    id("io.swagger.core.v3.swagger-gradle-plugin")
}

val edcVersion: String by project
val edcGroup: String by project
val jupiterVersion: String by project
val assertj: String by project
val mockitoVersion: String by project
val faker: String by project

dependencies {
    implementation("${edcGroup}:core:${edcVersion}")

    implementation(project(":extensions:participant-store-spi"))
    testImplementation("org.assertj:assertj-core:${assertj}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${jupiterVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${jupiterVersion}")
    testImplementation("org.mockito:mockito-core:${mockitoVersion}")
    testImplementation("com.github.javafaker:javafaker:${faker}")
    testImplementation(testFixtures(project(":extensions:participant-store-spi")))
}

