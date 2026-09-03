cmake_minimum_required(VERSION 4.4.3)

project("nativeanalyzer" C)

set(CMAKE_C_STANDARD 23)
set(CMAKE_C_STANDARD_REQUIRED ON)

add_library(${CMAKE_PROJECT_NAME} SHARED
        nativeanalyzer.c)

target_link_options(${CMAKE_PROJECT_NAME} PRIVATE "-Wl,--gc-sections")

target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log)