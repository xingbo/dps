package com.dps.cos.pay.vo;

import java.util.Date;

public class PayChannel {
	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 2;
	
	private long id;

	private String name;

	private int status;

	private int type;

	private String description;

	private Date createDate;

	private Date updateDate;


	public long getId(){
		return this.id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getStatus(){
		return this.status;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getType(){
		return this.type;
	}

	public void setType(int type){
		this.type = type;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Date getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getUpdateDate(){
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

}
