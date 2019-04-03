package com.galveston.security;

import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;

import java.util.Map;

public class Validator extends ApprovalImpl {

    private boolean auth = false;
    private String role = "volunteer";
    private Long userId= null;

    public boolean userAuthentication(final String user, final String password){

        if(user.toLowerCase().equals("admin")&&password.equals("password")){
            auth = true;
            role = "admin";
        }else {
            for (Map.Entry<Long, User> map : RunTimeObjectHolder.getInstance().users.entrySet()) {
                if (map.getValue().getUserName().equals(user) || map.getValue().getEmail().equals(user)) {
                    if (map.getValue().getPassword().equals(password)) {
                        auth = true;
                        userId = map.getKey();
                        break;
                    }
                }
            }
        }
        if(auth==true){
            SessionHolder.getSession().session = true;
            SessionHolder.getSession().userId = userId;
            SessionHolder.getSession().role = role;
        }
        return auth;
    }


    public void kicOut(){
        SessionHolder.getSession().userId = null;
        SessionHolder.getSession().role = null;
        SessionHolder.getSession().session = false;
    }




}
