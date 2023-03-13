package io.github.warung88.driver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DriverServerApplication

fun main(args: Array<String>) {
    runApplication<DriverServerApplication>(*args)
}
