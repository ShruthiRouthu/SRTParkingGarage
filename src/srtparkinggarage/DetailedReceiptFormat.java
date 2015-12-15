
package srtparkinggarage;

import java.text.DateFormat;
import java.text.DecimalFormat;

/**
 * This class implements <code>ReceiptFormatStrategy</code> and has method to
 * format Receipts
 *
 * @author Shruthi Routhu
 */
public class DetailedReceiptFormat implements ReceiptFormatStrategy {

    private static final String INVALID_STRING_MSG = "DetailedReceiptFormat: Input String cannot be null or empty ";
    private static final String INVALID_FEE_MSG = "DetailedReceiptFormat: Input fee cannot be less than 0 ";
    private static final String INVALID_OBJECT_MSG = "DetailedReceiptFormat: Input object cannot be null ";

    private DecimalFormat formatter = new DecimalFormat("#0.00");
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

    /**
     * Method to format Receipts with a lot of details
     *
     * @param garageName - data type  <code>String</code>
     * @param ticket - data type  <code>TicketStrategy </code>
     * @param parkingFee - data type  <code>double </code>
     * @param payType - enum   <code>PaymentType</code> has options for cash, card
     * ,check etc.
     *
     * @returns formatted receipt as <code>String</code> re
     *
     * @throws <code>Exception </code> if parameters are null or empty(in case
     * of strings) and also because date manipulations are involved.
     */
    @Override
    public final String getReceiptString(final String garageName, final TicketStrategy ticket,
            final double parkingFee, final PaymentType payType) throws Exception {

        // Input parameter validation
        if ((garageName == null) || (garageName.isEmpty())) {
            throw new CustomIllegalArgumentException(INVALID_STRING_MSG);
        }
        if (parkingFee < 0) {
            throw new CustomIllegalArgumentException(INVALID_FEE_MSG);
        }
        if ((ticket == null)) {
            throw new CustomIllegalArgumentException(INVALID_OBJECT_MSG);
        }

        int minutes = (int) Math.floor(ticket.getHoursParked() * 60);

        String receiptStr = "\n----------" + garageName.toUpperCase() + "----------";
        receiptStr += "\nReciept for CarID : " + ticket.getCarID();

        receiptStr += "\nHours Parked : " + ticket.getHoursParked();
        receiptStr += "\nFee charged : $" + formatter.format(parkingFee);
        receiptStr += "\nPayment Type : " + payType;

        receiptStr += "\n-------------Thank you for your business,";
        receiptStr += "Please come again!---------------- \n";

        return receiptStr;

    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
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
