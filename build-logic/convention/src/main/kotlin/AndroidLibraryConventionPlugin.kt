import br.com.lucad.logtextanalyzer.compileSdk
import br.com.lucad.logtextanalyzer.javaVersion
import br.com.lucad.logtextanalyzer.libs
import br.com.lucad.logtextanalyzer.minSdk
import br.com.lucad.logtextanalyzer.ndkVersion
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = libs.compileSdk
                ndkVersion = libs.ndkVersion

                defaultConfig {
                    minSdk = libs.minSdk
                }

                buildTypes {
                    getByName("debug") {
                        // Debug build type defaults
                    }

                    getByName("release") {
                        isMinifyEnabled = false
                        consumerProguardFiles("consumer-rules.pro")
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
