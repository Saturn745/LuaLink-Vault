plugins {
    id("java")
}

group = "xyz.galaxyy.lualink.addons.vault"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
    maven("https://jitpack.io")
    maven {
        url = uri("https://maven.pkg.github.com/LuaLink/LuaLink")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("xyz.galaxyy.lualink:lualink:1.20.1-14")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
}

tasks.test {
    useJUnitPlatform()
}