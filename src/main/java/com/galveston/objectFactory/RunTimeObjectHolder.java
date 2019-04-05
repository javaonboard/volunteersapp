package com.galveston.objectFactory;

import com.galveston.entities.Event;
import com.galveston.entities.Reward;
import com.galveston.entities.User;
import com.galveston.services.LoaderImpl;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*This Singelton Class is Factory to hold all created data as objects in Runtime Memory.*/
public class RunTimeObjectHolder extends LoaderImpl {

    private static RunTimeObjectHolder singleton_instance = null;

    public Map<Long, User> users;
    public Map<Long, Event> events;
    public Map<Long, Reward> rewards;

    private RunTimeObjectHolder(){
        users = new HashMap<>();
        events = new HashMap<>();
        rewards = new HashMap<>();
    }

    public static RunTimeObjectHolder getInstance(){
        if(singleton_instance == null) singleton_instance = new RunTimeObjectHolder();
        return singleton_instance;
    }

    public Long getLastID(){
        List<User> mapUsers = users.values().stream().collect(Collectors.toList());
        if(mapUsers.size()>0) {
            User user = mapUsers.get(mapUsers.size() - 1);
            return user.getUserId();
        }
        return 1000L;
    }

}
