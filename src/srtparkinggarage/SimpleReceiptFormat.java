
package srtparkinggarage;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * This class implements <code>ReceiptFormatStrategy</code> and has method to
 * format Receipts
 *
 * @author Shruthi Routhu
 */
public class SimpleReceiptFormat implements ReceiptFormatStrategy {

    private static final String INVALID_STRING_MSG = "SimpelReceiptFormat: Input String cannot be null or empty ";
    private static final String INVALID_FEE_MSG = "SimpleReceiptFormat: Input fee cannot be less than 0 ";
    private static final String INVALID_OBJECT_MSG = "SimpleReceiptFormat: Input object cannot be null ";

    private DecimalFormat formatter = new DecimalFormat("#0.00");

    /**
     * Method to format Receipts with basic details
     *
     * @param garageName - data type  <code>String</code>
     * @param ticket - data type  <code>TicketStrategy </code>
     * @param parkingFee - data type  <code>double </code>
     * @param payType - enum   <code>PaymentType</code> has options for cash, card
     * ,check etc.
     *
     * @returns formatted receipt as <code>String</code>
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

        String receiptStr = "\n" + garageName.toUpperCase();
        receiptStr += "\nReciept for CarID :  " + ticket.getCarID();
        receiptStr += "\nHours Parked : " + ticket.getHoursParked();
        receiptStr += "\nFee charged : $" + formatter.format(parkingFee);

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
        final SimpleReceiptFormat other = (SimpleReceiptFormat) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SimpleReceiptFormat{" + "formatter=" + formatter + '}';
    }

}
