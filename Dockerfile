FROM openjdk:21

WORKDIR /app

COPY . .

RUN javac -d /bin src/*.java

CMD ["java", "-cp", "/bin", "Main"]
