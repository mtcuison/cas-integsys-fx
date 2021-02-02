package org.rmj.cas.integsys.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.callback.IFXML;

public class ParameterController implements Initializable, IFXML {
    private GRider poGRider;
    @FXML
    private AnchorPane acWorkStation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void setGRider(GRider foGRider) {
        poGRider = foGRider;
    }

    @FXML
    private void cmdBrand_Click(ActionEvent event) {
    }

    @FXML
    private void cmdCategory1_Click(ActionEvent event) {
    }

    @FXML
    private void cmdCategory2_Click(ActionEvent event) {
    }

    @FXML
    private void cmdCategory3_Click(ActionEvent event) {
    }

    @FXML
    private void cmdCategory4_Click(ActionEvent event) {
    }

    @FXML
    private void cmdColor_Click(ActionEvent event) {
    }

    @FXML
    private void cmdCompany_Click(ActionEvent event) {
    }

    @FXML
    private void cmdInvLocation_Click(ActionEvent event) {
    }

    @FXML
    private void cmdInvType_Click(ActionEvent event) {
    }

    @FXML
    private void cmdMeasure_Click(ActionEvent event) {
    }

    @FXML
    private void cmdModel_Click(ActionEvent event) {
    }

    @FXML
    private void cmdLabor_Click(ActionEvent event) {
    }

    @FXML
    private void cmdSalesPerson_Click(ActionEvent event) {
    }

    @FXML
    private void cmdSupplier_Click(ActionEvent event) {
    }

    @FXML
    private void cmdTerm_Click(ActionEvent event) {
    }

    @FXML
    private void cmdPartners_Click(ActionEvent event) {
    }
    
}
