package mySqlUI.eventhandler;

import mySqlManager.server.events.ServerRegisterBase;
import mySqlManager.server.events.ServerRegisterBaseListener;
import mySqlUI.Ui;

public class RegisterBase extends MainListener implements ServerRegisterBaseListener {

    public RegisterBase(Ui ui) {
        super(ui);
    }

    @Override
    public void ServerRegisterBaseListener(ServerRegisterBase e){
        System.out.println("Зарегистрирована база "+e.getBase().getName());
        this.getUi().getFrame().getInfoMonitor().addBase(e.getBase());
    }
}
