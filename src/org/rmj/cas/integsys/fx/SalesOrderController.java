/*
    iMac 2018-06-27 08:30am
        Started creating this class.
*/
package org.rmj.cas.integsys.fx;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.F3;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.callback.IFXML;
import org.rmj.cas.client.base.XMClient;
import org.rmj.cas.integsys.fx.child.ModifySPDetailController;
import org.rmj.cas.inventory.base.InvMaster;
import org.rmj.cas.parameter.agent.XMBrand;
import org.rmj.cas.parameter.agent.XMInventoryType;
import org.rmj.cas.parameter.agent.XMTerm;
import org.rmj.sales.agentfx.XMSalesOrder;

public class SalesOrderController implements Initializable, IFXML {
    @FXML
    private BorderPane bpContent;
    @FXML
    private Button cmdButton01;
    @FXML
    private AnchorPane acDetailTable;
    @FXML
    private TableView tblSalesMaster;
    @FXML
    private TableColumn index01;
    @FXML
    private TableColumn index02;
    @FXML
    private TableColumn index03;
    @FXML
    private TableColumn index04;
    @FXML
    private TableColumn index05;
    @FXML
    private TableColumn index06;
    @FXML
    private TableColumn index07;
    @FXML
    private TableColumn index08;
    @FXML
    private TableColumn index09;
    @FXML
    private TableColumn index10;
    @FXML
    private TableColumn index11;
    @FXML
    private Button cmdButton05;
    @FXML
    private Button cmdButton06;
    @FXML
    private Button cmdButton07;
    @FXML
    private Button cmdButton02;
    @FXML
    private Button cmdButton03;
    @FXML
    private Button cmdButton04;
    @FXML
    private TextField txtSeeks99;
    @FXML
    private Label lblOrderNo;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private TextArea txtField06;
    @FXML
    private TextField txtField07;
    @FXML
    private AnchorPane acWorkStation;
    @FXML
    private FontAwesomeIconView SAVE;
    @FXML
    private TextField txtField17;
    @FXML
    private TextField txtField11;
    @FXML
    private TextField txtField09;
    @FXML
    private TextField txtField10;
    @FXML
    private AnchorPane acSearchItem;
    @FXML
    private Label lblTranStat;
    @FXML
    private TextField txtAddress;
    @FXML
    private AnchorPane acClient;
    @FXML
    private TextField txtField90;
    @FXML
    private TextField txtField91;
    @FXML
    private Button cmdButton08;
    @FXML
    private TextField txtField13;
    @FXML
    private TextField txtField05;
    @FXML
    private Label lblSubTotal;
    @FXML
    private Label lblVatExclsv;
    @FXML
    private Label lblDiscount;
    @FXML
    private Label lblNetSales;
    @FXML
    private Label lblAddVat;
    @FXML
    private Label lblAmountDue;
    @FXML
    private Button cmdButton09;
    @FXML
    private Button cmdButton10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (psBranchCd.equals("")) psBranchCd = poGRider.getBranchCode();
        
        poTrans = new XMSalesOrder(poGRider, psBranchCd, false);
        
        cmdButton01.setOnAction(this::cmdButton_Click);
        cmdButton02.setOnAction(this::cmdButton_Click);
        cmdButton03.setOnAction(this::cmdButton_Click);
        cmdButton04.setOnAction(this::cmdButton_Click);
        cmdButton05.setOnAction(this::cmdButton_Click);
        cmdButton06.setOnAction(this::cmdButton_Click);
        cmdButton07.setOnAction(this::cmdButton_Click);
        cmdButton08.setOnAction(this::cmdButton_Click);
        cmdButton09.setOnAction(this::cmdButton_Click);
        cmdButton10.setOnAction(this::cmdButton_Click);
        
        txtSeeks99.setOnKeyPressed(this::txtField_KeyPressed);
        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
        txtField06.setOnKeyPressed(this::txtFieldArea_KeyPressed);
        txtField07.setOnKeyPressed(this::txtField_KeyPressed);
        txtField09.setOnKeyPressed(this::txtField_KeyPressed);
        txtField10.setOnKeyPressed(this::txtField_KeyPressed);
        txtField11.setOnKeyPressed(this::txtField_KeyPressed);
        txtField17.setOnKeyPressed(this::txtField_KeyPressed);
        
