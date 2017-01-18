# Spring Social Yammer

Updated with the latest version of spring and jackson to match spring boot v.1.1.4

- springSocialVersion = '1.1.4.RELEASE'
- jacksonVersion = '2.8.5'
- junitVersion = '4.12'
- mockitoVersion = '1.10.19'
- springVersion = '4.3.5.RELEASE'
- springSecurityCryptoVersion = '4.1.4.RELEASE'

## Travis CI

[![Build Status](https://travis-ci.org/emeraldjava/spring-social-yammer.svg?branch=master)](https://travis-ci.org/emeraldjava/spring-social-yammer)

## Jitpack

[![Release](https://jitpack.io/v/emeraldjava/spring-social-yammer.svg)](https://jitpack.io/#emeraldjava/spring-social-yammer)

## Current Spring

- http://docs.spring.io/spring-boot/docs/current/reference/html/appendix-dependency-versions.html
- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl

## Install the jar to local maven repo

```
mvn install:install-file -Dfile=./spring-social-yammer/build/libs/spring-social-yammer-1.0.2.BUILD-SNAPSHOT.jar -DgroupId=org.springframework.social -DartifactId=spring-social-yammer -Dversion=1.0.2.BUILD-SNAPSHOT -Dpackaging=jar
```

http://stackoverflow.com/questions/26737297/api-rest-yammer-cant-retrieve-admins-from-a-group

