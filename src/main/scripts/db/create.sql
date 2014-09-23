-- Initialize create database tables


-- TABLE: USERINFO
create table userinfo
(
  id bigint auto_increment,
  name varchar(128),
  login varchar(128),
  password varchar(128),
  email varchar(128),
  type int(5),
  truename varchar(256),
  gender varchar(20),
  birthday date,
  hobby varchar(256),
  major varchar(256),                   -- coach user only
  majorlevel varchar(256),              -- coach user only
  legalpersonname varchar(256),         -- clubcourt user only
  legalpersonidentity varchar(20),      -- clubcourt user only
  phone varchar(128),
  address varchar(128),
  status int(5),
  description varchar(128),
  createdate date,
  updatedate date,
  CONSTRAINT userinfo_pk PRIMARY KEY (id)
) default charset=utf8;


-- TABLE: SERVPROVIDER
create table servprovider
(
  id bigint auto_increment,
  name varchar(128),
  category int(5),
  city varchar(256),
  district varchar(256),
  address varchar(256),
  phone varchar(256),
  servicearea varchar(256),
  advertisementimage varchar(256),
  certificationimage varchar(256),
  description varchar(256),
  status  int(5),
  createdate date,
  updatedate date,
  CONSTRAINT servprovider_pk PRIMARY KEY (id)
) default charset=utf8;


-- TABLE: USER_TO_SERVPROVIDER
create table user_to_servprovider
(
  id bigint auto_increment,
  userid bigint(11),
  spid bigint(11),
  isOwner int(1),
  status  int(5),
  createdate date,
  updatedate date,
  CONSTRAINT user_to_servprovider_pk PRIMARY KEY (id),
  CONSTRAINT user_to_servprovider_fk_1 FOREIGN KEY (userid) REFERENCES USERINFO(id),
  CONSTRAINT user_to_servprovider_fk_2 FOREIGN KEY (spid) REFERENCES SERVPROVIDER(id)
) default charset=utf8;


-- TABLE: SERVTYPE
create table servtype
(
  id bigint auto_increment,
  servtypename varchar(256),
  servtypedesc varchar(256),
  CONSTRAINT servtype_pk PRIMARY KEY (id)
) default charset=utf8;


-- TABLE: SERVPROVIDER_TO_SERVTYPE
create table servprovider_to_servtype
(
  id bigint auto_increment,
  spid bigint(11),
  stid bigint(11),
  status int(5),
  createdate date,
  updatedate date,
  CONSTRAINT servprovider_to_servtype_pk PRIMARY KEY (id),
  CONSTRAINT servprovider_to_servtype_fk_1 FOREIGN KEY (spid) REFERENCES SERVPROVIDER(id),
  CONSTRAINT servprovider_to_servtype_fk_2 FOREIGN KEY (stid) REFERENCES SERVTYPE(id)
) default charset=utf8;
