package mySqlUI.eventhandler;

import mySqlManager.server.events.ServerDisconnect;
import mySqlManager.server.events.ServerDisconnectListener;
import mySqlUI.Ui;

public class Disonnect extends MainListener implements ServerDisconnectListener {

    public Disonnect(Ui ui) {
        super(ui);
    }

    @Override
    public void ServerDisconnectListener(ServerDisconnect e) {
        this.getUi().getFrame().getInfoMonitor().setConnectStatus(false);
        this.getUi().getFrame().getLogFrame().log(
                e.getServerEventManager().getServer().getIp()+"@"+e.getServerEventManager().getServer().getUser()+" connection was broken!");
    }
}
