//
//package org.rmj.cas.integsys.fx;
//
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.ResourceBundle;
//import javafx.beans.property.ReadOnlyBooleanPropertyBase;
//import javafx.beans.value.ChangeListener;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.KeyCode;
//import static javafx.scene.input.KeyCode.DOWN;
//import static javafx.scene.input.KeyCode.ENTER;
//import static javafx.scene.input.KeyCode.F3;
//import static javafx.scene.input.KeyCode.UP;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import org.json.simple.JSONObject;
//import org.rmj.appdriver.constants.EditMode;
//import org.rmj.appdriver.GRider;
//import org.rmj.appdriver.agentfx.ShowMessageFX;
//import org.rmj.appdriver.agentfx.CommonUtils;
//import org.rmj.appdriver.agentfx.callback.IFXML;
//import org.rmj.cas.integsys.fx.child.AddLaborController;
//import org.rmj.cas.integsys.fx.child.AddPartsController;
//import org.rmj.cas.inventory.base.InvMaster;
//import org.rmj.servicecenter.agentfx.XMJobOrder;
//import org.rmj.servicecenter.agentfx.XMLabor;
//
//public class ServiceCenterController implements Initializable, IFXML{
//
//    @FXML
//    private BorderPane bpContent;
//    @FXML
//    private Button cmdButton05;
//    @FXML
//    private Button cmdButton06;
//    @FXML
//    private Button cmdButton07;
//    @FXML
//    private Button cmdButton01;
//    @FXML
//    private Button cmdButton02;
//    @FXML
//    private Button cmdButton04;
//    @FXML
//    private Button cmdButton08;
//    @FXML
//    private Button cmdButton09;
//    @FXML
//    private AnchorPane acClientInfo;
//    @FXML
//    private AnchorPane acSearchItem;
//    @FXML
//    private AnchorPane acDetailTable;
//    @FXML
//    private TableColumn index01;
//    @FXML
//    private TableColumn index02;
//    @FXML
//    private TableColumn index03;
//    @FXML
//    private TableColumn index04;
//    @FXML
//    private TableColumn index05;
//    @FXML
//    private TableColumn index06;
//    @FXML
//    private TableColumn index07;
//    @FXML
//    private TextField txtField02;
//    @FXML
//    private TextField txtField03;
//    @FXML
//    private TextField txtField06;
//    @FXML
//    private TextField txtField07;
//    @FXML
//    private TextField txtField08;
//    @FXML
//    private TextArea txtField10;
//    @FXML
//    private TextField txtField12;
//    @FXML
//    private TextField txtField15;
//    @FXML
//    private TextField txtField16;
//    @FXML
//    private AnchorPane acWorkStation;
//    @FXML
//    private Label lblLessDisc;
//    @FXML
//    private Button btnCombo;
//    @FXML
//    private Button btnLabor;
//    @FXML
//    private AnchorPane acMainModule;
//    @FXML
//    private TextArea txtField90;
//    @FXML
//    private TextField txtField91;
//    @FXML
//    private ComboBox cmbCardType;
//    @FXML
//    private ComboBox cmbCouponType;
//    @FXML
//    private TextField txtField38;
//    @FXML
//    private Label lblTotLaborAmt;
//    @FXML
//    private Label lblTotPartsAmt;
//    @FXML
//    private Label lblTotSalesAmt;
//    @FXML
//    private Label lblTotalAmtDue;
//    @FXML
//    private FontAwesomeIconView SAVE;
//    @FXML
//    private TextField txtField05;
//    @FXML
//    private TextField txtField11;
//    @FXML
//    private TextField txtField04;
//    @FXML
//    private TextField txtField41;
//    @FXML
//    private TextField txtField42;
//    @FXML
//    private TextField txtField43;
//    @FXML
//    private TableView tblParts;
//    @FXML
//    private TableView tblLabor;
//    @FXML
//    private Button btnParts;
//    @FXML
//    private TableColumn index11;
//    @FXML
//    private TableColumn index21;
//    @FXML
//    private TableColumn index31;
//    @FXML
//    private TableColumn index41;
//    @FXML
//    private TableColumn index51;
//    @FXML
//    private TableColumn index61;
//    @FXML
//    private TableColumn index71;
//    @FXML
//    private TableColumn index81;
//    @FXML
//    private AnchorPane acClient;
//    @FXML
//    private TextField txtField901;
//    @FXML
//    private TextField txtField911;
//    @FXML
//    private TextField txtSeeks99;
//    @FXML
//    private Label lblOrderNo;
//    @FXML
//    private Label lblTranStat;
//
//   
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        if (psBranchCd.equals("")) psBranchCd = poGRider.getBranchCode();
//        
//        poTrans = new XMJobOrder(poGRider, psBranchCd, false);
//         
//        cmdButton01.setOnAction(this::cmdButton_Click);
//        cmdButton02.setOnAction(this::cmdButton_Click);
//        cmdButton04.setOnAction(this::cmdButton_Click);
//        cmdButton05.setOnAction(this::cmdButton_Click);
//        cmdButton06.setOnAction(this::cmdButton_Click);
//        cmdButton07.setOnAction(this::cmdButton_Click);
//        cmdButton08.setOnAction(this::cmdButton_Click);
//        cmdButton09.setOnAction(this::cmdButton_Click);
//        btnCombo.setOnAction(this::cmdButton_Click);
//        
//        txtField02.focusedProperty().addListener(txtField_Focus);
//        txtField03.focusedProperty().addListener(txtField_Focus);
//        txtField90.focusedProperty().addListener(txtArea_Focus);
//        txtField91.focusedProperty().addListener(txtField_Focus);
//        txtField06.focusedProperty().addListener(txtField_Focus);
//        txtField07.focusedProperty().addListener(txtField_Focus);
//        txtField08.focusedProperty().addListener(txtField_Focus);
//        txtField10.focusedProperty().addListener(txtArea_Focus);
//        txtField12.focusedProperty().addListener(txtField_Focus);
//        txtField15.focusedProperty().addListener(txtField_Focus);
//        txtField16.focusedProperty().addListener(txtField_Focus);
//        txtField38.focusedProperty().addListener(txtField_Focus);
//        txtField05.focusedProperty().addListener(txtField_Focus);
//        txtField04.focusedProperty().addListener(txtField_Focus);
//        txtField11.focusedProperty().addListener(txtField_Focus);
//        
//        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField90.setOnKeyPressed(this::txtFieldArea_KeyPressed);
//        txtField91.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField06.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField07.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField08.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField10.setOnKeyPressed(this::txtFieldArea_KeyPressed);
//        txtField12.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField15.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField16.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField38.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField04.setOnKeyPressed(this::txtField_KeyPressed);
//        txtField11.setOnKeyPressed(this::txtField_KeyPressed);
//        
//        cmbCardType.setOnKeyPressed(this::ComboBox_KeyPressed);
//        cmbCouponType.setOnKeyPressed((this::ComboBox_KeyPressed));        
//        
//        cmbCardType.setItems(cCardType);
//        cmbCardType.getSelectionModel().select(2);
//        
//        cmbCouponType.setItems(cCoupnType);
//        cmbCouponType.getSelectionModel().select(0);        
//       
//        pnEditMode = EditMode.UNKNOWN;
//        
//        initPartsTable();
//        initLaborTable();
//        
//        clearFields();
//        initButton(pnEditMode);
// 
//        pbLoaded = true;
//    }
//    
//    public void initPartsTable(){      
//        index01.setStyle("-fx-alignment: CENTER;");   
//        index02.setStyle("-fx-alignment: CENTER-LEFT;"); 
//        index03.setStyle("-fx-alignment: CENTER-RIGHT;"); 
//        index04.setStyle("-fx-alignment: CENTER-RIGHT;"); 
//        index05.setStyle("-fx-alignment: CENTER-RIGHT;"); 
//        index06.setStyle("-fx-alignment: CENTER-RIGHT;"); 
//        index07.setStyle("-fx-alignment: CENTER-RIGHT;"); 
//     
//        index01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
//        index02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
//        index03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));
//        index04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));
//        index05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05"));
//        index06.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index06"));
//        index07.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index07"));
//        
//        tblParts.setItems(partsData);
//    }
//    
//    public void initLaborTable(){
//        index11.setStyle("-fx-alignment: CENTER;");
//        index21.setStyle("-fx-alignment: CENTER-LEFT;");
//        index31.setStyle("-fx-alignment: CENTER-RIGHT;");
//        index41.setStyle("-fx-alignment: CENTER-RIGHT;");
//        index51.setStyle("-fx-alignment: CENTER-RIGHT;");
//        index61.setStyle("-fx-alignment: CENTER-RIGHT;");
//        index71.setStyle("-fx-alignment: CENTER-RIGHT;");
//        index81.setStyle("-fx-alignment: CENTER;");
//
//        index11.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
//        index21.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
//        index31.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));
//        index41.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));
//        index51.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05"));
//        index61.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index06"));
//        index71.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index07"));
//        index81.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index08"));
//        
//        tblLabor.setItems(laborData);
//    }
//       
//    public void loadLabor2Grid() {
//        int lnCtr;
//        int lnRow = poTrans.getLaborCount();
//        double lnRowTotal = 0;
//        
//        XMLabor loLabor = new XMLabor(poGRider, psBranchCd, true);
//        JSONObject loJSON;
//        
//        
//        laborData.clear();
//        /*ADD THE DETAIL*/
//        for(lnCtr = 0; lnCtr <= lnRow -1; lnCtr++){
//            lnRowTotal = (int) poTrans.getLabor(lnCtr, "nQuantity") * Double.valueOf(poTrans.getLabor(lnCtr, "nUnitPrce").toString());
//            lnRowTotal = lnRowTotal - (lnRowTotal * Double.valueOf(poTrans.getLabor(lnCtr, "nDiscount").toString()));
//            lnRowTotal = lnRowTotal - Double.valueOf(poTrans.getLabor(lnCtr, "nDiscAmtx").toString());
//            
//            loJSON = loLabor.searchLabor((String) poTrans.getLabor(lnCtr, "sLaborIDx"), true);
//            
//            if (loJSON != null){
//                laborData.add(new TableModel(String.valueOf(lnCtr + 1),
//                                    (String) loJSON.get("sDescript"),
//                                    String.valueOf(poTrans.getLabor(lnCtr, "nQuantity").toString()),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getLabor(lnCtr, "nUnitPrce").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getLabor(lnCtr, "nDiscount").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getLabor(lnCtr, "nDiscAmtx").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(lnRowTotal, "#,##0.00"),
//                                    String.valueOf(poTrans.getLabor(lnCtr, "cWaivedxx").toString().matches("0") ? "NO" : "YES"),
//                                    "",
//                                    ""));
//            }
//        }
//        //lblTotalAmtDue.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nTranTotl").toString()), "#,##0.00"));
//        pnLabor = lnRow -1;
//    }
//    
//    public void loadParts2Grid() {
//        int lnCtr;
//        int lnRow = poTrans.getDetailCount();
//        double lnRowTotal = 0;
//        
//        partsData.clear();
//        /*ADD THE DETAIL*/
//        for(lnCtr = 0; lnCtr <= lnRow -1; lnCtr++){
//            
//            lnRowTotal = (int) poTrans.getDetail(lnCtr, "nQuantity") * Double.valueOf(poTrans.getDetail(lnCtr, "nUnitPrce").toString());
//            lnRowTotal = lnRowTotal - (lnRowTotal * Double.valueOf(poTrans.getDetail(lnCtr, "nDiscount").toString()));
//            lnRowTotal = lnRowTotal - Double.valueOf(poTrans.getDetail(lnCtr, "nDiscAmtx").toString());
//            
//            InvMaster loInvMaster = new InvMaster(poGRider, psBranchCd, true);
//            if (loInvMaster.SearchStock(String.valueOf(poTrans.getDetail(lnCtr, "sStockIDx")),String.valueOf(poTrans.getDetail(lnCtr, "sSerialID")), true, true)){
//                partsData.add(new TableModel(String.valueOf(lnCtr + 1),
//                                    (String) loInvMaster.getMaster("sDescript"),
//                                    String.valueOf(poTrans.getDetail(lnCtr, "nQuantity").toString()),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nUnitPrce").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nDiscount").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nDiscAmtx").toString()), "#,##0.00"),
//                                    CommonUtils.NumberFormat(lnRowTotal, "#,##0.00"),
//                                    "",
//                                    "",
//                                    ""));
//            }
//        } 
//        //lblTotalAmtDue.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster("nTranTotl").toString()), "#,##0.00"));
//        pnRow = lnRow -1;
//    }
//        
//    public void cmdButton_Click(ActionEvent event) {
//        String lsButton = ((Button)event.getSource()).getId();
//        
//        switch (lsButton){
//            case "cmdButton01":  //browse
//                break;    
//                
//            case "cmdButton02":  //new
//               if (poTrans.newTransaction()== true){
//                    clearFields();
//                    
//                    poTrans.setMaster("sBranchCd", psBranchCd);
//                    loadTransaction();
//                    pnEditMode = poTrans.getEditMode();
//                }
//                break;
//                
//            case "cmdButton03": //Update
//                break;
//                
//            case "cmdButton04": //Close
//                unloadForm();
//                return;
//                
//            case "cmdButton05": //Save
//                break;
//                
//            case "cmdButton06": //Search
//                break;
//                
//            case "cmdButton07": //Cancel
//                 if (ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to disregard changes?") == true){
//                 pnEditMode = EditMode.UNKNOWN;
//                 clearFields();
//                 }else
//                     return;
//                 break;
//                 
//        }
//        initButton(pnEditMode);
//    }
//    
//    private void unloadForm(){
//        bpContent.getChildren().clear();
//    }
//    
//    public void loadTransaction() {
//        
//        lblOrderNo.setText((String) poTrans.getMaster("sTransNox"));       
//        txtField02.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dTransact")));
//        txtField05.setText((String) poTrans.getMaster("sEstimate"));
//        txtField06.setText(String.valueOf(poTrans.getMaster("nKmReadng").toString()));
//        txtField07.setText((String) poTrans.getMaster("sDealerxx"));
//        txtField08.setText((String) poTrans.getMaster("sMechanic"));
//        txtField10.setText((String) poTrans.getMaster("sJobDesctip"));
//        txtField11.setText((String) poTrans.getMaster("sJobOrdNo"));
//        txtField12.setText((String) poTrans.getMaster("sCtrlNoxx"));
//        
//        cmbCardType.getSelectionModel().select(Integer.parseInt(poTrans.getMaster("cCardType").toString()));
//        cmbCouponType.getSelectionModel().select(Integer.parseInt(poTrans.getMaster("cCouponTp").toString()));
//        
//        txtField15.setText((String) poTrans.getMaster("sCouponNo"));
//        txtField16.setText(String.valueOf(poTrans.getMaster("nNoCoupon").toString()));
//        txtField38.setText((String) poTrans.getMaster("sCardIDxx,"));
//        
//        loadParts2Grid();
//        loadLabor2Grid();
//        setTranStat((String) poTrans.getMaster("cTranStat"));
//        
//        pnOldRow = 0;
//        psOldRec = lblOrderNo.getText();
//    }
//    
//    private void setTranStat(String fsValue){
//        switch (fsValue){
//            case "0":
//                lblTranStat.setText("NEW"); break;
//            case "1":
//                lblTranStat.setText("CLOSED"); break;
//            case "2":
//                lblTranStat.setText("POSTED"); break;
//            case "3":
//                lblTranStat.setText("CANCELLED"); break;
//            case "4":
//                lblTranStat.setText("VOIDED"); break;
//            default:
//                lblTranStat.setText("UNKNOWN");
//        } 
//    }
//    
//    public void initButton(int fnValue){
//        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
//        
//        cmdButton01.setVisible(!lbShow);
//        cmdButton02.setVisible(!lbShow);
//        cmdButton04.setVisible(!lbShow);
//        cmdButton08.setVisible(!lbShow);
//        cmdButton09.setVisible(!lbShow);
//                
//        cmdButton05.setVisible(lbShow);
//        cmdButton06.setVisible(lbShow);
//        cmdButton07.setVisible(lbShow);
//        acWorkStation.setDisable(!lbShow);
//        acMainModule.setDisable(!lbShow);
//        
//        if (lbShow)
//            txtField03.requestFocus();
//        else
//            cmdButton02.requestFocus();
//    }
//    
//    public void clearFields(){
//        txtField02.setText("");
//        txtField04.setText("");
//        txtField41.setText("");
//        txtField42.setText("");
//        txtField43.setText("");
//        txtField03.setText("");
//        txtField05.setText("");
//        txtField06.setText("");
//        txtField07.setText("");
//        txtField08.setText("");
//        txtField10.setText("");
//        txtField11.setText("");
//        txtField12.setText("");
//        txtField15.setText("");
//        txtField16.setText("");
//        txtField38.setText("");
//        txtField90.setText("");
//        txtField91.setText("");
//        lblLessDisc.setText("0.0");
//        lblTotLaborAmt.setText("0.0");
//        lblTotPartsAmt.setText("0.0");
//        lblTotSalesAmt.setText("0.0");
//        lblTotalAmtDue.setText("0.0");
//        lblOrderNo.setText("-");
//        
//        cmbCardType.getSelectionModel().select(2);
//        cmbCouponType.getSelectionModel().select(0);        
//        setTranStat("-1");
//        psDealerxx = "";
//        psClientNm = "";
//        psMechanic = "";
//        psAddress = "";
//        psCustNo = "";
//        psBarcode = "";
//        psDescript = "";
//        psEngineNo = "";
//        psMobileNo = "";
//        
//        laborData.clear();
//        partsData.clear();
//    }
//   
//    @Override
//    public void setGRider(GRider foGRider){
//        this.poGRider = foGRider;
//    }  
//    
//    public void setBranchCd(String fsBranchCd) {
//        this.psBranchCd = fsBranchCd;
//    }
//    
//    private final String pxeModuleName = "org.rmj.integsysfx.applications.SerViceCenterController";   
//    private ObservableList<TableModel> partsData = FXCollections.observableArrayList();
//    private ObservableList<TableModel> laborData = FXCollections.observableArrayList();
//    private static GRider poGRider;
//    private String psBranchCd = "";
//    private String psDealerxx = "";
//    private String psClientNm = "";
//    private String psMechanic = "";
//    private String psAddress = "";
//    private String psCustNo = "";
//    private String psBarcode = "";
//    private String psDescript = "";
//    private String psMobileNo = "";
//    private String psEngineNo = "";
//    private XMJobOrder poTrans;
//    private int pnEditMode;    
//    private boolean pbLoaded = false;
//    private final String pxeDateFormat = "yyyy-MM-dd";
//    private final String pxeCurrentDate = java.time.LocalDate.now().toString();
//
//    private String psOldRec = "";
//    private int pnIndex = -1;
//    private int pnRow = 0;
//    private int pnLabor = 0;
//    private int pnOldRow = 0;
//    private int pnReturnMP = 7;
//    private int pnReturnSP = 180;
//    
//    ObservableList<String> cCardType = FXCollections.observableArrayList("Premium", "Plus", "None");
//    ObservableList<String> cCoupnType = FXCollections.observableArrayList("None", "Service Coupon 1", "Service Coupon 2", " Service Coupon 3", "FSEC", "FSEC Yellow");
//    ObservableList<String> cSelection = FXCollections.observableArrayList("Job Order No.", "Client Name");
//   
//    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{
//        if (!pbLoaded) return;
//        
//        TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
//        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//        String lsValue = txtField.getText();
//        
//        if (lsValue == null) return;
//            
//        if(!nv){ /*Lost Focus*/
//            switch (lnIndex){
//                case 90:
//                case 91:
//                    break;
//                case 3:
//                     if(lsValue.equals("") || lsValue.equals("%")){
//                            poTrans.setMaster("sClientID", "");
//                            txtField.setText("");
//                         }else
//                         txtField.setText(psClientNm);  return;
//                
//                case 2: /*dTransact*/
//                     if (CommonUtils.isDate(lsValue, pxeDateFormat)){
//                            poTrans.setMaster("dTransact", CommonUtils.toDate(lsValue));
//                        } else{
//                            ShowMessageFX.Warning("Invalid date entry.", pxeModuleName, "Date format must be yyyy-MM-dd (e.g. 1991-07-07)");
//                            poTrans.setMaster("dTransact", CommonUtils.toDate(pxeCurrentDate));
//                        }
//                        txtField.setText(CommonUtils.xsDateLong((Date)poTrans.getMaster("dTransact")));
//                        break;
//                case 4: /*sSerialId*/
//                        if(lsValue.equals("") || lsValue.equals("%")){
//                               poTrans.setMaster("sSerialID", "");
//                               txtField.setText("");
//                             }else
//                             txtField.setText(psEngineNo);  return;
//                case 5: /*sEstimate*/
//                    if (lsValue.length() > 8) lsValue = lsValue.substring(0, 8);
//                    poTrans.setMaster("sEstimate", lsValue);
//                    txtField.setText((String) poTrans.getMaster("sEstimate"));
//                    break;
//                    
//                case 6: /*nKmReadng*/
//                    int lnKMReadng;
//                    try {
//                        lnKMReadng = Integer.parseInt(lsValue);
//                    }catch (NumberFormatException ex){
//                        lnKMReadng = 0;
//                    }
//                    poTrans.setMaster("nKmReadng", lnKMReadng);
//                    txtField.setText(poTrans.getMaster("nKmReadng").toString());
//                    break;
//                    
//                case 7: /*sDealerxx*/
//                    if(lsValue.equals("") || lsValue.equals("%")){
//                            poTrans.setMaster("sDealerxx", "");
//                            txtField.setText("");
//                         }else
//                         txtField.setText(psDealerxx);  return;
//                case 8:/*sMechanic*/
//                     if(lsValue.equals("") || lsValue.equals("%")){
//                            poTrans.setMaster("sMechanic", "");
//                            txtField.setText("");
//                         }else
//                         txtField.setText(psMechanic);  return;
//                    
//                case 11: /*sJobOrdNo*/
//                    if(lsValue.length() > 9) lsValue = lsValue.substring(0, 9);
//                    poTrans.setMaster("sJobOrdNo", lsValue);
//                    txtField.setText((String) poTrans.getMaster("sJobOrdNo"));
//                    break;
//                case 12: /*sCtrlNoxx*/
//                    if(lsValue.length() > 13) lsValue = lsValue.substring(0, 13);
//                    poTrans.setMaster("sCtrlNoxx", lsValue);
//                    txtField.setText((String) poTrans.getMaster("sCtrlNoxx"));
//                    break;
//                case 15: /*sCouponNo*/
//                    if(lsValue.length() > 15) lsValue = lsValue.substring(0,15);
//                    poTrans.setMaster("sCouponNo", lsValue);
//                    txtField.setText((String) poTrans.getMaster("sCouponNo"));
//                    break;
//                case 16: /*nNoCoupon*/
//                    break;
//                case 38: /*sCardIDxx*/
//                    if(lsValue.length() > 15) lsValue = lsValue.substring(0, 15);
//                    poTrans.setMaster("sCardIDxx", lsValue);
//                    txtField.setText((String) poTrans.getMaster("sCardIDxx"));
//                    break;
//            }
//        } else{
//            switch (lnIndex){
//                case 2: /*dTransact*/
//                    try{
//                        txtField.setText(CommonUtils.xsDateShort(lsValue));
//                    }catch(ParseException e){
//                        ShowMessageFX.Error(e.getMessage(), pxeModuleName, null);
//                    }
//                default:
//            }
//                if (lnIndex == 50)
//                    pnIndex = lnIndex;
//                   
//                else pnIndex = -1;
//            
//                txtField.selectAll();
//        }
//    };
//    
//    final ChangeListener<? super Boolean> txtArea_Focus = (o,ov,nv)->{
//        if (!pbLoaded) return;
//        
//        TextArea txtField = (TextArea)((ReadOnlyBooleanPropertyBase)o).getBean();
//        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//        String lsValue = txtField.getText();
//        
//        if (lsValue == null) return;
//        
//        if(!nv){ /*Lost Focus*/            
//            switch (lnIndex){
//                case 90:
//                    txtField.setText((String) CommonUtils.TitleCase(lsValue));
//                    break;
//                case 10: /*Job Description*/
//                    if (lsValue.length() > 250) lsValue = lsValue.substring(0, 250);
//                    
//                    poTrans.setMaster("sJobDescr", CommonUtils.TitleCase(lsValue));
//                    txtField.setText((String)poTrans.getMaster("sJobDescr"));
//            }
//        }else{ 
//            pnIndex = -1;
//            txtField.selectAll();
//        }
//    };
//
//    public void ComboBox_KeyPressed(KeyEvent event) {
//        ComboBox combo = (ComboBox)event.getSource(); 
//       if (null != event.getCode())switch (event.getCode()) {
//            case ENTER:
//            case DOWN:
//                CommonUtils.SetNextFocus(combo);
//                break;
//            case UP:
//                CommonUtils.SetPreviousFocus(combo);
//                break;
//                
//            default:
//                break;
//        }
//    }
//
//    public void txtField_KeyPressed(KeyEvent event) {
//        TextField txtField = (TextField)event.getSource();        
//        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//        String lsValue = txtField.getText();
//        
//        if (event.getCode() == F3 || event.getCode() == ENTER){
//            switch (lnIndex){
//                case 3:
//                    if (poTrans.SearchMaster("sClientID", lsValue, false)){
//                        txtField.setText(poTrans.getClientName()); 
//                        psClientNm= poTrans.getClientName();
//                        
//                        txtField90.setText(poTrans.getClientAddress());
//                        psAddress= poTrans.getClientAddress();
//                        
//                        txtField91.setText(poTrans.getMobileNo());
//                        psMobileNo= poTrans.getMobileNo(); 
//                        }
//                        break;
//                case 4:
//                    if (poTrans.SearchMaster("sSerialID", lsValue, true)){
//                        /*txtField.setText(poTrans.getClientNm()); 
//                        psClientNm= poTrans.getClientNm();
//                        
//                        txtField90.setText(poTrans.getAddressx());
//                        psAddress= poTrans.getAddressx(); */
//                        }
//                        break;
//                        
//                case 7:
//                    if (poTrans.SearchMaster("sDealerxx", lsValue, true)){
//                        /*txtField.setText(poTrans.getClientNm()); 
//                        psClientNm= poTrans.getClientNm();
//                        
//                        txtField90.setText(poTrans.getAddressx());
//                        psAddress= poTrans.getAddressx(); */
//                        }
//                        break;   
//                
//                  case 8:
//                    if (poTrans.SearchMaster("sMechanic", lsValue, true)){
//                        /*txtField.setText(poTrans.getClientNm()); 
//                        psClientNm= poTrans.getClientNm();
//                        
//                        txtField90.setText(poTrans.getAddressx());
//                        psAddress= poTrans.getAddressx(); */
//                        }
//                        break;     
//                
//                case 50: //lsResult = txtField06.getText(); break;
//                      if (!lsValue.equals("")){
//                            /*if (cmbBrowseSelection.getSelectionModel().getSelectedIndex() == 0){
//                                if (poTrans.SearchDetail(pnRow, 80, lsValue)) poTrans.addDetail();
//                            }else{
//                                if (poTrans.SearchDetail(pnRow, 81, lsValue)) poTrans.addDetail();
//                            }*/
//                        }
//                        break;          
//            }
//        }        
//        if (event.getCode() == DOWN || event.getCode() == ENTER){
//             CommonUtils.SetNextFocus(txtField);                  
//        }
//        if (event.getCode() == UP){
//             CommonUtils.SetPreviousFocus(txtField);                 
//        }
//    }
//    
//    public void txtFieldArea_KeyPressed(KeyEvent event) {
//        if (event.getCode() == ENTER || event.getCode() == DOWN){
//            event.consume();
//            CommonUtils.SetNextFocus((TextArea)event.getSource());
//        }else if (event.getCode() ==KeyCode.UP){
//        event.consume();
//            CommonUtils.SetPreviousFocus((TextArea)event.getSource());
//        }
//    }
//
//    @FXML
//    private void tblParts_Clicked(MouseEvent event) {
//        if (event.getClickCount() ==2){
//            int lnRow = tblParts.getSelectionModel().getSelectedIndex();        
//            pnRow = lnRow;
//        
//            boolean lbShow = (pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE);
//            if (!lbShow) return;
//            if (poTrans.getDetail(pnRow, "sStockIDx").equals("")) return;
//        
//            AddPartsController detailParts = new AddPartsController();
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("child/AddParts.fxml"));
//            detailParts.setEditMode(EditMode.UPDATE);
//            detailParts.setData(poTrans.Detail(pnRow));
//            try {
//                fxmlLoader.setController(detailParts);
//                fxmlLoader.load();
//
//                Parent parent = fxmlLoader.getRoot();
//                Scene scene = new Scene(parent);
//                scene.setFill(new Color(0, 0, 0, 0));
//
//                Stage nStage = new Stage();
//                nStage.setScene(scene);
//                nStage.initModality(Modality.APPLICATION_MODAL);
//                nStage.initStyle(StageStyle.TRANSPARENT);
//                nStage.showAndWait();
//
//                if (!detailParts.isCancelled() && detailParts.getEditMode() == EditMode.UPDATE ){
//                    try {
//                        poTrans.setDetail(pnRow, "sStockIDx", ((String) detailParts.getData().getStockIDx()));
//                        poTrans.setDetail(pnRow, "nUnitPrce", Double.valueOf(detailParts.getData().getUnitPrce().toString()));
//                        poTrans.setDetail(pnRow, "nQuantity", detailParts.getData().getQuantity());
//                        poTrans.setDetail(pnRow, "nDiscount", Double.valueOf(detailParts.getData().getDiscount().toString()));
//                        poTrans.setDetail(pnRow, "nDiscAmtx", Double.valueOf(detailParts.getData().getDiscAmtx().toString()));
//
//                        loadParts2Grid();
//
//                    } catch (SQLException ex) {
//                        ShowMessageFX.Error(ex.getMessage(), pxeModuleName, "Please inform MIS department.");
//                        System.exit(1);
//                    }
//                }else if(!detailParts.isCancelled() && detailParts.getEditMode() == EditMode.DELETE){
//                    poTrans.deleteDetail(pnRow);
//                    loadParts2Grid();
//                    
//                }
//            } catch (IOException e) {
//                ShowMessageFX.Error(e.getMessage(), pxeModuleName, "Please inform MIS department.");
//                System.exit(1);
//            }
//        }
//    }
//
//    @FXML
//    private void tblLabor_Clicked(MouseEvent event) {
//        if (event.getClickCount() ==2){
//            int lnLabor = tblLabor.getSelectionModel().getSelectedIndex();        
//            pnLabor = lnLabor;
//        
//            boolean lbShow = (pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE);
//            if (!lbShow) return;
//            if (poTrans.getLabor(pnLabor, "sLaborIDx").equals("")) return;
//        
//            AddLaborController detailLabor = new AddLaborController();
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("child/AddLabor.fxml"));
//            
//            detailLabor.setEditMode(EditMode.UPDATE);
//            detailLabor.setData(poTrans.Labor(pnLabor));
//            try {
//                fxmlLoader.setController(detailLabor);
//                fxmlLoader.load();
//
//                Parent parent = fxmlLoader.getRoot();
//                Scene scene = new Scene(parent);
//                scene.setFill(new Color(0, 0, 0, 0));
//
//                Stage nStage = new Stage();
//                nStage.setScene(scene);
//                nStage.initModality(Modality.APPLICATION_MODAL);
//                nStage.initStyle(StageStyle.TRANSPARENT);
//                nStage.showAndWait();
//
//                if (!detailLabor.isCancelled()){
//                    try {
//                        poTrans.setLabor(pnLabor, "sStockIDx", ((String) detailLabor.getData().getLaborIDx()));
//                        poTrans.setLabor(pnLabor, "nUnitPrce", Double.valueOf(detailLabor.getData().getUnitPrce().toString()));
//                        poTrans.setLabor(pnLabor, "nQuantity", detailLabor.getData().getQuantity());
//                        poTrans.setLabor(pnLabor, "nDiscount", Double.valueOf(detailLabor.getData().getDiscount().toString()));
//                        poTrans.setLabor(pnLabor, "nDiscAmtx", Double.valueOf(detailLabor.getData().getDiscAmtx().toString()));
//
//                        loadParts2Grid();
//
//                    } catch (SQLException ex) {
//                        ShowMessageFX.Error(ex.getMessage(), pxeModuleName, "Please inform MIS department.");
//                        System.exit(1);
//                    }
//                }else if(!detailLabor.isCancelled() && detailLabor.getEditMode() == EditMode.DELETE){
//                    poTrans.deleteLabor(pnLabor);
//                    loadLabor2Grid();
//                } 
//            } catch (IOException e) {
//                ShowMessageFX.Error(e.getMessage(), pxeModuleName, "Please inform MIS department.");
//                System.exit(1);
//            }
//        }
//    }
//
//    @FXML
//    private void btnParts_Click(ActionEvent event) {
//        AddPartsController detailParts = new AddPartsController();
//        detailParts.setGRider(poGRider);
//        detailParts.setEditMode(EditMode.ADDNEW);
//        detailParts.setData(poTrans.Detail(poTrans.getDetailCount() -1));
//        
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("child/addParts.fxml"));
//        try {
//            
//            fxmlLoader.setController(detailParts);
//            fxmlLoader.load();
//            
//            Parent parent = fxmlLoader.getRoot();
//            Scene scene = new Scene(parent);
//            scene.setFill(new Color(0, 0, 0, 0));
//            
//            Stage nStage = new Stage();
//            nStage.setScene(scene);
//            nStage.initModality(Modality.APPLICATION_MODAL);
//            nStage.initStyle(StageStyle.TRANSPARENT);
//            nStage.showAndWait();
//            
//            if (!detailParts.isCancelled()){
//                    try {
//                        poTrans.setDetail(pnRow, "sStockIDx", ((String) detailParts.getData().getStockIDx()));
//                        poTrans.setDetail(pnRow, "nUnitPrce", Double.valueOf(detailParts.getData().getUnitPrce().toString()));
//                        poTrans.setDetail(pnRow, "nQuantity", detailParts.getData().getQuantity());
//                        poTrans.setDetail(pnRow, "nDiscount", Double.valueOf(detailParts.getData().getDiscount().toString()));
//                        poTrans.setDetail(pnRow, "nDiscAmtx", Double.valueOf(detailParts.getData().getDiscAmtx().toString()));
//                        
//                        if (!poTrans.getDetail(pnRow, "sStockIDx").equals("") 
//                            && detailParts.getEditMode() == EditMode.ADDNEW) {
//                           poTrans.addDetail(); 
//                        }
//                        
//                        loadParts2Grid();
//                        
//                    } catch (SQLException ex) {
//                        ShowMessageFX.Error(ex.getMessage(), pxeModuleName, "Please inform MIS department.");
//                        System.exit(1);
//                    }
//            } 
//        } catch (IOException e) {
//            ShowMessageFX.Error(e.getMessage(), pxeModuleName, "Please inform MIS department.");
//            System.exit(1);
//            }
//    }
//
//    @FXML
//    private void btnLabor_Clicked(ActionEvent event) {
//        AddLaborController detailLabor = new AddLaborController();
//        detailLabor.setGRider(poGRider);
//        detailLabor.setEditMode(EditMode.ADDNEW);
//        detailLabor.setData(poTrans.Labor(poTrans.getLaborCount()-1));
//        
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("child/addLabor.fxml"));
//        try {
//            
//            fxmlLoader.setController(detailLabor);
//            fxmlLoader.load();
//            
//            Parent parent = fxmlLoader.getRoot();
//            Scene scene = new Scene(parent);
//            scene.setFill(new Color(0, 0, 0, 0));
//            
//            Stage nStage = new Stage();
//            nStage.setScene(scene);
//            nStage.initModality(Modality.APPLICATION_MODAL);
//            nStage.initStyle(StageStyle.TRANSPARENT);
//            nStage.showAndWait();
//            
//            if (!detailLabor.isCancelled()){
//                    try {
//                        poTrans.setLabor(pnLabor, "sLaborIDx", ((String) detailLabor.getData().getLaborIDx()));
//                        poTrans.setLabor(pnLabor, "nUnitPrce", Double.valueOf(detailLabor.getData().getUnitPrce().toString()));
//                        poTrans.setLabor(pnLabor, "nQuantity", detailLabor.getData().getQuantity());
//                        poTrans.setLabor(pnLabor, "nDiscount", Double.valueOf(detailLabor.getData().getDiscount().toString()));
//                        poTrans.setLabor(pnLabor, "nDiscAmtx", Double.valueOf(detailLabor.getData().getDiscAmtx().toString()));
//                        
//                        if (!poTrans.getLabor(pnLabor, "sLaborIDx").equals("") 
//                            && detailLabor.getEditMode() == EditMode.ADDNEW) {
//                           poTrans.addLabor(); 
//                        }
//                        
//                        loadLabor2Grid();
//                        
//                    } catch (SQLException ex) {
//                        ShowMessageFX.Error(ex.getMessage(), pxeModuleName, "Please inform MIS department.");
//                        System.exit(1);
//                    }
//            } 
//        } catch (IOException e) {
//            ShowMessageFX.Error(e.getMessage(), pxeModuleName, "Please inform MIS department.");
//            System.exit(1);
//        }
//    }
//}
