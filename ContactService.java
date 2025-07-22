package com.nextgen.contactmanager.Service;

import com.nextgen.contactmanager.DTO.ContactDTO;
import com.nextgen.contactmanager.Model.Contact;
import com.nextgen.contactmanager.Model.MyUser;
import com.nextgen.contactmanager.Repository.ContactRepository;
import com.nextgen.contactmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository myUserRepository;

    @Transactional
    public List<ContactDTO> getContactsForUser(String userEmail) {
        MyUser user = myUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));

        // Force the contacts collection to be loaded (initialize the lazy-loaded collection)
        user.getContacts().size();  // This ensures contacts are loaded before returning

        return user.getContacts().stream()
                .map(this::convertToContactDTO)  // Map each Contact to ContactDTO
                .collect(Collectors.toList());
    }

    // Map Contact to ContactDTO
    private ContactDTO convertToContactDTO(Contact contact) {
        return new ContactDTO(contact.getContactId(),contact.getName(), contact.getPhoneNumber(), contact.getEmailId());
    }

    // Add a new contact
    public Contact addContact(Contact contact) {
        try {
            return contactRepository.save(contact);
        } catch (Exception e) {
            logger.error("Error saving contact: {}", contact, e);
            throw new RuntimeException("Error saving contact.");
        }
    }

    @Transactional
    public List<ContactDTO> searchContactsByNameAndEmail(String name, String userEmail) {
        MyUser user = myUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));

        List<Contact> contacts = contactRepository.findByUserAndNameContainingIgnoreCase(userEmail, name);
        return contacts.stream()
                .map(this::convertToContactDTO)
                .collect(Collectors.toList());
    }

    // Add a contact for a specific user
    public ContactDTO addContactForUser(String userEmail, Contact contact) {
        try {
            MyUser user = myUserRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));
            contact.setUser(user);  // Associate the user with the contact
            Contact savedContact = contactRepository.save(contact);
            return convertToContactDTO(savedContact);  // Return the saved contact as DTO
        } catch (Exception e) {
            logger.error("Error adding contact for user: {}", userEmail, e);
            throw new RuntimeException("Error adding contact for user.");
        }
    }
    public ContactDTO updateContact(Contact contact) {
        // logic to update the contact in the database
        Contact updatedContact = contactRepository.save(contact);
        return convertToContactDTO(updatedContact);
    }
    public void deleteContact(Long id, String userEmail) {
        // Ensure the contact belongs to the user before deleting
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contactRepository.delete(contact);
    }

}
