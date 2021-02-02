
package org.rmj.cas.integsys.fx;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rmj.appdriver.agentfx.StringHelper;
import org.rmj.appdriver.agentfx.WebClient;


public class testPrint {
    public static void main(String[] args) {
        try {
            JSONObject loMasx = new JSONObject();
            
            JSONObject loJSON = new JSONObject();
            
            //START - HEADER
            loJSON.put("sCompnyNm", "Guanzon Merchandising Corporation");
            loJSON.put("sBranchNm", "GMC Dagupan - Honda");
            loJSON.put("sAddress1", "Guanzon Bldg., Perez Blvd.,");
            loJSON.put("sAddress2", "Dagupan City, Pangasinan 2400");
            loJSON.put("sVATREGTN", "XXX-XXX-XXX-XXXX");
            loJSON.put("sMINumber", "XXXXXXXXXXXXXXXXX");
            loJSON.put("sSerialNo", "XXXXXXXXXX");
            loJSON.put("sSlipType", "SI");
            loJSON.put("cReprintx", "1");
            loMasx.put("Header", loJSON);
            //END - HEADER
            
            //START - MASTER
            loJSON = new JSONObject();
            loJSON.put("nTranTotl", 11000.00);
            loJSON.put("nFreightx", 0.00);
            loJSON.put("nVATRatex", 1.12);
            loJSON.put("nDiscount", 0.10);
            loJSON.put("nAddDiscx", 100.00);
            loJSON.put("sClientNm", "Casilang, Jovan Ali");
            loJSON.put("sAddressx", "Calasiao, Pangasinan");
            loJSON.put("sTINumber", "000-000-000-000");
            loJSON.put("sBusStyle", "Dog style");
            loJSON.put("sCashierx", "Cuison, Michael Torres");
            loJSON.put("sTerminal", "001");
            loJSON.put("sInvoicex", "00000001");
            loJSON.put("sDateTime", "2019-11-21 16:04:30");
            loMasx.put("Master", loJSON);
            //END - MASTER
            
            //START - DETAIL
            JSONArray loDetail = new JSONArray();
            loJSON = new JSONObject();
            loJSON.put("sBarCodex", "0000000001");
            loJSON.put("sDescript", "Test 1");
            loJSON.put("cSerialzd", "0");
            loJSON.put("sSerialNo", "");
            loJSON.put("nQuantity", "1");
            loJSON.put("nAmountxx", 1000.00);
            loJSON.put("nDiscount", 0.00);
            loJSON.put("nAddDiscx", 0.00);
            loJSON.put("cVatablex", "1");
            loDetail.add(loJSON);
            
            loJSON = new JSONObject();
            loJSON.put("sBarCodex", "0000000002");
            loJSON.put("sDescript", "Test 2");
            loJSON.put("cSerialzd", "1");
            loJSON.put("sSerialNo", "123456789");
            loJSON.put("nQuantity", "1");
            loJSON.put("nAmountxx", 10000.00);
            loJSON.put("nDiscount", 0.00);
            loJSON.put("nAddDiscx", 0.00);
            loJSON.put("cVatablex", "1");
            loDetail.add(loJSON);
            loMasx.put("Detail", loDetail);            
            //END - DETAIL
            
            //START - PAYMENT
            JSONObject loPayment = new JSONObject();
            loPayment.put("nCashAmtx", 1000.00);
            
            JSONArray laPayment = new JSONArray();
            
            loJSON = new JSONObject();
            loJSON.put("sBankCode", "BDO");
            loJSON.put("sCardNoxx", "12345678901234");
            loJSON.put("nAmountxx", "5000");
            laPayment.add(loJSON);
            loPayment.put("sCredtCrd", laPayment);    
            
            laPayment = new JSONArray();
            loJSON = new JSONObject();
            loJSON.put("sBankCode", "BDO");
            loJSON.put("sCheckNox", "0000000001");
            loJSON.put("nAmountxx", "4000");
            laPayment.add(loJSON);
            loPayment.put("sCheckPay", laPayment);   
            
            laPayment = new JSONArray();
            loPayment.put("sGiftCert", laPayment); 
            
            laPayment = new JSONArray();
            loMasx.put("Payment", loPayment);
            
            //START - FOOTER
            loJSON = new JSONObject();
            loJSON.put("sDevelopr", "RMJ Business Solutions");
            loJSON.put("sAddress1", "021 Pogo grande");
            loJSON.put("sAddress2", "Dagupan City, Pangasinan 2400");
            loJSON.put("sVATREGTN", "942-188-655-00000");
            loJSON.put("sAccrNmbr", "XXXXXXXXXXXXXXXXXXXXXX");
            loJSON.put("sAccrIssd", "XXXX-XX-XX");
            loJSON.put("sAccdExpr", "XXXX-XX-XX");
            loJSON.put("sPTUNmber", "XXXXXXXXXXXXXXXXXXXXXX");
            loJSON.put("sPTUIssdx", "XXXX-XX-XX");
            loJSON.put("sPTUExpry", "XXXX-XX-XX");
            loMasx.put("Footer", loJSON);
            //END - FOOTER
            
            String lsSQL = WebClient.sendHTTP("http://localhost/escpos-php/src/invoice.php", loMasx.toJSONString(), null);
            System.out.println(lsSQL);
            /*PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new BillPrintable(), PrintJob.getPageFormat(pj));
            try {
            pj.print();
            
            }
            catch (PrinterException ex) {
            ex.printStackTrace();
            }*/
        } catch (IOException ex) {
            Logger.getLogger(testPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static class BillPrintable implements Printable {
        private final int LEN001 = 40; //Font("Lucida Console", Font.PLAIN, 8)
        private final int LEN002 = 35; //Font("Lucida Console", Font.PLAIN, 9)
        private final int LEN003 = 29; //Font("Lucida Console", Font.BOLD, 11)
        private final int LEN004 = 28; //Font("Lucida Console", Font.BOLD, 12)
        
        private final int QTYLEN = 3;
        private final int DSCLEN = 14;
        private final int PRCLEN = 9;
        private final int TTLLEN = 9;
        private final int REGLEN = 12;
        private final int LONGDS = 30;
        
        private final String FONT = "Consolas";
        
        private char asterisk = '*';
        private char dash = '-';
        
        
        Graphics2D g2d;
        int yShift = 10;
        int headerRectHeight= 25;
        
        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) throws PrinterException{       
            int result = NO_SUCH_PAGE;    
        
            if (pageIndex == 0) {                    
                                    
                g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();                    

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

                ////////// code by alqama//////////////

                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                int idLength= metrics.stringWidth("000");
                int amtLength= metrics.stringWidth("000000");
                int qtyLength= metrics.stringWidth("00000");
                int priceLength= metrics.stringWidth("000000");
                int prodLength= (int)width - idLength - amtLength - qtyLength - priceLength - 17;
            
                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;
            
            try{             
                int y = 10;
                y = createHeader("Guanzon Mdsg. Corp.",
                                "GMC Dagupan - Honda", 
                                "Guanzon Bldg., Perez Blvd.,",
                                "Dagupan City, Pangasinan",
                                "XXX-XXX-XXX-XXXX", 
                                "XXXXXXXXXXXXXXXXX", 
                                "XXXXXXXXXX", 
                                "OFFICIAL RECEIPT",
                                y);
                y = createBody(y);
                y = createFooter(y);
                
                /*
                g2d.setFont(new Font("Lucida Console", Font.PLAIN, 7));
                g2d.drawString("----------------------------------------", 1, y); y += yShift;
                g2d.drawString("****************************************", 1, y); y += yShift;
                g2d.drawString("      Restaurant Bill Receipt           ", 1, y); y += yShift;
                g2d.drawString("----------------------------------------", 1, y); y += headerRectHeight;
                g2d.drawString("----------------------------------------", 1, y); y += yShift;
                g2d.drawString(" Food Name                 T.Price      ", 1, y); y += yShift;
                g2d.drawString("----------------------------------------", 1, y); y += headerRectHeight;
                */
                /*
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Food Name                 T.Price   ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                g2d.drawString(" "+pn1a+"                  "+pp1a+"  ",10,y);y+=yShift;
                g2d.drawString(" "+pn2a+"                  "+pp2a+"  ",10,y);y+=yShift;
                g2d.drawString(" "+pn3a+"                  "+pp3a+"  ",10,y);y+=yShift;
                g2d.drawString(" "+pn4a+"                  "+pp4a+"  ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Total amount: "+sum+"               ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString("          Free Home Delivery         ",10,y);y+=yShift;
                g2d.drawString("             03111111111             ",10,y);y+=yShift;
                g2d.drawString("*************************************",10,y);y+=yShift;
                g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ",10,y);y+=yShift;
                g2d.drawString("*************************************",10,y);y+=yShift;
                */       
           
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

                } catch(Exception r){
                    r.printStackTrace();
                }
                result = PAGE_EXISTS;    
            }    
        
            return result;    
        }
        
        private int createBody(int fnYValue){
            if (g2d == null) return fnYValue;
            
            String ls4Print = "";
            
            g2d.setFont(new Font(FONT, Font.PLAIN, 8));
            
            //****************************************
            ls4Print = StringHelper.midpad(StringHelper.postpad("*", LEN001, asterisk), LEN001);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Detail Title
            ls4Print = "QTY" + " " + StringHelper.postpad("DESCRIPTION", DSCLEN) + " " + 
                            StringHelper.prepad("UPRICE", PRCLEN) + " " + StringHelper.prepad("AMOUNT", TTLLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Print Detail
            ls4Print = StringHelper.prepad(String.format("0", "1"), QTYLEN) + " ";
            ls4Print = ls4Print + StringHelper.postpad("OPENLINEW/WRTY", DSCLEN) + " ";
            ls4Print = ls4Print + StringHelper.prepad("100000.50", PRCLEN) + " ";
            ls4Print = ls4Print + StringHelper.prepad("100000.50", TTLLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //----------------------------------------
            ls4Print = StringHelper.midpad(StringHelper.postpad("-", LEN001, dash), LEN001);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //No. of Items: n
            ls4Print = " No. of Items: " + "100";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift * 2;

            //Sub-Total
            ls4Print = StringHelper.postpad(" Sub-Total", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Separator
            ls4Print = StringHelper.postpad(" ", 25) + " " + StringHelper.prepad("-", REGLEN, dash);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Price Exclusive of VAT
            ls4Print = StringHelper.postpad(" Price Exclusive of VAT", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Less: n% Discount
            ls4Print = StringHelper.postpad(" Less: 10% Discount", 25) + " " + StringHelper.prepad("5000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Separator
            ls4Print = StringHelper.postpad(" ", 25) + " " + StringHelper.prepad("-", REGLEN, dash);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Net Sales (w/o VAT)
            ls4Print = StringHelper.postpad(" Net Sales (w/o VAT)", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Net Sales (w/o VAT)
            ls4Print = StringHelper.postpad(" Add: VAT", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Separator
            ls4Print = StringHelper.postpad(" ", 25) + " " + StringHelper.prepad("-", REGLEN, dash);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //TOTAL AMOUNT DUE :
            ls4Print = StringHelper.postpad(" TOTAL AMOUNT DUE :", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Cash
            ls4Print = StringHelper.postpad(" Cash", 25) + " " + StringHelper.prepad("500000.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Separator
            ls4Print = StringHelper.postpad(" ", 25) + " " + StringHelper.prepad("-", REGLEN, dash);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //CHANGE           :
            ls4Print = StringHelper.postpad(" CHANGE           :", 25) + " " + StringHelper.prepad("0.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //----------------------------------------
            ls4Print = StringHelper.midpad(StringHelper.postpad("-", LEN001, dash), LEN001);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift * 2;
            
            //VAT Exempt Sales
            ls4Print = "  VAT Exempt Sales      " + StringHelper.prepad("0.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Zero-Rated Sales
            ls4Print = "  Zero-Rated Sales      " + StringHelper.prepad("0.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //VATable Sales
            ls4Print = "  VATable Sales         " + StringHelper.prepad("0.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //VAT Amount
                ls4Print = "  VAT Amount            " + StringHelper.prepad("0.00", REGLEN);
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift * 2;
            
            //Customer Name
            ls4Print = " Cust Name: ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer Address
            ls4Print = " Address  : ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer TIN
            ls4Print = " TIN      : ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer business style
            ls4Print = " Bus Style: ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            //Customer Name
            ls4Print = " Cashier: ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer Address
            ls4Print = " Terminal No.: ";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer TIN
            ls4Print = " OR No.:";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            //Customer business style
            ls4Print = " Date/Time:";
            g2d.drawString(ls4Print, 1, fnYValue); fnYValue += yShift;
            
            return fnYValue;
        }
        
        
        private int createHeader(String fsCompanyNm, 
                                    String fsBranchNm,
                                    String fsAddress1,
                                    String fsAddress2,
                                    String fsREGTINxx,
                                    String fsMINxxxxx,
                                    String fsSerialNo,
                                    String fsSlipType,
                                    int fnYValue){
            
            if (g2d == null) return fnYValue;
            
            g2d.setFont(new Font(FONT, Font.BOLD, 12));
            g2d.drawString(StringHelper.midpad(fsCompanyNm, LEN004), 1, fnYValue); fnYValue += yShift;

            g2d.setFont(new Font(FONT, Font.BOLD, 11));
            g2d.drawString(StringHelper.midpad(fsBranchNm, LEN003), 1, fnYValue); fnYValue += yShift;

            g2d.setFont(new Font(FONT, Font.PLAIN, 9));
            g2d.drawString(StringHelper.midpad(fsAddress1, LEN002), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad(fsAddress2, LEN002), 1, fnYValue); fnYValue += yShift;

            g2d.setFont(new Font(FONT, Font.PLAIN, 9));
            g2d.drawString(StringHelper.midpad("VAT REG TIN: " + fsREGTINxx, LEN002), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("MIN: " + fsMINxxxxx, LEN002), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Serial No.: " + fsSerialNo, LEN002), 1, fnYValue); fnYValue += headerRectHeight;

            g2d.setFont(new Font(FONT, Font.BOLD, 12));
            g2d.drawString(StringHelper.midpad(fsSlipType, LEN003), 1, fnYValue); fnYValue += headerRectHeight;
                    
            return fnYValue;
        }
        
        private int createFooter(int fnYValue){
            if (g2d == null) return fnYValue;
            
            g2d.setFont(new Font(FONT, Font.PLAIN, 8));
            g2d.drawString(StringHelper.midpad(StringHelper.postpad("*", LEN001, asterisk), LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("SAMPLE RECEIPT", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Thank you, and please come again.", LEN001), 1, fnYValue); fnYValue += headerRectHeight;
            
            g2d.drawString(StringHelper.midpad("RMJ Business Solutions", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Pogo grande, Dagupan City", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("VAT REG TIN: 942-188-655-00000", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("ACCR NO.: XXXXXXXXXXXXXXXXXXXXXX", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Date Issued: XX/XX/XXXX", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Valid Until: XX/XX/XXXX", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("PTU NO.: XXXXXXXXXXXXXXXXXXXXXX", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Date Issued: XX/XX/XXXX", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("Valid Until: XX/XX/XXXX", LEN001), 1, fnYValue); fnYValue += headerRectHeight;
            
            g2d.drawString(StringHelper.midpad("THIS RECEIPT SHALL BE VALID", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("FOR FIVE(5) YEARS FROM THE DATE OF", LEN001), 1, fnYValue); fnYValue += yShift;
            g2d.drawString(StringHelper.midpad("THE PERMT TO USE.", LEN001), 1, fnYValue); fnYValue += yShift;
                    
            return fnYValue;
        }
    }

    
    public static class PrintJob{
        // The number of CMs per Inch
        private final static double CM_PER_INCH = 0.393700787d;
        // The number of Inches per CMs
        private final static double INCH_PER_CM = 2.545d;
        // The number of Inches per mm's
        private final static double INCH_PER_MM = 25.45d;
        
        public static PageFormat getPageFormat(PrinterJob pj){
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();    

            double width = cmsToPixel(10, 72);
            double height = cmsToPixel(15, 72);
            paper.setSize(width, height);
            paper.setImageableArea(
                            cmsToPixel(0.1, 72),
                            cmsToPixel(0.1, 72),
                            width - cmsToPixel(0.1, 72),
                            height - cmsToPixel(0.1, 72));

            //select orientation portrait or landscape but for this time portrait
            pf.setOrientation(PageFormat.PORTRAIT);           
            pf.setPaper(paper);    

            return pf;
        }
        
        /**
        * Converts the given pixels to cm's based on the supplied DPI
        *
        * @param pixels
        * @param dpi
        * @return
        */
        private static double pixelsToCms(double pixels, double dpi) {
            return inchesToCms(pixels / dpi);
        }

        /**
        * Converts the given cm's to pixels based on the supplied DPI
        *
        * @param cms
        * @param dpi
        * @return
        */
        private static double cmsToPixel(double cms, double dpi) {
            return cmToInches(cms) * dpi;
        }

        /**
        * Converts the given cm's to inches
        *
        * @param cms
        * @return
        */
        private static double cmToInches(double cms) {
            return cms * CM_PER_INCH;
        }

        /**
        * Converts the given inches to cm's
        *
        * @param inch
        * @return
        */
        private static double inchesToCms(double inch) {
            return inch * INCH_PER_CM;
        }
    }
}
