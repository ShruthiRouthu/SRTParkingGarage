package srtparkinggarage;

import filemanagementsystem.FileFormatStrategy;
import java.io.File;
import java.util.Objects;

/**
 * This class performs the processes related to checking out a car from parking
 * garage.
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageExitTerminal {

    private static final String PARAMETER_NULL_MSG = "ParkingGarageExitTerminal : Parameter objects cannot be null";
    private static final String INVALID_STRING_PARAMETER_MSG = "ParkingGarageExitTerminal : String parameters cannot be null or empty";
    private static final String INVALID_FILE_MSG = "ParkingGarageExitTerminal : File not valid";

    private Receipt receipt;
    private RunningTotalManager runningTotalManager;
    private ParkingFeeStrategy parkingFeeStrategy;

    //CONSTRUCTOR
    /**
     * Creates an object of <code>ParkingGarageExitTerminal</code>
     *
     * @param parkingGarageName of data type <code> String</code>
     * @param parkingFeeStrategy of data type <code>ParkingFeeStrategy</code>
     * @param receiptFormatStrategy of data type
     * <code>ReceiptFormatStrategy</code>
     * @param receiptOpStrategy of data type <code>OutputStrategy</code>
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public ParkingGarageExitTerminal(final String parkingGarageName, final ParkingFeeStrategy parkingFeeStrategy,
            final ReceiptFormatStrategy receiptFormatStrategy, final OutputStrategy receiptOpStrategy, final File totalsFile,
            final FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {

        if ((parkingGarageName == null) || (parkingGarageName.isEmpty())) {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        if ((parkingFeeStrategy == null) || (receiptFormatStrategy == null)
                || (receiptOpStrategy == null) || (fileFormatStrategy == null) || (totalsFile == null)) {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }
        if (!totalsFile.exists()) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }

        setParkingFeeStrategy(parkingFeeStrategy);
        this.receipt = new Receipt(parkingGarageName, receiptFormatStrategy, receiptOpStrategy);
        this.runningTotalManager = new RunningTotalManager(parkingGarageName, totalsFile, fileFormatStrategy);
    }

    //METHODS
    /**
     * Method to check out a car from parking garage
     *
     * @param ticket of data type <code> TicketStrategy</code>
     * @param payType enum option of <code>PaymentType</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>Exception</code> if input parameters are null
     */
    public final void checkOutCar(final TicketStrategy ticket, final PaymentType payType) throws Exception {
        //validate input before using
        double parkingFee = calculateParkingFee(ticket);
        receipt.outputReceipt(ticket, parkingFee, payType);
        runningTotalManager.updateTotal(ticket.getHoursParked(), parkingFee);

    }

    /**
     * Private Method to calculate parking fee for a car
     *
     * @param ticket of data type <code> TicketStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>Exception</code> if input parameters are null
     */
    private double calculateParkingFee(final TicketStrategy ticket) throws Exception {
        if (ticket != null) {
            return this.parkingFeeStrategy.calculateParkingFee(ticket.getHoursParked());
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }

    }

    //SETTER
    /**
     * Method to set ParkingFeeStrategy
     *
     * @param parkingFeeStrategy of data type <code>ParkingFeeStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null
     */
    public final void setParkingFeeStrategy(final ParkingFeeStrategy parkingFeeStrategy)
            throws CustomIllegalArgumentException {

        if (parkingFeeStrategy != null) {
            this.parkingFeeStrategy = parkingFeeStrategy;
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }

    /**
     * Method to set ParkingGarageName
     *
     * @param parkingGarageName of data type <code>String</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null or empty
     */
    public final void setParkingGarageName(final String parkingGarageName) throws CustomIllegalArgumentException {

        if ((parkingGarageName != null) && (parkingGarageName.length() > 0)) {
            receipt.setParkingGarageName(parkingGarageName);
            runningTotalManager.setParkingGarageName(parkingGarageName);
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
            this.receipt.setReceiptFormatStrategy(receiptFormatStrategy);
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
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
    public final void setReceiptOutputStrategy(final OutputStrategy receiptOutputStrategy)
            throws CustomIllegalArgumentException {

        if (receiptOutputStrategy != null) {
            this.receipt.setReceiptOutputStrategy(receiptOutputStrategy);
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }

    }

    /**
     * Method to set FileSystem ie to set the file in which totals will be
     * stored and the format in which the totals should be stored.
     *
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public final void setFileSystem(File totalsFile, FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {

        if ((totalsFile != null) && fileFormatStrategy != null) {
            if (totalsFile.exists()) {
                this.runningTotalManager.setFileSystem(totalsFile, fileFormatStrategy);
            } else {
                throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
            }
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }

    }

    //MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.receipt);
        hash = 37 * hash + Objects.hashCode(this.runningTotalManager);
        hash = 37 * hash + Objects.hashCode(this.parkingFeeStrategy);
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
        final ParkingGarageExitTerminal other = (ParkingGarageExitTerminal) obj;
        if (!Objects.equals(this.receipt, other.receipt)) {
            return false;
        }
        if (!Objects.equals(this.runningTotalManager, other.runningTotalManager)) {
            return false;
        }
        return Objects.equals(this.parkingFeeStrategy, other.parkingFeeStrategy);
    }

    @Override
    public String toString() {
        return "ParkingGarageExitTerminal{" + "receipt=" + receipt + ", runningTotalManager=" + runningTotalManager + ", parkingFeeStrategy=" + parkingFeeStrategy + '}';
    }

}
