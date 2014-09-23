package com.dps.css.service.vo;

import java.io.Serializable;
import java.util.Date;

public class DPService implements Serializable {
	
	/**  */
	private static final long serialVersionUID = 1L;

	private long id;

	private String title;

	private int type;

	private int status;

	private Date createDate;

	private Date updateDate;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return "";
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isPay() {
		return false;
	}

	public String getServiceItemId4Selling() {
		return null;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPrice4ServiceDate(Date serviceDate) {
		return 0;
	}

	public int getTotalAmount4ServiceDate(Date serviceDate) {
		return 0;
	}
}
