FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /build

COPY gradle/ gradlew build.gradle settings.gradle /build/

COPY gradle/ /build/gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies

COPY src /build/src

RUN ./gradlew build


# ------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /build/build/libs/kanflow*SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "application.jar"]
