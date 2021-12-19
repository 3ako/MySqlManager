package mySqlManager.events;

import java.util.EventObject;

public class RegisterServer extends EventObject {
    public RegisterServer(Object source) {
        super(source);
    }
}
