#syntax=docker/dockerfile:1.2
### build stage ###
#FROM azul/zulu-openjdk:21 AS builder
FROM eclipse-temurin:21-jdk AS builder
# set arg
ARG WORKSPACE=/home/spring-docker
ARG BUILD_TARGET=${WORKSPACE}/build/libs
WORKDIR ${WORKSPACE}

COPY . .
RUN ./gradlew clean bootJar

# unpack jar
WORKDIR ${BUILD_TARGET}
RUN jar -xf *.jar


### create image stage ###
FROM eclipse-temurin:21-jdk

ARG WORKSPACE=/home/spring-docker
ARG BUILD_TARGET=${WORKSPACE}/build/libs
ARG DEPLOY_PATH=${WORKSPACE}/deploy

# copy from build stage
COPY --from=builder ${BUILD_TARGET}/org ${DEPLOY_PATH}/org
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/lib ${DEPLOY_PATH}/BOOT-INF/lib
COPY --from=builder ${BUILD_TARGET}/META-INF ${DEPLOY_PATH}/META-INF
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/classes ${DEPLOY_PATH}/BOOT-INF/classes

WORKDIR ${DEPLOY_PATH}

EXPOSE 8080/tcp
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
