package com.dps.user.domain;
/*
 * auth: nwei
 * desc: pin code for registration
 */
public class PinCode {
	private String pinCode;
	private String cellphone;
	private String status;//0: active 1: inactive
	private String creationDate;
	private String consumerDate;
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
