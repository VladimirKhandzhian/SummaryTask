CREATE DATABASE airport;

CREATE TABLE flights(
Id INT(11) PRIMARY KEY AUTO_INCREMENT,
Flight_number VARCHAR(45),
Name_of_flight VARCHAR(45) NOT NULL,
Departure VARCHAR(45) NOT NULL,
Destination VARCHAR(45) NOT NULL,
Departure_date DATETIME NOT NULL,
Destination_date DATETIME NOT NULL,
Id_airplane INT(11) NULL,
Empty_of_seats INT(11) NULL,
Price DOUBLE NULL,
Flight_status ENUM("Ïî ðàññïèñàíèþ", "Îæèäàåòñÿ", "Ïðèáûë", "Îòìåíåí") NOT NULL DEFAULT "Ïî ðàññïèñàíèþ",
Id_brigad INT(11) NULL,
FOREIGN KEY (Id_airplane) REFERENCES airplane(Id_airplane) ON DELETE RESTRICT);

CREATE TABLE workers(
Id_worker INT(11) PRIMARY KEY AUTO_INCREMENT,
First_name VARCHAR(45) NOT NULL,
Second_name VARCHAR(45) NOT NULL,
Phone_number VARCHAR(45) NOT NULL, 
Email VARCHAR(45) NOT NULL,
Id_status INT(11) NOT NULL,
Login VARCHAR(45) NOT NULL,
Password VARCHAR(45) NOT NULL,
Profession VARCHAR(45) NOT NULL,
FOREIGN KEY (Id_status) REFERENCES status(Id_status) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE status(
Id_status INT(11) PRIMARY KEY AUTO_INCREMENT,
Status_name VARCHAR(45) NOT NULL);

CREATE TABLE brigads(
Id_brigad INT(11),
Id_worker INT(11) UNIQUE,
PRIMARY KEY(Id_brigad, Id_worker),
FOREIGN KEY (Id_worker) REFERENCES workers(Id_worker) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE requests(
Id_request INT(11) PRIMARY KEY AUTO_INCREMENT,
TextOfRequest TEXT NOT NULL,
Id_worker INT(11) NULL,
Response ENUM("Âûïîëíåíà", "Îòêëîíåíà", "Íå ðàññìîòðåíà") DEFAULT "Íå ðàññìîòðåíà",
FOREIGN KEY (Id_worker) REFERENCES workers(Id_worker) ON DELETE SET NULL);

CREATE TABLE airplane(
Id_airplane INT(11) PRIMARY KEY AUTO_INCREMENT,
Model VARCHAR(45) NOT NULL,
Number_of_seats INT(11) NOT NULL);

CREATE TABLE passengers(
Id_passport VARCHAR(45) PRIMARY KEY,
First_name VARCHAR(45) NOT NULL,
Second_name VARCHAR(45) NOT NULL,
Email VARCHAR(45) NOT NULL,
Login VARCHAR(45) NOT NULL,
Password VARCHAR(45) NOT NULL,
Id_status INT(11) DEFAULT 4,
FOREIGN KEY (Id_status) REFERENCES status(Id_status) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE basket(
Id INT(11),
Id_ticket INT(11),
Id_passport VARCHAR(45),
SessionId VARCHAR(100) NULL,
PRIMARY KEY(Id, Id_ticket),
FOREIGN KEY (Id) REFERENCES flights(Id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Id_passport) REFERENCES passengers(Id_passport) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE orders(
Id INT(11) NOT NULL,
Name_of_flight VARCHAR(45) NULL,
Departure VARCHAR(45) NULL,
Destination VARCHAR(45) NULL,
Departure_date DATETIME NULL,
Destination_date DATETIME NULL,
Id_ticket INT(11) NOT NULL,
Id_passport VARCHAR(45) NULL,
First_name VARCHAR(45) NULL,
Second_name VARCHAR(45) NULL,
Email VARCHAR(45) NULL,
Price DOUBLE NOT NULL,
PRIMARY KEY(Id, Id_ticket),
FOREIGN KEY(Id_passport) REFERENCES passengers(Id_passport) ON UPDATE CASCADE);

DELIMITER |
CREATE PROCEDURE `getSumToPaid`(IN passportId1 VARCHAR(45), IN flightId1 INT(11), OUT sumToPaid DOUBLE)
BEGIN
SET sumToPaid = (SELECT SUM(price) 
FROM flights INNER JOIN basket
ON flights.Id = basket.Id
WHERE basket.Id_passport = passportId1 AND basket.Id = flightId1);
END;

DELIMITER |
CREATE TRIGGER delAirplane 
AFTER DELETE ON brigads
  FOR EACH ROW BEGIN
	IF((SELECT COUNT(Id_brigad) FROM brigads WHERE Id_brigad = OLD.Id_brigad) = 0) 
    THEN UPDATE flights SET Id_brigad = 0 WHERE Id_brigad = OLD.Id_brigad;
  END IF;
END;

