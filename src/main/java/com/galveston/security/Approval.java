package com.galveston.security;

import com.galveston.entities.User;
import com.galveston.error.GenericException;
import javafx.scene.control.Tab;

public interface Approval {

    boolean isOnline();
    String whatRole();
    User whoIsIt(Long id);
    String nameAndPoint(Long id) throws GenericException;
    void lockTab(Tab... tabs);
    void unlockTab(Tab... tabs);
}
