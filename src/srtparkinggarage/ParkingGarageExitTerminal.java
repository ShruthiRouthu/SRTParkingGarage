package srtparkinggarage;

import filemanagementsystem.FileFormatStrategy;
import java.io.File;
import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageExitTerminal {

    private static final String PARAMETER_NULL_MSG = "ParkingGarageExitTerminal : Method Parameter cannot be null" ;
    private static final String INVALID_STRING_PARAMETER_MSG = "ParkingGarageExitTerminal : String parameter not valid" ;
    private static final String INVALID_FILE_MSG = "ParkingGarageExitTerminal : File not valid" ;
    
    private Receipt receipt;
    private RunningTotalManager runningTotalManager;
    private ParkingFeeStrategy parkingFeeStrategy ;
    
    //CONSTRUCTOR
    public ParkingGarageExitTerminal(final String parkingGarageName, final ParkingFeeStrategy parkingFeeStrategy,
           final ReceiptFormatStrategy receiptFormatStrategy,final OutputStrategy receiptOpStrategy,final File totalsFile , 
            final FileFormatStrategy fileFormatStrategy ) throws IllegalArgumentException {
       
        // need to validate parameters before using
        setParkingFeeStrategy(parkingFeeStrategy);
        this.receipt = new Receipt(parkingGarageName, receiptFormatStrategy,  receiptOpStrategy);
        this.runningTotalManager = new RunningTotalManager(parkingGarageName, totalsFile, fileFormatStrategy );
    }
    
    //METHODS
    public final void checkOutCar(final TicketStrategy ticket, final PaymentType payType)throws Exception {
        //validate input before using
        double parkingFee = calculateParkingFee(ticket);
        receipt.outputReceipt(ticket, parkingFee, payType);
        runningTotalManager.updateTotal(ticket.getHoursParked(), parkingFee);
        
    } 
    
    private double calculateParkingFee(final TicketStrategy ticket)throws Exception {
        return this.parkingFeeStrategy.calculateParkingFee(ticket.getHoursParked());
    }
    
    //SETTER
    public final void setParkingFeeStrategy( final ParkingFeeStrategy parkingFeeStrategy ) 
            throws IllegalArgumentException {
        
        if(parkingFeeStrategy != null){
            this.parkingFeeStrategy = parkingFeeStrategy;
        }else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
        } 
    }
    
    public final void setParkingGarageName(final String parkingGarageName) throws IllegalArgumentException {
        
        if((parkingGarageName != null) && (parkingGarageName.length() > 0)){
            receipt.setParkingGarageName(parkingGarageName);
            runningTotalManager.setParkingGarageName(parkingGarageName);
        }else{
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
    }
    
    public final void setReceiptFormatStrategy(final ReceiptFormatStrategy receiptFormatStrategy)
            throws IllegalArgumentException{
       
        if(receiptFormatStrategy != null){
            this.receipt.setReceiptFormatStrategy(receiptFormatStrategy);
        }else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
        }
        
    }
     
    public final void setReceiptOutputStrategy(final OutputStrategy receiptOutputStrategy)
            throws IllegalArgumentException{
        
        if(receiptOutputStrategy != null){
            this.receipt.setReceiptOutputStrategy(receiptOutputStrategy);
        }else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
        }
        
    }
    
    public final void setFileSystem(File totalsFile , FileFormatStrategy fileFormatStrategy)throws IllegalArgumentException {
        
        if( (totalsFile != null) && fileFormatStrategy != null ){
            if( totalsFile.exists()){
                this.runningTotalManager.setFileSystem(totalsFile, fileFormatStrategy);
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
       
   
    

