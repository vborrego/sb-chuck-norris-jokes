# sb-chuck-norris-jokes
Spring Boot app that gets Chuck Norris jokes from an external API

# Create keystore
```sh
cd src/main/resources/
rm keystore.p12
keytool -genkey -alias mysslcert -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
keytool -list -v -keystore keystore.p12
```

# Build and run
```sh
mvn clean install
export SERVER_SSL_KEY_STORE_PASSWORD=????????
java -jar target/chucknorris-0.5.0.jar
```
URLs
 * http://localhost:8181/swagger-ui/index.html
 * http://localhost:8181/?name=Vitor
 * http://localhost:8181/hello.txt

```sh
cd src/main/webapp
wget https://github.com/twbs/bootstrap/releases/download/v5.3.3/bootstrap-5.3.3-dist.zip
wget https://code.jquery.com/jquery-3.7.1.min.js
unzip bootstrap-*zip
```
