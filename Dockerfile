# Используем образ Maven для сборки приложения
FROM maven as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный jar файл из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Определяем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]