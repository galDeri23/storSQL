CREATE TABLE storeTable (
    SID int NOT NULL,
    StoreName varchar(255) NOT NULL,
    PRIMARY KEY (SID)
);

CREATE TABLE shippingCompanyTable (
	SCID int NOT NULL,
	SCName varchar(255) NOT NULL,
	contact varchar(255) NOT NULL,
	shippingfee DECIMAL(10, 2) NOT NULL,
	Contactmobile varchar(255) NOT NULL,
	PRIMARY KEY (SCID)
);

CREATE TABLE StoreShippingCompanyTable (
	SCID int NOT NULL,
	SID int NOT NULL,
	PRIMARY KEY (SCID, SID),
	FOREIGN KEY (SCID) REFERENCES shippingCompanyTable(SCID),
	FOREIGN KEY (SID) REFERENCES storeTable(SID)
);

CREATE TABLE ProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID)
);

CREATE TABLE WebsiteProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	DestCountry VARCHAR(255) NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID)
);

CREATE TABLE SoldThroughWebsiteProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	DestCountry VARCHAR(255),
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE SoldInStoreProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE SoldToWholesellersProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE StoreProductTable (
	PID VARCHAR(255) NOT NULL,
	SID int NOT NULL,
	PRIMARY KEY (PID, SID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID),
	FOREIGN KEY (SID) REFERENCES storeTable(SID)
);

CREATE TABLE OrdersProductTable (
	PID VARCHAR(255) NOT NULL,
	OID int NOT NULL,
	PRIMARY KEY (PID,OID ),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID),
	FOREIGN KEY (OID) REFERENCES OrdersTable(OID)
);

CREATE TABLE OrdersTable (
	OID int NOT NULL,
	quantity int NOT NULL,
	shipmentType VARCHAR(50) NOT NULL,
	PRIMARY KEY (OID),
	CHECK (shipmentType IN ('STANDARD', 'EXPRESS' , 'Without'))
);

CREATE TABLE ShippingOrderTable (
	SCID int NOT NULL,
	OID int NOT NULL,
	PRIMARY KEY (SCID, OID),
	FOREIGN KEY (SCID) REFERENCES shippingCompanyTable(SCID),
	FOREIGN KEY (OID) REFERENCES OrdersTable(OID)	
);

CREATE TABLE CustomerTable (
	CID int NOT NULL,
	CustomerName VARCHAR(255) NOT NULL,
	Mobile VARCHAR(255) NOT NULL,
	PRIMARY KEY (CID)
);

CREATE TABLE OrderCustomerTable (
	OID int NOT NULL,
	CID int NOT NULL,
	PRIMARY KEY (OID, CID),
	FOREIGN KEY (OID) REFERENCES OrdersTable(OID),	
	FOREIGN KEY (CID) REFERENCES CustomerTable(CID)
);

INSERT INTO customertable VALUES
(111,'amit','0528569874'),
(222,'ben','0548965234'),
(333,'gal','0545595578');

INSERT INTO storetable VALUES
(1,'GAB');

INSERT INTO productstable VALUES
('aaa','pants',20,50,10,10),
('bbb','gloves',10,20,15.5,50),
('ccc','hats',20,40,20.2,30);

INSERT INTO soldthroughwebsiteproductstable VALUES
('aaa','pants',20,50,'israel',10,10),
('bbb','gloves',10,20,'france',15.5,50),
('ccc','hats',20,40,'USA',20.2,30);

INSERT INTO storeproducttable VALUES
('aaa',1),
('bbb',1),
('ccc',1);

INSERT INTO productstable VALUES
('ddd','shirt',50,150,35,20),
('eee','socks',5,20,50.3,30),
('fff','shoes',100,200,60.5,10);

INSERT INTO soldinstoreproductstable VALUES
('ddd','shirt',50,150,35,20),
('eee','socks',5,20,50.3,30),
('fff','shoes',100,200,60.5,10);

INSERT INTO storeproducttable VALUES
('ddd',1),
('eee',1),
('fff',1);

INSERT INTO productstable VALUES
('ggg','polo',20,50,100,10),
('hhh','scarf',10,20,6.9,30),
('zzz','underwear',40,80,98.99,20);

INSERT INTO soldtowholesellersproductstable VALUES
('ggg','polo',20,50,100,10),
('hhh','scarf',10,20,6.9,30),
('zzz','underwear',40,80,98.99,20);

INSERT INTO storeproducttable VALUES
('ggg',1),
('hhh',1),
('zzz',1);

INSERT INTO orderstable VALUES
(11,1,'EXPRESS'),
(12,1,'STANDARD'),
(13,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(11,111),
(12,222),
(13,333);

INSERT INTO orderstable VALUES
(21,1,'EXPRESS'),
(22,1,'STANDARD'),
(23,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(21,111),
(22,222),
(23,333);

INSERT INTO orderstable VALUES
(31,1,'EXPRESS'),
(32,1,'STANDARD'),
(33,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(31,111),
(32,222),
(33,333);

INSERT INTO orderstable VALUES
(41,1,'EXPRESS'),
(42,1,'STANDARD'),
(43,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(41,111),
(42,222),
(43,333);

INSERT INTO orderstable VALUES
(51,1,'EXPRESS'),
(52,1,'STANDARD'),
(53,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(51,111),
(52,222),
(53,333);

INSERT INTO orderstable VALUES
(61,1,'EXPRESS'),
(62,1,'STANDARD'),
(63,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(61,111),
(62,222),
(63,333);

INSERT INTO orderstable VALUES
(71,1,'EXPRESS'),
(72,1,'STANDARD'),
(73,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(71,111),
(72,222),
(73,333);

INSERT INTO orderstable VALUES
(81,1,'EXPRESS'),
(82,1,'STANDARD'),
(83,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(81,111),
(82,222),
(83,333);

INSERT INTO orderstable VALUES
(91,1,'EXPRESS'),
(92,1,'STANDARD'),
(93,1,'EXPRESS');

INSERT INTO ordercustomertable VALUES
(91,111),
(92,222),
(93,333);

INSERT INTO shippingcompanytable VALUES
(1111,'DHL','ben',0,'052-4444444'),
(2222,'FDEX','amit',0,'052-2222222');

INSERT INTO ordersproducttable VALUES
('aaa',11),
('aaa',12),
('aaa',13);

INSERT INTO ordersproducttable VALUES
('bbb',21),
('bbb',22),
('bbb',23);

INSERT INTO ordersproducttable VALUES
('ccc',31),
('ccc',32),
('ccc',33);

INSERT INTO ordersproducttable VALUES
('ddd',41),
('ddd',42),
('ddd',43);

INSERT INTO ordersproducttable VALUES
('eee',51),
('eee',52),
('eee',53);

INSERT INTO ordersproducttable VALUES
('fff',61),
('fff',62),
('fff',63);

INSERT INTO ordersproducttable VALUES
('ggg',71),
('ggg',72),
('ggg',73);

INSERT INTO ordersproducttable VALUES
('hhh',81),
('hhh',82),
('hhh',83);

INSERT INTO ordersproducttable VALUES
('zzz',91),
('zzz',92),
('zzz',93);






