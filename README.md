# file-cloud

## Enviroment
- JDK 8
- Node.js v12.16.3

### Deployment 
- file-cloud-0.0.1-SNAPSHOT.jar is already buid and move to the root of project
- you can run command below for a look
```
java -jar file-cloud-0.0.1-SNAPSHOT.jar
```

## Project setup
```
npm install
```

### Compiles and hot-reloads for development

run node.js
```
npm run serve
```

another terminal for java
```
./gradlew bootRun(unix-like)
.\gradlew.bat bootRun(windows powershell)
```

### Compiles and minifies for production
```
npm run build
```
static resources located at src/main/resources/static

```
./gradlew bootJar(unix-like)
.\gradlew.bat bootJar(windows powershell)
```
.jar file located at build/libs