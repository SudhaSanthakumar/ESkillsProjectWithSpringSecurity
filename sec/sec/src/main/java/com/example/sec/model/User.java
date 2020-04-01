package com.example.sec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import org.hibernate.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	@NotEmpty(message = "Please provide a name")
	private String userName;
	
	
	@NotEmpty(message = "Please provide a password")
	@Column(name="password", length=100)
//	@Length(min=4, max=10, message="Password should be between 5 - 10 charactes")
	private String password;
	
	
	@NotEmpty(message = "Please retype the password")
	
	@Column(name="confirm_password", length=100)
	private String confirmPassword;
	
	@ManyToMany
	@JoinTable(name="role_users", 
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Role> roles;
		
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Message> messages;


	public User() {
		super();
	}


	public User( String userName, String password, String confirmPassword) {
		super();
		
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		
	}


	public User(String userName, String password, String confirmPassword, Set<Role>  roles) {
		// TODO Auto-generated constructor stub
		
super();
		
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.roles=roles;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Set<Role> getRole() {
		return roles;
	}


	public void setRole(Set<Role> role) {
		this.roles = role;
	}


	public Set<Message> getMessages() {
		return messages;
	}


	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}


	@Override
	public int hashCode() {
		
		return this.userId;
	}


	@Override
	public boolean equals(Object obj) {
		
		
		if(obj==null || !(obj instanceof User) )
			return false;
		return this.userId==((User)obj).getUserId();
	}


	
	
	
	
	
	
}
