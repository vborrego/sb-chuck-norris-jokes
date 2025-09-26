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
java -jar target/chucknorris-0.5.1.jar
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

## Create user and DB in postgresql
```sh
psql -U postgres
create user userx with login password '????????';
create database dbx;
grant all privileges on database dbx to userx;
\c dbx
grant all on schema public to userx;
\l
\q

```

## Update application.yaml
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dbx
    username: userx
    password: ????????
  jpa:
    hibernate:
      ddl-auto: update
  database-platform: org.hibernate.dialect.PostgreSQLDialect

``` 
