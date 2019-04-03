package com.galveston;

import com.galveston.controller.UserController;
import com.galveston.dao.Login;
import com.galveston.security.SessionHolder;
import com.galveston.util.Checker;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController extends Checker {

    @FXML
    TextField fName;
    @FXML
    TextField mName;
    @FXML
    TextField lName;
    @FXML
    TextField user;
    @FXML
    TextField pass;
    @FXML
    TextField cPass;
    @FXML
    TextField address;
    @FXML
    TextField city;
    @FXML
    TextField state;
    @FXML
    TextField zip;
    @FXML
    TextField phone;
    @FXML
    TextField email;
    @FXML
    Button create;
    @FXML
    Label createOutPut;
    @FXML
    Label successOutPut;
    @FXML
    TextField loginUser;
    @FXML
    TextField loginPass;
    @FXML
    Label loginLabel;
    @FXML
    Tab eventTab;
    @FXML
    Tab rewardTab;
    @FXML
    Tab volunteerTab;
    @FXML
    Button logout;


    @FXML
    public void createUser(){
        createOutPut.setText(null);successOutPut.setText(null);
        UserController userController = new UserController();
        String response = userController.createUser(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
        if(!response.equals("You are all set."))createOutPut.setText(response);
        else{
            setNull(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
            successOutPut.setText(response);}
    }

    @FXML
    public void login(){
        if(SessionHolder.getSession().session)loginLabel.setText("Please First logout!");
        else {
            Login login = new Login();
            if (!login.userAuthentication(loginUser.getText(), loginPass.getText()))
                loginLabel.setText("Invalid User&Password!");
            else {

                setNull(loginPass,loginUser);
                unlockTab(rewardTab,eventTab);
                logout.setDisable(false);
                eventTab.getTabPane().getSelectionModel().selectNext();
            }
        }
    }

    @FXML
    public void logout(){
        if(isOnline()){
            kicOut();
            lockTab(rewardTab,eventTab,volunteerTab);
            eventTab.getTabPane().getSelectionModel().selectFirst();
            logout.setDisable(true);
        }


    }

    @FXML
    public void closeWindow(){
        System.exit(0);
    }

}
