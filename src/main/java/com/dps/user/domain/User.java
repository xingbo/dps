/*
 * Copyright (C) 2014, DPS software.
 *
 * This file is part of the dps software project. All rights reserved.
 */

package com.dps.user.domain;

import com.dps.common.enums.StatusEnum;
import com.dps.common.enums.UserTypeEnum;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Base class for all 3 kinds of User(common user/coach user/club&court user).
 *
 * Field activeCode will be initialized to "0" when a new user is created.
 * If user begins to validate phone/email, then a one-time token(a 6-8 digitals of number and character) will be
 * stored in activeCode and send to user via phone/email, and a given validation window(1 day) will be set to filed
 * lastUpdateTime. Only when the validation passes, the phone/email flag will be set to true and activeCode will be
 * reset to "0".
 *
 */
@Data
public class User {

	private int userId;

	private String loginName;

    private String password;

    private String nickName;

    private String avatar;

	private String email;

    private String phone;

    private UserTypeEnum type;

	private StatusEnum status;

    private String activeCode;

    private boolean isPhoneValid;

    private boolean isEmailValid;

    private Date createTime;

    private Date lastUpdateTime;

}
