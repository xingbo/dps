-- delete all dps database tables


DROP TABLE IF EXISTS servprovider_to_servtype;
DROP TABLE IF EXISTS user_to_servprovider;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS attention;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS bill;
DROP TABLE IF EXISTS course_user;
DROP TABLE IF EXISTS coach_user;
DROP TABLE IF EXISTS consume_user;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS servtype;
DROP TABLE IF EXISTS servprovider;
DROP TABLE IF EXISTS user;

COMMIT;
