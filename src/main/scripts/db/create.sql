-- Initialize create database tables
-- total 27 tables


-- ------------------------------------------------------
--  User relative tables
-- ------------------------------------------------------

-- TABLE: USER
CREATE TABLE usr
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
  CONSTRAINT consu_user_fk_1 FOREIGN KEY (userid) REFERENCES usr(id)
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
  CONSTRAINT coach_user_fk_1 FOREIGN KEY (userid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


-- TABLE: PICTURE
CREATE TABLE picture
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  path VARCHAR(128),
  uploadtime TIMESTAMP,
  status TINYINT,
  CONSTRAINT picture_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  SP/SPORT relative tables
-- ------------------------------------------------------

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
  CONSTRAINT sp_to_user_fk_1 FOREIGN KEY (userid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


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
CREATE TABLE sport_user_rel
(
  sportid INT UNSIGNED,
  userid INT UNSIGNED,
  CONSTRAINT sport_fk_1 FOREIGN KEY (sportid) REFERENCES sport_type(id),
  CONSTRAINT user_fk_2 FOREIGN KEY (userid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COACH_CERT_REL
CREATE TABLE coach_cert_rel
(
  userid INT UNSIGNED,
  sportid INT UNSIGNED,
  content VARCHAR(512),
  certification VARCHAR(255),
  createtime TIMESTAMP,
  verifycontent VARCHAR(512),
  verifytime TIMESTAMP,
  status TINYINT,
  CONSTRAINT cert_user_fk_1 FOREIGN KEY (userid) REFERENCES usr(id),
  CONSTRAINT cert_sport_fk_1 FOREIGN KEY (sportid) REFERENCES sport_type(id)
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
  coachid INT,
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
  status TINYINT,
  coursefans INT,
  courserank INT,
  CONSTRAINT course_pk PRIMARY KEY (id),
  CONSTRAINT course_to_sp_fk_1 FOREIGN KEY (spid) REFERENCES sp(id),
  CONSTRAINT course_to_sport_type_fk_2 FOREIGN KEY (stid) REFERENCES sport_type(id),
  CONSTRAINT course_to_coach_fk_3 FOREIGN KEY (coachid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


-- TABLE: COURSE_USER_REL
CREATE TABLE course_user_rel
(
  courseid BIGINT UNSIGNED,
  buyerid INT UNSIGNED,
  orderid BIGINT UNSIGNED,
  registertime TIMESTAMP,
  paytime TIMESTAMP,
  consumetime TIMESTAMP,
  receipt VARCHAR(255),
  CONSTRAINT course_user_pk PRIMARY KEY (courseid, buyerid),
  CONSTRAINT course_fk_1 FOREIGN KEY (courseid) REFERENCES course(id),
  CONSTRAINT course_user_fk_1 FOREIGN KEY (buyerid) REFERENCES usr(id),
  CONSTRAINT course_order_fk_2 FOREIGN KEY (orderid) REFERENCES orders(id)
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
  CONSTRAINT picture_to_user_fk_2 FOREIGN KEY (userid) REFERENCES usr(id),
  CONSTRAINT picture_to_course_fk_3 FOREIGN KEY (courseid) REFERENCES course(id)
) DEFAULT CHARSET = utf8;


-- TABLE: ASSESSMENT
CREATE TABLE assessment
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
  CONSTRAINT comment_to_user_fk_2 FOREIGN KEY (userid) REFERENCES usr(id)
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
  CONSTRAINT attention_to_user_fk_1 FOREIGN KEY (fanee) REFERENCES usr(id),
  CONSTRAINT attention_to_user_fk_2 FOREIGN KEY (faner) REFERENCES usr(id),
  CONSTRAINT attention_to_course_fk_3 FOREIGN KEY (course) REFERENCES course(id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  Miscellaneous relative tables
-- ------------------------------------------------------

-- TABLE: NOTICE
CREATE TABLE notice
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  content VARCHAR(1024),
  createtime TIMESTAMP,
  status TINYINT,
  CONSTRAINT notice_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;


-- TABLE: NOTICE_USER
CREATE TABLE notice_user
(
  noticeid BIGINT UNSIGNED,
  userid INT UNSIGNED,
  status TINYINT,
  CONSTRAINT notice_fk_1 FOREIGN KEY (noticeid) REFERENCES notice(id),
  CONSTRAINT notice_to_user_fk_2 FOREIGN KEY (userid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


-- TABLE: FEEDBACK
CREATE TABLE feedback
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  title VARCHAR(256),
  content VARCHAR(1024),
  createtime TIMESTAMP,
  userid INT UNSIGNED,
  replyerid INT UNSIGNED,
  replycontent VARCHAR(1024),
  replytime TIMESTAMP,
  status TINYINT,
  CONSTRAINT feedback_pk PRIMARY KEY (id),
  CONSTRAINT feedback_to_user_fk_1 FOREIGN KEY (userid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  Server side authority management tables
-- ------------------------------------------------------

CREATE TABLE sys_user
(
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(255),
  description VARCHAR(255),
  email VARCHAR(64),
  mobile VARCHAR(24),
  status TINYINT(1),
  createtime TIMESTAMP,
  logintime TIMESTAMP,
  lastlogintime TIMESTAMP,
  lastlogouttime TIMESTAMP,
  CONSTRAINT sys_user_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

CREATE TABLE role
(
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(255),
  description VARCHAR(255),
  type TINYINT(1),
  visible TINYINT(1),
  editable TINYINT(1),
  CONSTRAINT role_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

CREATE TABLE user_role_rel
(
  userid INT UNSIGNED,
  roleid INT UNSIGNED,
  CONSTRAINT sys_user_fk_1 FOREIGN KEY (userid) REFERENCES sys_user(id),
  CONSTRAINT role_fk_2 FOREIGN KEY (roleid) REFERENCES role(id)
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
  CONSTRAINT payment_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES usr(id),
  CONSTRAINT payment_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES usr(id)
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
  CONSTRAINT refund_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES usr(id),
  CONSTRAINT refund_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES usr(id)
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
  CONSTRAINT release_to_payer_fk_1 FOREIGN KEY (payerid) REFERENCES usr(id),
  CONSTRAINT release_to_payee_fk_2 FOREIGN KEY (payeeid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE orders
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  trackingid VARCHAR(255),
  buyerid INT UNSIGNED,
  sellerid INT UNSIGNED,
  price DECIMAL(10,2),
  quantity TINYINT,
  amount DECIMAL(10,2),
  refundamount DECIMAL(10,2),
  status TINYINT,
  createtime TIMESTAMP,
  updatetime TIMESTAMP,
  CONSTRAINT order_pk PRIMARY KEY (id),
  CONSTRAINT order_to_buyer_fk_1 FOREIGN KEY (buyerid) REFERENCES usr(id),
  CONSTRAINT order_to_seller_fk_2 FOREIGN KEY (sellerid) REFERENCES usr(id)
) DEFAULT CHARSET = utf8;


CREATE TABLE orders_revision
(
  orderid BIGINT UNSIGNED,
  prestatus TINYINT,
  nextstatus TINYINT,
  opid INT,
  opaction VARCHAR(128),
  opdescription VARCHAR(255),
  createtime TIMESTAMP,
  CONSTRAINT order_fk_1 FOREIGN KEY (orderid) REFERENCES orders(id),
  CONSTRAINT sys_user_fk_2 FOREIGN KEY (opid) REFERENCES sys_user(id)
) DEFAULT CHARSET = utf8;


-- ------------------------------------------------------
--  System audit tables
-- ------------------------------------------------------

CREATE TABLE sys_audit_log
(
  userid INT UNSIGNED,
  username VARCHAR(255),
  subsystem VARCHAR(255),
  activity VARCHAR(255),
  keyword VARCHAR(255),
  description VARCHAR(2018)
) DEFAULT CHARSET = utf8;

CREATE TABLE usr_activity_log
(
  userid INT UNSIGNED,
  username VARCHAR(255),
  subsystem VARCHAR(255),
  activity VARCHAR(255),
  keyword VARCHAR(255),
  description VARCHAR(2018)
) DEFAULT CHARSET = utf8;

COMMIT;