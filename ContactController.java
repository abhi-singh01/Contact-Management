package com.nextgen.contactmanager.Controller;

import com.nextgen.contactmanager.DTO.ContactDTO;
import com.nextgen.contactmanager.Service.ContactService;
import com.nextgen.contactmanager.Model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Get contacts for a user
    @GetMapping("/user/{email}")
    public ResponseEntity<List<ContactDTO>> getContactsForUser(@PathVariable String email) {
        try {
            List<ContactDTO> contacts = contactService.getContactsForUser(email);
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Add a new contact
    @PostMapping("/add")
    public ResponseEntity<ContactDTO> addContact(@RequestBody Contact contact) {
        try {
            ContactDTO contactDTO = contactService.addContactForUser(contact.getUser().getEmail(), contact);
            return ResponseEntity.ok(contactDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Search contacts by name and email
    @GetMapping("/search")
    public ResponseEntity<List<ContactDTO>> searchContactsByNameAndEmail(
            @RequestParam String name, @RequestParam String userEmail) {
        try {
            List<ContactDTO> contacts = contactService.searchContactsByNameAndEmail(name, userEmail);
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Update a contact
    @PutMapping("/update")
    public ResponseEntity<ContactDTO> updateContact(@RequestBody Contact contact) {
        try {
            ContactDTO contactDTO = contactService.updateContact(contact);
            return ResponseEntity.ok(contactDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Delete a contact
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id, @RequestParam String userEmail) {
        try {
            contactService.deleteContact(id, userEmail);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
