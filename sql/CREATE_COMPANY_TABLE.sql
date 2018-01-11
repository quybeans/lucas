CREATE TABLE company (
  id int  IDENTITY(1,1) NOT NULL,
  name varchar(255) NOT NULL,
  phone varchar(30) NULL,
  address varchar(100) NULL,
  location varchar(100) NULL,
  postal varchar(10) ,
  website varchar(255),
  avatar varchar(2000)
)
