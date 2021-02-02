package org.rmj.cas.integsys.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.callback.IFXML;

public class DashboardController implements Initializable, IFXML {
    private GRider poGRider;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void setGRider(GRider foGRider) {
        poGRider = foGRider;
    }
}
