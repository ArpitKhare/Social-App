package com.project.dao;

import com.project.entities.Organization;
import com.project.entities.Person;

import java.util.List;

/**
 * Created by arjunshukla on 11/4/15.
 */

public interface InterfaceForPersons {

    Person save(Person personObject);

    public void update(Person person);

    public void delete(Person person);

    public Person getPersonById(Integer personID);

    Person getPersonByEmail(String email);

    Organization getOrganizationById(Integer org_id);
}