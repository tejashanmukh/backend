package com.app.pojos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Laboratories 
{
private Integer labid;
private String name;
private String address;
private String city;
private String phonenum;
private String email;
public Laboratories() {
	System.out.println("in inside labaraoties constructor");
}
public Laboratories(Integer labid,String name,String address,String city,String phonenum,String email)
{
	this.labid=labid;
	this.name=name;
	this.address=address;
	this.city=city;
	this.email=email;
	this.phonenum=phonenum;
}
public Laboratories(String name,String address,String city,String phonenum,String email)
{
	
	this.name=name;
	this.address=address;
	this.city=city;
	this.email=email;
	this.phonenum=phonenum;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getLabid() {
	return labid;
}
public void setLabid(Integer labid) {
	this.labid = labid;
}
@NotNull
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@NotNull
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@NotNull
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
@NotNull
public String getPhonenum() {
	return phonenum;
}
public void setPhonenum(String phonenum) {
	this.phonenum = phonenum;
}
@NotNull
@Column(unique=true)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "Laboratories [labid=" + labid + ", name=" + name + ", address=" + address + ", city=" + city + ", phonenum="
			+ phonenum + ", email=" + email + "]";
}
}
