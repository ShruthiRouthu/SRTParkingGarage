package srtparkinggarage;

import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageExitTerminal {

    private static final String PARAMETER_NULL_MSG = "Method Parameter cannot be null" ;
    
    private Receipt receipt;
    private RunningTotalManager runningTotalManager;
    private ParkingFeeStrategy parkingFeeStrategy ;
    
    //CONSTRUCTOR
    public ParkingGarageExitTerminal(final String parkingGarageName, final ParkingFeeStrategy parkingFeeStrategy,
           final ReceiptFormatStrategy receiptFormatStrategy,final OutputStrategy runningTotalOpStrategy )
           throws IllegalArgumentException {
       
        // need to validate parameters before using
        setParkingFeeStrategy(parkingFeeStrategy);
        this.receipt = new Receipt(parkingGarageName, receiptFormatStrategy);
        this.runningTotalManager = new RunningTotalManager(parkingGarageName ,runningTotalOpStrategy);
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
    
    public final void setReceiptFormatStrategy(final ReceiptFormatStrategy receiptFormatStrategy)
            throws IllegalArgumentException{
        //validate parameters
        this.receipt.setReceiptFormatStrategy(receiptFormatStrategy);
    }
     
    public final void setRTOutputStrategy(final OutputStrategy runningTotalOpStrategy)
            throws IllegalArgumentException{
        //validate parameters
        this.runningTotalManager.setRunningTotalOpStrategy(runningTotalOpStrategy);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.receipt);
        hash = 37 * hash + Objects.hashCode(this.runningTotalManager);
        hash = 37 * hash + Objects.hashCode(this.parkingFeeStrategy);
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
        final ParkingGarageExitTerminal other = (ParkingGarageExitTerminal) obj;
        if (!Objects.equals(this.receipt, other.receipt)) {
            return false;
        }
        if (!Objects.equals(this.runningTotalManager, other.runningTotalManager)) {
            return false;
        }
        if (!Objects.equals(this.parkingFeeStrategy, other.parkingFeeStrategy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParkingGarageExitTerminal{" + "receipt=" + receipt + ", runningTotalManager=" + runningTotalManager + ", parkingFeeStrategy=" + parkingFeeStrategy + '}';
    }
    
    
    
     
}
       
   
    

