plugins {
    id("com.gradle.enterprise") version("3.9")
}

rootProject.name = "warung88"

include("authorization-server")

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}