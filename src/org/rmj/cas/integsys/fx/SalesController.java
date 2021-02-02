/*
    iMac 2018-08-03 08:30am
        Started creating this class.
*/
package org.rmj.cas.integsys.fx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.F3;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.CustomTextField;
import org.json.simple.JSONObject;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.SQLUtil;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.callback.IFXML;
import org.rmj.appdriver.constants.CRMEvent;
import org.rmj.cas.client.base.XMClient;
import org.rmj.cas.integsys.fx.child.ModifySPDetailController;
import org.rmj.cas.inventory.base.InvMaster;
import org.rmj.cas.parameter.agent.XMBrand;
import org.rmj.cas.parameter.agent.XMInventoryType;
import org.rmj.cas.parameter.agent.XMTerm;
import org.rmj.sales.agentfx.XMSales;

public class SalesController implements Initializable, IFXML {
    @FXML private BorderPane bpContent;
    @FXML private Button cmdButton05;
    @FXML private Button cmdButton06;
    @FXML private Button cmdButton07;
    @FXML private Button cmdButton01;
    @FXML private Button cmdButton02;
    @FXML private TableView tblSalesMaster;
    @FXML private TableColumn index01;
    @FXML private TableColumn index02;
    @FXML private TableColumn index03;
    @FXML private TableColumn index04;
    @FXML private TableColumn index05;
    @FXML private TableColumn index06;
    @FXML private TableColumn index07;
    @FXML private TableColumn index08;
    @FXML private TableColumn index09;
    @FXML private TableColumn index10;
    @FXML private TableColumn index11;
    @FXML private TextField txtField03;
    @FXML private CustomTextField txtField15;
    @FXML private TextField txtField13;
    @FXML private TextField txtField11;
    @FXML private TextField txtField12;
    @FXML private TextField txtField09;
    @FXML private TextArea txtField06;
    @FXML private CustomTextField txtField07;
    @FXML private TextField txtField14;
    @FXML private Label lblSubTotal;
    @FXML private Label lblVatExclsv;
    @FXML private Label lblDiscount;
    @FXML private Label lblAmountDue;
    @FXML private Label lblNetSales;
    @FXML private Label lblAddVat;
    @FXML private Button cmdButton08;
    @FXML private Button cmdButton09;
    @FXML private Button cmdButton10;
    @FXML private CustomTextField txtField90;
    @FXML private CustomTextField txtField91;
    @FXML private CustomTextField txtSeeks99;
    @FXML private Label lblOrderNo;
    @FXML private Label lblTranStat;
    @FXML private CustomTextField txtField04;
    @FXML private TextField txtAddress;
    @FXML private Button cmdButton03;
    @FXML private Label lblField00;
    @FXML private Label lblField01;
    @FXML private Label lblField02;
    @FXML private Button cmdButton12;
    @FXML private Button cmdButton11;
    @FXML private Label lblField03;
    @FXML private Label lblField04;
    @FXML private MenuItem mnuEndShift;
    @FXML private MenuItem mnuNewDay;
    @FXML private MenuItem mnuCashDrawer;
    @FXML private MenuItem mnuLogout;
    @FXML private MenuItem mnuCancelInvoice;
    @FXML private MenuItem mnuReprintInvoice;
    
    public static final Image search = new Image("org/rmj/cas/integsys/images/search.png");
    private final String pxeModuleName = "Sales";
    private final String pxeDefaultDte = "1900-01-01";
    private final String pxeDateFormat = "yyyy-MM-dd";
    private final String pxeCurrentDate = java.time.LocalDate.now().toString();
    private static GRider poGRider;
    private XMSales poTrans;
    
    private int pnEditMode;
    private String psBranchCd = "";
    private String psOldRec = "";
    private String psTermCode = "";
    private String psInvTypeCd = "SP";
    private boolean pbLoaded = false;
    private int pnIndex = -1;
    private int pnRow = 0;
    private int pnOldRow = 0;
    private int pnReturnMP = 7;
    private int pnReturnSP = 180;
    
    
    private double xOffset = 0; 
    private double yOffset = 0;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (psBranchCd.equals("")) psBranchCd = poGRider.getBranchCode();
  
