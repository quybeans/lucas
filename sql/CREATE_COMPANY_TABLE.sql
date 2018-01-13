-- Table: yell_comnay
CREATE TABLE yell_company (
  id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  name varchar(255) NOT NULL,
  phone varchar(30) NULL,
  address varchar(100) NULL,
  location varchar(100) NULL,
  postal varchar(10) ,
  website varchar(255),
  avatar varchar(2000)
)

-- Table: Company
CREATE TABLE company (
    id int  IDENTITY(1,1) NOT NULL,
    categoryCodes nvarchar(80)  NOT NULL,
    ownerId varchar(50)  NOT NULL,
    name nvarchar(200)  NOT NULL,
    companyType varchar(20)  NULL,

    phone varchar(13)  NULL,
    email varchar(345)  NULL,
    description nvarchar(600)  NULL,
    address nvarchar(200)  NULL,
    thumbnail varchar(300)  NULL,
    picture varchar(300)  NULL,
    rating real  NULL,

    nationCode varchar(3)  NOT NULL,
    city varchar(40)  NULL,

    status varchar(15)  NOT NULL,

    CONSTRAINT Company_pk PRIMARY KEY  (id)
);
