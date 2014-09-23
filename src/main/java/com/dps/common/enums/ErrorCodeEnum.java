package com.dps.common.enums;

public enum ErrorCodeEnum {
	USER_INTERNALERROR       ("1000", "userInternalError"),
	USER_INVALIDPASSWORD         ("1001", "invalidPassWord"),
	USER_EXISTED             ("1002", "existedUser"),
	USER_EXISTEDPHONENUM         ("1003", "existedPhoneNum"),
	USER_UNEXISTED           ("1004", "unexistedPhoneNum"),
	USER_INVALID_DATA		("1005", "invalid data"),
	//SP exception
	SP_INVALID_DATA			("2000", "invalid data"),
	SP_EXISTEDCOACH			("2001", "existedCoachSP"),
	SP_EXISTEDCOURT          ("2002", "existedcourtSP"),
	SP_STATUS_NOT_RIGHT		("2003", "SP status is not right"),
	SP_IDENTITYCARD_NAME_NULL ("2004", "identity card or true name is null"),
	SP_UPDATE_SINGLE_COURSE_EXCEPTION ("2101", "SP delete is not right"),
	//product exception
	PR_FIND_SINGLE_COURSE_EXCEPTION        ("3001", "find single course failed"),
	PR_DELETE_SINGLE_COURSE_EXCEPTION		("3002", "delete SingleCourse fail"),
	PR_UPDATE_SINGLE_COURSE_EXCEPTION		("3003", "update SingleCourse fail"),
	PR_ADD_SINGLE_COURSE_EXCEPTION			("3004", "create SingleCourse fail"),
	PR_NOT_SINGLE_COURSE					("3005", "not single course"),
	PR_STATUS_NOT_RIGHT_SINGLE_COURSE      ("3006", "single course status is not right");
	private ErrorCodeEnum(String id, String name){
		this.id = id;
		this.name = name;
	}
	private String id; 
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
