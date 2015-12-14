package srtparkinggarage;

import java.util.Objects;
import java.util.Random;

/**
 * This class implements the interface <code>TicketStrategy</code> . Parked
 * hours are entered in the constructor . Entry and exit times are not
 * associated with this class.
 *
 * @author Shruthi Routhu
 */
public class HourTicket implements TicketStrategy {

    private static final String INVALID_HOURS_MSG = "Sorry ! Hours parked must (0 and 24]";
    private static final String INVALID_CARID_MSG = "Sorry ! Invalid CarID ";

    private String carID;
    private int ticketID;
    private double hoursParked;
    private Random randomNumber = new Random(System.nanoTime());

    //CONSTRUCTOR
    /**
     * Constructor for creating an object of type <code>NoTimeTicket</code>
     *
     * @param carID - data type <String> value to uniquely identify
     * @param hoursParked data type <double> duration for which a car will be
     * parked, can be set only once .
     * @throws <code>CustomIllegalArgumentException</code> if carID or
     * hoursParked are invalid .
     */
    public HourTicket(final String carID, final double hoursParked) throws CustomIllegalArgumentException {
        setCarID(carID);
        this.ticketID = randomNumber.nextInt(999999) + 100000;
        setHoursParked(hoursParked);
    }

    //SETTERS
    /**
     * Private Setter Method to set hours parked
     *
     * @param hoursParked data type <double> duration for which a car will be
     * parked, can be set only once .
     * @throws <code>CustomIllegalArgumentException</code> if hoursParked are
     * less than 0
     */
    private void setHoursParked(final double hoursParked) throws CustomIllegalArgumentException {
        if ((hoursParked > 0) && (hoursParked <= 24)) {
            this.hoursParked = hoursParked;
        } else {
            throw new CustomIllegalArgumentException(INVALID_HOURS_MSG);
        }
    }

    /**
     * Method to set CarID
     *
     * @param carID - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter carID is null
     */
    public final void setCarID(final String carID) throws CustomIllegalArgumentException {
        if ((carID != null) && carID.length() > 0) {
            this.carID = carID;
        } else {
            throw new CustomIllegalArgumentException(INVALID_CARID_MSG);
        }
    }

    //GETTERS    
    /**
     * Method to get hours parked
     *
     * @retuns <code>double</code>
     *
     */
    public final double getHoursParked() {
        return hoursParked;
    }

    /**
     * Method to get TicketID
     *
     * @retuns <code>int</code>
     */
    public final int getTicketID() {
        return ticketID;
    }

    /**
     * Method to get CarID
     *
     * @retuns <code>String</code>
     */
    public final String getCarID() {
        return carID;
    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.carID);
        hash = 29 * hash + this.ticketID;
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
        final HourTicket other = (HourTicket) obj;
        if (!Objects.equals(this.carID, other.carID)) {
            return false;
        }
        if (this.ticketID != other.ticketID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HourTicket{" + "carID=" + carID + ", ticketID=" + ticketID + '}';
    }

//// UNIT TEST
//    public static void main(String[] args) throws CustomIllegalArgumentException{
//        HourTicket ht = new  HourTicket("CAR!@#" , 1);
//        System.out.println( ht.getHoursParked());
//    }
    
    
    
}
