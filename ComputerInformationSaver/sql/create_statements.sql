------------------------------------------------------
-- Create the main tables of this database
------------------------------------------------------

-------------------------------------------------
-- Create table Computer (COM)
-------------------------------------------------

CREATE TABLE COMPUTER (
	COM_ID INTEGER PRIMARY KEY NOT NULL,
	COM_NAME VARCHAR(65) NOT NULL,
	COM_VERSION INTEGER NOT NULL DEFAULT 1,
	COM_DELETED SMALLINT NOT NULL DEFAULT 0
);
	
	
-------------------------------------------------
-- Create table Network_Interface_Card (NIC)
-------------------------------------------------

CREATE TABLE NETWORK_INTERFACE_CARD (
	NIC_ID INTEGER PRIMARY KEY NOT NULL,
	NIC_COM_ID INTEGER NOT NULL,
	NIC_MAC_ADDRESS CHAR(17) NOT NULL,
	NIC_IP_ADDRESS VARCHAR(15) NOT NULL,
	NIC_SUBNET_MASK VARCHAR(15) NOT NULL DEFAULT '255.255.255.0',
	NIC_DNS VARCHAR(15) NOT NULL,
	NIC_GATEWAY VARCHAR(15) NOT NULL,
	NIC_DOMAIN VARCHAR(24) NOT NULL,
	NIC_VERSION INTEGER NOT NULL DEFAULT 1,
	NIC_DELETED SMALLINT NOT NULL DEFAULT 0,
	FOREIGN KEY(NIC_COM_ID) REFERENCES COMPUTER(COM_ID)
);


-------------------------------------------------
-- Create table Operating_System (OSY)
-------------------------------------------------

CREATE TABLE OPERATING_SYSTEM (
	OSY_ID INTEGER PRIMARY KEY NOT NULL,
	OSY_NAME VARCHAR(50) NOT NULL,
	OSY_DESCRIPTION VARCHAR(255),
	OSY_VERSION INTEGER NOT NULL DEFAULT 1,
	OSY_DELETED SMALLINT NOT NULL DEFAULT 0
);


-------------------------------------------------
-- Create table Software (SOF)
-------------------------------------------------

CREATE TABLE SOFTWARE (
	SOF_ID INTEGER PRIMARY KEY NOT NULL,
	SOF_NAME VARCHAR(50) NOT NULL,
	SOF_DESCRIPTION VARCHAR(255),
	SOF_VERSION_NUMBER VARCHAR(20) NOT NULL,
	SOF_VERSION INTEGER NOT NULL DEFAULT 1,
	SOF_DELETED SMALLINT NOT NULL DEFAULT 0
);


------------------------------------------------------
-- Create the mapping tables between the main tables
------------------------------------------------------

-------------------------------------------------
-- Create table Mapping_Computer_Operating_System (MCO)
-------------------------------------------------

CREATE TABLE MAPPING_COMPUTER_OPERATING_SYSTEM (
	MCO_ID INTEGER PRIMARY KEY NOT NULL,
	MCO_COM_ID INTEGER NOT NULL,
	MCO_OSY_ID INTEGER NOT NULL,
	MCO_DELETED SMALLINT NOT NULL DEFAULT 0,
	FOREIGN KEY(MCO_COM_ID) REFERENCES COMPUTER(COM_ID),
	FOREIGN KEY(MCO_OSY_ID) REFERENCES OPERATING_SYSTEM(OSY_ID)
);


-------------------------------------------------
-- Create table Mapping_Computer_Software (MCS)
-------------------------------------------------

CREATE TABLE MAPPING_COMPUTER_SOFTWARE (
	MCS_ID INTEGER PRIMARY KEY NOT NULL,
	MCS_COM_ID INTEGER NOT NULL,
	MCS_SOF_ID INTEGER NOT NULL,
	MCS_DELETED SMALLINT NOT NULL DEFAULT 0,
	FOREIGN KEY(MCS_COM_ID) REFERENCES COMPUTER(COM_ID),
	FOREIGN KEY(MCS_SOF_ID) REFERENCES SOFTWARE(SOF_ID)
);
