
package srtparkinggarage;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 *
 * @author Shruthi Routhu
 */
public class DetailedReceiptFormat implements ReceiptFormatStrategy{

    private DecimalFormat formatter = new DecimalFormat("#0.00");
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
   
    @Override
    public final String getReceiptString(final String garageName, final TicketStrategy ticket,
            final double parkingFee , final PaymentType payType) throws Exception {
        
        // Parameters have to be validated before using
        
        int minutes = (int)Math.floor(ticket.getHoursParked()* 60 );
     
        
        String receiptStr  = "\n----------" +garageName.toUpperCase() + "----------";
        receiptStr += "\nReciept for CarID : " + ticket.getCarID();
     
        receiptStr += "\nHours Parked : " + ticket.getHoursParked();
        receiptStr += "\nFee charged : $" + formatter.format(parkingFee);
        receiptStr += "\nPayment Type : " + payType ;
        
        receiptStr +="\n-------------Thank you for your business,";
        receiptStr +="Please come again!---------------- \n" ;
        
        
        return receiptStr;
  
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailedReceiptFormat other = (DetailedReceiptFormat) obj;
        return true;
    }

    @Override
    public String toString() {
        return "DetailedReceiptFormat{" + "formatter=" + formatter + ", df=" + df + '}';
    }
    
    
    

  
}
