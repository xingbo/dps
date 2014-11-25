-- DROP all dps database tables

DROP TABLE IF EXISTS picture_rel;
DROP TABLE IF EXISTS SPORT_USER_REL;
DROP TABLE IF EXISTS course_user_rel;
DROP TABLE IF EXISTS sp_sport_rel;
DROP TABLE IF EXISTS coach_cert_rel;

DROP TABLE IF EXISTS sp;
DROP TABLE IF EXISTS course;

DROP TABLE IF EXISTS assessment;
DROP TABLE IF EXISTS attention;
DROP TABLE IF EXISTS notice_user;

DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS feedback;

DROP TABLE IF EXISTS sport_type;
DROP TABLE IF EXISTS picture;

DROP TABLE IF EXISTS consume_user;
DROP TABLE IF EXISTS coach_user;
DROP TABLE IF EXISTS usr;


DROP TABLE IF EXISTS ledgerPayment;
DROP TABLE IF EXISTS ledgerRefund;
DROP TABLE IF EXISTS ledgerRelease;

DROP TABLE IF EXISTS orderitemhist;
DROP TABLE IF EXISTS ordershist;
DROP TABLE IF EXISTS orderitem;
DROP TABLE IF EXISTS orders;

DROP TABLE IF EXISTS sys_audit_log;
DROP TABLE IF EXISTS usr_activity_log;

DROP TABLE IF EXISTS user_role_rel;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS role;


COMMIT;
