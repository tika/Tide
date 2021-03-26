# Tide
![CurrentVersion](https://jitpack.io/v/tika/Tide.svg)

<i>Surf along the waves of Spigot with a simple utility-first library</i>

### Features:
<ul>
    <li>TidePlugin > Tide's plugin system, required when using this library</li>
    <li>Commands > A template for commands and subcommands, supporting parameters & easy support for help commands</li>
    <li>Hue > Text utility for Spigot, easily print-to-console & other utils</li>
    <li>Number utilities > Easily convert numbers into popular formats</li>
    <li>Collection utilities > A utility to create TreeMaps, HashMaps as well as other useful collection methods</li>
    <li>Vertex > Skull utilities, an implementation of [SkullCreator]("https://github.com/deanveloper/SkullCreator")</li>
    <li>Persistent data utilities > A plethora of clean and helpful methods when dealing with Persistent Data, an implementation of [PDUtils]("https://github.com/Cronuside/PersistentDataUtilities")</li>
    <li>Menus > Create GUIs without the pain</li>
    <li>Config > A self-explanatory class to create Spigot YML configurations</li>
</ul>

### Developers
In order to use this library, add either of the following to your gradle / maven file, the current version can be found at the top of this file

<b>Maven</b>
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.tika</groupId>
        <artifactId>Tide</artifactId>
        <version>Current version</version>
    </dependency>
</dependencies>
```

<b>Gradle</b>
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    implementation 'com.github.tika:Tide:Current Version'
}
```

#### Requires Java 8+