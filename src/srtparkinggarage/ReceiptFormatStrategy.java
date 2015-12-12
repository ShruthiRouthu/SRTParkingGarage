
package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 */
public interface ReceiptFormatStrategy {
    
    public String getReceiptString(String garageName, TicketStrategy ticket, double parkingFee, PaymentType payType) throws Exception;
    
}
