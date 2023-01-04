package gradle.plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.ScriptHandlerScope
import org.gradle.kotlin.dsl.buildscript
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.maven(repoUrl: String): MavenArtifactRepository {
    return maven(repoUrl)
}

fun Project.repositoriesFrom(listOfRepos: Collection<String>): Project =
    also {
        repositories.run {
            listOfRepos.forEach { repo ->
                maven(repo)
            }
        }
    }

fun Project.implementationDependenciesFrom(listOfDeps: Collection<String>): Project =
    also {
        dependencies.run {
            listOfDeps.forEach { dep ->
                implementation(dep)
            }
        }
    }

fun Project.implementationProjectsFrom(listOfDeps: Collection<String>): Project =
    also {
        dependencies.run {
            listOfDeps.forEach { dep ->
                implementation(project(":$dep"))
            }
        }
    }

fun Project.annotationProcessors(listOfDeps: Collection<String>): Project =
    also {
        dependencies.run {
            listOfDeps.forEach { dep ->
                kapt(dep)
            }
        }
    }

fun ScriptHandlerScope.repositoriesFrom(listOfRepos: Collection<String>) {
    repositories.run {
        listOfRepos.forEach { repo ->
            maven(repo)
        }
    }
}

fun ScriptHandlerScope.dependenciesFrom(listOfDeps: List<String>) {
    dependencies.run {
        listOfDeps.forEach { repo ->
            classpath(repo)
        }
    }
}

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: Any) {
    add("kapt", dependency)
}

fun DependencyHandler.kaptTest(dependency: Any) {
    add("kaptTest", dependency)
}

fun DependencyHandler.kaptAndroidTest(dependency: Any) {
    add("kaptAndroidTest", dependency)
}

fun JavaPluginExtension.configureJava() {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

fun Project.configureRepositories(): Project =
    this.also {
        it.buildscript {
            repositories.google()
        }
        it.repositories.google()
    }
