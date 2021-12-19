package mySqlUI.eventhandler;

import mySqlManager.server.events.ServerSetSelectBase;
import mySqlManager.server.events.ServerSetSelectBaseListener;
import mySqlUI.Ui;

public class SetSelectBaseListener extends MainListener implements ServerSetSelectBaseListener {

    public SetSelectBaseListener(Ui ui) {
        super(ui);
    }

    @Override
    public void ServerSetSelectBaseListener(ServerSetSelectBase e) {
        this.getUi().getFrame().getInfoMonitor().setBase(e.getBase().getName());
        this.getUi().getFrame().getInfoMonitor().reloadBaseForTable(e.getBase().getName(),e.getBase());
    }
}
