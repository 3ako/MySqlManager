package mySqlUI.eventhandler;

import mySqlManager.server.events.ServerConnect;
import mySqlManager.server.events.ServerConnectListener;
import mySqlUI.Ui;

public class Connect extends MainListener implements ServerConnectListener {

    public Connect(Ui ui) {
        super(ui);
    }

    @Override
    public void ServerConnectListener(ServerConnect e) {
        this.getUi().getFrame().getInfoMonitor().setConnectStatus(true);
        this.getUi().getFrame().getLogFrame().log(
                e.getServerEventManager().getServer().getIp()+"@"+e.getServerEventManager().getServer().getUser()+" connection established!");
    }
}
