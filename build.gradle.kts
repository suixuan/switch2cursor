plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    // 升级到全新的 IntelliJ Platform Gradle Plugin (2.x)
    id("org.jetbrains.intellij.platform") version "2.16.0"
}

group = "com.github.qczone"
version = "1.0.3"

repositories {
    mavenCentral()
    // 新插件需要的仓库配置
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    // 方式 B：指定本地 IDEA 路径作为依赖
    intellijPlatform {
        local(file("D:/develop/jetbrains/ideaIU-2025.2.6.win"))
    }
}

intellijPlatform {
    pluginConfiguration {
        id.set("com.github.qczone.switch2ai.local")
        name.set("Switch2AI-Local")
        
        ideaVersion {
            sinceBuild.set("252")
            untilBuild.set("")
        }
    }
    
    signing {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishing {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

tasks {
    // 设置 JVM 兼容性
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}
