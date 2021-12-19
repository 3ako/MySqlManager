package mySqlUI.eventhandler;

import mySqlManager.server.events.ServerGetResultSet;
import mySqlManager.server.events.ServerGetOneEventListener;
import mySqlUI.Ui;

public class GetOneEventListener extends MainListener implements ServerGetOneEventListener {

    public GetOneEventListener(Ui ui) {
        super(ui);
    }

    @Override
    public void getOneEventListener(ServerGetResultSet e){
        System.out.println("Событите сработало от сервера "+e.getServerEventManager().getServer().getIp());
        this.getUi().getFrame().getLogFrame().log(e.getSql());
    }
}
