package com.nextgen.contactmanager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class MyUser {
   private String firstname;
   private String lastname;
   @Id
   private String email;
   private String password;
   private String role="USER";
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)  // Eagerly load the contacts
	private List<Contact> contacts;

	// Getters and setters...
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
@Override
public String toString() {
	return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password
			+ ", role=" + role + "]";
}
   
}
