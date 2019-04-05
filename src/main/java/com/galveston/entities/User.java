package com.galveston.entities;

import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.util.Checker;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends Checker {

    private Long userId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String cPassword;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private Long points;
    private List<Event> events = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();
    private boolean isAdmin;

    public User(String fName,String mName,String lName,String user,String pass,String cPass,String address,String city,String state,String zip,String phone,String email){

        this.userId = RunTimeObjectHolder.getInstance().getLastID()+1;
        this.firstName = fName;
        this.middleName = mName==null?"":mName;
        this.lastName = lName;
        this.userName = user;
        this.password = pass;
        this.cPassword = cPass;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
        this.phoneNumber = phone;
        this.email = email;
        this.points = 0L;
        this.events = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.isAdmin = false;
    }

    public void setEvent(Event e){
        //RunTimeObjectHolder.getInstance().events.values().stream().forEach(System.out::println);
       // Event ev = new Event(e.getEventId(),e.getCategory(),e.getName(),temp.getLevel(),temp.getPoint(),temp.getDate(),temp.getTime(),false);
        events.add(e);
    }
    // need to change to copy temp create new
    public void setReward(Long rewardId){
        if(events.isEmpty()|| events==null) rewards = new ArrayList<>();
        rewards.add(RunTimeObjectHolder.getInstance().rewards.get(rewardId));
    }

}
