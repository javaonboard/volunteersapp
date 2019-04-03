package com.galveston;

import com.galveston.controller.UserController;
import com.galveston.dao.Login;
import com.galveston.error.GenericException;
import com.galveston.security.SessionHolder;
import com.galveston.util.Checker;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


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
    Label Hello;
    @FXML
    TabPane mainTab;



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
    public void login() throws GenericException {
        if(SessionHolder.getSession().session)loginLabel.setText("Please First logout!");
        else {
            Login login = new Login();
            if (!login.userAuthentication(loginUser.getText(), loginPass.getText()))
                loginLabel.setText("Invalid User or Password!");
            else {
                infoAlert("SignIn","Welcome "+whoIsIt(SessionHolder.getSession().userId).getFirstName());
                setNull(loginPass,loginUser);
                unlockTab(rewardTab,eventTab);
                Hello.setText(nameAndPoint(SessionHolder.getSession().userId));
                loginLabel.setText(null);createOutPut.setText(null);
                logout.setDisable(false);
                eventTab.getTabPane().getSelectionModel().selectNext();
            }
        }
    }

    @FXML
    public void logout(){
        loginLabel.setText(null);createOutPut.setText(null);
        if(isOnline()){
            kicOut();
            lockTab(rewardTab,eventTab,volunteerTab);
            eventTab.getTabPane().getSelectionModel().selectFirst();
            logout.setDisable(true);Hello.setText(null);
        }


    }

    @FXML
    public void closeWindow(){
        System.exit(0);
    }

    private void infoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
