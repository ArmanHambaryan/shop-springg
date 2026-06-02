# Օգտագործում ենք Java-ի image-ը
FROM eclipse-temurin:21-jre-alpine

# Սահմանում ենք աշխատանքային թղթապանակը կոնտեյների ներսում
WORKDIR /app

# Պատճենում ենք մեր հավաքված .jar ֆայլը կոնտեյների մեջ
COPY target/*.jar app.jar

# Հրաման, որով պրոյեկտը կստարտ լինի
ENTRYPOINT ["java", "-jar", "app.jar"]