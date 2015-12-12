package srtparkinggarage;

import java.time.LocalDateTime;

/**
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageManager {
    
    private ParkingGarageEntryTerminal entryTerminal; 
    private ParkingGarageExitTerminal exitTerminal ;
    
    //CONSTRUCTOR
    public ParkingGarageManager(final String parkingGarageName,
           final ParkingFeeStrategy parkingFeeStrategy,
           final ReceiptFormatStrategy receiptFormatStrategy, final OutputStrategy runningTotalOpStrategy ) {
        
        try{
            //validate input before using
            this.entryTerminal = new ParkingGarageEntryTerminal();
            this.exitTerminal = new ParkingGarageExitTerminal(parkingGarageName, parkingFeeStrategy,
                                    receiptFormatStrategy, runningTotalOpStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    // METHODS
    
    // car will be parked only if that car (i.e carID) is not parked before
    // Parking a car without checking it out first will be prevented.
    public final void parkCar(TicketStrategy ticketStrategy){
        try{ 
           //validate input before using 
           this.entryTerminal.parkCar(ticketStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } 
  
    // first checks wearher that car is in parking garage, if thats true
    // then prints receipt and running totals, 
    // receipt and runningtotals wont be printed if checkout is invalid i.e hrs > 24
    // if checkout happens successfully then that car is deleted from Ticketlist/ database .
    
    public final void checkOutCar(final String carID, final PaymentType payType){
        try{
            //validate input before using
            TicketStrategy selectedTicket = entryTerminal.getTicket(carID);
            exitTerminal.checkOutCar(selectedTicket, payType);
            this.entryTerminal.deleteTicketFromList(carID);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //SETTERS
    public final void setParkingFeeStrategy( final ParkingFeeStrategy parkingFeeStrategy ) {
           
        
        try{
            //validate parameters
            this.exitTerminal.setParkingFeeStrategy(parkingFeeStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public final void setReceiptFormatStrategy(final ReceiptFormatStrategy receiptFormatStrategy){
        
        try{    
            //validate parameters
            this.exitTerminal.setReceiptFormatStrategy(receiptFormatStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
     
    public final void setRTOutputStrategy(final OutputStrategy runningTotalOpStrategy){
        
        try{
            //validate parameters
            this.exitTerminal.setRTOutputStrategy(runningTotalOpStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
//    // Method to test how array is working
//    public void getCarList(){
//        System.out.println(this.entryTerminal.getCarList());
//    }
  
} 