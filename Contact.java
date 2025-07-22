package com.nextgen.contactmanager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generate ID
    private Long contactId;
    private String name;
    private String phoneNumber;
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @ManyToOne
    @JoinColumn(name = "user_email") // Maps to the `email` field in `MyUser`
    private MyUser user;

    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }

    public void setEmail(String email) {
        if (this.user == null) {
            this.user = new MyUser();
        }
        this.user.setEmail(email);
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email_id='" + emailId + '\'' +
                '}';
    }
}
