#!/bin/sh
nohup java -Dserver.port=8282 -jar chucknorris-0.3.0.jar >> chuck.log  &
