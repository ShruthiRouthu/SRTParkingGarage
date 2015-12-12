package srtparkinggarage;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Shruthi Routhu
 */
public class NoTimeTicket implements TicketStrategy{
    
    private static final String INVALID_HOURS_MSG = "Sorry ! Hours parked must be greater than 0";
    private static final String INVALID_CARID_MSG = "Sorry ! Invalid CarID " ;
    private static final String TIME_LIMIT_VIOLATED = "Parking Rule Violation !!! ";
    
    
    private String carID;
    private int ticketID;
    private double hoursParked;
    
    private LocalDateTime entryTime;
    private boolean calcHours ;
    private Random randomNumber = new Random(System.nanoTime());
    private DecimalFormat formatter = new DecimalFormat("#0.00");
   
    //CONSTRUCTOR
    public NoTimeTicket(final String carID )  {
        setCarID(carID);
        this.ticketID = randomNumber.nextInt(999999)+ 100000;
        this.entryTime =   LocalDateTime.now(); //LocalDateTime.of(2015, 10, 20, 7, 55, 6,123 );
        this.calcHours = true;
    }
    
    private void calculateHours() throws Exception{
        LocalDateTime exitTime =  LocalDateTime.now(); // LocalDateTime.of(2015, 10, 19, 10, 05, 6,123 ); 
        
        System.out.println( "\n entry time: " + entryTime );
        System.out.println( "\n exit time: " + exitTime + "\n");
        
        Duration diff = Duration.between(entryTime, exitTime).abs();
        if(diff.toDays() <= 1){
               
            // then calculating parking hours  
            if(entryTime.isBefore(exitTime)){
                
                diff = Duration.between(entryTime, exitTime).abs();
               // long temp = diff.toMinutes();
                long temp = diff.toMillis(); //considering milli seconds as minutes for practicality
                double parkedHours = temp/60.0; 
                this.hoursParked = Double.parseDouble(formatter.format(parkedHours));
                calcHours = false;
            }
        }
        else{
            throw new Exception(TIME_LIMIT_VIOLATED);
        }
        
    }
 
    //GETTERS     
    public final double getHoursParked() throws Exception {
        //To ensure that exitTime can only be set once when the get hours is first called
        if(this.calcHours){
            calculateHours();
        }
        return hoursParked;
    }
    
    public final int getTicketID() {
        return ticketID;
    }

    public final String getCarID() {
        return carID;
    }
    
    //SETTERS
    public final void setCarID(final String carID) throws IllegalArgumentException {
        if((carID != null)&& carID.length() > 0){
            this.carID = carID;
        }
        else{
            throw new IllegalArgumentException(INVALID_CARID_MSG);
        }
    }   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.carID);
        hash = 47 * hash + this.ticketID;
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
        final NoTimeTicket other = (NoTimeTicket) obj;
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
        return "NoTimeTicket{" + "carID=" + carID + ", ticketID=" + ticketID + '}';
    }
    
       
    //UNIT TESTING
    public static void main(String[] args) {
        NoTimeTicket t = new NoTimeTicket("CAR!))");
       try{ System.out.println(t.getHoursParked());}
       catch(Exception e){
           System.out.println(e.getMessage());
       }  
        
    }

    
    
}
