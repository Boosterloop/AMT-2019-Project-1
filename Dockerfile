FROM payara/server-full:5.193

COPY target/citylog.war $DEPLOY_DIR
COPY docker/payara/mysql-connector-java-8.0.18.jar ${PAYARA_DIR}/glassfish/lib/
COPY docker/payara/post-boot-commands.asadmin /opt/payara/config/post-boot-commands.asadmin
