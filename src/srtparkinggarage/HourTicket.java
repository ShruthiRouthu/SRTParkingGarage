package srtparkinggarage;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Shruthi Routhu
 */
public class HourTicket implements TicketStrategy{
    
    private static final String INVALID_HOURS_MSG = "Sorry ! Hours parked must be greater than 0";
    private static final String INVALID_CARID_MSG = "Sorry ! Invalid CarID " ;
    
    private String carID;
    private int ticketID;
    private double hoursParked;
    private Random randomNumber = new Random(System.nanoTime());
   
    //CONSTRUCTOR
    public HourTicket(final String carID ,final double hoursParked)  {
        setCarID(carID);
        this.ticketID = randomNumber.nextInt(999999)+ 100000;
        setHoursParked(hoursParked);
    }
    
    //SETTERS
    public final void setHoursParked(final double hoursParked) throws IllegalArgumentException {
        if(hoursParked > 0){
            this.hoursParked = hoursParked;
        }
        else{
            throw new IllegalArgumentException(INVALID_HOURS_MSG);
        }
    }

    public final void setCarID(final String carID) throws IllegalArgumentException {
        if((carID != null)&& carID.length() > 0){
            this.carID = carID;
        }
        else{
            throw new IllegalArgumentException(INVALID_CARID_MSG);
        }
    }
    
    
    
    //GETTERS     
    public final double getHoursParked() {
        return hoursParked;
    }
    
    public final int getTicketID() {
        return ticketID;
    }

    public final String getCarID() {
        return carID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.carID);
        hash = 29 * hash + this.ticketID;
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
    
    
    
    
    
    
}
