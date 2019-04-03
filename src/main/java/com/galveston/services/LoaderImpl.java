package com.galveston.services;

import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class LoaderImpl implements Loader{


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
                List<Long> events = new ArrayList<>();
                while (jsonReader.hasNext()) events.add(jsonReader.nextLong());
                    user.setEvents(events);
                jsonReader.endArray();
                jsonReader.nextName();
                jsonReader.beginArray();
                List<Long> rewards = new ArrayList<>();
                while (jsonReader.hasNext()) rewards.add(jsonReader.nextLong());
                    user.setRewards(rewards);
                jsonReader.endArray();
                users.add(user);
                jsonReader.endObject();

            }
            //add all users from json file to factory.
            users.stream().forEach(u -> RunTimeObjectHolder.getInstance().users.put(u.getUserId(), u));
            //RunTimeObjectHolder.getInstance().users.entrySet().stream().forEach(o -> System.out.println(o.getValue()));

        }
    }

    boolean isEmpty(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        if (br.readLine() == null) {
            return true;
        }
        return false;
    }
}
