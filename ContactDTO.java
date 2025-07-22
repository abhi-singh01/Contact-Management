package com.nextgen.contactmanager.DTO;

public class ContactDTO {
    private Long contactId;
    private String name;
    private String phoneNumber;
    private String emailId;

    // Constructor with contactId, name, and phoneNumber
    public ContactDTO(Long contactId, String name, String phoneNumber , String emailId) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    // Getter and Setter for contactId
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //Getter and Setter for email id
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
