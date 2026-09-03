package br.com.lucad.logtextanalyzer

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val VersionCatalog.compileSdk: Int
    get() = findVersion("compileSdk").get().requiredVersion.toInt()

val VersionCatalog.minSdk: Int
    get() = findVersion("minSdk").get().requiredVersion.toInt()

val VersionCatalog.targetSdk: Int
    get() = findVersion("targetSdk").get().requiredVersion.toInt()

val VersionCatalog.ndkVersion: String
    get() = findVersion("ndk").get().requiredVersion

val javaVersion = JavaVersion.VERSION_21
