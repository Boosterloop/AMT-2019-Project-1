SET foreign_key_checks = 0;

LOAD DATA INFILE '/load-data/visits.csv'
INTO TABLE citylogdb.Visit
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(idVisit, fk_username, fk_idCity, startDate, endDate)
SET idVisit = NULL;

SET foreign_key_checks = 1;
