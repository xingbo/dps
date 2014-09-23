package com.dps.css.product.court.vo;

import com.dps.css.service.vo.DPService;


public class Court extends DPService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7050735755077857341L;

	private long id;

	private String code;

	private String name;

	private int type;

	private int level;

	private int num;

	private String area;

	private String linker;

	private String address;

	private String phone;

	private String description;


	public long getId(){
		return this.id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
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

	public int getNum(){
		return this.num;
	}

	public void setNum(int num){
		this.num = num;
	}

	public String getArea(){
		return this.area;
	}

	public void setArea(String area){
		this.area = area;
	}

	public String getLinker(){
		return this.linker;
	}

	public void setLinker(String linker){
		this.linker = linker;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description = description;
	}

}
