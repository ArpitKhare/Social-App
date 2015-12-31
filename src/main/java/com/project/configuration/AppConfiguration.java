package com.project.configuration;

import com.project.dao.*;
import com.project.implementation.FriendshipImplementation;
import com.project.implementation.OrganizationImplementation;
import com.project.implementation.PersonImplementation;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class AppConfiguration {

/*
 * Bean for Implementation
 */

    @Bean
    public PersonImplementation getPersonImplementation(){
        return new PersonImplementation();
    }

    @Bean
    public OrganizationImplementation getOrganizationImplementation(){
        return new OrganizationImplementation();
    }

    @Bean
    public FriendshipImplementation getFriendshipImplementation(){
        return new FriendshipImplementation();
    }

    /*
     * Bean For DAO
     */

    @Bean
    public InterfaceForPersons getPersonDao(){
        return new PersonDAO();
    }

    @Bean
    public InterfaceForOrganization getOrganizationDao(){
        return new OrganizationDAO();
    }

    @Bean
    public InterfaceForFriendship getFriendshipDao(){
        return new FriendshipDAO();
    }




    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        dataSource.setUrl("jdbc:mysql://amazonaws.com/connections");
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setInitialSize(2);
        dataSource.setMaxTotal(5);
        return dataSource;
    }

    /**
          * @return HibernateTemplate() This is bean creation method for
          *         HibernateTemplate.
          */
    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory());

    }
    /**
          * @return SessionFactory() This is bean creation method for SessionFactory.
          */
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.project.*");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        builder.addProperties(hibernateProperties);
        return builder.buildSessionFactory();
    }


    /**
          * @return HibernateTransactionManager() This is bean creation method for
          *         HibernateTransactionManager.
          */
    @Bean
    @Primary
    public HibernateTransactionManager hibTransMan() {
        return new HibernateTransactionManager(sessionFactory());
    }
}