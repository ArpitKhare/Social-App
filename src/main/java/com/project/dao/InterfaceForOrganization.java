package com.project.dao;

import com.project.entities.Organization;

import java.util.List;

public interface InterfaceForOrganization {
    Organization save(Organization organization);
    public void update(Organization organization);
    public void delete(Organization organization);
    public Organization getOrganizationById(Integer org_id);

    public List<Organization> getAllOrganization();


}
