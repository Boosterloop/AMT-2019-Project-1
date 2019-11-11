FROM mysql:5.7.28

COPY get-data/visits.csv /var/lib/mysql-files/
COPY db-scripts/* docker-entrypoint-initdb.d/