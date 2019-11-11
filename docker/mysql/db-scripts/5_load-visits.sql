LOAD DATA INFILE '/var/lib/mysql-files/visits.csv'
INTO TABLE citylogdb.Visit
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(idVisit, fk_username, fk_idCity, startDate, endDate)
SET idVisit = NULL;
