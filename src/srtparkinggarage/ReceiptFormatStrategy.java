
package srtparkinggarage;

/**
 * Interface(strategy Interface) to enable usage of various
 * ReceiptFormatStrategies based on polymorphism
 *
 * @author Shruthi Routhu
 */
public interface ReceiptFormatStrategy {

    /**
     * Abstract Method to format Receipts
     *
     * @param garageName - data type  <code>String</code>
     * @param ticket - data type  <code>TicketStrategy </code>
     * @param parkingFee - data type  <code>double </code>
     * @param payType - enum   <code>PaymentType</code> has options for cash, card
     * ,check etc.
     *
     * @returns formatTed receipt as <code>String</code>
     *
     * @throws <code>Exception </code> if parameters are null or empty(in case
     * of strings) and also because date manipulations are involved.
     */
    public String getReceiptString(String garageName, TicketStrategy ticket, double parkingFee, PaymentType payType) throws Exception;

}
