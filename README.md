# Desktop Builder

## About

This application creates .desktop files. This has only been tested on Ubuntu 22.04


## Parameters

| Flag name | Required | Description                                                      |
|-----------|----------|------------------------------------------------------------------|
| location  | Yes      | The location of the application the .desktop file will reference |
| name      | Yes      | The name that the .desktop file will have                        |
| icon      | No       | Path to the icon of the .desktop file                            |

## Usage
Using the gradle wrapper

```./gradlew bootRun --args='-location /usr/bin/steam -name Steam -icon /home/user/Images/steam'```

Using the native binary

```desktop-builder -location /usr/bin/steam -name Steam -icon /home/user/Images/steam'```



## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks

If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./gradlew bootBuildImage
```

Then, you can run the app like any other container:

```
$ docker run --rm desktop-builder:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools

Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./gradlew nativeCompile
```

Then, you can run the app as follows:

```
$ build/native/nativeCompile/desktop-builder
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./gradlew nativeTest
```

