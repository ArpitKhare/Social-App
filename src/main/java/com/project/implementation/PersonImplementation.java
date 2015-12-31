package com.project.implementation;

import com.project.dao.InterfaceForFriendship;
import com.project.dao.InterfaceForPersons;
import com.project.dto.Address;
import com.project.dto.OrganizationDTO;
import com.project.entities.Friendship;
import com.project.entities.Organization;
import org.neo4j.cypher.internal.compiler.v1_9.commands.expressions.Add;
import org.springframework.beans.factory.annotation.Autowired;


import com.project.dto.PersonDTO;
import com.project.entities.Person;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjunshukla on 11/4/15.
 */

public class PersonImplementation {

    @Autowired
    InterfaceForPersons personsDao;

    @Autowired
    InterfaceForFriendship friendshipDao;

    @Autowired
    FriendshipImplementation friendshipImplementation;


	 /*  Create Person*/

    public PersonDTO createPerson(PersonDTO personDTObject) {

        Person personObject = new Person();

        try { org.apache.commons.beanutils.BeanUtils.copyProperties(personObject, personDTObject);}
        catch (IllegalAccessException e) { e.printStackTrace(); }
        catch (InvocationTargetException e) { e.printStackTrace(); }


        personObject.setFirstname(personDTObject.getFirstname());
        personObject.setLastname(personDTObject.getLastname());
        personObject.setEmail(personDTObject.getEmail());
        personObject.setDescription(personDTObject.getDescription());

        personObject.setStreet(personDTObject.getAddress().getStreet());
        personObject.setCity(personDTObject.getAddress().getCity());
        personObject.setState(personDTObject.getAddress().getState());
        personObject.setZip(personDTObject.getAddress().getZip());

        // for org
        Organization organization = personsDao.getOrganizationById(personDTObject.getOrg_id());

        if(organization!=null)
        personObject.setOrganization(organization);

        personObject = personsDao.save(personObject);

        personDTObject.setPerson_id(personObject.getPerson_id());
        return personDTObject;
    }

    @Transactional
    public PersonDTO getPersonbyId(Integer personId)

    {
        PersonDTO personDTO = new PersonDTO();

        Person person = personsDao.getPersonById(personId);
        if(person!=null) {
            Address address = new Address();
            Organization organization = new Organization();

            personDTO.setPerson_id(person.getPerson_id());
            personDTO.setFirstname(person.getFirstname());
            personDTO.setLastname(person.getLastname());
            personDTO.setEmail(person.getEmail());
            personDTO.setDescription(person.getDescription());

            address.setStreet(person.getStreet());
            address.setCity(person.getCity());
            address.setState(person.getState());
            address.setZip(person.getZip());

            personDTO.setAddress(address);

            ArrayList<Friendship> friends = new ArrayList<Friendship>();
            friends = friendshipDao.getFriendsForId(person.getPerson_id());
            personDTO.setFriendship(friends);

            if (person.getOrganization() != null) {
                System.out.println("person has org");
                if (person.getOrganization().getOrg_id() != null)
                    personDTO.setOrg_id(person.getOrganization().getOrg_id());
                else
                    personDTO.setOrg_id(0);
            }
            return personDTO;
        } else
            return null;
    }


    public PersonDTO updatePerson(PersonDTO personDTOObject) {
        Person personObject = new Person();

        personObject = personsDao.getPersonByEmail(personDTOObject.getEmail());

        if (personObject != null && personObject.getPerson_id() == personDTOObject.getPerson_id()) {

            try {
                org.apache.commons.beanutils.BeanUtils.copyProperties(personObject, personDTOObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            personObject.setFirstname(personDTOObject.getFirstname());
            personObject.setLastname(personDTOObject.getLastname());
            personObject.setEmail(personDTOObject.getEmail());
            personObject.setDescription(personDTOObject.getDescription());

            personObject.setStreet(personDTOObject.getAddress().getStreet());
            personObject.setCity(personDTOObject.getAddress().getCity());
            personObject.setState(personDTOObject.getAddress().getState());
            personObject.setZip(personDTOObject.getAddress().getZip());


            ArrayList<Friendship> friends = new ArrayList<Friendship>();
            friends = friendshipDao.getFriendsForId(personObject.getPerson_id());
            personDTOObject.setFriendship(friends);


            // for org
            Organization organization = new Organization();

            organization = personsDao.getOrganizationById(personDTOObject.getOrg_id());

            if (organization != null)
                personObject.setOrganization(organization);


            personsDao.update(personObject);
            personObject = personsDao.getPersonByEmail(personDTOObject.getEmail());

            personDTOObject.setPerson_id(personObject.getPerson_id());
            return personDTOObject;
        }
        return null;
    }

    public PersonDTO deletePersonbyId(Integer person_id) {
        PersonDTO personDTOObject = getPersonbyId(person_id);

        if (personDTOObject != null) {
            Person personObject = new Person();
            personObject.setPerson_id(person_id);
            personObject.setFirstname(personDTOObject.getFirstname());
            personObject.setLastname(personDTOObject.getLastname());
            personObject.setEmail(personDTOObject.getEmail());
            personObject.setDescription(personDTOObject.getDescription());

            personObject.setStreet(personDTOObject.getAddress().getStreet());
            personObject.setCity(personDTOObject.getAddress().getCity());
            personObject.setState(personDTOObject.getAddress().getState());
            personObject.setZip(personDTOObject.getAddress().getZip());

            ArrayList<Integer> friends = friendshipDao.getFriendIdsForId(personObject.getPerson_id());

            for (int i = 0; i < friends.size(); i++) {
                friendshipImplementation.removeFriendship(person_id, friends.get(i));
            }
            personsDao.delete(personObject);
            return personDTOObject;
        }
        return null;
    }

}
