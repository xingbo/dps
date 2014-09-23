package com.dps.msg.info.vo;

import java.util.Date;

public class Msg {
	private long id;

	private String title;

	private int type;

	private int level;

	private String content;

	private int status;

	private Date createDate;

	private Date updateDate;


	public long getId(){
		return this.id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getTitle(){
		return this.title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public int getType(){
		return this.type;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getLevel(){
		return this.level;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public String getContent(){
		return this.content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public int getStatus(){
		return this.status;
	}

	public void setStatus(int status){
		this.status = status;
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
