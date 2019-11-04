FROM tomee

COPY target/citylog.war /usr/local/tomee/webapps/citylog.war
COPY docker/tomee/tomee.xml /usr/local/tomee/conf/tomee.xml
COPY docker/tomee/mysql-connector-java-8.0.18.jar /usr/local/tomee/lib/mysql-driver.jar
