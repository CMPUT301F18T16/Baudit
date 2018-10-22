### Task Basics
#### US 01.01.01
As a patient I want to record my problems, each denoted with a title, date started, and a brief description.
```
INSERT INTO problem (patient, title, description, datestarted)
VALUES ($patient, $title, $description, $datestarted);
```

#### US 01.01.02 (added 2018-02-14)
As a patient, I want the maximum length of the task title to be at least 30 characters.
```
title VARCHAR(30)
```

#### US 01.01.03 (added 2018-02-14)
As a patient, I want the maximum length of the task description to be at least 300 characters.
```
description VARCHAR(300)
```

#### US 01.02.01
As a patient, I want to view a list of my problems, with their titles, dates, and number of records.
```
SELECT problem.title, problem.datestarted, numrecords (select count(*) from record where problem.id=record.problem)
FROM problem
WHERE problem.patient = $patient
ORDER BY problem.id ASC
```

#### US 01.03.01 (revised 2018-02-16)
As a patient, I want to edit the details for any one of my problems.
```
UPDATE problem
SET problem.title = $title, problem.description = $description, problem.datestarted = $datestarted
WHERE problem.id = $problemid
```

#### US 01.04.01
As a patient, I want to delete a problem of mine.
```
DELETE FROM problem
WHERE problem.id = $problemid
```

#### US 01.05.01
As a patient I want to add records (photos, locations, comments) to problems.
```
INSERT INTO record
(problem, title, date, comment, geolat, geolong, bodyphoto, bodyx, bodyy)
VALUES
($problem, $title, $date, $comment, $geolat, $geolong, $bodyphoto, $bodyx, $bodyy)
INSERT INTO photo (record, photo)
VALUES ((SELECT MAX(record.id) FROM record WHERE record.problem = $problem), $photo)
```

### Record Details
#### US 02.01.01
As a patient or care provider, I want to view all the records for a given problem, including its title, description, photo, etc.
```
SELECT record.title, record.date, record.comment, photo.photo
FROM record LEFT OUTER JOIN photo ON record.id = photo.record
WHERE record.problem = $problem
AND (photo.id = MIN(select photo.id from photo where photo.record = record.id) OR photo.id IS NULL)
ORDER BY record.id ASC
```

#### US 02.02.01
As a patient, I want to add records to problems.
Same as 01.05.01

#### US 02.03.01
As a patient, records can have a geo-location.
```
UPDATE record
SET geolat = $geolat, geolong = $geolong
WHERE record.id = $recordid
```

#### US 02.04.01
As a patient, records can have a body location.
```
UPDATE record
SET bodyphoto = $bodyphoto, bodyx = $bodyx, bodyy = $bodyy
WHERE record.id = $recordid
```

#### US 02.05.01
As a patient, records can have a title and comment.
```
UPDATE record
SET title = $title, comment = $comment
WHERE record.id = $recordid
```

#### US 02.06.01
As a patient, records can have a photo.
```
INSERT INTO photo (record, photo)
VALUES ($record, $photo)
```

#### US 02.07.01
As care provider, I want to add new comment records to a problem for my patients.
```
INSERT INTO record (problem, comment)
VALUES ($problem, $comment)
```

#### US 02.08.01
As a patient, records have a timestamp.
```
date TIMESTAMP
```

#### US 02.09.01
As a patient, I want some method of helping me take consistent photos, so that when I show the doctor any progression such as the growth of a mole is evident.
NON-SQL

#### US 02.10.01
As a patient, I need to have a slideshow mode whereby photo records of a problem can be browsed by clicking. So I could "animate” changes to a doctor.
```
SELECT photo.photo
FROM record LEFT OUTER JOIN photo ON record.id = photo.record
WHERE record.problem = $problem
ORDER BY photo.id ASC
```

### User Profile
#### US 03.01.01
As a patient, I want a profile with a unique userid and my contact information.
```
id VARCHAR(20) NOT NULL UNIQUE
```

#### US 03.01.02
As a patient, I want the minimum userid to be at least 8 characters.
NON-SQL

