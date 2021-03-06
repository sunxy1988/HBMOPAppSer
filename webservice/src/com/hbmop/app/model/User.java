package com.hbmop.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="users")
public class User {
	private String account_name;
	private String password;
	private String name;
	private String phone_num;
	private String email;
	private String role_num;
	private String city;
	private String department;
	private String rule_region;
	private Integer role_id;
	private boolean flag;//true代表正在使用，false代表已经删除的账户
	public String getRule_region() {
		return rule_region;
	}

	public void setRule_region(String rule_region) {
		this.rule_region = rule_region;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	@Id
	public String getAccount_name() {
		return account_name;
	}
			
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getPassword() {
		return password;
	}
		
	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole_num() {
		return role_num;
	}
	public void setRole_num(String role_num) {
		this.role_num = role_num;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


}
