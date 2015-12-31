package com.project.dao;

import com.project.entities.Friendship;
import com.project.entities.Person;

import java.util.ArrayList;

public interface InterfaceForFriendship {

    public void save(Friendship friend);

    boolean checkFriendshipStatus(Integer friend1, Integer friend2);

    Person getPersonById(Integer friend1);

    void remove(Friendship friendship);

    ArrayList<Friendship> getFriendsForId(Integer person_id);

    ArrayList<Integer> getFriendIdsForId(Integer person_id);

}
