import br.com.lucad.logtextanalyzer.compileSdk
import br.com.lucad.logtextanalyzer.javaVersion
import br.com.lucad.logtextanalyzer.libs
import br.com.lucad.logtextanalyzer.minSdk
import br.com.lucad.logtextanalyzer.ndkVersion
import br.com.lucad.logtextanalyzer.targetSdk
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = libs.compileSdk
                ndkVersion = libs.ndkVersion

                defaultConfig {
                    minSdk = libs.minSdk
                    targetSdk = libs.targetSdk
                }

                buildTypes {
                    getByName("debug") {
                        isDebuggable = true
                        applicationIdSuffix = ".debug"
                    }

                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = javaVersion
                    targetCompatibility = javaVersion
                }
            }
        }
    }
}
