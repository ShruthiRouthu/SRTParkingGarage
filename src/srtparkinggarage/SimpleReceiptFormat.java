
package srtparkinggarage;

import java.text.DecimalFormat;

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
    
}
