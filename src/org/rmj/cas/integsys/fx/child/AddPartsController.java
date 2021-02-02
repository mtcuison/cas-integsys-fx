//package org.rmj.cas.integsys.fx.child;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.beans.property.ReadOnlyBooleanPropertyBase;
//import javafx.beans.value.ChangeListener;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import static javafx.scene.input.KeyCode.DOWN;
//import static javafx.scene.input.KeyCode.ENTER;
//import static javafx.scene.input.KeyCode.F3;
//import static javafx.scene.input.KeyCode.UP;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import org.json.simple.JSONObject;
//import org.rmj.appdriver.GRider;
//import org.rmj.appdriver.agentfx.CommonUtils;
//import org.rmj.appdriver.constants.EditMode;
//import org.rmj.cas.inventory.base.InvMaster;
//import org.rmj.cas.parameter.agent.XMInventory;
//import org.rmj.servicecenter.pojo.UnitJobOrderDetail;
//
//
//public class AddPartsController implements Initializable {
//
//    @FXML
//    private TextField txtField02;
//    @FXML
//    private TextField txtField03;
//    @FXML
//    private TextField txtField04;
//    @FXML
//    private TextField txtField06;
//    @FXML
//    private Button btnDelete;
//    @FXML
//    private Button btnClose;
//    @FXML
//    private Button btnOkay;
//    @FXML
//    private TextField txtField05;
//    @FXML
//    private TextField txtField07;
//    @FXML
//    private TextField txtField08;
//    @FXML
//    private AnchorPane acRoot;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        
//        btnOkay.setOnAction(this::cmdButton_Click);
//        btnDelete.setOnAction(this::cmdButton_Click);
//        btnClose.setOnAction(this::cmdButton_Click);
//        
//        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField04.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField06.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField07.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField08.setOnKeyPressed(this::txtField_KeyPressed);
//        
//        txtField02.focusedProperty().addListener(masterFocus);
//        txtField03.focusedProperty().addListener(masterFocus);
//        txtField04.focusedProperty().addListener(masterFocus);
//        txtField05.focusedProperty().addListener(masterFocus);
//        txtField06.focusedProperty().addListener(masterFocus);
//        txtField07.focusedProperty().addListener(masterFocus);
//        txtField08.focusedProperty().addListener(masterFocus);
//        
//        clearFields();
//        loadRecord();
//
//        pbLoaded = true;
//    }
//    
//    public void loadRecord(){
//        txtField04.setText(String.valueOf(oDetail.getUnitPrce()));
//        txtField06.setText(String.valueOf(oDetail.getQuantity()));
//        txtField07.setText(String.valueOf(oDetail.getDiscount()));
//        txtField08.setText(String.valueOf(oDetail.getDiscAmtx()));
//        
//        //load barcord, description and qtyonhand
//        if (!oDetail.getStockIDx().isEmpty()){
//            InvMaster loInvMaster = new InvMaster(poGRider, psBranchCd, true);
//            if (loInvMaster.SearchStock(oDetail.getStockIDx(),"", true, true)){
//                txtField02.setText((String) loInvMaster.getMaster("sBarCodex"));
//                txtField03.setText((String) loInvMaster.getMaster("sDescript"));
//                txtField04.setText((String) loInvMaster.getMaster("nSelPrice"));
//                txtField05.setText((String) loInvMaster.getMaster("nQtyOnHnd"));
//
//                oDetail.setStockIDx((String) loInvMaster.getMaster("sBarCodex"));
//                oDetail.setUnitPrce(Double.valueOf((String) loInvMaster.getMaster("nSelPrice")));
//            } else {
//                txtField02.setText("");
//                txtField03.setText("");
//                txtField04.setText("0.00");
//                txtField05.setText("0");
//            }
//        }
//    }
//    
//    public void txtField_KeyPressed(KeyEvent event) {
//           TextField txtField = (TextField)event.getSource();
//           int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//           String lsValue = txtField.getText();
//           String lsResult = "";
//           
//           InvMaster loInvMaster = new InvMaster(poGRider, psBranchCd, true);
//           JSONObject loJSON;
//           
//           if(event.getCode() == F3 || event.getCode() == ENTER){
//                switch(lnIndex){
//                    case 2:                        
//                        if (loInvMaster.SearchStock(lsValue, "", true, false)){
//                            txtField02.setText((String) loInvMaster.getMaster("sBarCodex"));
//                            txtField03.setText((String) loInvMaster.getMaster("sDescript"));
//                            txtField04.setText((String) loInvMaster.getMaster("nSelPrice"));
//                            txtField05.setText((String) loInvMaster.getMaster("nQtyOnHnd"));
//
//                            oDetail.setStockIDx((String) loInvMaster.getMaster("sBarCodex"));
//                            oDetail.setUnitPrce(Double.valueOf((String) loInvMaster.getMaster("nSelPrice")));
//                        } else {
//                            txtField02.setText("");
//                            txtField03.setText("");
//                            txtField04.setText("0.00");
//                            txtField05.setText("0");
//                        }
//                        break;
//                    case 3:
//                        if (loInvMaster.SearchStock(lsValue, "", false, false)){
//                            txtField02.setText((String) loInvMaster.getMaster("sBarCodex"));
//                            txtField03.setText((String) loInvMaster.getMaster("sDescript"));
//                            txtField04.setText((String) loInvMaster.getMaster("nSelPrice"));
//                            txtField05.setText((String) loInvMaster.getMaster("nQtyOnHnd"));
//
//                            oDetail.setStockIDx((String) loInvMaster.getMaster("sBarCodex"));
//                            oDetail.setUnitPrce(Double.valueOf((String) loInvMaster.getMaster("nSelPrice")));
//                        } else {
//                            txtField02.setText("");
//                            txtField03.setText("");
//                            txtField04.setText("0.00");
//                            txtField05.setText("0");
//                        }
//                        break;
//                    }
//                } 
//        if (event.getCode() == DOWN || event.getCode() == ENTER){
//             CommonUtils.SetNextFocus(txtField);
//        }
//        if (event.getCode() == UP){
//             CommonUtils.SetPreviousFocus(txtField);                 
//        }
//    }
//    
//    public void cmdButton_Click(ActionEvent event) {
//        String lsButton = ((Button)event.getSource()).getId();
//        
//        switch (lsButton){
//            case "btnOkay":
//                bCancelled = false;
//                break;
//            case "btnDelete":
//                bCancelled = false; 
//                nEditMode = EditMode.DELETE;
//                break;
//            case "btnCancel":
//                bCancelled = true;
//        }
//        unloadScene(event);
//    }
//    
//    private void unloadScene(ActionEvent event){
//        Node source = (Node)  event.getSource(); 
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
//    }
//    
//    final ChangeListener<? super Boolean> masterFocus = (o,ov,nv)->{
//        if(!nv){
//                TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
//                int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//                String lsValue = txtField.getText();
//                double lnValue = 0;
//                int lnVal = 0;
//                switch (lnIndex){
//                    case 2:
//                    case 3:
//                        break;
//                    case 4: //unit price
//                         try {
//                            lnValue = Double.parseDouble(lsValue);
//                        } catch (NumberFormatException e) {
//                            txtField.setText("0.0");
//                            pnUnitPrce = 0.0;
//                            return;
//                        }
//                        pnUnitPrce = lnValue; 
//                        oDetail.setUnitPrce(pnUnitPrce);
//                        txtField.setText(String.valueOf(pnUnitPrce));
//                        break;
//                    case 5: //QtyOnHand
//                      break;
//                    case 6: // Quantity
//                        try {
//                            lnVal = Integer.parseInt(lsValue);
//                            if (lnVal <= 0){
//                            txtField.setText("1");
//                            lnVal = 1;
//                            }
//                        } catch (NumberFormatException e) {
//                            pnQuantity = 0;
//                            txtField.setText("0");
//                            return;
//                        }
//                        pnQuantity = lnVal;
//                        oDetail.setQuantity(pnQuantity);
//                        break;
//                    case 7: //disc rate
//                        try {
//                            lnValue = Double.parseDouble(lsValue);
//                            if (lnValue > 1){
//                            lnValue = 1.0;
//                            }
//                        } catch (NumberFormatException e) {
//                            txtField.setText("0.0");
//                            pnDiscRate = 0.0;
//                            return;
//                        }
//                        pnDiscRate = lnValue;
//                        oDetail.setDiscount(pnDiscRate);
//                        txtField.setText(String.valueOf(pnDiscRate));
//                        break;
//
//                    case 8: //add disc
//                        try {
//                            lnValue = Double.parseDouble(lsValue);
//                        } catch (NumberFormatException e) {
//                            pnAddDiscx = 0.0;
//                            txtField.setText("0.0");
//                            return;
//                        }
//                        pnAddDiscx = lnValue;
//                        oDetail.setDiscAmtx(pnAddDiscx);
//                        txtField.setText(String.valueOf(pnAddDiscx));
//                        break;
//                    default:
//                }
//        }
//    };
//    
//    public void setGRider(GRider foGRider){
//        this.poGRider = foGRider;
//    }
//    
//    public void setBranchCd(String fsBranchCd) {
//        this.psBranchCd = fsBranchCd;
//    }
//    
//    public void clearFields(){
//        txtField02.setText("");
//        txtField03.setText("");
//        txtField04.setText("0.00");
//        txtField05.setText("0");
//        txtField06.setText("0");
//        txtField07.setText("0.00");
//        txtField08.setText("0.00");
//    }
//    
//    private static GRider poGRider;
//    private static UnitJobOrderDetail oDetail = new UnitJobOrderDetail();
//    private String psBranchCd = "";
//
//    public void setData(UnitJobOrderDetail foDetail){oDetail = foDetail;}
//    public void setIndex(int fnIndex){pnRow = fnIndex;}
//    public UnitJobOrderDetail getData(){
//        return oDetail;
//    }
//    
//    public boolean isCancelled(){return bCancelled;}
//    public int getIndex(){return pnRow;}
//    
//    private static int pnQuantity = 0;
//    private static double pnAddDiscx = 0;
//    private static double pnDiscRate = 0.0;
//    private static double pnUnitPrce = 0.0;
//    private int pnRow = -1;
//    private int nEditMode = EditMode.UNKNOWN;
//    private boolean bCancelled = true;
//    private boolean pbLoaded = false;
//    private final String pxeModuleName = AddPartsController.class.getName();
//    
//    public void setEditMode(int fnValue){
//        nEditMode = fnValue;
//    }
//    
//    public int getEditMode(){
//    return nEditMode;
//    }
//    
//}