        poTrans = new XMSales(poGRider, psBranchCd, false);
        cmdButton01.setOnAction(this::cmdButton_Click);
        cmdButton02.setOnAction(this::cmdButton_Click);
        cmdButton03.setOnAction(this::cmdButton_Click);
        cmdButton05.setOnAction(this::cmdButton_Click);
        cmdButton06.setOnAction(this::cmdButton_Click);
        cmdButton07.setOnAction(this::cmdButton_Click);
        cmdButton08.setOnAction(this::cmdButton_Click);
        cmdButton09.setOnAction(this::cmdButton_Click);
        cmdButton10.setOnAction(this::cmdButton_Click);
        cmdButton11.setOnAction(this::cmdButton_Click);
        cmdButton12.setOnAction(this::cmdButton_Click);
        
        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
        txtField04.setOnKeyPressed(this::txtField_KeyPressed);
        txtField07.setOnKeyPressed(this::txtField_KeyPressed);
        txtField15.setOnKeyPressed(this::txtField_KeyPressed);
        txtField09.setOnKeyPressed(this::txtField_KeyPressed);
        txtField11.setOnKeyPressed(this::txtField_KeyPressed);
        txtField12.setOnKeyPressed(this::txtField_KeyPressed);
        txtField13.setOnKeyPressed(this::txtField_KeyPressed);
        txtField14.setOnKeyPressed(this::txtField_KeyPressed);
        txtField15.setOnKeyPressed(this::txtField_KeyPressed);
        txtField06.setOnKeyPressed(this::txtFieldArea_KeyPressed);
        txtField90.setOnKeyPressed(this::txtField_KeyPressed);
        txtField91.setOnKeyPressed(this::txtField_KeyPressed);
        txtSeeks99.setOnKeyPressed(this::txtField_KeyPressed);
        
        txtField03.focusedProperty().addListener(txtField_Focus);
        txtField04.focusedProperty().addListener(txtField_Focus);
        txtField06.focusedProperty().addListener(txtArea_Focus);
        txtField07.focusedProperty().addListener(txtField_Focus);
        txtField09.focusedProperty().addListener(txtField_Focus);
        txtField11.focusedProperty().addListener(txtField_Focus);
        txtField12.focusedProperty().addListener(txtField_Focus);
        txtField13.focusedProperty().addListener(txtField_Focus);
        txtField14.focusedProperty().addListener(txtField_Focus);
        txtField15.focusedProperty().addListener(txtField_Focus);
        txtField90.focusedProperty().addListener(txtField_Focus);
        txtField91.focusedProperty().addListener(txtField_Focus);
        
        txtField90.setLeft(new ImageView(search));
        txtField91.setLeft(new ImageView(search));
        txtSeeks99.setLeft(new ImageView(search));
        txtField07.setLeft(new ImageView(search));
        txtField04.setLeft(new ImageView(search));
        txtField15.setLeft(new ImageView(search));
        
        mnuCancelInvoice.setOnAction(this::mnuItem_Click);
        mnuCashDrawer.setOnAction(this::mnuItem_Click);
        mnuEndShift.setOnAction(this::mnuItem_Click);
        mnuLogout.setOnAction(this::mnuItem_Click);
        mnuNewDay.setOnAction(this::mnuItem_Click);
        mnuReprintInvoice.setOnAction(this::mnuItem_Click);
        
        String lsDate = poTrans.getDailySales().DailySummary().getTransactionDate();
        lsDate = lsDate.substring(0, 4) + "-" + lsDate.substring(4, 6) + "-" + lsDate.substring(6, 8);
        lblField04.setText(lsDate);
        
        String lsTranMode = "";
        if (!System.getProperty("pos.clt.tran.mode").equalsIgnoreCase("a")) lsTranMode = " (Training Mode)";
        
        //set the pos date on system properties
        System.setProperty("pos.clt.date", SQLUtil.dateFormat(poGRider.getServerDate(), SQLUtil.FORMAT_SHORT_DATEX));
        
