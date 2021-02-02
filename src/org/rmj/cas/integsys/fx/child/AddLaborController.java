//
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
//import javafx.scene.control.CheckBox;
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
//import org.rmj.servicecenter.agentfx.XMLabor;
//import org.rmj.servicecenter.pojo.UnitJobOrderLabor;
//
//public class AddLaborController implements Initializable {
//
//    @FXML
//    private TextField txtField02;
//    @FXML
//    private TextField txtField03;
//    @FXML
//    private TextField txtField04;
//    @FXML
//    private TextField txtField05;
//    @FXML
//    private TextField txtField06;
//    @FXML
//    private Button btnDelete;
//    @FXML
//    private Button btnClose;
//    @FXML
//    private Button btnOkay;
//    @FXML
//    private AnchorPane acRoot;
//    @FXML
//    private TextField txtField01;
//    @FXML
//    private CheckBox Check07;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        
//        btnOkay.setOnAction(this::cmdButton_Click);
//        btnDelete.setOnAction(this::cmdButton_Click);
//        btnClose.setOnAction(this::cmdButton_Click);
//        
//        txtField01.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField04.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField06.setOnKeyPressed(this::txtField_KeyPressed);
//        
//        txtField01.focusedProperty().addListener(masterFocus);
//        txtField02.focusedProperty().addListener(masterFocus);
//        txtField03.focusedProperty().addListener(masterFocus);
//        txtField04.focusedProperty().addListener(masterFocus);
//        txtField05.focusedProperty().addListener(masterFocus);
//        txtField06.focusedProperty().addListener(masterFocus);
//        
//        Check07.focusedProperty().addListener(check_Box);
//        Check07.setOnKeyPressed(this::Check_KeyPressed);
//        
//        clearFields();
//        loadRecord();
//        pbLoaded = true;
//    }
//    
//    public void loadRecord() {
//        
//        txtField03.setText(String.valueOf(oDetail.getUnitPrce()));
//        txtField04.setText(String.valueOf(oDetail.getQuantity()));
//        txtField05.setText(String.valueOf(oDetail.getDiscount()));
//        txtField06.setText(String.valueOf(oDetail.getDiscAmtx()));
//        
//        boolean lbCheck;
//       
//       lbCheck = oDetail.getWaivedxx().equals("1");
//       Check07.selectedProperty().setValue(lbCheck);
//        
//        String lsResult = "";
//        
//        //load barcord, description and qtyonhand
//        if (!oDetail.getLaborIDx().isEmpty()){
//            XMLabor loLabor = new XMLabor(poGRider, psBranchCd, true);
//            JSONObject loJSON = loLabor.searchLabor(oDetail.getLaborIDx(), true);
//            if (loJSON != null){
//                String [] lasResult = lsResult.split("»");
//                txtField01.setText(lasResult[0]);
//                txtField02.setText(lasResult[1]);
//                txtField03.setText(String.valueOf(oDetail.getUnitPrce()));
//                txtField04.setText(String.valueOf(oDetail.getQuantity()));
//                txtField05.setText(String.valueOf(oDetail.getDiscount()));
//                txtField06.setText(String.valueOf(oDetail.getDiscAmtx()));
//            }else{
//                txtField01.setText("");
//                txtField02.setText("");
//                txtField03.setText("0.00");
//                txtField04.setText("0");
//                txtField05.setText("0");
//                txtField06.setText("0");
//            }            
//        }
//    }
//    
//    private void Check_KeyPressed(KeyEvent event){
//        CheckBox chkBox = (CheckBox)event.getSource();
//        
//        
//        switch (event.getCode()){
//        case ENTER:
//        case DOWN:
//            CommonUtils.SetNextFocus(chkBox);
//            break;
//        case UP:
//            CommonUtils.SetPreviousFocus(chkBox);
//        }
//    }
//    
//    private void unloadScene(ActionEvent event){
//        Node source = (Node)  event.getSource(); 
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
//    }
//
//    public void txtField_KeyPressed(KeyEvent event) {
//           TextField txtField = (TextField)event.getSource();
//           int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//           String lsValue = txtField.getText();
//           String lsResult = "";
//           JSONObject loJSON;
//           XMLabor loLabor;
//           
//           if(event.getCode() == F3){
//                switch(lnIndex){
//                    case 1:
//                        loLabor = new XMLabor(poGRider, psBranchCd, true);
//                        loJSON = loLabor.searchLabor(lsValue, true);
//                        
//                        if (loJSON != null){
//                            txtField01.setText((String) loJSON.get("sLaborIDx"));
//                            txtField02.setText((String) loJSON.get("sLaborNme"));
//                            txtField03.setText((String) loJSON.get("nPriceLv1"));
//                            txtField04.setText(String.valueOf(oDetail.getQuantity()));
//                            txtField05.setText(String.valueOf(oDetail.getDiscount()));
//                            txtField06.setText(String.valueOf(oDetail.getDiscAmtx()));
//                            
//                            oDetail.setLaborIDx((String) loJSON.get("sLaborIDx"));
//                            oDetail.setUnitPrce(Double.valueOf((String) loJSON.get("nPriceLv1")));
//                        } else {
//                            txtField01.setText("");
//                            txtField02.setText("");
//                            txtField03.setText("0.00");
//                            txtField04.setText("0.00");
//                            txtField05.setText("0");
//                            txtField06.setText("0");
//                        }break;
//                        
//                    case 2:
//                        loLabor = new XMLabor(poGRider, psBranchCd, true);
//                        loJSON = loLabor.searchLabor(lsValue, false);
//                        
//                        if (loJSON != null){
//                            txtField01.setText((String) loJSON.get("sLaborIDx"));
//                            txtField02.setText((String) loJSON.get("sLaborNme"));
//                            txtField03.setText((String) loJSON.get("nPriceLv1"));
//                            txtField04.setText(String.valueOf(oDetail.getQuantity()));
//                            txtField05.setText(String.valueOf(oDetail.getDiscount()));
//                            txtField06.setText(String.valueOf(oDetail.getDiscAmtx()));
//                            
//                            oDetail.setLaborIDx((String) loJSON.get("sLaborIDx"));
//                            oDetail.setUnitPrce(Double.valueOf((String) loJSON.get("nPriceLv1")));
//                        } else {
//                            txtField01.setText("");
//                            txtField02.setText("");
//                            txtField03.setText("0.00");
//                            txtField04.setText("0.00");
//                            txtField05.setText("0");
//                            txtField06.setText("0");
//                        }break;
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
//            String lsButton = ((Button)event.getSource()).getId();
//
//            switch (lsButton){
//            case "btnOkay":
//                bCancelled = false;
//                System.out.println(oDetail.getWaivedxx());
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
//    final ChangeListener<? super Boolean> masterFocus = (o,ov,nv)->{
//        if(!nv){
//                TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
//                int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//                String lsValue = txtField.getText();
//                double lnValue = 0;
//                int lnVal = 0;
//                switch (lnIndex){
//                    case 1: //LaborIDx
//                    case 2: //Descriptx
//                        break;
//                    case 3: //UnitPrce
//                        try {
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
//                    case 4: //Quantity
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
//                        
//                    case 5: //Discountx
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
//                    case 6: // addDiscx
//                            try {
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
//                        
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
//        txtField01.setText("");
//        txtField02.setText("");
//        txtField03.setText("0.00");
//        txtField04.setText("0");
//        txtField05.setText("0.0");
//        txtField06.setText("0.0");
//    }
//    
//    public void setData(UnitJobOrderLabor foDetail){oDetail = foDetail;}
//    public void setIndex(int fnIndex){pnRow = fnIndex;}
//    public UnitJobOrderLabor getData(){
//        return oDetail;
//    }
//    
//    public boolean isCancelled(){return bCancelled;}
//    private static GRider poGRider;
//    private static UnitJobOrderLabor oDetail = new UnitJobOrderLabor();
//    private String psBranchCd = "";
//    private static int pnQuantity = 0;
//    private static double pnAddDiscx = 0;
//    private static double pnDiscRate = 0.0;
//    private static double pnUnitPrce = 0.0;
//    private int pnRow = -1;
//    private int nEditMode = EditMode.UNKNOWN;
//    private boolean bCancelled = true;
//    private boolean pbLoaded = false;
//    private final String pxeModuleName = AddLaborController.class.getName();
//    
//    public void setEditMode(int fnValue){
//        nEditMode = fnValue;
//    }
//    
//    public int getIndex(){return pnRow;}
//    
//    public int getEditMode(){
//    return nEditMode;
//    }
//    
//    final ChangeListener<? super Boolean> check_Box = (o,ov,nv)->{
//        if (!pbLoaded) return;
//        
//        CheckBox loField = (CheckBox)((ReadOnlyBooleanPropertyBase)o).getBean();
//        
//        if(!nv){ /*Lost Focus*/
//            switch (loField.getId()){
//                case "Check07":
//                    oDetail.setWaivedxx(Check07.isSelected() == true ? "1" : "0");
//                    break;
//            }       
//        }         
//    };
//
//}
