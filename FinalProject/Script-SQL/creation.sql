CREATE DATABASE SHOPPINGDB;

USE SHOPPINGDB;

CREATE TABLE USER(
    idUser char(15) not null,
    password char(10) not null,
    firstname char(15) not null,
    lastname char(15) not null,
    email char(20) not null,
    CONSTRAINT PK_user PRIMARY KEY (idUser)
);
CREATE TABLE PRODUCT(
    idProduct char(10) not null,
    name char(15) not null,
    category char(30) not null,
    value float not null,
    CONSTRAINT PK_product PRIMARY KEY(idProduct)
);
CREATE TABLE TICKET(
    idTicket integer not null AUTO_INCREMENT,
    idUser char(15) not null,
    dateBuy date not null,
    CONSTRAINT PK_ticket PRIMARY KEY(idTicket, idUser),
    CONSTRAINT FK_user_ticket FOREIGN KEY (idUser) REFERENCES USER(idUser)
);
CREATE TABLE TICKET_ROW(
    idTicket integer not null,
    idUser char(15) not null,
    idProduct char(10) not null,
    quantity integer not null,
    unitValue float not null,
    CONSTRAINT PK_ticket_row PRIMARY KEY(idTicket, idUser, idProduct),
    CONSTRAINT FK_ticket_ticket_row FOREIGN KEY(idTicket, idUser) REFERENCES TICKET(idTicket, idUser),
    CONSTRAINT FK_product_ticket_row FOREIGN KEY(idProduct) REFERENCES PRODUCT(idProduct)
);
CREATE TABLE CART(
    idUser char(15) not null,
    idProduct char(10) not null,
    quantity integer not null,
    CONSTRAINT PK_cart PRIMARY KEY(idUser, idProduct),
    CONSTRAINT FK_user_cart FOREIGN KEY(idUser) REFERENCES USER(idUser),
    CONSTRAINT FK_product_cart FOREIGN KEY(idProduct) REFERENCES PRODUCT(idProduct)
);