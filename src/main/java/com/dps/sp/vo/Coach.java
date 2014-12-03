package com.dps.sp.vo;

import java.util.Date;

public class Coach {
	private long id;

	private String name;

	private int type;

	private int level;

	private String phone;

	private int age;

	private Date birthday;

	private String address;


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

	public String getPhone(){
		return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public int getAge(){
		return this.age;
	}

	public void setAge(int age){
		this.age = age;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

}
