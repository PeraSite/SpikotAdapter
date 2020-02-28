import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    kotlin("jvm") version "1.3.61"
}

repositories{
    maven("https://maven.heartpattern.kr/repository/maven-public/")
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}