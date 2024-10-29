pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
    versionCatalogs {
        create("shihcheeng") {
            from(files("gradle/shihcheeng.toml"))
        }
        create("everyuse") {
            from(files("gradle/everyuse.versions.toml"))
        }
    }
}

rootProject.name = "HACG"
include(":app")
 