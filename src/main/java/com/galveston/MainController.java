package com.galveston;

import com.galveston.controller.AdminController;
import com.galveston.controller.EventController;
import com.galveston.controller.UserController;
import com.galveston.dao.Login;
import com.galveston.entities.Event;
import com.galveston.entities.VolunteerRequest;
import com.galveston.error.GenericException;
import com.galveston.security.SessionHolder;
import com.galveston.util.Alert;
import com.galveston.util.Checker;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController extends Checker{

    @FXML TextField fName;
    @FXML TextField mName;
    @FXML TextField lName;
    @FXML TextField user;
    @FXML TextField pass;
    @FXML TextField cPass;
    @FXML TextField address;
    @FXML TextField city;
    @FXML TextField state;
    @FXML TextField zip;
    @FXML TextField phone;
    @FXML TextField email;
    @FXML Button create;
    @FXML Label createOutPut;
    @FXML Label successOutPut;
    @FXML TextField loginUser;
    @FXML TextField loginPass;
    @FXML Label loginLabel;
    @FXML Tab eventTab;
    @FXML Tab rewardTab;
    @FXML Button logout;
    @FXML Label status;
    @FXML TabPane mainTab;

    @FXML TableView<Event> evTableView;
    @FXML TableColumn<Event, Long> evTableId;
    @FXML TableColumn<Event, String> evTableCategory;
    @FXML TableColumn<Event, String> evTableName;
    @FXML TableColumn<Event, String> evTableLevel;
    @FXML TableColumn<Event, Integer> evTablePoint;
    @FXML TableColumn<Event, String> evTableDate;
    @FXML TableColumn<Event, String> evTableTime;
    @FXML TableColumn evAction;

    @FXML Tab adminTab;
    @FXML TableView<VolunteerRequest> adminTable;
    @FXML TableColumn<VolunteerRequest,String> adminEvents;
    @FXML TableColumn<VolunteerRequest,String> adminVolunteers;
    @FXML TableColumn adminAction;

    @FXML
    public void createUser(){
        createOutPut.setText(null);successOutPut.setText(null);
        UserController userController = new UserController();
        String response = userController.createUser(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
        if(!response.equals("You are all set."))createOutPut.setText(response);
        else{
            setNull(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
            successOutPut.setText(response);
        }
    }

    @FXML
    public void login() throws GenericException {
        if(SessionHolder.getSession().session)loginLabel.setText("Please First logout!");
        else {
            Login login = new Login();
            if (!login.userAuthentication(loginUser.getText(), loginPass.getText()))
                loginLabel.setText("Invalid User or Password!");
            else {
                Alert.infoAlert("SignIn","Welcome "+whoIsIt(SessionHolder.getSession().userId).getFirstName()+", "+ whoIsIt(SessionHolder.getSession().userId).getLastName());
                setNull(loginPass,loginUser);

                loginLabel.setText(null);createOutPut.setText(null);
                logout.setDisable(false);

                if(SessionHolder.getSession().role.equals("admin")){
                    status.setText(nameAndPoint(SessionHolder.getSession().userId));
                    unlockTab(adminTab);
                    AdminController.adminController(adminTable,adminEvents,adminVolunteers,adminAction);
                    adminTab.getTabPane().getSelectionModel().selectLast();

                }else {
                    status.setText(nameAndPoint(SessionHolder.getSession().userId));
                    unlockTab(rewardTab, eventTab);
                    eventTab.getTabPane().getSelectionModel().selectNext();
                    EventController.eventViewController(evTableView, evTableId, evTableCategory, evTableName, evTableLevel, evTablePoint, evTableDate, evTableTime, evAction);
                }
            }
        }
    }

    @FXML
    public void logout(){
        loginLabel.setText(null);createOutPut.setText(null);successOutPut.setText(null);
        if(isOnline()){
            kicOut();
            lockTab(rewardTab,eventTab,adminTab);
            eventTab.getTabPane().getSelectionModel().selectFirst();
            logout.setDisable(true);
            status.setText(null);
        }


    }

    @FXML
    public void closeWindow(){
        System.exit(0);
    }


}