        lblField00.setText("IntegSysFX POS System v1.0" + lsTranMode);
        lblField01.setText("Accreditation No.: " + System.getProperty("pos.clt.accrd.no"));
        lblField02.setText("Machine No.: " + System.getProperty("pos.clt.crm.no"));
        
        clearFields();
        initGrid();
        
        pnEditMode = EditMode.UNKNOWN;
        initButton(pnEditMode);
        
        pnRow = 0;
        pnOldRow = 0;
        pbLoaded = true;
    }

    public void ComboBox_KeyPressed(KeyEvent event) {
        ComboBox combo = (ComboBox)event.getSource();
             
        if (null != event.getCode())switch (event.getCode()) {
            case ENTER:
            case DOWN:
                CommonUtils.SetNextFocus(combo);
                break;
            case UP:
                CommonUtils.SetPreviousFocus(combo);
                break;
            default:
                break;
        } 
    }

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
                        txtField90.setText(lblOrderNo.getText()); 
                        txtField91.setText(txtField04.getText()); 
                        pnEditMode = poTrans.getEditMode();
                    }
                }
                break;
            case "cmdButton11":
                if (pnIndex == 90 || pnIndex == 91){
                    if (pnIndex == 90)
                        lsButton = txtField90.getText();
                    else
                        lsButton = txtField91.getText();
                    
                    if (poTrans.SearchTransaction(lsButton, (pnIndex == 90))){
                        loadTransaction();
                        txtField90.setText(lblOrderNo.getText()); 
                        txtField91.setText(txtField04.getText()); 
                        pnEditMode = poTrans.getEditMode();
                    }
                }break;
            case "cmdButton12":
                if (poTrans.SearchDetail(pnRow, 81, txtSeeks99.getText())){
                        poTrans.addDetail();
                        loadDetail2Grid(); 
                        txtSeeks99.setText(""); 
                    }else
                        ShowMessageFX.Warning(poTrans.getErrMsg(), pxeModuleName, poTrans.getWarnMsg());
                break;
            case "cmdButton02": //new             
                if (poTrans.newTransaction()== true){
                    clearFields();
                    poTrans.setMaster("sBranchCd", psBranchCd);
                    poTrans.setMaster("sInvTypCd", psInvTypeCd);
                    loadTransaction();
                    pnEditMode = poTrans.getEditMode();
                }break;
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
            case "cmdButton05":
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
            case "cmdButton06":
                break;
            case "cmdButton07":
                if(ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to discard changes?") == true){
                    clearFields();
                    pnEditMode = EditMode.UNKNOWN;
                } 
                break;
            case "cmdButton08": //pay
                if (!psOldRec.equals("")){
                    if (psOldRec.equals("")){
                        ShowMessageFX.Warning(null, pxeModuleName, "Please select a transaction to pay.");
                        return;
                    }else if(!poTrans.getMaster("cTranStat").equals("0")){
                        ShowMessageFX.Warning("Transaction already "+ lblTranStat.getText()+ "..!", pxeModuleName, "Unable to pay transaction..");
                        return;
                    }
                    
                    if (poTrans.closeTransaction(psOldRec)){
                        ShowMessageFX.Information(null, pxeModuleName, "Transaction PAYED successfully.");
                        clearFields();
                        initGrid();
                        pnEditMode = EditMode.UNKNOWN;
                        initButton(pnEditMode);
                    }
                } else 
                    ShowMessageFX.Warning(null, pxeModuleName, "Please select a record to pay!");
                break;
            case "cmdButton09":
                if (!psOldRec.equals("")){
                    if (psOldRec.equals("")){
                        ShowMessageFX.Warning(null, pxeModuleName, "Please select a transaction to void.");
                        return;
                    }else if(!poTrans.getMaster("cTranStat").equals("0")){
                        ShowMessageFX.Warning("Transaction already "+ lblTranStat.getText()+ "..!", pxeModuleName, "Unable to void transaction..");
                        return;
                    }

                    if (ShowMessageFX.YesNo(null, pxeModuleName, "Do you want to void this transaction?") == true){
                        if (poTrans.voidTransaction((String) poTrans.getMaster("sTransNox"))){
                            ShowMessageFX.Information(null, pxeModuleName, "Transaction voided successfully.");
                            if (poTrans.openTransaction(psOldRec)) {
                                loadTransaction(); 
                                pnEditMode = poTrans.getEditMode();
                            }
                        } else 
                            ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                    } 
                } else 
                    ShowMessageFX.Warning(null, pxeModuleName, "Please select a record to void!");
                
                break;
            case "cmdButton10":
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
            default:
                ShowMessageFX.Warning(null, pxeModuleName, "Button with name " + lsButton + " not registered.");
                return;
        }
        initButton(pnEditMode);
    }
    
    public void txtField_KeyPressed(KeyEvent event) {
        TextField txtField = (TextField)event.getSource();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        JSONObject loJSON;

        if (event.getCode() == F3 || event.getCode() == ENTER){
            switch (lnIndex){
                case 15: //search term
                    loJSON = poTrans.SearchMaster(lnIndex, lsValue);
                    if (loJSON != null){
                        poTrans.setMaster(lnIndex, (String) loJSON.get("sTermCode"));
                        txtField.setText((String) loJSON.get("sDescript"));
                    } else {
                        poTrans.setMaster(lnIndex, "");
                        txtField.setText("");
                    }
                    break;
                case 4: //client name     
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
                case 7: //salesman
                    if (lsValue.equals("")) return;
                    loJSON = poTrans.SearchMaster(lnIndex, lsValue);
                    if (loJSON != null){
                        poTrans.setMaster(lnIndex, (String) loJSON.get("sEmployID"));
                        txtField.setText((String) loJSON.get("sClientNm"));
                    } else {
                        poTrans.setMaster(lnIndex, "");
                        txtField.setText("");
                    }
                    break;
                case 90: //transaction no
                case 91: //client name                    
                    if (poTrans.SearchTransaction(lsValue, (lnIndex == 90))){
                        loadTransaction();
                        txtField90.setText(lblOrderNo.getText()); 
                        txtField91.setText(txtField04.getText()); 
                        pnEditMode = poTrans.getEditMode();
                    }
                    break;
                case 99: //item search
                    //if (lsValue.equals("")) return;
                    if (poTrans.SearchDetail(pnRow, event.getCode() == ENTER ? 80 : 81, lsValue)){
                        poTrans.addDetail();
                        loadDetail2Grid(); 
                        txtSeeks99.setText(""); 
                    }else
                        ShowMessageFX.Warning(poTrans.getErrMsg(), pxeModuleName, poTrans.getWarnMsg());
                    break;
                case 11:
                case 12:
                    if (poTrans.searchDiscount(lnIndex == 11)){
                        txtField11.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "0.00"));
                        txtField12.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAddDiscx").toString()), "0.00"));
                        loadDetail2Grid();
                    }
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
                event.consume();
                CommonUtils.SetNextFocus(txtArea);
                break;
            case UP:
                CommonUtils.SetPreviousFocus(txtArea);
                break;
            default:
                break;
        } 
    }

    public void clearFields() {      
        txtField03.setText("");
        txtField04.setText("");
        txtField06.setText("");
        txtField07.setText("");
        txtField15.setText("");
        
        txtField09.setText("0.00");
        txtField11.setText("0.00");
        txtField12.setText("0.00");
        txtField13.setText("0.00");
        txtField14.setText("0.00");
        
        lblSubTotal.setText("0.00");
        lblVatExclsv.setText("0.00");
        lblDiscount.setText("0.00");
        lblNetSales.setText("0.00");
        lblAddVat.setText("0.00");
        lblAmountDue.setText("0.00");

        lblTranStat.setText("-");
        lblOrderNo.setText("-");
        txtAddress.setText("");
        
        txtField90.setText("");
        txtField91.setText("");
        txtSeeks99.setText("");
        
        setTranStat("-1");
        
        psOldRec = "";
        data.clear();
    }

    public void initButton(int fnValue) {
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
        
        cmdButton01.setVisible(!lbShow);
        cmdButton02.setVisible(!lbShow);
        cmdButton03.setVisible(!lbShow);
        cmdButton08.setVisible(!lbShow);
        cmdButton09.setVisible(!lbShow);
                
        cmdButton05.setVisible(lbShow);
        cmdButton06.setVisible(lbShow);
        cmdButton07.setVisible(lbShow);
        cmdButton10.setVisible(lbShow);
        
        txtField90.setDisable(lbShow);
        txtField91.setDisable(lbShow);
        cmdButton11.setDisable(lbShow);
        
        txtSeeks99.setDisable(!lbShow);
        cmdButton12.setDisable(!lbShow);
        
        txtField03.setDisable(!lbShow);
        txtField04.setDisable(!lbShow);
        txtField06.setDisable(!lbShow);
        txtField07.setDisable(!lbShow);
        txtField09.setDisable(!lbShow);
        txtField11.setDisable(!lbShow);
        txtField12.setDisable(!lbShow);
        txtField13.setDisable(!lbShow);
        txtField14.setDisable(!lbShow);
        txtField15.setDisable(!lbShow);
        txtField14.setEditable(false);
        
        if (lbShow)
            txtSeeks99.requestFocus();
        else
            txtField91.requestFocus();
    }
    
    private void mnuItem_Click(ActionEvent event){
        String mnuItem = ((MenuItem)event.getSource()).getId().toLowerCase();
        switch(mnuItem){
            case "mnuendshift":
                if (poTrans.PrintXReading()){
                    ShowMessageFX.Information("Shift successfully closed.", "Notice", "Shift Ended");
                    System.exit(0);
                }
                break;
            case "mnunewday":
                if (!ShowMessageFX.YesNo("Declaring End-Of-Day will finalize the POS for this day.\nDo you want to continue?", "Confirm", "Declaring End-Of-Day Transaction"))
                return;
                    if (poTrans.PrintXReading()){
                        if (poTrans.PrintZReading()){
                            ShowMessageFX.Information("You have successfully declared End-Of-Day.", "Notice", "End-Of-Day");
                            System.exit(0);
                        }
                    }
                break;
            case "mnulogout":
                if (ShowMessageFX.YesNo("Do you want to print cashier sales report?", "Confirm", "Please confirm...")==true) {
                    poTrans.getDailySales().PrintCashierSales(poTrans.getDailySales().DailySummary().getTransactionDate(), 
                                                                poTrans.getDailySales().DailySummary().getMachineNo(), 
                                                                poTrans.getDailySales().DailySummary().getCashier());
                }
                
                CommonUtils.createEventLog(poGRider, poGRider.getBranchCode() + System.getProperty("pos.clt.trmnl.no")
                        , CRMEvent.CASHIER_LOGOUT, "Date: " + poTrans.getDailySales().DailySummary().getTransactionDate() + "; " + "Cashier: " + poTrans.getDailySales().DailySummary().getCashier()
                        , System.getProperty("pos.clt.crm.no"));
                
                System.exit(0);
                break;
            case "mnucashdrawer":
                if (!poTrans.OpenCashDrawer())
                    ShowMessageFX.Warning(poTrans.getErrMsg(), "Warning", null);
                break;
            case "mnucancelinvoice":
                if (!psOldRec.equals("")){
                    if (ShowMessageFX.YesNo("Cancelling paid transaction will also cancel the invoice.", "Confirm", "Do you want to continue?")){
                        if (poTrans.cancelTransaction(psOldRec)){
                            ShowMessageFX.Information(null, pxeModuleName, "Transaction cancelled successfully.");
                            clearFields();
                            initGrid();
                            pnEditMode = EditMode.UNKNOWN;
                            initButton(pnEditMode);
                        } else
                            ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                    }
                }else
                    ShowMessageFX.Warning("Please select a record to update!", pxeModuleName, "No record to update.");
                break;     
            case "mnureprintinvoice":
                if (!psOldRec.equals("")){
                    if (ShowMessageFX.YesNo(null, "Confirm", "Do you want to re-print invoice?")){
                        if (poTrans.getMaster("cTranStat").equals("3")){
                            if (poTrans.PrintCancelledInvoice(psOldRec)){
                                ShowMessageFX.Information(null, pxeModuleName, "Transaction printed successfully.");
                                clearFields();
                                initGrid();
                                pnEditMode = EditMode.UNKNOWN;
                                initButton(pnEditMode);
                            } else
                                ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                        } else {
                            if (poTrans.PrintInvoice(psOldRec)){
                                ShowMessageFX.Information(null, pxeModuleName, "Transaction printed successfully.");
                                clearFields();
                                initGrid();
                                pnEditMode = EditMode.UNKNOWN;
                                initButton(pnEditMode);
                            } else
                                ShowMessageFX.Warning(poTrans.getWarnMsg(), pxeModuleName, poTrans.getErrMsg());
                        }
                    }
                }else
                    ShowMessageFX.Warning("Please select a record to update!", pxeModuleName, "No record to update.");
                break;   
            default:
                 ShowMessageFX.Warning("Button"+ mnuItem+ "is not registered...", "Warning", null);
                break;
        }
    
    }

    public void initGrid() {
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
                
                if (!lsSerialID.equals(""))
                    data.add(new TableModel(String.valueOf(lnCtr + 1), 
                                            (String) loInv.getSerial("sSerial01"), 
                                            (String) loInv.getInventory("sDescript"), 
                                            CommonUtils.NumberFormat(lnSelPrice, "#,##0.00"),
                                            "-", //"1"
                                            String.valueOf(lnQuantity),
                                            CommonUtils.NumberFormat(lnDiscount*100, "#,##0.00"),
                                            CommonUtils.NumberFormat(lnAddDiscx, "#,##0.00"),
                                            CommonUtils.NumberFormat(lnRowTotal, "#,##0.00"),
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            ""));
                else
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
        
        txtField09.setText(CommonUtils.NumberFormat(lnTotlOrdr, "#,##0.00"));
        
        computeTotal();
        pnRow = lnRow -1;
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

    public void loadTransaction() {
        lblOrderNo.setText((String) poTrans.getMaster("sTransNox"));        
        
        txtField03.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dTransact")));
        
        txtField09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nTranTotl").toString()), "#,##0.00"));
        txtField11.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "#,##0.00"));
        txtField12.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAddDiscx").toString()), "#,##0.00"));
        txtField13.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nFreightx").toString()), "#,##0.00"));
        txtField14.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAmtPaidx").toString()), "#,##0.00"));
        
        txtField06.setText((String) poTrans.getMaster("sRemarksx"));
        
        JSONObject loJSON;
        JSONObject loSales;
        
        //load term
        if (!poTrans.getMaster("sTermCode").toString().equals("")){
            XMTerm loTerm = new XMTerm(poGRider, psBranchCd, pbLoaded);
            loJSON = loTerm.searchTerm((String) poTrans.getMaster("sTermCode"), true);
            if (loJSON != null)
                txtField15.setText((String) loJSON.get("sDescript"));
        }
        
        //load inventory type
        
        //load client info
        if (!poTrans.getMaster("sClientID").toString().equals("")){
            XMClient loClient = new XMClient(poGRider, psBranchCd, true);
            loJSON = loClient.SearchClient((String) poTrans.getMaster("sClientID"), true);
            if (loJSON != null){
                txtField04.setText((String) loJSON.get("sClientNm"));
                txtAddress.setText((String) loJSON.get("xAddressx"));
                }
            if (!poTrans.getMaster("sSalesman").toString().equals("")){
                loSales = loClient.SearchClient((String) poTrans.getMaster("sSalesman"), true);
                if(loSales != null){
                    //salesman
                    txtField07.setText((String) loSales.get("sClientNm"));
                }
            }
        }
        
        setTranStat((String) poTrans.getMaster("cTranStat"));
        
        loadDetail2Grid();
        
        pnOldRow = 0;
        psOldRec = lblOrderNo.getText();
    }

    @Override
    public void setGRider(GRider foGRider) {
        this.poGRider = foGRider;
    }
    
    public void setBranchCd(String fsBranchCd) {
        this.psBranchCd = fsBranchCd;
    }
        
    private void setTranStat(String fsValue){
        switch (fsValue){
            case "0":
                lblTranStat.setText("OPEN"); break;
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
    
    private void unloadForm(){
        bpContent.getChildren().clear();
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
            spDetail.setGRider(poGRider);
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("child/modifySPDetail.fxml"));
            spDetail.setEditMode(EditMode.UPDATE);
            
            try {
                InvMaster loInv = new InvMaster(poGRider, psBranchCd, true);
                if (loInv.SearchStock((String) poTrans.getDetail(pnRow, "sStockIDx"),(String) poTrans.getDetail(pnRow, "sSerialID") , true, true)){
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
    
    private ObservableList<TableModel> data = FXCollections.observableArrayList();
   
    final ChangeListener<? super Boolean> txtField_Focus = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
            if (!pbLoaded) return;
            
            TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
            int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
            String lsValue = txtField.getText();
            double lnValue = 0.0;
            
            if (lsValue == null) return;
            
            if(!nv){ /*Lost Focus*/
                switch (lnIndex){
                    case 3:
                        if (CommonUtils.isDate(lsValue, pxeDateFormat)){
                            poTrans.setMaster("dTransact", CommonUtils.toDate(lsValue));
                        } else{
                            ShowMessageFX.Warning("Invalid date entry.", pxeModuleName, "Date format must be yyyy-MM-dd (e.g. 1991-07-07)");
                            poTrans.setMaster("dTransact", CommonUtils.toDate(pxeCurrentDate));
                        }
                        txtField.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster("dTransact")));
                        break;
                    case 10:
                        try {
                            lnValue = Double.parseDouble(lsValue);
                        } catch (NumberFormatException e) {
                            lnValue = 0.0;
                        }
                        
                        poTrans.setMaster("nVATRatex", lnValue);
                        txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nVATRatex").toString()), "0.00"));
                        loadDetail2Grid();
                        break;
                    /*case 11:
                        try {
                            lnValue = Double.parseDouble(lsValue);
                        } catch (NumberFormatException e) {
                            lnValue = 0.0;
                        }
                        
                        if (lnValue > 1 ){
                            poTrans.setMaster("nDiscount", 0);
                            txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "0.00"));
                        } else{
                            poTrans.setMaster("nDiscount", (lnValue > 0 ? lnValue : 0));
                            txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nDiscount").toString()), "0.00"));
                        }
                        loadDetail2Grid();
                        break;
                    case 12:
                        try {
                            lnValue = Double.parseDouble(lsValue);
                        } catch (NumberFormatException e) {
                            lnValue = 0.0;
                        }
                        
                        poTrans.setMaster("nAddDiscx", lnValue);
                        txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nAddDiscx").toString()), "#,##0.00"));
                        loadDetail2Grid();
                        break;*/
                    case 13:
                        try {
                            lnValue = Double.parseDouble(lsValue);
                        } catch (NumberFormatException e) {
                            lnValue = 0.0;
                        }
                        
                        poTrans.setMaster("nFreightx", lnValue);
                        txtField.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nFreightx").toString()), "#,##0.00"));
                        loadDetail2Grid();
                        break;
                }
            } else{
                switch (lnIndex){
                case 3: /*dTransact*/
                    try{
                        txtField.setText(CommonUtils.xsDateShort(lsValue));
                    }catch(ParseException e){
                        ShowMessageFX.Error(e.getMessage(), pxeModuleName, null);
                    }
                    break;
                default:
                }
                
                if (lnIndex == 7 || lnIndex == 15 || lnIndex == 20 || lnIndex == 90 || lnIndex == 91)
                    pnIndex = lnIndex;
                else pnIndex = -1;
            
                txtField.requestFocus();
                txtField.selectAll();
            }
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
}
