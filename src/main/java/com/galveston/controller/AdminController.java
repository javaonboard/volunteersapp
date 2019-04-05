package com.galveston.controller;

import com.galveston.entities.Event;
import com.galveston.entities.User;
import com.galveston.entities.VolunteerRequest;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.repository.UserRepo;
import com.galveston.util.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminController {

    public static void adminController(TableView adminTable, TableColumn<VolunteerRequest, String> adminEvents, TableColumn<VolunteerRequest, String> adminVolunteers, TableColumn adminAction) {

        adminVolunteers.setCellValueFactory(new PropertyValueFactory<>("volunteer"));
        adminEvents.setCellValueFactory(new PropertyValueFactory<>("eventDetails"));

        adminAction.setCellFactory(EventActionButton.forTableColumn("Confirm",(VolunteerRequest v)-> {
            for(Map.Entry<Long,User> map : RunTimeObjectHolder.getInstance().users.entrySet()) {
                for (Event ev : map.getValue().getEvents()) {
                    if (v.getUserId().equals(map.getKey())) {
                        if (v.getEventId().equals(ev.getEventId())) {
                            if(ev.isConfirmed()){
                                Alert.warningAlert("Confirmed!","This Activity Already Confirmed");
                            }else {
                                ev.setConfirmed(true);
                                map.getValue().setPoints(map.getValue().getPoints()+ev.getPoint());
                                Alert.infoAlert("Thanks","Thanks for confirmation!");
                                List<User> users = new ArrayList<>();
                                RunTimeObjectHolder.getInstance().users.values().stream().forEach(u->users.add(u));
                                UserRepo.updateDb(users);
                            }
                        }
                    }
                }
            }

            return v;
        }));
        List<VolunteerRequest> vr = getAllRequests(RunTimeObjectHolder.getInstance().users);
        ObservableList<VolunteerRequest> requests = FXCollections.observableArrayList(vr);
        adminTable.setItems(requests);
    }

    //Create Pending Requests
    private static List<VolunteerRequest> getAllRequests(Map<Long, User> users) {
        List<VolunteerRequest> vr = new ArrayList<>();
        for (Map.Entry<Long, User> map : users.entrySet()) {

            for (Event ev : map.getValue().getEvents()) {

                //System.out.println(map.getKey()+" "+ev.isConfirmed());
                if (!ev.isConfirmed()) {
                    VolunteerRequest v = new VolunteerRequest(map.getKey(), ev.getEventId(),
                            map.getValue().getFirstName() + " " + map.getValue().getLastName(),
                            ev.getName() + " " + ev.getDate() + "-" + ev.getTime() + " " + ev.getLevel(), ev.getPoint());
                    vr.add(v);
                }
            }
        }
        return vr;
    }

}
