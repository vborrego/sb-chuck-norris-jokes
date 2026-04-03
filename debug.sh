#!/bin/sh
# set the pass for the created keystore
export SERVER_SSL_KEY_STORE_PASSWORD=????????
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address="8000" -jar target/chucknorris-0.5.2.jar