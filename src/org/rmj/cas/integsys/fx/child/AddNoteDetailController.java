
package org.rmj.cas.integsys.fx.child;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.rmj.appdriver.agentfx.CommonUtils;


public class AddNoteDetailController implements Initializable {

    @FXML
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private TextField txtField04;
    @FXML
    private TextField txtField05;
    @FXML
    private Button btnOkay;
    @FXML
    private Button btnCancel;
    @FXML
    private TextArea txtField06;
    @FXML
    private AnchorPane acRoot;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtField01.setText(sStockIDx);
       txtField02.setText(sBarCodex);
       txtField03.setText(sDescript);
       txtField04.setText(String.valueOf(nUnitPrce));
       txtField05.setText(String.valueOf(nQuantity));
       txtField06.setText(String.valueOf(sNotesxxx));
       
        btnOkay.setOnAction(this::cmdButton_Click);
        btnCancel.setOnAction(this::cmdButton_Click);
        
        txtField01.setOnKeyPressed(this::txtField_KeyPressed);
        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
        txtField04.setOnKeyPressed(this::txtField_KeyPressed);
        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
        
        txtField04.focusedProperty().addListener(masterFocus);
        txtField05.focusedProperty().addListener(masterFocus);
        txtField06.focusedProperty().addListener(txtArea_Focus);
    }    
    
     public void txtField_KeyPressed(KeyEvent event) {
        TextField txtField = (TextField)event.getSource();
        
        switch (event.getCode()) {
                case ENTER:
                case DOWN:
                    CommonUtils.SetNextFocus(txtField);
                    break;
                case UP:
                    CommonUtils.SetPreviousFocus(txtField);
                    break;
                default:
                    break;
            }
    }
    
    public void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        
        switch (lsButton){
            case "btnOkay":
                bCancelled = false; break;
            case "btnCancel":
                bCancelled = true;
        }
        unloadScene(event);
    }
    
    private void unloadScene(ActionEvent event){
        Node source = (Node)  event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    final ChangeListener<? super Boolean> masterFocus = (o,ov,nv)->{
        if(!nv){
            TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
            int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
            String lsValue = txtField.getText();
            double lnValue = 0;
            int lnVal = 0;
            switch (lnIndex){
                case 4: //unit price
                    try {
                        lnValue = Double.parseDouble(lsValue);
                    } catch (NumberFormatException e) {
                        txtField.setText("0.0");
                        nUnitPrce = 0.0;
                        return;
                    }
                    nUnitPrce = lnValue; break;
                case 5: //Quantity
                    try {
                        lnVal = Integer.parseInt(lsValue);
                    } catch (NumberFormatException e) {
                        txtField.setText("1");
                        nQuantity = 1;
                        return;
                    }
                    nQuantity = lnVal; break;
               
                default:
            }
        }
    };
    
     final ChangeListener<? super Boolean> txtArea_Focus = (o,ov,nv)->{
        if(!nv){
        TextArea txtField = (TextArea)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
                 
            switch (lnIndex){
                case 6: //note
                   if (lsValue.length() > 64) 
                       lsValue = lsValue.substring(0, 64);
                    
                    sNotesxxx = lsValue;break;
                    
                    default:
       }
      }
    };
    
    //todo: textfield validations
    
    public void setStockIDx(String fsStockIDx){sStockIDx = fsStockIDx;}
    public void setBarCodex(String fsBarCodex){sBarCodex = fsBarCodex;}
    public void setDescript(String fsDescript){sDescript = fsDescript;}
    public void setUnitPrce(Number fsUnitPrce){nUnitPrce = fsUnitPrce;}
    public void setQuantity(int fnQuantity){nQuantity = fnQuantity;}
    public void setNotes(String fsNotesxxx){sNotesxxx = fsNotesxxx;}
    
    public String getStockIDx(){return sStockIDx;}
    public String getBarCodex(){return sBarCodex;}
    public String getDescript(){return sDescript;}
    public Number getUnitPrce(){return nUnitPrce;}
    public int getQuantity(){return nQuantity;}
    public String getNotes(){return sNotesxxx;}

    
    public boolean isCancelled(){return bCancelled;}
    
    private static String sStockIDx = "";
    private static String sBarCodex = "";
    private static String sDescript = "";
    private static Number nUnitPrce = 0.0;
    private static int nQuantity = 0;
    private static String sNotesxxx = "";

    private boolean bCancelled = false;
    private final String pxeModuleName = "AddNoteDetailController";
    
}
