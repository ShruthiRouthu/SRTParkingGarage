package srtparkinggarage;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Shruthi Routhu
 */
public class EntryTimeTicket implements TicketStrategy{
    
     
    private static final String INVALID_HOURS_MSG = "Sorry ! Hours parked must be greater than 0";
    private static final String INVALID_CARID_MSG = "Sorry ! Invalid CarID " ;
    private static final String TIME_LIMIT_VIOLATED = "Parking Rule Violation !!! ";
    
    
    private String carID;
    private int ticketID;
    private double hoursParked;
    
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean calcHours ;
    private Random randomNumber = new Random(System.nanoTime());
    private DecimalFormat formatter = new DecimalFormat("#0.00");
   
    //CONSTRUCTOR
    public EntryTimeTicket(final String carID, final LocalDateTime entryTime )  {
        //validat input parameters before using
        setCarID(carID);
        this.ticketID = randomNumber.nextInt(999999)+ 100000;
        this.entryTime =  entryTime;
        this.calcHours = true;
    }
    
    private void calculateHours() throws Exception{
        
        this.exitTime = LocalDateTime.now();//LocalDateTime.of(2015, 10, 20, 7, 55, 6,123 );
      
        System.out.println( "\n entry time: " + entryTime );
        System.out.println( "\n exit time: " + exitTime + "\n");
        
        if((entryTime.getYear() == exitTime.getYear()) &&(entryTime.getDayOfYear() == exitTime.getDayOfYear())){
            
            // converting time to mins
            int entryMins = (entryTime.getHour()*60) + entryTime.getMinute() ;
            int exitMins =  (exitTime.getHour()*60) + exitTime.getMinute() ;     
            
            // then calculating parking hours  
            if(exitMins > entryMins){
                int parkedMins = exitMins-entryMins;
                double parkedHours = parkedMins/60.0;

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
    
//    
//    //UNIT TESTING
//    public static void main(String[] args) {
//        NoTimeTicket t = new NoTimeTicket("CAR!))");
//       try{ System.out.println(t.getHoursParked());}
//       catch(Exception e){
//           System.out.println(e.getMessage());
//       }
//        
//        try{ System.out.println(t.getHoursParked());}
//       catch(Exception e){
//           System.out.println(e.getMessage());
//       }
//       
//        
//    }
//   
    
    
}
