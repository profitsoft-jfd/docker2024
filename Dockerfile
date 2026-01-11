# Используем образ Maven для сборки приложения
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:17

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный jar файл из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Определяем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]