#!/bin/sh

sudo keytool -trustcacerts -keystore /usr/lib/jvm/java-8-openjdk/jre/lib/security/cacerts -storepass changeit -alias payara -import -file ./payara-self-signed-certificate.crt