#### US 03.01.03
As a patient, I want the contact information to include an email address and a phone number.
```
INSERT INTO patient (id, first, last, email, phone)
VALUES ($id, $first, $last, $email, $phone)
```

#### US 03.02.01
As a patient, I want to edit the contact information in my profile.
```
UPDATE patient
SET email = $email, phone = $phone
WHERE patient.id = $patientid
```

#### US 03.03.01
As a patient or care provider, I want to, whenever a username is shown, be able to retrieve and see its corresponding contact information.
```
SELECT *
FROM patient
WHERE patient.id = $patientid
```

### Searching
#### US 04.01.01
As a patient or care provider, I want to specify a set of keywords, and search for all problems or records that contains all the keywords.
ELASTICSEARCH

#### US 04.02.01
As a patient or care provider, I want to specify a set of keywords, and search for all problems or records that are near a particular Geo-location.
ELASTICSEARCH

#### US 04.02.01
As a patient or care provider, I want to specify a set of keywords, and search for all problems or records that are near a particular body-location.
ELASTICSEARCH

### Patient Assigned
#### US 06.01.01
As a care provider, I want to view a list of patients I am assigned to.
```
SELECT patient.*
FROM patient, relationship
WHERE patient.id = relationship.patient
AND relationship.careprovider = $careprovider
```

### Care Provider
#### US 07.01.01
As a care provider, I want to add patients that I am assigned to.
```
INSERT INTO relationship
VALUES $careprovider, $patient
```

#### US 07.01.02
As a care provider, I want to want to browse my patients problems.
Same as US 01.02.01

#### US 07.01.03
As a care provider, I want to add comment records to my patients' problems
Same as US 02.07.01

### Offline behavior
#### US 08.01.01
As a patient, I want to add or edit problems and records while off the network, and have these changes synchronized once I regain connectivity.
NOT SURE YET

### Photographs
#### US 09.01.01
As a patient, I want to optionally attach one or more photographs as further viewable details to a record of mine.
Same as US 02.06.01

#### US 09.01.02 (added 2018-04-06)
As a patient, I want the maximum number of photographs that can be attached to a record to be at least 10.
NON-SQL but will use something like allow adding a photo if 10 > :
```
SELECT COUNT(*)
FROM photo
WHERE photo.record = $record
```

#### US 09.02.01
As a patient or care provider, I want to view any attached photograph for a record.
```
SELECT photo.photo
FROM photo
WHERE photo.record = $record
```

#### US 09.03.01
As a sys admin, I want each photograph to be under 65536 bytes in size.
NON-SQL

### Geolocation and Maps
#### US 10.01.01
As a patient, I want to specify a geo location on a map of a record.
```
UPDATE record
SET geolat = $geolat, geolong = $geolong
WHERE record.id = $recordid
```

#### US 10.02.01
As a patient or provider, I want to view any geo location for a record, on a map.
```
SELECT geolat, geolong
FROM record
WHERE record.id = $recordid
```

#### US 10.03.01
As a patient or care provider, I want to see a map of all records for a patient (that have locations).
```
SELECT geolat, geolong
FROM record, problem
WHERE record.problem = problem.id
AND problem.patient = $patientid
```

### Body-locations
#### US 11.01.01
As a patient, I want to upload at least front and back body-location photos so I may indicate where photo records were taken on my body.
```
INSERT INTO bodyphoto (patient, photo)
VALUES $patient, $photo
```

#### US 11.02.01
As a patient, body locations are effectively a reference to a body-location photo and a point location on the photo.
Same as US 02.04.01

#### US 11.03.01
As a patient, I should be able to have at least 2 body location pictures.
NON-SQL

#### US 11.04.01
As a patient, when I view a body location it should be a clear indicator of a point on a body-location picture.
```
SELECT bodyphoto.photo, record.bodyx, record.bodyy
FROM record LEFT OUTER JOIN bodyphoto ON record.bodyphoto = bodyphoto.id
WHERE record.id = $recordid
```
