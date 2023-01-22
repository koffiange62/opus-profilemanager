FROM public.ecr.aws/docker/library/amazoncorretto:11.0.18-alpine3.17 as builder
WORKDIR application
ARG JAR_FILE=build/libs/opusprofilemanager-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM public.ecr.aws/docker/library/amazoncorretto:11.0.18-alpine3.17
WORKDIR application
EXPOSE 8080
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]