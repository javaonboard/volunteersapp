package com.galveston.controller;

import com.galveston.entities.Event;
import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.repository.UserRepo;
import com.galveston.security.SessionHolder;
import com.galveston.util.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class EventController {

    public static void eventViewController(TableView<Event> evTableView, TableColumn<Event,Long> evTableId, TableColumn<Event, String> evTableCategory,
                                           TableColumn<Event, String> evTableName, TableColumn<Event, String> evTableLevel, TableColumn<Event, Integer> evTablePoint,
                                           TableColumn<Event, String> evTableDate, TableColumn<Event, String> evTableTime, TableColumn evAction)
            {

                                evTableId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
                                evTableCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
                                evTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
                                evTableLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
                                evTablePoint.setCellValueFactory(new PropertyValueFactory<>("point"));
                                evTableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                                evTableTime.setCellValueFactory(new PropertyValueFactory<>("time"));

                                            evAction.setCellFactory(EventActionButton.forTableColumn("I'm In",(Event e)-> {
                                                if(!RunTimeObjectHolder.getInstance().users.get(SessionHolder.getSession().userId).getEvents()
                                                        .stream().noneMatch(ee->ee.getEventId().equals(e.getEventId()))) Alert.warningAlert("Duplicate","You Already Signed for This Event!");
                                                else {
                                                    try {
                                                        RunTimeObjectHolder.getInstance().users.get(SessionHolder.getSession().userId).setEvent(e);
                                                    } catch (Exception ex) {
                                                        Alert.infoAlert("oops!", "Something went wrong!");
                                                    }
                                                    Alert.infoAlert("Confirmation", "Congratulation You are In, After Admin review you will get your point!");
                                                    List<User> updatedUser = new ArrayList<>();
                                                    RunTimeObjectHolder.getInstance().users.values().stream().forEach(u -> updatedUser.add(u));
                                                    UserRepo.updateDb(updatedUser);
                                                }
                                                return e;
                                            }));
                                            ObservableList<Event> events = FXCollections.observableArrayList(RunTimeObjectHolder.getInstance().events.values());
                                            evTableView.setItems(events);


    }


}
