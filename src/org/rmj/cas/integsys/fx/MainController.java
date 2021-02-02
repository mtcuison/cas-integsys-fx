package org.rmj.cas.integsys.fx;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.SQLUtil;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.callback.IFXML;
import org.rmj.cas.parameter.fx.ParameterFX;
import org.rmj.sales.agentfx.XMSales;

public class MainController implements Initializable {
    @FXML private StackPane acBody;
    @FXML private AnchorPane acPromotions;
    @FXML private Label lblDate;
    @FXML private Label lblUser;
    @FXML private Label lblBranch;
    @FXML private AnchorPane acMain;
    @FXML private ImageView image01;
    @FXML private ImageView image03;
    @FXML private ImageView image02;
    @FXML private AnchorPane acAds01;
    @FXML private AnchorPane acAds03;
    @FXML private AnchorPane acAds02;

    private double xOffset = 0;
    private XMSales poTrans;
    private double yOffset = 0;
    private String psBranchCd = "";
    final MenuItem psEndShift = new MenuItem("End Shit");
    final MenuItem psDeclareNewDay = new MenuItem("Declare New Day");
    final MenuItem psCashDrawer = new MenuItem("Cash Drawer");
    final MenuItem psLogout = new MenuItem("Logout");
    final ContextMenu contextMenu = new ContextMenu(psEndShift, psDeclareNewDay, psCashDrawer, psLogout);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (psBranchCd.equals("")) psBranchCd = poGRider.getBranchCode();
  
        poTrans = new XMSales(poGRider, psBranchCd, false);
    
        loadRecord();
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);        
            String temp = "" + second;

            Date date;
            date = poGRider.getServerDate();
            String strTimeFormat = "hh:mm:";
            String strDateFormat = "MMMM dd, yyyy";
            String secondFormat = "ss a";

            DateFormat timeFormat = new SimpleDateFormat(strTimeFormat + secondFormat);
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            String formattedTime= timeFormat.format(date);
            String formattedDate= dateFormat.format(date);

            lblDate.setText(formattedDate+ " || " + formattedTime);

        }),
             new KeyFrame(Duration.seconds(1))
        );
        
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play(); 

        //show dashboard
        setScene(LoadAnimate("Sales.fxml"));
        initMarketing(0);
    }    

    private AnchorPane LoadAnimate(String fsFormName){
        IFXML fxObj = getController(fsFormName);
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxObj.getClass().getResource(fsFormName));
        fxmlLoader.setController(fxObj);      
   
        AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(root);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            return root;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    private void initMarketing(int fnLogical){
        if (fnLogical == 0) {
            image01.setImage(null);
            image02.setImage(null);
            image03.setImage(null);
            image01.setFitWidth(0);
            image02.setFitWidth(0);
            image03.setFitWidth(0);
            acAds01.setPrefWidth(0);
            acAds02.setPrefWidth(0);
            acAds03.setPrefWidth(0);
            acPromotions.setPrefWidth(0);
        } else {
            acAds01.setPrefWidth(200);
            acAds02.setPrefWidth(200);
            acAds03.setPrefWidth(200);
            image01.setFitWidth(200);
            image02.setFitWidth(200);
            image03.setFitWidth(200);
            image01.setImage(new Image("org/rmj/cas/integsys/style/logo.png"));
            image02.setImage(new Image("org/rmj/cas/integsys/style/logo.png"));
            image03.setImage(new Image("org/rmj/cas/integsys/style/logo.png"));
            acPromotions.setPrefWidth(200);
        }
    }
    
    private IFXML getController(String fsValue){
        initMarketing(1);
        
        IFXML instance;
        
        switch (fsValue){
            case "Sales.fxml":
                instance = new SalesController();
                instance.setGRider(poGRider);
                return instance;
            default: 
                return null;
        }
    }
    
    private void setScene(AnchorPane foPane){
        acBody.getChildren().clear();
        acBody.getChildren().add(foPane);
    }
    
    public void setGRider(GRider foGRider){
        this.poGRider = foGRider;
    }
    
    private final String pxeModuleName = "MainController";
    private static GRider poGRider;
    
    private void loadRecord(){
        lblBranch.setText((String) poGRider.getBranchName());
        String lsSQL = "SELECT" +
                            " IFNULL(b.sClientNm, 'UNSET ID') xCashierx" + 
                        " FROM xxxSysUser a" + 
                            " LEFT JOIN Client_Master b" +
                                " ON a.sEmployNo = b.sClientID" + 
                        " WHERE a.sUserIDxx = " + SQLUtil.toSQL(poGRider.getUserID());
        
        ResultSet loRS = poGRider.executeQuery(lsSQL);
        try {
            if (loRS.next())
                lblUser.setText("Cashier: " + loRS.getString("xCashierx"));
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