        txtSeeks99.setOnKeyPressed(this::txtField_KeyPressed);
        txtField90.setOnKeyPressed(this::txtField_KeyPressed);
        txtField91.setOnKeyPressed(this::txtField_KeyPressed);
        
        txtField02.focusedProperty().addListener(txtField_Focus);
        txtField03.focusedProperty().addListener(txtField_Focus);
        txtField06.focusedProperty().addListener(txtArea_Focus);
        txtField09.focusedProperty().addListener(txtField_Focus);
        txtField10.focusedProperty().addListener(txtField_Focus);
        txtField11.focusedProperty().addListener(txtField_Focus);        
        txtField90.focusedProperty().addListener(txtField_Focus);
        txtField91.focusedProperty().addListener(txtField_Focus);
        txtSeeks99.focusedProperty().addListener(txtField_Focus);

        clearFields();
        initGrid();
        
        pnEditMode = EditMode.UNKNOWN;
        initButton(pnEditMode);
        
        pnRow = 0;
        pbLoaded = true;
    }

    public void initGrid(){  
        index01.setStyle("-fx-alignment: CENTER-LEFT;");
        index02.setStyle("-fx-alignment: CENTER-LEFT;");
        index03.setStyle("-fx-alignment: CENTER-LEFT;");
        index04.setStyle("-fx-alignment: CENTER-LEFT;");
        index05.setStyle("-fx-alignment: CENTER-LEFT;");
        index06.setStyle("-fx-alignment: CENTER;");
        index07.setStyle("-fx-alignment: CENTER-RIGHT;");
        index08.setStyle("-fx-alignment: CENTER;");
        index09.setStyle("-fx-alignment: CENTER-RIGHT;");
        index10.setStyle("-fx-alignment: CENTER-RIGHT;");
        index11.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        index01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        index02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        index03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));
        index04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));
        index05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05"));
        index06.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index06"));
        index07.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index07"));
        index08.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index08"));
        index09.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index09"));
        index10.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index10"));
        index11.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index11"));
        
        tblSalesMaster.setItems(data);
    }
    
    private ObservableList<TableModel> data = FXCollections.observableArrayList();
    
    public void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        
        switch (lsButton){
            case "cmdButton01": //browse
                if (pnIndex == 90 || pnIndex == 91){
                    if (pnIndex == 90)
                        lsButton = txtField90.getText();
                    else
                        lsButton = txtField91.getText();
                    
                    if (poTrans.SearchTransaction(lsButton, (pnIndex == 90))){
                        loadTransaction();
                        pnEditMode = poTrans.getEditMode();
                    }
                }
                break;
            case "cmdButton02": //new             
                if (poTrans.newTransaction()== true){
                    clearFields();
                    
                    poTrans.setMaster("sBranchCd", psBranchCd);
                    poTrans.setMaster("sInvTypCd", psInvTypeCd);
                    
                    loadTransaction();
                    pnEditMode = poTrans.getEditMode();
                }
                break;
            case "cmdButton03": //update
                if (!psOldRec.equals("")){
                    if (poTrans.updateTransaction()){
                        loadDetail2Grid();
                        pnEditMode = poTrans.getEditMode();
                    } else
                        ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                }else
                    ShowMessageFX.Warning("Please select a record to update!", pxeModuleName, "No record to update.");
                break;
            case "cmdButton04": //close
                unloadForm();
                return;
            case "cmdButton05": //save
                if (poTrans.saveTransaction()){
                    ShowMessageFX.Information(null, pxeModuleName, "Transaction saved successfully.");
                    clearFields();
                    initGrid();
                    pnEditMode = EditMode.UNKNOWN;
                    initButton(pnEditMode); 
                } else{
                    if (!poTrans.getErrMsg().isEmpty()){
                        if (!poTrans.getWarnMsg().isEmpty())
                            ShowMessageFX.Error(poTrans.getErrMsg(), pxeModuleName, poTrans.getWarnMsg());
                        else 
                            ShowMessageFX.Error(null, pxeModuleName, poTrans.getErrMsg());
                    } else 
                        ShowMessageFX.Information(poTrans.getWarnMsg(), pxeModuleName, null);		
                }
                break;
            case "cmdButton06": //search
                break;
            case "cmdButton07": //cancel
                if(ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to discard changes?") == true){
                    clearFields();
                    pnEditMode = EditMode.UNKNOWN;
                }
                break;
            case "cmdButton08": //del item
                int lnIndex = tblSalesMaster.getSelectionModel().getFocusedIndex();    
                if(tblSalesMaster.getSelectionModel().getSelectedItem() == null){
                     ShowMessageFX.Warning(null, pxeModuleName, "Please select item to remove!");
                     break;
                }
                if(ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to remove this item?") == true){
                    if(tblSalesMaster.getItems().size() == 1){
                        poTrans.deleteDetail(lnIndex);
                        poTrans.addDetail();
                        loadDetail2Grid();
                        break;
                    }                  
                                      
                    if (lnIndex >= 0) {                         
                        if (poTrans.deleteDetail(lnIndex) == true) {
                            loadDetail2Grid();
                        }  
                    }
                }                  
                break;
            case "cmdButton09": //void
                if (!psOldRec.equals("")){
                    if (ShowMessageFX.YesNo(null, pxeModuleName, "Do you want to cancel this transaction?") == true){
                        if (poTrans.cancelTransaction((String) poTrans.getMaster("sTransNox"))){
                            ShowMessageFX.Information(null, pxeModuleName, "Transaction cancelled successfully.");
                            if (poTrans.openTransaction(psOldRec)) {
                                loadTransaction(); 
                                pnEditMode = poTrans.getEditMode();
                            }
                        } else 
                            ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                    }
                }else{
                    ShowMessageFX.Warning(null, pxeModuleName, "Please select a transaction to void.");
                }    
                break;
            case "cmdButton10": //pay
                if (!psOldRec.equals("")){
                    if (poTrans.closeTransaction(psOldRec)){
                        ShowMessageFX.Information(null, pxeModuleName, "Transaction PAYED successfully.");
                        clearFields();
                        initGrid();
                        pnEditMode = EditMode.UNKNOWN;
                        initButton(pnEditMode);
                    } else
                        ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                } else 
                    ShowMessageFX.Warning(null, pxeModuleName, "Please select a record to pay!");
                break;
            default:
                ShowMessageFX.Warning(null, pxeModuleName, " Button with name " + lsButton + " not registered.");
                return;
        }
        
        initButton(pnEditMode);
    }
    
    private void unloadForm(){
        bpContent.getChildren().clear();
    }
    
    public void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
        
        cmdButton01.setVisible(!lbShow);
        cmdButton02.setVisible(!lbShow);
        cmdButton03.setVisible(!lbShow);
        cmdButton04.setVisible(!lbShow);
        cmdButton09.setVisible(!lbShow);
        cmdButton10.setVisible(!lbShow);
                        
        cmdButton08.setVisible(lbShow);
        cmdButton05.setVisible(lbShow);
        cmdButton06.setVisible(lbShow);
        cmdButton07.setVisible(lbShow);

        acClient.setDisable(lbShow);
        acSearchItem.setDisable(!lbShow);
        txtField02.setDisable(!lbShow);
        txtField03.setDisable(!lbShow);
        txtField05.setDisable(!lbShow);
        txtField17.setDisable(!lbShow);
        txtField11.setDisable(!lbShow);
        txtField13.setDisable(!lbShow);
        txtField09.setDisable(!lbShow);
        txtField10.setDisable(!lbShow);
        txtField06.setDisable(!lbShow);
        txtField07.setDisable(!lbShow);
        
        if (lbShow)
            txtSeeks99.requestFocus();
        else
            cmdButton01.requestFocus();
    }
      
    public void clearFields(){
        txtField90.setText("");
        txtField91.setText("");
        txtSeeks99.setText("");
        
        txtField02.setText("");
        txtField03.setText("");
        txtField17.setText("");
        txtField05.setText("");
        txtField06.setText("");
        txtAddress.setText("");
        
        txtField07.setText("0.00");        
        txtField09.setText("0.00");
        txtField10.setText("0.00");
        txtField11.setText("0.00");
        txtField13.setText("0.00");
        
        lblSubTotal.setText("0.00");
        lblVatExclsv.setText("0.00");
        lblDiscount.setText("0.00");
        lblNetSales.setText("0.00");
        lblAddVat.setText("0.00");
        lblAmountDue.setText("0.00");
        
        lblTranStat.setText("-");
        lblOrderNo.setText("-");
        setTranStat("-1");
        
        psOldRec = "";
        data.clear();
    }
      
    @Override
    public void setGRider(GRider foGRider){this.poGRider = foGRider;}
    public void setBranchCd(String fsBranchCd){this.psBranchCd = fsBranchCd;}
    
    private final String pxeModuleName = "InventoryController";
    private final String pxeDefaultDte = "1900-01-01";
    private final String pxeDateFormat = "yyyy-MM-dd";
    private static GRider poGRider;
    private XMSalesOrder poTrans;
    
    private int pnEditMode;
    private String psBranchCd = "";
    private String psInvTypeCd = "SP";
    private boolean pbLoaded = false;
    private int pnIndex = -1;
    private int pnRow = 0;
    private String psOldRec = "";
    
    ObservableList<String> cSearchTp = FXCollections.observableArrayList("Barcode", "Description");
   
    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
            
        if(!nv){ /*Lost Focus*/
            switch (lnIndex){
                case 2:
                    if (CommonUtils.isDate(lsValue, pxeDateFormat)){
                        poTrans.setMaster("dTransact", CommonUtils.toDate(lsValue));
                    } else{
                        ShowMessageFX.Warning("Invalid date entry.", pxeModuleName, "Date format must be yyyy-MM-dd (e.g. 1991-07-07)");
                        poTrans.setMaster("dTransact", CommonUtils.toDate(pxeDefaultDte));
                    }
                    txtField.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster("dTransact")));
                    break;
                case 3:
                    if (lsValue.equals("")){
                        poTrans.setMaster("dExpected", CommonUtils.toDate(pxeDefaultDte));
                        txtField.setText("");
                        break;
                    }
                    
                    if (CommonUtils.isDate(lsValue, pxeDateFormat)){
                        poTrans.setMaster("dExpected", CommonUtils.toDate(lsValue));
                    } else{
                        ShowMessageFX.Warning("Invalid date entry.", pxeModuleName, "Date format must be yyyy-MM-dd (e.g. 1991-07-07)");
                        poTrans.setMaster("dExpected", CommonUtils.toDate(pxeDefaultDte));
                    }
                    txtField.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster("dExpected")));
                    break;
                case 15:
                    if (lsValue.equals("")){
                        poTrans.setMaster("dDueDatex", CommonUtils.toDate(pxeDefaultDte));
                        txtField.setText("");
                        break;
                    }
                    
                    if (CommonUtils.isDate(lsValue, pxeDateFormat)){
                        poTrans.setMaster("dDueDatex", CommonUtils.toDate(lsValue));
                    } else{
                        ShowMessageFX.Warning("Invalid date entry.", pxeModuleName, "Date format must be yyyy-MM-dd (e.g. 1991-07-07)");
                        poTrans.setMaster("dDueDatex", CommonUtils.toDate(pxeDefaultDte));
                    }
                    txtField.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster("dDueDatex")));
                    break;
                case 9:
                    poTrans.setMaster("nDiscount", Double.parseDouble(txtField.getText()));
                    txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "0.00"));
                    loadDetail2Grid();
                    break;
                case 10:
                    poTrans.setMaster("nAddDiscx", Double.parseDouble(txtField.getText()));
                    txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAddDiscx").toString()), "#,##0.00"));
                    loadDetail2Grid();
                    break;
                case 11:
                    poTrans.setMaster("nFreightx", Double.parseDouble(txtField.getText()));
                    txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nFreightx").toString()), "#,##0.00"));
                    loadDetail2Grid();
                    break;
            }
        } else{
            switch (lnIndex){
                case 2:
                case 3:
                case 15:
                    if (lsValue.equals("")) break;
                    
                    try {
                        txtField.setText(CommonUtils.xsDateShort(lsValue));
                    } catch (ParseException e) {
                        ShowMessageFX.Error(e.getMessage(), pxeModuleName, null);
                    }
                    
                    break;
                default:
            }
            
            if (lnIndex == 5 || lnIndex == 17 || lnIndex == 90 || lnIndex == 91 || lnIndex == 99 )
                pnIndex = lnIndex;
            else pnIndex = -1;

            txtField.requestFocus();
            txtField.selectAll();
        }    
    };
    
    final ChangeListener<? super Boolean> txtArea_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        TextArea txtField = (TextArea)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
        
        if(!nv){ /*Lost Focus*/            
            switch (lnIndex){
                case 6: /*sRemarksx*/
                    if (lsValue.length() > 256) lsValue = lsValue.substring(0, 256);
                    
                    poTrans.setMaster("sRemarksx", CommonUtils.TitleCase(lsValue));
                    txtField.setText((String)poTrans.getMaster("sRemarksx"));
            }
        }else{ 
            pnIndex = -1;
            txtField.selectAll();
        }
    };
    
    private void setTranStat(String fsValue){
        switch (fsValue){
            case "0":
                lblTranStat.setText("NEW"); break;
            case "1":
                lblTranStat.setText("CLOSED"); break;
            case "2":
                lblTranStat.setText("POSTED"); break;
            case "3":
                lblTranStat.setText("CANCELLED"); break;
            case "4":
                lblTranStat.setText("VOIDED"); break;
            default:
                lblTranStat.setText("UNKNOWN");
        } 
    }

    public void txtField_KeyPressed(KeyEvent event) {
        TextField txtField = (TextField)event.getSource();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        JSONObject loJSON;
             
        if (event.getCode() == F3){
            switch (lnIndex){
                case 5: //client name     
                    if (lsValue.equals("")) return;
                    
                    loJSON = poTrans.SearchMaster(lnIndex, lsValue);
                    if (loJSON != null){
                        poTrans.setMaster(lnIndex, (String) loJSON.get("sClientID"));
                        txtField.setText((String) loJSON.get("sClientNm"));
                        txtAddress.setText((String) loJSON.get("xAddressx"));
                    } else {
                        poTrans.setMaster(lnIndex, "");
                        txtField.setText("");
                        txtAddress.setText("");
                    }
                    break;
                case 17: //search term
                    loJSON = poTrans.SearchMaster(lnIndex, lsValue);
                    if (loJSON != null){
                        poTrans.setMaster(lnIndex, (String) loJSON.get("sTermCode"));
                        txtField.setText((String) loJSON.get("sDescript"));
                    } else {
                        poTrans.setMaster(lnIndex, "");
                        txtField.setText("");
                    }
                    break;
                case 99: //item search
                    if (lsValue.equals("")) return;
                    
                    if (poTrans.SearchDetail(pnRow, event.getCode() == ENTER ? 80 : 81, lsValue)){
                        txtSeeks99.setText(""); 
                        loadDetail2Grid(); 
                    }else
                        ShowMessageFX.Warning(poTrans.getErrMsg(), pxeModuleName, poTrans.getWarnMsg());
            }
        }
        
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

    public void txtFieldArea_KeyPressed(KeyEvent event) {
        TextArea txtArea = (TextArea)event.getSource();
             
        if (null != event.getCode())switch (event.getCode()) {
            case ENTER:
            case DOWN:
                CommonUtils.SetNextFocus(txtArea);
                break;
            case UP:
                CommonUtils.SetPreviousFocus(txtArea);
                break;
            default:
                break;
        } 
    }
    
    public void loadDetail2Grid() {
        int lnCtr;
        int lnRow = poTrans.getDetailCount();
        
        JSONObject loJSON;
        String lsBrandNme;
        String lsInvTypNm;
        
        String lsStockIDx;
        String lsSerialID;
        double lnSelPrice;
        double lnDiscount;
        double lnAddDiscx;
        double lnRowTotal;
        double lnTotlOrdr;
        int lnQuantity;

        data.clear();
        lnTotlOrdr = 0;
        for(lnCtr = 0; lnCtr <= lnRow -1; lnCtr++){
            lsStockIDx = (String) poTrans.getDetail(lnCtr, "sStockIDx");
            lsSerialID = (String) poTrans.getDetail(lnCtr, "sSerialID");
            lnSelPrice = Double.valueOf(poTrans.getDetail(lnCtr, "nUnitPrce").toString());
            lnDiscount = Double.valueOf(poTrans.getDetail(lnCtr, "nDiscount").toString());
            lnAddDiscx = Double.valueOf(poTrans.getDetail(lnCtr, "nAddDiscx").toString());
            lnQuantity = (int) poTrans.getDetail(lnCtr, "nQuantity");

            lnRowTotal = lnQuantity * lnSelPrice;
            lnRowTotal = lnRowTotal - (lnRowTotal * lnDiscount);
            lnRowTotal = lnRowTotal - lnAddDiscx;
            
            lnTotlOrdr = lnTotlOrdr + lnRowTotal;
            
            InvMaster loInv = new InvMaster(poGRider, psBranchCd, true);
            if (loInv.SearchStock(lsStockIDx, lsSerialID, true, true)){
                lsBrandNme = "";
                lsInvTypNm = "";
                
                if (!"".equals((String) loInv.getInventory("sBrandCde"))){
                    XMBrand loBrand = new XMBrand(poGRider, psBranchCd, true);
                    loJSON = loBrand.searchBrand((String) loInv.getInventory("sBrandCde"), true);
                    if (loJSON != null)
                        lsBrandNme = (String) loJSON.get("sDescript");
                }
                
                if (!"".equals((String) loInv.getInventory("sInvTypCd"))){
                    XMInventoryType loType = new XMInventoryType(poGRider, psBranchCd, true);
                    loJSON = loType.searchInvType((String) loInv.getInventory("sInvTypCd"), true);
                    if (loJSON != null)
                        lsInvTypNm = (String) loJSON.get("sDescript");;
                }

                data.add(new TableModel(String.valueOf(lnCtr + 1), 
                                    (String) loInv.getInventory("sBarCodex"), 
                                    (String) loInv.getInventory("sDescript"), 
                                    lsInvTypNm,
                                    lsBrandNme,
                                    "-", //String.valueOf(loInv.getMaster("nQtyOnHnd"))
                                    CommonUtils.NumberFormat(lnSelPrice, "#,##0.00"),
                                    String.valueOf(lnQuantity),
                                    CommonUtils.NumberFormat(lnDiscount, "#,##0.00"),
                                    CommonUtils.NumberFormat(lnAddDiscx, "#,##0.00"),
                                    CommonUtils.NumberFormat(lnRowTotal, "#,##0.00"),
                                    "",
                                    "",
                                    "",
                                    ""));
                
            } else {
                data.add(new TableModel(String.valueOf(lnCtr + 1), 
                                    "", 
                                    "", 
                                    "",
                                    "",
                                    "0",
                                    "0.00",
                                    "0",
                                    "0.00",
                                    "0.00",
                                    "0.00",
                                    "",
                                    "",
                                    "",
                                    ""));
            }
            
        } 
        
        txtField07.setText(CommonUtils.NumberFormat(lnTotlOrdr, "#,##0.00"));
        
        computeTotal();
        pnRow = lnRow -1;
    }

    public void loadTransaction() {
        lblOrderNo.setText((String) poTrans.getMaster("sTransNox"));
        txtField02.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dTransact")));
        
        //check the dates
        if (!String.valueOf(poTrans.getMaster("dExpected")).equals(pxeDefaultDte)){
            txtField03.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dExpected")));
        }
        
        txtField06.setText((String) poTrans.getMaster("sRemarksx"));
        txtField07.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAmtPaidx").toString()), "###0.00"));
        txtField09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "0.00"));
        txtField10.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAddDiscx").toString()), "###0.00"));
        txtField11.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nFreightx").toString()), "###0.00"));
        
        JSONObject loJSON;
        
        //load term
        if (!poTrans.getMaster("sTermCode").toString().equals("")){
            XMTerm loTerm = new XMTerm(poGRider, psBranchCd, pbLoaded);
            loJSON = loTerm.searchTerm((String) poTrans.getMaster("sTermCode"), true);
            if (loJSON != null)
                txtField17.setText((String) loJSON.get("sDescript"));
        }
        
        //load inventory type
        
        //load client info
        if (!poTrans.getMaster("sClientID").toString().equals("")){
            XMClient loClient = new XMClient(poGRider, psBranchCd, true);
            loJSON = loClient.SearchClient((String) poTrans.getMaster("sClientID"), true);
            if (loJSON != null){
                txtField05.setText((String) loJSON.get("sClientNm"));
                txtAddress.setText((String) loJSON.get("xAddressx"));
            }
        }
        
        loadDetail2Grid();
        setTranStat((String) poTrans.getMaster("cTranStat"));
        
        psOldRec = lblOrderNo.getText();
    }

    @FXML
    private void tblSalesMaster_Click(MouseEvent event) {
        int lnRow = tblSalesMaster.getSelectionModel().getSelectedIndex();        
        pnRow = lnRow;
        
        if (event.getClickCount() == 2){              
            boolean lbShow = pnEditMode == EditMode.ADDNEW || 
                                pnEditMode == EditMode.UPDATE;

            if (!lbShow) return;
            
            if (poTrans.getDetail(pnRow, "sStockIDx").equals("")) return;

            ModifySPDetailController spDetail = new ModifySPDetailController();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("child/modifySPDetail.fxml"));
            spDetail.setEditMode(EditMode.UPDATE);
            
            try {
                InvMaster loInv = new InvMaster(poGRider, psBranchCd, true);
                if (loInv.SearchStock((String) poTrans.getDetail(pnRow, "sStockIDx"), (String) poTrans.getDetail(pnRow, "sSerialID"), true, true)){
                    spDetail.setStockIDx((String) loInv.getInventory("sStockIDx"));
                    spDetail.setBarCodex((String) loInv.getInventory("sBarCodex"));
                    spDetail.setDescript((String) loInv.getInventory("sDescript"));
                    spDetail.setQtyOnHnd((int) loInv.getMaster("nQtyOnHnd"));
                }
                
                spDetail.setUnitPrce(Double.valueOf(poTrans.getDetail(pnRow, "nUnitPrce").toString()));
                spDetail.setQuantity((int)poTrans.getDetail(pnRow, "nQuantity"));
                spDetail.setDiscRate(Double.valueOf(poTrans.getDetail(pnRow, "nDiscount").toString()));
                spDetail.setAddDiscx(Double.valueOf(poTrans.getDetail(pnRow, "nAddDiscx").toString()));

                fxmlLoader.setController(spDetail);
                fxmlLoader.load();

                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));

                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.showAndWait();

                if (!spDetail.isCancelled() && spDetail.getEditMode()== EditMode.UPDATE){
                        poTrans.setDetail(pnRow, "nUnitPrce", Double.valueOf(spDetail.getUnitPrce().toString()));
                        poTrans.setDetail(pnRow, "nQuantity", spDetail.getQuantity());
                        poTrans.setDetail(pnRow, "nDiscount", Double.valueOf(spDetail.getDiscRate().toString()));
                        poTrans.setDetail(pnRow, "nAddDiscx", Double.valueOf(spDetail.getAddDiscx().toString()));
                        
                        if (spDetail.getQuantity() > 0 && pnRow + 1 == poTrans.getDetailCount())
                            poTrans.addDetail();
                        
                        loadDetail2Grid();
                }
            } catch (IOException | SQLException e) {
                ShowMessageFX.Error(e.getMessage(), pxeModuleName, "Please inform MIS department.");
                System.exit(1);
            }
        }
    }
    
    private void computeTotal(){
        double lnSubTotal = Double.valueOf(poTrans.getMaster("nTranTotl").toString()) + Double.valueOf(poTrans.getMaster("nFreightx").toString());
        double lnVATRatex = Double.valueOf(poTrans.getMaster("nVATRatex").toString());
        double lnVATExcls = lnSubTotal / lnVATRatex;
        double lnDiscount = lnVATExcls * Double.valueOf(poTrans.getMaster("nDiscount").toString());
        double lnAddDiscx = Double.valueOf(poTrans.getMaster("nAddDiscx").toString()) / lnVATRatex;
        double lnVATSales = lnVATExcls - (lnDiscount + lnAddDiscx);
        double lnVATAmntx = lnVATSales * 0.12;
        double lnAmntDuex = lnVATSales + lnVATAmntx;        
        
        lblSubTotal.setText(String.valueOf(CommonUtils.NumberFormat(lnSubTotal, "#,##0.00")));
        lblVatExclsv.setText(String.valueOf(CommonUtils.NumberFormat(lnVATExcls, "#,##0.00")));
        lblDiscount.setText(String.valueOf(CommonUtils.NumberFormat(lnDiscount + lnAddDiscx, "#,##0.00")));
        lblNetSales.setText(String.valueOf(CommonUtils.NumberFormat(lnVATSales, "#,##0.00")));
        lblAddVat.setText(String.valueOf(CommonUtils.NumberFormat(lnVATAmntx, "#,##0.00")));        
        lblAmountDue.setText(String.valueOf(CommonUtils.NumberFormat(lnAmntDuex, "#,##0.00")));
    }
}
