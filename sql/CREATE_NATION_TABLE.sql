CREATE TABLE nation (
  id char(3) NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  callingCode varchar(10) NULL
);

-- Data from http://www.nationsonline.org/oneworld/country_code_list.htm
INSERT INTO nation (id, name, callingCode)
VALUES
    ('GBR', 'United Kingdom', '44'),
		('USA', 'United State', '1'),
		('VNM', 'Vietname', '84'),
		('CHE', 'Switzerland', '756')
