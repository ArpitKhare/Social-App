package com.project.implementation;

import com.project.dao.InterfaceForOrganization;
import com.project.dto.Address;
import com.project.dto.OrganizationDTO;
import com.project.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by arjunshukla on 11/7/15.
 */
public class OrganizationImplementation {
    @Autowired
    InterfaceForOrganization organizationDao;

	 /*  Create Organization*/
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTObject) {

        Organization organizationObject = new Organization();

        try { org.apache.commons.beanutils.BeanUtils.copyProperties(organizationObject, organizationDTObject);}
        catch (IllegalAccessException e) { e.printStackTrace(); }
        catch (InvocationTargetException e) { e.printStackTrace(); }

        organizationObject.setOrg_name(organizationDTObject.getOrg_name());
        organizationObject.setOrg_description(organizationDTObject.getOrg_description());

        organizationObject.setCity(organizationDTObject.getAddress().getCity());
        organizationObject.setStreet(organizationDTObject.getAddress().getStreet());
        organizationObject.setState(organizationDTObject.getAddress().getState());
        organizationObject.setZip(organizationDTObject.getAddress().getZip());

        organizationObject = organizationDao.save(organizationObject);
        organizationDTObject.setOrg_id(organizationObject.getOrg_id());
        return organizationDTObject;
    }

    @Transactional
    public OrganizationDTO getOrganizationbyId(Integer org_id) {
        OrganizationDTO organizationDTOObject = new OrganizationDTO();

        Organization organization = organizationDao.getOrganizationById(org_id);
        if (organization != null) {
            Address address = new Address();

            address.setStreet(organization.getStreet());
            address.setCity(organization.getCity());
            address.setState(organization.getState());
            address.setZip(organization.getZip());

            organizationDTOObject.setOrg_id(organization.getOrg_id());
            organizationDTOObject.setOrg_name(organization.getOrg_name());
            organizationDTOObject.setOrg_description(organization.getOrg_description());
            organizationDTOObject.setAddress(address);

            return organizationDTOObject;
        }else {
            return null;
        }
    }

    public OrganizationDTO updateOrganization(OrganizationDTO organizationDTOObject) {
        Organization organizationObject = organizationDao.getOrganizationById(organizationDTOObject.getOrg_id());
        if (organizationObject != null){

            try {
                org.apache.commons.beanutils.BeanUtils.copyProperties(organizationObject, organizationDTOObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            organizationObject.setOrg_name(organizationDTOObject.getOrg_name());
            organizationObject.setOrg_description(organizationDTOObject.getOrg_description());

            organizationObject.setCity(organizationDTOObject.getAddress().getCity());
            organizationObject.setStreet(organizationDTOObject.getAddress().getStreet());
            organizationObject.setState(organizationDTOObject.getAddress().getState());
            organizationObject.setZip(organizationDTOObject.getAddress().getZip());

            organizationDao.update(organizationObject);
            return organizationDTOObject;
        }
        return null;
    }

    public OrganizationDTO deleteOrganizationbyId(Integer org_id) {
        OrganizationDTO organizationDTOObject = getOrganizationbyId(org_id);

        Organization organizationObject = new Organization();

        try { org.apache.commons.beanutils.BeanUtils.copyProperties(organizationObject, organizationDTOObject);}
        catch (IllegalAccessException e) { e.printStackTrace(); }
        catch (InvocationTargetException e) { e.printStackTrace(); }
        organizationDao.delete(organizationObject);

        return organizationDTOObject;
    }
}