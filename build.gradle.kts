import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0" // Spring Boot 버전 업데이트
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20" // Kotlin 버전 업데이트
	kotlin("plugin.spring") version "1.9.20"
}

group = "com.henry"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21 // Java 버전 21로 설정

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21" // Kotlin 컴파일러의 JVM 타겟 버전 설정
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
