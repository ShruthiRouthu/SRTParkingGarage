
package srtparkinggarage;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class SimpleReceiptFormat implements ReceiptFormatStrategy {
    
    private DecimalFormat formatter = new DecimalFormat("#0.00");
    
    @Override
    public final String getReceiptString(final String garageName,final TicketStrategy ticket,
           final double parkingFee, final PaymentType payType) throws Exception {
        
        // Parameters have to be validated before using
        
        String receiptStr  = "\n"+ garageName.toUpperCase()  ;
        receiptStr += "\nReciept for CarID :  " + ticket.getCarID();
        receiptStr += "\nHours Parked : " + ticket.getHoursParked();
        receiptStr += "\nFee charged : $" + formatter.format(parkingFee);
       
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
        final SimpleReceiptFormat other = (SimpleReceiptFormat) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SimpleReceiptFormat{" + "formatter=" + formatter + '}';
    }

    
   
    

    
    
    
    
}
