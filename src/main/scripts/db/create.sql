-- Initialize create database tables


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
  createdate TIMESTAMP,
  updatedate TIMESTAMP,
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
  CONSTRAINT consu_user_fk_1 FOREIGN KEY (userid) REFERENCES USER(id)
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
  CONSTRAINT coach_user_fk_1 FOREIGN KEY (userid) REFERENCES USER(id)
) DEFAULT CHARSET = utf8;

-- TABLE: CERTIFICATION
CREATE TABLE certification
(
  userid INT UNSIGNED,
  sportid INT UNSIGNED,
  level VARCHAR(255),
  certification VARCHAR(255),
  CONSTRAINT cert_user_fk_1 FOREIGN KEY (userid) REFERENCES USER(id),
  CONSTRAINT cert_sport_fk_1 FOREIGN KEY (sport) REFERENCES SPORT_TYPE(id)
) DEFAULT CHARSET = utf8;

-- TABLE: SPORT_TYPE
CREATE TABLE sport_type
(
  id INT UNSIGNED AUTO_INCREMENT,
  sportname VARCHAR(64),
  sportdesc VARCHAR(512),
  CONSTRAINT sport_type_pk PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;



-- TABLE: SERVPROVIDER
CREATE TABLE servprovider
(
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(128),
  country VARCHAR(64),
  province VARCHAR(64),
  city VARCHAR(64),
  district VARCHAR(64),
  address VARCHAR(255),
  phone VARCHAR(64),
  advertisementimage VARCHAR(255),
  certificationimage VARCHAR(255),
  description VARCHAR(255),
  category TINYINT,
  status  TINYINT,
  createdate TIMESTAMP,
  updatedate TIMESTAMP,
  CONSTRAINT servprovider_pk PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- TABLE: USER_TO_SERVPROVIDER
CREATE TABLE user_to_servprovider
(
  id INT UNSIGNED AUTO_INCREMENT,
  uid INT,
  spid INT,
  isOwner TINYINT(1),
  status  TINYINT,
  createdate TIMESTAMP,
  updatedate TIMESTAMP,
  CONSTRAINT user_to_servprovider_pk PRIMARY KEY (id),
  CONSTRAINT user_to_servprovider_fk_1 FOREIGN KEY (uid) REFERENCES USER(id),
  CONSTRAINT user_to_servprovider_fk_2 FOREIGN KEY (spid) REFERENCES SERVPROVIDER(id)
) DEFAULT CHARSET=utf8;


-- TABLE: SERVTYPE
CREATE TABLE servtype
(
  id INT UNSIGNED AUTO_INCREMENT,
  servtypename VARCHAR(255),
  servtypedesc VARCHAR(255),
  CONSTRAINT servtype_pk PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- TABLE: SERVPROVIDER_TO_SERVTYPE
CREATE TABLE servprovider_to_servtype
(
  id INT UNSIGNED AUTO_INCREMENT,
  spid INT,
  stid INT,
  status TINYINT,
  createdate TIMESTAMP,
  updatedate TIMESTAMP,
  CONSTRAINT servprovider_to_servtype_pk PRIMARY KEY (id),
  CONSTRAINT servprovider_to_servtype_fk_1 FOREIGN KEY (spid) REFERENCES servprovider(id),
  CONSTRAINT servprovider_to_servtype_fk_2 FOREIGN KEY (stid) REFERENCES servtype(id)
) DEFAULT CHARSET=utf8;


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
  createdate TIMESTAMP,
  publishdate TIMESTAMP,
  enddate TIMESTAMP,
  offlinedate TIMESTAMP,
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

-- TABLE: BILL
CREATE TABLE bill
(
  bid BIGINT UNSIGNED AUTO_INCREMENT,
  buyerid INT UNSIGNED,
  sellerid INT UNSIGNED,
  courseid BIGINT UNSIGNED,
  createtime TIMESTAMP,
  paytime TIMESTAMP,
  number TINYINT,
  price DECIMAL(10,2),
  totalfee DECIMAL(10,2),
  status TINYINT,
  gatewayid BIGINT,
  CONSTRAINT bill_pk PRIMARY KEY (bid),
  CONSTRAINT bill_to_buyer_fk_1 FOREIGN KEY (buyerid) REFERENCES user(id),
  CONSTRAINT bill_to_seller_fk_2 FOREIGN KEY (sellerid) REFERENCES user(id),
  CONSTRAINT bill_to_course_fk_3 FOREIGN KEY (courseid) REFERENCES user(id)
) DEFAULT CHARSET = utf8;

-- TABLE: MESSAGE
CREATE TABLE message
(
  id BIGINT UNSIGNED AUTO_INCREMENT,
  srcid INT UNSIGNED,
  dstid INT UNSIGNED,
  message VARCHAR(1024),
  status TINYINT,
  isdelete TINYINT,
  CONSTRAINT user_message_pk PRIMARY KEY (id),
  CONSTRAINT user_message_fk_1 FOREIGN KEY (srcid) REFERENCES USER(id),
  CONSTRAINT user_message_fk_2 FOREIGN KEY (dstid) REFERENCES USER(id)
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
  faner INT UNSIGNED,
  fanee INT UNSIGNED,
  course BIGINT UNSIGNED,
  type TINYINT(1),    -- 1表示关注教练，2表示关注课程
  CONSTRAINT attention_pk PRIMARY KEY (id),
  CONSTRAINT attention_to_user_fk_1 FOREIGN KEY (faner) REFERENCES user(id),
  CONSTRAINT attention_to_user_fk_2 FOREIGN KEY (fanee) REFERENCES user(id),
  CONSTRAINT attention_to_course_fk_3 FOREIGN KEY (course) REFERENCES course(id)
) DEFAULT CHARSET = utf8;

COMMIT;