#!/bin/sh
export SERVER_SSL_KEY_STORE_PASSWORD=????????
nohup java -jar target/chucknorris-0.5.3.jar  >> chuck.log  &
