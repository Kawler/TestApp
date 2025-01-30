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
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EffMob"
include(":app")
include(":core-db-api")
include(":core-db-impl")
include(":core-network-api")
include(":core-network-impl")
include(":core-utils")
include(":feature-main-api")
include(":feature-main-impl")
include(":feature-vacancy-details-api")
include(":feature-vacancy-details-impl")
include(":feature-favorite-api")
include(":feature-favorite-impl")
