package srtparkinggarage;

import filemanagementsystem.FileFormatStrategy;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class manages all the operations of PArking Garage, all input will talk to only this class. 
 * 
 * @author Shruthi Routhu
 */
public class ParkingGarageManager {
    
    private static final String PARAMETER_NULL_MSG = "ParkingGarageManager : Method Parameter cannot be null" ;
    private static final String INVALID_STRING_PARAMETER_MSG = "ParkingGarageManager : String parameter not valid" ;
    private static final String INVALID_FILE_MSG = "ParkingGarageManager : File not valid" ;
    
    private ParkingGarageEntryTerminal entryTerminal; 
    private ParkingGarageExitTerminal exitTerminal ;
    
    //CONSTRUCTOR
    /**
     * Creates an object of <code>ParkingGarageManager</code>
     *
     * @param parkingGarageName of data type <code> String</code>
     * @param parkingFeeStrategy of data type <code>ParkingFeeStrategy</code>
     * @param receiptFormatStrategy of data type
     * <code>ReceiptFormatStrategy</code>
     * @param receiptOutputStrategy of data type <code>OutputStrategy</code>
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throw <code>CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public ParkingGarageManager(final String parkingGarageName,
            final ParkingFeeStrategy parkingFeeStrategy,
            final ReceiptFormatStrategy receiptFormatStrategy,
            final OutputStrategy receiptOutputStrategy,
            final File totalsFile,
            final FileFormatStrategy fileFormatStrategy) {
        
      
        try {

            // Validation
            if ((parkingGarageName == null) || (parkingGarageName.isEmpty())) {
                throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
            }
            if ((parkingFeeStrategy == null) || (receiptFormatStrategy == null)
                    || (receiptOutputStrategy == null) || (fileFormatStrategy == null) || (totalsFile == null)) {
                throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
            }
            if (!totalsFile.exists()) {
                throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
            }
            
            this.entryTerminal = new ParkingGarageEntryTerminal();
            this.exitTerminal = new ParkingGarageExitTerminal(parkingGarageName, parkingFeeStrategy,
                    receiptFormatStrategy, receiptOutputStrategy, totalsFile, fileFormatStrategy);
            
        } catch (CustomIllegalArgumentException e) {
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
     
    public final void setReceiptOutputStrategy(final OutputStrategy receiptOutputStrategy){
        
        try{
            //validate parameters
            this.exitTerminal.setReceiptOutputStrategy(receiptOutputStrategy);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
     public final void setFileSystem(File totalsFile , FileFormatStrategy fileFormatStrategy)throws IllegalArgumentException {
        
        if( (totalsFile != null) && fileFormatStrategy != null ){
            if( totalsFile.exists()){
                this.exitTerminal.setFileSystem(totalsFile, fileFormatStrategy); 
            }
            else{
               throw new IllegalArgumentException(INVALID_FILE_MSG); 
            }
        }
        else{
           throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
        }
        
    }
   
    

    //MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.entryTerminal);
        hash = 13 * hash + Objects.hashCode(this.exitTerminal);
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
        final ParkingGarageManager other = (ParkingGarageManager) obj;
        if (!Objects.equals(this.entryTerminal, other.entryTerminal)) {
            return false;
        }
        return Objects.equals(this.exitTerminal, other.exitTerminal);
    }

    @Override
    public String toString() {
        return "ParkingGarageManager{" + "entryTerminal=" + entryTerminal + ", exitTerminal=" + exitTerminal + '}';
    }

    
//    // Method to test how array is working
//    public void getCarList(){
//        System.out.println(this.entryTerminal.getCarList());
//    }
  
} 