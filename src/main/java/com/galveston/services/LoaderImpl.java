package com.galveston.services;

import com.galveston.dao.Registration;
import com.galveston.entities.Event;
import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class LoaderImpl implements Loader{


    @Override
    public void eventsFireUpTimeLoader() {
        //Adding Initial Events in Running Time.
        Event ev1 = new Event(1001L, "Music", "Lady GAGA", "Silver", 10, "04/10/2019","10-14",false);
        Event ev2 = new Event(1002L, "Sport", "Football", "Gold", 15, "05/13/2019","14-18",false);
        Event ev3 = new Event(1003L, "Art", "Painting", "Bronze", 5, "04/21/2019","06-10",false);
        Event ev4 = new Event(1004L, "Technology", "Artificial Intelligence", "Silver", 10, "06/08/2019","10-16",false);
        Event ev5 = new Event(1005L, "Cars", "American muscle", "Gold", 15, "05/18/2019","14-18",false);
        RunTimeObjectHolder.getInstance().events.put(ev1.getEventId(),ev1);
        RunTimeObjectHolder.getInstance().events.put(ev2.getEventId(),ev2);
        RunTimeObjectHolder.getInstance().events.put(ev3.getEventId(),ev3);
        RunTimeObjectHolder.getInstance().events.put(ev4.getEventId(),ev4);
        RunTimeObjectHolder.getInstance().events.put(ev5.getEventId(),ev5);
    }


    //This method Responsible to Read the json file and parse the data as a json object and map it to Java Object.
    @Override
    public void userFireUpTimeLoader() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File("userDB.json");
        if(!file.exists())file.createNewFile();
        FileReader fr = new FileReader("userDB.json");
        if(!isEmpty(fr)) {
            JsonReader jsonReader = new JsonReader(new FileReader("userDB.json"));
            jsonReader.beginObject();
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                User user = new User();
                jsonReader.beginObject();
                jsonReader.nextName();
                    user.setUserId(jsonReader.nextLong());
                jsonReader.nextName();
                    user.setFirstName(jsonReader.nextString());
                jsonReader.nextName();
                    user.setMiddleName(jsonReader.nextString());
                jsonReader.nextName();
                    user.setLastName(jsonReader.nextString());
                jsonReader.nextName();
                    user.setUserName(jsonReader.nextString());
                jsonReader.nextName();
                    user.setPassword(jsonReader.nextString());
                jsonReader.nextName();
                    user.setCPassword(jsonReader.nextString());
                jsonReader.nextName();
                    user.setAddress(jsonReader.nextString());
                jsonReader.nextName();
                    user.setCity(jsonReader.nextString());
                jsonReader.nextName();
                    user.setState(jsonReader.nextString());
                jsonReader.nextName();
                    user.setZipCode(jsonReader.nextString());
                jsonReader.nextName();
                    user.setPhoneNumber(jsonReader.nextString());
                jsonReader.nextName();
                    user.setEmail(jsonReader.nextString());
                jsonReader.nextName();
                    user.setPoints(jsonReader.nextLong());
                jsonReader.nextName();
                jsonReader.beginArray();
                List<Event> events = new ArrayList<>();
                while (jsonReader.hasNext()){
                    Event event = new Event();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                        event.setEventId(jsonReader.nextLong());
                    jsonReader.nextName();
                        event.setCategory(jsonReader.nextString());
                    jsonReader.nextName();
                        event.setName(jsonReader.nextString());
                    jsonReader.nextName();
                        event.setLevel(jsonReader.nextString());
                    jsonReader.nextName();
                        event.setPoint(jsonReader.nextInt());
                    jsonReader.nextName();
                        event.setDate(jsonReader.nextString());
                    jsonReader.nextName();
                        event.setTime(jsonReader.nextString());
                    jsonReader.nextName();
                        event.setConfirmed(jsonReader.nextBoolean());
                        events.add(event);
                    jsonReader.endObject();

                }
                //need to change to object
                events.stream().forEach(e->user.setEvent(e));
                jsonReader.endArray();
                jsonReader.nextName();
                jsonReader.beginArray();
                List<Long> rewards = new ArrayList<>();
                //need to change from id to object.
                while (jsonReader.hasNext()) rewards.add(jsonReader.nextLong());
                    rewards.stream().forEach(r->user.setReward(r));
                jsonReader.endArray();
                jsonReader.nextName();
                user.setAdmin(jsonReader.nextBoolean());
                users.add(user);
                jsonReader.endObject();

            }
            //add all users from json file to factory.
            users.stream().forEach(u -> RunTimeObjectHolder.getInstance().users.put(u.getUserId(), u));
            //RunTimeObjectHolder.getInstance().users.entrySet().stream().forEach(o -> System.out.println(o.getValue()));

        }
    }

    @Override
    public void whoIsBoss(){
        User admin = new User("Dear","Boss","Boss","admin","password","password","Global",
                "world","world","12345","0000000000","admin@admin.com");
        admin.setAdmin(true);
        Registration.persistUserInFileAndMemory(admin);
    }


    boolean isEmpty(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        if (br.readLine() == null) {
            return true;
        }
        return false;
    }

}
