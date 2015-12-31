package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by arjunshukla on 11/8/15.
 */

@Entity
@Table(name = "friendship")
public class Friendship implements Serializable{

    @Id
    @Column
    private Integer friend1;

    @Id
    @Column
    private Integer friend2;

    public Integer getFriend1() {
        return friend1;
    }

    public void setFriend1(Integer friend1) {
        this.friend1 = friend1;
    }

    public Integer getFriend2() {
        return friend2;
    }

    public void setFriend2(Integer friend2) {
        this.friend2 = friend2;
    }
}
