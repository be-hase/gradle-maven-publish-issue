plugins {
    `java-library`
    `maven-publish`
    `java-test-fixtures`
}

group = "com.example"
version = "1.0"

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.14.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            if (System.getenv().containsKey("ARTIFACT_ID")) {
                artifactId = System.getenv()["ARTIFACT_ID"]
            }

            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}
