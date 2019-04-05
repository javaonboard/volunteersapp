package com.galveston.dao;

import com.galveston.entities.Event;
import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Registration {

    public static void persistUserInFileAndMemory(User user){
        RunTimeObjectHolder.getInstance().users.put(user.getUserId(),user);
        List<User> users = new ArrayList<>();
        RunTimeObjectHolder.getInstance().users.values().stream().forEach(u->users.add(u));
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(User u:users){
            Map obj1 = new LinkedHashMap();
            obj1.put("userId",u.getUserId());
            obj1.put("firstName",u.getFirstName());
            obj1.put("middleName",u.getMiddleName());
            obj1.put("lastName",u.getLastName());
            obj1.put("userName",u.getUserName());
            obj1.put("password",u.getPassword());
            obj1.put("cPassword",u.getCPassword());
            obj1.put("address",u.getAddress());
            obj1.put("city",u.getCity());
            obj1.put("state",u.getState());
            obj1.put("zipCode",u.getZipCode());
            obj1.put("phoneNumber",u.getPhoneNumber());
            obj1.put("email",u.getEmail());
            obj1.put("points",u.getPoints());
            List<Event> events = u.getEvents();
            JSONArray evArray = new JSONArray();
            for(Event e:events){
                Map evObj = new LinkedHashMap();
                evObj.put("eventId",e.getEventId());
                evObj.put("category",e.getCategory());
                evObj.put("name",e.getName());
                evObj.put("level",e.getLevel());
                evObj.put("point",e.getPoint());
                evObj.put("date",e.getDate());
                evObj.put("time",e.getTime());
                evObj.put("isConfirmed",e.isConfirmed());
                evArray.add(evObj);
            }
            obj1.put("events",evArray);
            obj1.put("rewards",u.getRewards());
            obj1.put("isAdmin",u.isAdmin());
            jsonArray.add(obj1);
        }

        obj.put("users",jsonArray);

        //System.out.println(obj.toJSONString());

        try (FileWriter file = new FileWriter("userDB.json",false)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
