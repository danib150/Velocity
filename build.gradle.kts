import com.velocitypowered.script.VelocityCheckstylePlugin
import com.velocitypowered.script.VelocitySpotlessPlugin

plugins {
    `java-library`
    id("maven-publish")
}

val junitVersion: String by project.extra

allprojects {
    group = "com.github.danib150"
    version = "3.2.0-SNAPSHOT"
}


subprojects {
    apply<JavaLibraryPlugin>()

    apply<VelocityCheckstylePlugin>()
    apply<VelocitySpotlessPlugin>()

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    repositories {
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // adventure
        maven("https://repo.papermc.io/repository/maven-public/")

    }
    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    }



    tasks {
        test {
            useJUnitPlatform()
            reports {
                junitXml.required.set(true)
            }
        }
    }
}
