DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS address;

CREATE TABLE patient (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(60) NOT NULL,
  lastname VARCHAR(60) NOT NULL,
  status VARCHAR(8) DEFAULT NULL,
  contact_no VARCHAR(10) DEFAULT NULL
);

CREATE TABLE address (
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT,
  line1 VARCHAR(60) NOT NULL,
  line2 VARCHAR(60) NOT NULL,
  city VARCHAR(20) NOT NULL,
  country VARCHAR(2) NOT NULL,
  postal VARCHAR(8) DEFAULT NULL
);

ALTER TABLE address ADD FOREIGN KEY (patient_id) REFERENCES patient(id);

	
insert into patient values(10001,'Thomas', 'Edison', 'Active', '61287172');
insert into patient values(10002,'Albert', 'Einstein', 'Active', '61287110');
insert into patient values(10003,'Charles', 'Darwin', 'Active', '61287120');
insert into patient values(10004,'Stephen', 'Hawking', 'Active', '61287130');
insert into patient values(10005,'Nikola', 'Tesla', 'Active', '61287140');
insert into patient values(10006,'Michael', 'Faraday', 'Active', '61287150');
insert into patient values(10007,'James', 'Watson', 'Active', '61287160');
insert into patient values(10008,'Isaac', 'Newton', 'Active', '61287760');
insert into patient values(10009,'Gelileo', 'Galilei', 'Active', '61287860');
insert into patient values(10010,'Carl', 'Sagan', 'Active', '61281860');
insert into patient values(10011,'Leonardo', 'da Vinci', 'Inactive', '61281861');
insert into patient values(10012,'Alfred', 'Nobel', 'Inactive', '61281862');
insert into patient values(10013,'Alexander', 'Fleming', 'Inactive', '61281863');

insert into address values(10001, 10001, 'The White House', '1600 Pennsylvania Avenue', 'New Jersey', 'US', '11520');
insert into address values(10002, 10002, '19 Kingston Drive', 'Wellington, FL 33414', 'Lansing', 'BR', '01960');
insert into address values(10003, 10002, '14 East Peninsula Lane', '57 Rockcrest Drive', 'Florida', 'US', '32034');
insert into address values(10004, 10003, '32 Roosevelt Drive', 'Snellville, GA 30039', 'Paris', 'FR', '48910');

