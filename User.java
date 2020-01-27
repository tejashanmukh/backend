package com.app.pojos;

import javax.persistence.*;

@Entity
public class User
{

	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Integer age;
	private int otp;
	//private String address;
	private UserType userType;
	private UserGender userGender; 
	
	
	private DoctorDetails drDetails;
	private PatientDetails ptDetails;
	
	
	public User() 
	{			
		System.out.println("Inside User Ctor");
	}
	
	//Constructor for setting up newPassword	
	
	
	
	public User(Integer age, String name, String email, String password, String phone, UserType userType,
			UserGender userGender) {
		super();
		this.age = age;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
		this.userGender = userGender;
	}

	public User(Integer userId,String name, String email, Integer age, UserGender userGender) {
		super();
		 this.userId=userId;
		this.name = name;
		this.email = email;
		this.age = age;
		this.userGender = userGender;
	   
	}

	public User(Integer userId, String name, String email, String password, String phone, Integer age,
			UserType userType, UserGender userGender) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.age = age;
		this.userType = userType;
		this.userGender = userGender;
	}
	public User(String password, int otp) {
		super();
		this.password = password;
		this.otp = otp;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Enumerated(EnumType.STRING)
	public UserGender getUserGender() {
		return userGender;
	}
	public void setUserGender(UserGender userGender) {
		this.userGender = userGender;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", phone=" + phone + "]";
	}
  
	
	//dealing with Doctor details
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval =true)
	public DoctorDetails getDrDetails() {
		return drDetails;
	}

	public void setDrDetails(DoctorDetails drDetails) {
		this.drDetails = drDetails;
	}
	
	
	//helper method
	public void setDrDeatils(DoctorDetails drdt)
	{
		this.drDetails=drdt;
		drdt.setUser(this);
		
	}
	
	//dealing with patient details
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval =true)
	public PatientDetails getPtDetails() {
		return ptDetails;
	}

	public void setPtDetails(PatientDetails ptDetails) {
		this.ptDetails = ptDetails;
	}
	//helper Method
	public void setPtDeatails(PatientDetails ptdt)
	{
		this.ptDetails=ptdt;
		ptdt.setUser(this);	
	}

	//for Getting Otp######################################################
	@Transient
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
		

}
