# Quarkus - OpenCV

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.opencv/quarkus-opencv?logo=apache-maven&style=for-the-badge)](https://search.maven.org/artifact/io.quarkiverse.opencv/quarkus-opencv)
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=for-the-badge)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

## Introduction

With this Quarkus extension, you can use [OpenCV](https://opencv.org/) in native executables generated by GraalVM.

```xml
<dependency>
    <groupId>io.quarkiverse.opencv</groupId>
    <artifactId>quarkus-opencv</artifactId>
    <version>LATEST</version>
</dependency>
```

The OpenCV library is loaded automatically when starting your application so you don't need to load it yourself.

## Going native

Quarkus OpenCV supports creating native executables with Mandrel/GraalVM.

However, given how the OpenCV library is copied to a temporary location by the OpenPnP packaging, you might need to pass the `-XX:MaxDirectMemorySize=128m` option to the native executable.

This issue will be [solved in Java 22](https://bugs.openjdk.org/browse/JDK-8316156).

## Compatibility

| Quarkus | Quarkus OpenCV |
|---------|----------------|
| 3.x     | 2.x            |
| 2.x     | 1.x            |

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://www.redhat.com/"><img src="https://avatars.githubusercontent.com/u/1279749?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Guillaume Smet</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-opencv/commits?author=gsmet" title="Code">💻</a> <a href="#maintenance-gsmet" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
