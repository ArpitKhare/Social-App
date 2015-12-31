package com.project.dto;

import com.project.entities.Friendship;

import java.util.List;

public class PersonDTO {

    private Integer person_id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private String description;
    private Integer org_id;
    private List<Friendship> Friendship;


    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public List<Friendship> getFriendship() {
        return Friendship;
    }

    public void setFriendship(List<Friendship> friendship) {
        Friendship = friendship;
    }
}