-- Table: company_category
CREATE TABLE company_category (
    id char(3)  NOT NULL,
    name nvarchar(50)  NOT NULL,
    description varchar(100) NOT NULL,

    CONSTRAINT CompanyCategory_pk PRIMARY KEY  (id)
);

-- Data from http://www.nationsonline.org/oneworld/country_code_list.htm
INSERT INTO company_category (id, name, description)
VALUES
    ('F&B', 'Food and Beverage', 'Drinking and food, non alcohol.'),
		('IT', 'Information technology', 'IT stuffs, guys with glasses.'),
		('EDU', 'Education', 'Teaching insitute, school ...'),
		('POR', 'Pornography', 'Adults stuff.')
