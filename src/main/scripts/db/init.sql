-- Import some test data

-- TABLE: USER
INSERT INTO userinfo VALUES (1, '张三', 'zhang', NULL, 'zhang@163.com', 1, '张大山', 'M', '1986-04-01', '网球,羽毛球',
                         NULL, NULL, NULL, NULL, 123456, '上海', 102, '张三的描述', '2014-09-04', '2014-09-04');
INSERT INTO userinfo VALUES (2, '硬派健身', 'yingpai', NULL, 'yingpai@163.com', 2, '硬派创始人', 'F', '2001-01-01', '我的爱好',
                         '网球', '国家一级', NULL, NULL, 123456, '上海', 102, '硬派健身的描述', '2014-09-04', '2014-09-04');
INSERT INTO userinfo VALUES (3, '源深体育馆', 'yuanshen', NULL, 'yuanshen@163.com', 3, '源深体育馆(真名)', NULL, '1970-01-01', NULL,
                         NULL, NULL, '公司法人姓名', '310501199001010054', 67898754, '上海', 102, '源深体育馆的描述', '2014-09-04', '2014-09-04');


-- TABLE: SERVPROVIDER
INSERT INTO servprovider VALUES (1, '提供场地', 1, '上海', '浦东新区', '浦东新区源深路芳甸路', '65423210', '浦东新区', '广告图片', '资质图片', '提供场地简介', 202, '2014-09-04', '2014-09-04');
INSERT INTO servprovider VALUES (2, '提供俱乐部', 2, '上海', '杨浦区', '杨浦区新华路', '12357894', '杨浦区', '广告图片', '资质图片', '提供俱乐部简介', 203, '2014-09-04', '2014-09-04');
INSERT INTO servprovider VALUES (3, '提供教练', 3, '上海', '南汇区', '南汇区大华路', '65423210', '南汇区', '广告图片', '资质图片', '提供教练简介', 206, '2014-09-04', '2014-09-04');


-- TABLE: USER_TO_SERVPROVIDER
INSERT INTO user_to_servprovider VALUES (1, 2, 3, 1, 102, '2014-09-04', '2014-09-04');
INSERT INTO user_to_servprovider VALUES (2, 3, 1, 1, 102, '2014-09-04', '2014-09-04');
INSERT INTO user_to_servprovider VALUES (3, 3, 2, 1, 102, '2014-09-04', '2014-09-04');


-- TABLE: SERVICETYPE
INSERT INTO servtype VALUES (1, '网球', '网球描述');
INSERT INTO servtype VALUES (2, '羽毛球', '羽毛球描述');
INSERT INTO servtype VALUES (3, '健身', '健身描述');


-- TABLE: SERVPROVIDER_TO_SERVTYPE
INSERT INTO servprovider_to_servtype VALUES (1, 1, 1, 102, '2014-09-04', '2014-09-04');
INSERT INTO servprovider_to_servtype VALUES (2, 1, 2, 102, '2014-09-04', '2014-09-04');
INSERT INTO servprovider_to_servtype VALUES (3, 3, 3, 102, '2014-09-04', '2014-09-04');



-- only for check result
SELECT * FROM userinfo;
SELECT * FROM servprovider;
SELECT * FROM user_to_servprovider;
SELECT * FROM servtype;
SELECT * FROM servprovider_to_servtype;

