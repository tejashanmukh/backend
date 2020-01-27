package com.app.pojos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
	

@Entity
public class Medical 
{
	
private Integer shopid;
private String medicalshopname;
private String phno;
private String city;
private String ownername;
public String address;
public Medical() 
{
	System.out.println("in inside medical constructor");
}

public Medical(Integer shopid, String medicalshopname, String phno, String city, String ownername, String address) {
	super();
	this.shopid = shopid;
	this.medicalshopname = medicalshopname;
	this.phno = phno;
	this.city = city;
	this.ownername = ownername;
	this.address = address;
}

public Medical(String medicalshopname,String phno,String city,String ownername,String address)
{
  
   this.medicalshopname=medicalshopname;
   this.phno=phno;
   this.city=city;
   this.ownername=ownername;
   this.address=address;
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getShopid() {
	return shopid;
}
public void setShopid(Integer shopid) {
	this.shopid = shopid;
}
@NotNull
public String getMedicalshopname() {
	return medicalshopname;
}
public void setMedicalshopname(String medicalshopname) {
	this.medicalshopname = medicalshopname;
}
@NotNull
public String getPhno() {
	return phno;
}
public void setPhno(String phno) {
	this.phno = phno;
}
@NotNull
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
@NotNull
public String getOwnername() {
	return ownername;
}
public void setOwnername(String ownername) {
	this.ownername = ownername;
}
@NotNull
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@Override
public String toString() {
	return "Medical [shopid=" + shopid + ", medicalshopname=" + medicalshopname + ", phno=" + phno + ", city=" + city
			+ ", ownername=" + ownername + ", address=" + address + "]";
}

}
