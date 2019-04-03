package com.galveston.dao;

import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Registration {

    public static void persistUserInFileAndMemory(User user) throws Exception {
        RunTimeObjectHolder.getInstance().users.put(user.getUserId(),user);
        List<User> users = new ArrayList<>();
        RunTimeObjectHolder.getInstance().users.values().stream().forEach(u->users.add(u));
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(User u:users){
            JSONObject obj1 = new JSONObject();
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
            obj1.put("events",u.getEvents());
            obj1.put("rewards",u.getRewards());
            jsonArray.add(obj1);
        }

        obj.put("users",jsonArray);

        System.out.println(obj.toJSONString());

        try (FileWriter file = new FileWriter("userDB.json",false)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
