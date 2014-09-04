DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS ORDER_TABLE;


CREATE TABLE CUSTOMER (
       ID INT NOT NULL AUTO_INCREMENT
     , FIRSTNAME VARCHAR(60) NOT NULL
     , LASTNAME VARCHAR(60) NOT NULL
     , ADDRESS VARCHAR(60) NOT NULL
     , MOBPHONE INT NOT NULL DEFAULT 0
     , EMAIL VARCHAR(60) NOT NULL
     , SPECIALINSTRUCTIONS VARCHAR(60) NOT NULL
     , CREDIT INT NOT NULL DEFAULT 0
     , UNIQUE UQ_CONTACT_1 (FIRSTNAME, LASTNAME)
     , PRIMARY KEY (ID)
);

CREATE TABLE PRODUCT (
		ID INT NOT NULL AUTO_INCREMENT
	 , NAME VARCHAR(60) NOT NULL
     , COST DOUBLE NOT NULL
     , PRIMARY KEY (ID)
);


CREATE TABLE ORDER_TABLE (
		ID INT NOT NULL AUTO_INCREMENT
	 , CUSTOMER_ID INT NOT NULL
	 , PRODUCT_ID INT NOT NULL
     , PRIMARY KEY (ID, CUSTOMER_ID)
     , CONSTRAINT FK_ORDER_TABLE_1 FOREIGN KEY (CUSTOMER_ID)
                  REFERENCES CUSTOMER (ID) ON DELETE CASCADE
     , CONSTRAINT FK_ORDER_TABLE_2 FOREIGN KEY (PRODUCT_ID)
                  REFERENCES PRODUCT (ID) ON DELETE CASCADE
);



