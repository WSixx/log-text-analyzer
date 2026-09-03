plugins {
    id("logtextanalyzer.android.library")
}

android {
    namespace = "br.com.lucad.nativeanalyzer"

    defaultConfig {
        externalNativeBuild {
            cmake {
                cFlags(
                    "-std=c23",
                    "-Wall",                    // Enable standard compiler warnings
                    "-Wextra",                  // Enable extra diagnostic warnings
                    "-Wpedantic",               // Enforce strict ISO C compliance
                    "-Wconversion",             // Warn on implicit type conversions that may lose data
                    "-Wformat=2",               // Strict format string security checks (printf/scanf)
                    "-fstack-protector-strong"  // Protect against stack buffer overflow attacks
                )
            }
        }
    }

    buildTypes {
        debug {
            externalNativeBuild {
                cmake {
                    cFlags(
                        "-O0",      // Disable optimizations for accurate debugging
                        "-g",       // Generate debug symbols for LLDB
                        "-UNDEBUG"  // Keep runtime assertions (assert()) enabled
                    )
                }
            }
        }
        release {
            externalNativeBuild {
                cmake {
                    cFlags(
                        "-O3",                  // Maximize performance optimizations
                        "-DNDEBUG",             // Disable assertions for performance and smaller binary size
                        "-D_FORTIFY_SOURCE=2",  // Enable runtime buffer overflow safety checks
                        "-fvisibility=hidden",  // Hide non-exported symbols to reduce binary size
                        "-ffunction-sections",  // Put each function in its own section for dead code stripping
                        "-fdata-sections"      // Put each data item in its own section for unused data stripping
                    )
                }
            }
        }
    }

    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "4.4.3"
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
