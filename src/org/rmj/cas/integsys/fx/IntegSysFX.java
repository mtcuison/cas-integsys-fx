package org.rmj.cas.integsys.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.callback.IFXML;
import org.rmj.cas.integsys.fx.MainController;

public class IntegSysFX extends Application implements IFXML{
    public final static String pxeApplcName = "IntegSysFX POS v1.0";
    public final static String pxeStageIcon = "org/rmj/cas/integsys/style/ic_launcher1.png";
    public final static String pxeMainForm = "Main.fxml";
    
    public static GRider poGRider;
    
    @Override
    public void setGRider(GRider foGRider) {
        this.poGRider = foGRider;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pxeMainForm));
        
        //get the controller of the main interface
        MainController loControl = new MainController();
        //set the GRider Application Driver to the controller
        loControl.setGRider(poGRider);
        
        //the controller class to the main interface
        fxmlLoader.setController(loControl);
        
        //load the main interface
        Parent parent = fxmlLoader.load();
        //set the main interface as the scene
        Scene scene = new Scene(parent);
        
        //get the screen size
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(pxeStageIcon));
        stage.setTitle(pxeApplcName);
        
        //set stage as maximized but not full screen
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
