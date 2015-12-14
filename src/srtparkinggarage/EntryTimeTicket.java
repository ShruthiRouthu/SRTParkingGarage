package srtparkinggarage;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

/**
 * This class implements the interface <code>TicketStrategy</code> . Garage
 * entry time is entered in constructor. Garage exit time is set when method
 * calculateHours() is called. (this method can be called only once)
 *
 * @author Shruthi Routhu
 */
public class EntryTimeTicket implements TicketStrategy {

    private static final String INVALID_ENTRY_TIME_MSG = "Sorry ! Entry time  is invalid( max parking hours is 24) ";
    private static final String INVALID_CARID_MSG = "Sorry ! Invalid CarID ";
    private static final String TIME_LIMIT_VIOLATED = "Parking Rule Violation !!! ";

    private String carID;
    private int ticketID;
    private double hoursParked;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean calcHours;
    private Random randomNumber = new Random(System.nanoTime());
    private DecimalFormat formatter = new DecimalFormat("#0.00");

    //CONSTRUCTOR
    /**
     * Constructor for creating an object of type <code>EntryTimeTicket</code>
     *
     * @param carID - data type <code>String</code> value to uniquely identify a
     * car
     * @param entryTime - data type <code>LocalDateTime</code>
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are invalid
     */
    public EntryTimeTicket(final String carID, final LocalDateTime entryTime) throws CustomIllegalArgumentException {
        setCarID(carID);
        setEntryTime(entryTime);
        this.ticketID = randomNumber.nextInt(999999) + 100000;
        this.calcHours = true;
    }

    /**
     * Private method to calculate hours parked in garage. This method gets
     * called only once.
     *
     * @throws <code>Exception</code> since <code>LocalDateTime</code>
     * calculations are involved
     */
    private void calculateHours() throws Exception {

        this.exitTime = LocalDateTime.now();

        System.out.println("\n entry time: " + entryTime);
        System.out.println("\n exit time: " + exitTime + "\n");

        Duration diff = Duration.between(entryTime, exitTime).abs();
        if (diff.toDays() <= 1) {

            // then calculating parking hours  
            if (entryTime.isBefore(exitTime)) {

                diff = Duration.between(entryTime, exitTime).abs();
                // long temp = diff.toMinutes();
                long temp = diff.toMillis(); //considering milli seconds as minutes for practicality
                double parkedHours = temp / 60.0;
                this.hoursParked = Double.parseDouble(formatter.format(parkedHours));
                calcHours = false;
            }
        } else {
            throw new Exception(TIME_LIMIT_VIOLATED);
        }

    }

    //GETTERS   
    /**
     * Method to get hours parked
     *
     * @retuns <code>double</code>
     * @throws <code>  Exception </code> since Date manipulations are involved .
     */
    public final double getHoursParked() throws Exception {
        //To ensure that exitTime can only be set once when the get hours is first called
        if (this.calcHours) {
            calculateHours();
        }
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

    //SETTERS
    /**
     * Method to set CarID
     *
     * @param carID - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> if the parameter
     * carID is null
     */
    public final void setCarID(final String carID) throws CustomIllegalArgumentException {
        if ((carID != null) && carID.length() > 0) {
            this.carID = carID;
        } else {
            throw new CustomIllegalArgumentException(INVALID_CARID_MSG);
        }
    }

    /**
     * Private method to set EntryTime
     *
     * @param EntryTime - data type  <code>LocalDateTime</code>
     * @throws <code>  CustomIllegalArgumentException </code> if the parameter
     * EntryTime is null or if EntryTime is more than 24 hrs ago
     */
    private void setEntryTime(final LocalDateTime entryTime) throws CustomIllegalArgumentException {
        if( (entryTime == null) ||  Duration.between(LocalDateTime.now(), entryTime).abs().toDays() >= 1){
            throw new CustomIllegalArgumentException(INVALID_ENTRY_TIME_MSG);
        }    
        
        this.entryTime = entryTime;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.carID);
        hash = 61 * hash + this.ticketID;
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
        final EntryTimeTicket other = (EntryTimeTicket) obj;
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
        return "EntryTimeTicket{" + "carID=" + carID + ", ticketID=" + ticketID + '}';
    }

//    //UNIT TESTING
//    public static void main(String[] args) throws CustomIllegalArgumentException {
//       EntryTimeTicket t = new EntryTimeTicket("CAR12", LocalDateTime.of(2015, 12, 12, 23, 00, 0, 000 ));
//       try{ System.out.println(t.getHoursParked());}
//       catch(Exception e){
//           System.out.println(e.getMessage());
//       }
//    }  
    
}
