cmake_minimum_required(VERSION 3.5)
set(CMAKE_BUILD_TYPE Release)
set(CMAKE_VERBOSE_MAKEFILE on)

project (PPII)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -ggdb3 -static-libgcc -Wall -Wextra")
set(CMAKE_C_STANDARD 11)
set(CMAKE_C_STANDARD_REQUIRED ON)
file(COPY ${CMAKE_CURRENT_SOURCE_DIR}/routes.json
		DESTINATION ${CMAKE_CURRENT_BINARY_DIR})
add_subdirectory(CJSON)
add_subdirectory(src)
add_subdirectory(test)