package mySqlUI.eventhandler;

import mySqlUI.Ui;

public class MainListener {
    private Ui ui;
    public MainListener(Ui ui){
        this.setUi(ui);
    }
    public Ui getUi() {
        return ui;
    }

    private void setUi(Ui ui) {
        this.ui = ui;
    }
}
