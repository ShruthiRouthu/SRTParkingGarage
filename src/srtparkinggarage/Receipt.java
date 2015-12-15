package srtparkinggarage;

import java.util.Objects;

/**
 * This class generates receipt information associated with a carID/ticket
 * formats and outputs receipt as specified by delegating tasks to strategy
 * objects
 *
 * @author Shruthi Routhu
 */
public class Receipt {

    private static final String PARAMETER_NULL_MSG = "Method Parameter cannot be null";
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter cannot be null or empty";
    private static final String INVALID_CHECKOUT = "Parking fee cannot be <= 0 ! Invalid checkout !";

    private String parkingGarageName;
    private ReceiptFormatStrategy receiptFormatStrategy;
    private OutputStrategy receiptOutputStrategy;

    //CONSTRUCTOR
    /**
     * Creates an <code>Receipt</code> object
     *
     * @param parkingGarageName of data type <code> String</code>
     * @param receiptFormatStrategy of data type
     * <code>ReceiptFormatStrategy</code>
     * @param receiptOutputStrategy of data type <code>OutputStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public Receipt(final String parkingGarageName, final ReceiptFormatStrategy receiptFormatStrategy,
            final OutputStrategy receiptOutputStrategy) throws CustomIllegalArgumentException {

        setParkingGarageName(parkingGarageName);
        setReceiptFormatStrategy(receiptFormatStrategy);
        setReceiptOutputStrategy(receiptOutputStrategy);
    }

    //METHODS
    /**
     * Method to output receipt
     *
     * @param ticket of data type <code> TicketStrategy</code>
     * @param parkingFee of data type <code>double</code>
     * @param payType enum option of <code>PaymentType</code>
     * @throws java.lang.Exception
     * @throw <code>Exception</code> if ticket is null or if parkingFee less
     * than or equal to 0 also because date manipulations are involved
     */
    public final void outputReceipt(final TicketStrategy ticket, final double parkingFee, final PaymentType payType)
            throws Exception {

        if (ticket == null) {
            throw new CustomIllegalArgumentException("ticket: " + PARAMETER_NULL_MSG);
        }

        if (parkingFee > 0) {
            String receiptString = getRecieptString(ticket, parkingFee, payType);
            this.receiptOutputStrategy.outputData(receiptString);
        } else {
            throw new CustomIllegalArgumentException(INVALID_CHECKOUT);
        }
    }

    /**
     * Method to get formatted receipt as <code>string</code>
     *
     * @param ticket of data type <code> TicketStrategy</code>
     * @param parkingFee of data type <code>double</code>
     * @param payType enum option of <code>PaymentType</code>
     * @return formatted receipt as <code>string</code>
     * @throw <code>Exception</code> if ticket is null or if parkingFee less
     * than or equal to 0 also because date manipulations are involved
     */
    private String getRecieptString(final TicketStrategy ticket, final double parkingFee, final PaymentType payType)
            throws Exception {

        if (ticket == null) {
            throw new CustomIllegalArgumentException("ticket: " + PARAMETER_NULL_MSG);
        }
        if (parkingFee <= 0) {
            throw new CustomIllegalArgumentException(INVALID_CHECKOUT);
        }

        return receiptFormatStrategy.getReceiptString(parkingGarageName, ticket, parkingFee, payType);
    }

    // SETTER
    /**
     * Method to set ParkingGarageName
     *
     * @param parkingGarageName of data type <code> String</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null or empty(in case of strings)
     */
    public final void setParkingGarageName(final String parkingGarageName) throws CustomIllegalArgumentException {

        if ((parkingGarageName != null) && (parkingGarageName.length() > 0)) {
            this.parkingGarageName = parkingGarageName;
        } else {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }

    }

    /**
     * Method to set ReceiptFormatStrategy
     *
     * @param receiptFormatStrategy of data type
     * <code>ReceiptFormatStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null
     */
    public final void setReceiptFormatStrategy(final ReceiptFormatStrategy receiptFormatStrategy)
            throws CustomIllegalArgumentException {

        if (receiptFormatStrategy != null) {
            this.receiptFormatStrategy = receiptFormatStrategy;
        } else {
            throw new CustomIllegalArgumentException("receiptFormatStrategy : " + PARAMETER_NULL_MSG);
        }
    }

    /**
     * Method to set ReceiptOutputStrategy
     *
     * @param receiptOutputStrategy of data type
     * <code>ReceiptOutputStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null
     */
    public final void setReceiptOutputStrategy(OutputStrategy receiptOutputStrategy) throws CustomIllegalArgumentException {

        if (receiptOutputStrategy != null) {
            this.receiptOutputStrategy = receiptOutputStrategy;
        } else {
            throw new CustomIllegalArgumentException("receiptOutputStrategy: " + PARAMETER_NULL_MSG);
        }
    }

    //GETTER
    /**
     * Method to set ParkingGarageName
     * 
     * @return <code>String</code> object
     */
    public final String getParkingGarageName() {
        return parkingGarageName;
    }

    //MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.parkingGarageName);
        hash = 83 * hash + Objects.hashCode(this.receiptFormatStrategy);
        hash = 83 * hash + Objects.hashCode(this.receiptOutputStrategy);
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
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.parkingGarageName, other.parkingGarageName)) {
            return false;
        }
        if (!Objects.equals(this.receiptFormatStrategy, other.receiptFormatStrategy)) {
            return false;
        }
        return Objects.equals(this.receiptOutputStrategy, other.receiptOutputStrategy);
    }

    @Override
    public String toString() {
        return "Receipt{" + "parkingGarageName=" + parkingGarageName + ", receiptFormatStrategy=" + receiptFormatStrategy + ", receiptOutputStrategy=" + receiptOutputStrategy + '}';
    }
    
 
 
}
