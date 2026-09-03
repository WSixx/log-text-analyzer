plugins {
    `kotlin-dsl`
}

group = "br.com.lucad.logtextanalyzer.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "logtextanalyzer.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "logtextanalyzer.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "logtextanalyzer.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
