package com.nextgen.contactmanager.Repository;

import com.nextgen.contactmanager.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUserEmail(String userEmail);  // Find contacts by user email

    @Query("SELECT c FROM Contact c WHERE c.user.email = :userEmail AND LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Contact> findByUserAndNameContainingIgnoreCase(@Param("userEmail") String userEmail, @Param("name") String name);
}
