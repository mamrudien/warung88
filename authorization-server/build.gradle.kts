import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm")
	kotlin("plugin.spring") version "1.7.22"
}

group = "io.github.warung88"
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.security:spring-security-oauth2-authorization-server:1.0.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.named<BootBuildImage>("bootBuildImage") {
	val registry = System.getenv("REGISTRY") ?: "docker.io"
	val user = System.getenv("USER") ?: "library"
	val token = System.getenv("TOKEN") ?: ""

	imageName.set("$registry/$user/${project.rootProject.name}-${project.name}:${project.version}")

	docker {
		publishRegistry {
			url.set(registry)
			username.set(user)
			password.set(token)
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
