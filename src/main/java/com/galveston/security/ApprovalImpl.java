package com.galveston.security;

import com.galveston.entities.User;
import com.galveston.objectFactory.RunTimeObjectHolder;
import javafx.scene.control.Tab;

public class ApprovalImpl implements Approval{


    @Override
    public boolean isOnline() {

        return SessionHolder.getSession().session;
    }

    @Override
    public String whatRole() {
        return SessionHolder.getSession().role;
    }

    @Override
    public User whoIsIt(Long id) {
        return RunTimeObjectHolder.getInstance().users.get(SessionHolder.getSession().userId);
    }

    @Override
    public void lockTab(Tab...tabs) {
        for(Tab t : tabs)t.setDisable(true);
    }

    @Override
    public void unlockTab(Tab...tabs) {
        for(Tab t : tabs)t.setDisable(false);
    }

}
