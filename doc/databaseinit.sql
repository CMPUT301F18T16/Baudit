/* 
This table represents care providers i.e. doctors, nurses, etc.
Does not have contact information as it is not necessary
*/
CREATE TABLE careprovider  (
id VARCHAR(20) NOT NULL UNIQUE,
first VARCHAR(50),
last VARCHAR(50),
PRIMARY KEY (id)
);

/*
This table represents patients.
Includes contact information.
*/
CREATE TABLE patient (
id VARCHAR(20) NOT NULL UNIQUE,
first VARCHAR(50),
last VARCHAR(50),
email VARCHAR(50),
phone INT(20),
PRIMARY KEY (id)
);

/*
This table lists all the patients associated with each care provider.
*/
CREATE TABLE relationship (
careprovider VARCHAR(20) NOT NULL,
patient VARCHAR(20) NOT NULL,
PRIMARY KEY(careprovider, patient),
FOREIGN KEY(careprovider) REFERENCES careprovider(id),
FOREIGN KEY(patient) REFERENCES patient(id)
);

/*
This table holds all the bodyphotos patients upload.
It is not in the patient table as there are variable
amounts for each patient, and storing photos separately
increases performance.
*/
CREATE TABLE bodyphoto (
id INT NOT NULL AUTO_INCREMENT,
patient VARCHAR(20) NOT NULL,
photo VARBINARY(max),
PRIMARY KEY (patient, id),
FOREIGN KEY (patient) REFERENCES patient(id)
);

/*
This table represents all of the problems by all of the patients.
Contains general information about the problem.
*/
CREATE TABLE problem (
id INT NOT NULL AUTO_INCREMENT,
patient VARCHAR(20) NOT NULL,
title VARCHAR(30),
description VARCHAR(300),
datestarted DATETIME,
PRIMARY KEY(patient, id),
FOREIGN KEY(patient) REFERENCES patient(id)
);

/*
This table represents all of the records of each problem of each patient.
Contails the details of the problem at the time the patient inserts the record.
*/
CREATE TABLE record (
id INT NOT NULL AUTO_INCREMENT,
problem INT NOT NULL,
title VARCHAR(30),
date TIMESTAMP,
comment VARCHAR(300),
geolat DOUBLE,
geolong DOUBLE,
bodyphoto INT,
bodyx INT,
bodyy INT,
PRIMARY KEY(problem, id),
FOREIGN KEY(problem) REFERENCES problem(id)
FOREIGN KEY(bodyphoto) REFERENCES bodyphoto(id)
ON DELETE CASCADE
);

/*
This table holds all the record photos patients upload.
It is not in the record table as there are variable
amounts for each record, and storing photos separately
increases performance.
*/
CREATE TABLE photo (
id  INT NOT NULL AUTO_INCREMENT,
record INT,
photo VARBINARY(max),
PRIMARY KEY (record, id),
FOREIGN KEY record REFERENCES record(id)
ON DELETE CASCADE
);
