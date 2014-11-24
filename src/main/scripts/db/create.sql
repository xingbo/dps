-- Initialize create database tables


-- ------------------------------------------------------
--  User relative tables
-- ------------------------------------------------------

-- TABLE: USER
CREATE TABLE user
(
  id INT UNSIGNED AUTO_INCREMENT,
  loginname VARCHAR(64),
  nickname VARCHAR(64),
  avatar VARCHAR(128),
  password VARCHAR(128),
  email VARCHAR(64),
  phone VARCHAR(24),
  type TINYINT,
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  activecode VARCHAR(64),
  isphonevalid TINYINT(1),
  isemailvalid TINYINT(1),
  CONSTRAINT user_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

-- TABLE: CONSUME_USER
CREATE TABLE consume_user
(
  userid INT UNSIGNED,
  truename VARCHAR(64),
  gender TINYINT(1),
  birth DATE,
  country VARCHAR(64),
  provnice VARCHAR(64),
  city VARCHAR(64),
  district VARCHAR(64),
  street VARCHAR(128),
  apartment VARCHAR(128),
  description VARCHAR(512),
  CONSTRAINT consu_user_fk_1 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COACH_USER
CREATE TABLE coach_user
(
  userid INT UNSIGNED,
  truename VARCHAR(64),
  gender TINYINT(1),
  birth DATE,
  country VARCHAR(64),
  provnice VARCHAR(64),
  city VARCHAR(64),
  district VARCHAR(64),
  street VARCHAR(128),
  apartment VARCHAR(128),
  description VARCHAR(512),
  idcard VARCHAR(128),
  photo VARCHAR(128),
  servicearea VARCHAR(255),
  status TINYINT,
  CONSTRAINT coach_user_fk_1 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COACH_CERTIFICATION
CREATE TABLE coach_certification
(
  userid INT UNSIGNED,
  sportid INT UNSIGNED,
  content VARCHAR(512),
  certification VARCHAR(255),
  createtime TIMESTAMP,
  verifycontent VARCHAR(512),
  verifytime TIMESTAMP,
  status TINYINT,
  CONSTRAINT cert_user_fk_1 FOREIGN KEY (userid) REFERENCES user(id),
  CONSTRAINT cert_sport_fk_1 FOREIGN KEY (sportid) REFERENCES sport_type(id)
) DEFAULT CHARSET = utf8;


-- TABLE: PICTURE
CREATE TABLE picture
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  path VARCHAR(128),
  createtime TIMESTAMP,
  status TINYINT,
  CONSTRAINT picture_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;


-- TABLE: PICTURE_REL
CREATE TABLE picture_rel
(
  picid BIGINT UNSIGNED,
  userid INT UNSIGNED,
  courseid BIGINT UNSIGNED,
  createtime TIMESTAMP,
  type TINYINT(1),
  CONSTRAINT picture_fk_1 FOREIGN KEY (picid) REFERENCES picture(id),
  CONSTRAINT picture_to_user_fk_2 FOREIGN KEY (userid) REFERENCES user(id),
  CONSTRAINT picture_to_course_fk_3 FOREIGN KEY (courseid) REFERENCES course(id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  SP/SPORT relative tables
-- ------------------------------------------------------

-- TABLE: SPORT_TYPE
CREATE TABLE sport_type
(
  id INT UNSIGNED AUTO_INCREMENT,
  sportname VARCHAR(64),
  sportdesc VARCHAR(512),
  favusernum INT UNSIGNED,
  favcoursenum INT UNSIGNED,
  CONSTRAINT sport_type_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;


-- TABLE: SPORT_USER_REL
CREATE TABLE SPORT_USER_REL
(
  sportid INT UNSIGNED,
  userid INT UNSIGNED,
  CONSTRAINT sport_fk_1 FOREIGN KEY (sportid) REFERENCES sport_type(id),
  CONSTRAINT user_fk_2 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;

-- TABLE: SP
CREATE TABLE sp
(
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(128),
  description VARCHAR(255),
  userid BIGINT UNSIGNED,
  status  TINYINT,
  createtime TIMESTAMP,
  verifytime TIMESTAMP,
  CONSTRAINT sp_pk PRIMARY KEY (id),
  CONSTRAINT sp_to_user_fk_1 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: SP_SPORT_REL
CREATE TABLE sp_sport_rel
(
  spid INT,
  sportid INT,
  CONSTRAINT sp_fk_1 FOREIGN KEY (spid) REFERENCES sp(id),
  CONSTRAINT sport_fk_2 FOREIGN KEY (sportid) REFERENCES sport_type(id)
) DEFAULT CHARSET = utf8;



-- ------------------------------------------------------
--  COURSE relative tables
-- ------------------------------------------------------

-- TABLE: COURSE
CREATE TABLE course
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  spid INT,
  stid INT,
  teacherid INT,
  coursename VARCHAR(255),
  coursedetail VARCHAR(512),
  courseaddress VARCHAR(255),
  contactphone VARCHAR(64),
  publishtime TIMESTAMP,
  enrollendtime TIMESTAMP,
  attendtime TIMESTAMP,
  minrequiredperson INT,
  maxallowedperson INT,
  actualperson INT,
  courseprice DECIMAL(10,2),
  discount DECIMAL(10,2),
  coursefans INT,
  courserank INT,
  CONSTRAINT course_pk PRIMARY KEY (id),
  CONSTRAINT course_to_servprovider_fk_1 FOREIGN KEY (spid) REFERENCES servprovider(id),
  CONSTRAINT course_to_servtype_fk_2 FOREIGN KEY (stid) REFERENCES servtype(id),
  CONSTRAINT course_to_coach_fk_3 FOREIGN KEY (teacherid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COURSE_USER
CREATE TABLE course_user
(
  courseid BIGINT UNSIGNED,
  studentid INT UNSIGNED,
  createtime TIMESTAMP,
  consumetime TIMESTAMP,
  evidence VARCHAR(255),
  CONSTRAINT course_user_pk PRIMARY KEY (courseid, studentid),
  CONSTRAINT course_user_fk_1 FOREIGN KEY (courseid) REFERENCES course(id),
  CONSTRAINT course_user_fk_2 FOREIGN KEY (studentid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COMMENT
CREATE TABLE comment
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  courseid BIGINT UNSIGNED,
  userid INT UNSIGNED,
  content VARCHAR(1024),
  score TINYINT,
  createtime TIMESTAMP,
  status TINYINT,
  CONSTRAINT comment_pk PRIMARY KEY (id),
  CONSTRAINT comment_to_course_fk_1 FOREIGN KEY (courseid) REFERENCES course(id),
  CONSTRAINT comment_to_user_fk_2 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: ATTENTION
CREATE TABLE attention
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  fanee INT UNSIGNED,   -- 关注者
  faner INT UNSIGNED,   -- 被关注者
  course BIGINT UNSIGNED,  -- 被关注课程
  type TINYINT(1),    -- 1表示关注教练，2表示关注课程
  CONSTRAINT attention_pk PRIMARY KEY (id),
  CONSTRAINT attention_to_user_fk_1 FOREIGN KEY (fanee) REFERENCES user(id),
  CONSTRAINT attention_to_user_fk_2 FOREIGN KEY (faner) REFERENCES user(id),
  CONSTRAINT attention_to_course_fk_3 FOREIGN KEY (course) REFERENCES course(id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  Miscellaneous relative tables
-- ------------------------------------------------------

-- TABLE: NOTICE
CREATE TABLE NOTICE
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  content VARCHAR(1024),
  createtime TIMESTAMP,
  status TINYINT,
  CONSTRAINT notice_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;


-- TABLE: NOTICE_USER
CREATE TABLE NOTICE_USER
(
  noticeid BIGINT UNSIGNED,
  userid BIGINT UNSIGNED,
  status TINYINT,
  CONSTRAINT notice_fk_1 FOREIGN KEY (noticeid) REFERENCES notice(id),
  CONSTRAINT notice_to_user_fk_2 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


-- TABLE: FEEDBACK
CREATE TABLE feedback
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  title VARCHAR(256),
  content VARCHAR(1024),
  createtime TIMESTAMP,
  userid BIGINT UNSIGNED,
  reply VARCHAR(1024),
  replytime TIMESTAMP,
  status TINYINT,
  CONSTRAINT feedback_pk PRIMARY KEY (id),
  CONSTRAINT feedback_to_user_fk_1 FOREIGN KEY (userid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;



-- ------------------------------------------------------
--  Order relative tables
-- ------------------------------------------------------

CREATE TABLE ledgerPayment
(
  transtrackingid VARCHAR(255),
  payerid INT UNSIGNED,
  payeeid INT UNSIGNED,
  entityreferenceid VARCHAR(255),
  amount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT payment_pk PRIMARY KEY (transtrackingid),
  CONSTRAINT payment_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES user(id),
  CONSTRAINT payment_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE ledgerRefund
(
  transtrackingid VARCHAR(255),
  payerid INT UNSIGNED,
  payeeid INT UNSIGNED,
  entityreferenceid VARCHAR(255),
  amount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT refund_pk PRIMARY KEY (transtrackingid),
  CONSTRAINT refund_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES user(id),
  CONSTRAINT refund_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE ledgerRelease
(
  transtrackingid VARCHAR(255),
  payerid INT UNSIGNED,
  payeeid INT UNSIGNED,
  entityreferenceid VARCHAR(255),
  amount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT release_pk PRIMARY KEY (transtrackingid),
  CONSTRAINT release_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES user(id),
  CONSTRAINT release_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE order
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  buyerid INT UNSIGNED,
  sellerid INT UNSIGNED,
  amount DECIMAL(10,2),
  refundamount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT order_pk PRIMARY KEY (id),
  CONSTRAINT order_to_buyer_fk_1 FOREIGN KEY (buyerid) REFERENCES user(id),
  CONSTRAINT order_to_seller_fk_2 FOREIGN KEY (sellerid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE orderitem
(
  orderid BIGINT UNSIGNED,
  itemid INT UNSIGNED,
  price DECIMAL(10,2),
  number INT UNSIGNED,
  amount DECIMAL(10,2),
  refundamount DECIMAL(10,2),
  CONSTRAINT order_fk_1 FOREIGN KEY (orderid) REFERENCES order(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE orderhist
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  buyerid INT UNSIGNED,
  sellerid INT UNSIGNED,
  amount DECIMAL(10,2),
  refundamount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT orderhist_pk PRIMARY KEY (id),
  CONSTRAINT orderhist_to_buyer_fk_1 FOREIGN KEY (buyerid) REFERENCES user(id),
  CONSTRAINT orderhist_to_seller_fk_2 FOREIGN KEY (sellerid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE orderitemhist
(
  orderid BIGINT UNSIGNED,
  itemid INT UNSIGNED,
  price DECIMAL(10,2),
  number INT UNSIGNED,
  amount DECIMAL(10,2),
  refundamount DECIMAL(10,2),
  CONSTRAINT order_fk_1 FOREIGN KEY (orderid) REFERENCES order(id)
) DEFAULT CHARSET = utf8;


COMMIT;