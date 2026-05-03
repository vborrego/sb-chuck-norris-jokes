# sb-chuck-norris-jokes
Spring Boot app that gets Chuck Norris jokes from an external API

# Create keystore
```sh
cd src/main/resources/
rm keystore.p12
keytool -genkey -alias mysslcert -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650  -dname "cn=name, ou=group, o=company, c=country"
keytool -list -v -keystore keystore.p12
```

# Build and run
```sh
mvn clean install
export SERVER_SSL_KEY_STORE_PASSWORD=????????
java -jar target/chucknorris-0.5.2.jar
```

URLs
 * https://localhost:8443/swagger-ui/index.html
 * https://localhost:8443/
 * https://localhost:8443/hello.txt

```sh
cd src/main/webapp
wget https://github.com/twbs/bootstrap/releases/download/v5.3.3/bootstrap-5.3.3-dist.zip
wget https://code.jquery.com/jquery-3.7.1.min.js
unzip bootstrap-*zip
```

# Add postgresql support 
## Add dependency in pom.xml
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

## Run postgres docker container
```shell
podman network create mynet
podman run -p 5432:5432 --rm --name postgres-server -e POSTGRES_PASSWORD=postgres --network mynet -d postgres:18-alpine
```

## Create user and DB in postgresql
```sh
podman exec -it postgres-server sh
psql -U postgres
create user sa with login password 'sa';
create database mydb;
grant all privileges on database mydb to sa;
\c mydb
grant all on schema public to sa;
\l
\q
exit
```

## Update application.yaml
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
    username: sa
    password: sa
  jpa:
    hibernate:
      ddl-auto: update
  database-platform: org.hibernate.dialect.PostgreSQLDialect

``` 

## Select inserted jokes
```shell
podman exec -it postgres-server sh
psql -U postgres
\c mydb
\dt
select * from joke_entity;
\q
exit
``` 

## Debug in vscode
.vscode/launch.json
```json
{
    "configurations": [
    {
        "type": "java",
        "name": "Attach to Remote Program",
        "request": "attach",
        "hostName": "localhost",
        "port": "8000"
    }
    ]
}
```