package srtparkinggarage;

import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author Shruthi Routhu
 */
public class RunningTotalManager {
    
    private static final String PARAMETER_NULL_MSG = "Method Parameter cannot be null" ;
    private static final String INVALID_SUMMARY_MSG = "Invalid checkout summary !";
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
   
    private String parkingGarageName ;
    private OutputStrategy runningTotalOpStrategy ;
    
    private double parkedHoursToday = 0;
    private double feeCollectedToday = 0;
    private DecimalFormat formatter = new DecimalFormat("#0.00");
    
    //CONSTRUCTOR
    public RunningTotalManager(final String parkingGarageName ,final OutputStrategy outputStrategy) 
            throws IllegalArgumentException {
        setParkingGarageName(parkingGarageName);
        setRunningTotalOpStrategy(outputStrategy);
    }

    //METHODS
    public final void updateTotal( final double hours, final double fee ) 
            throws IllegalArgumentException{
        
        if((hours > 0) && (fee > 0)){
             this.parkedHoursToday += hours;
             this.feeCollectedToday += fee;
             sendOwnerReport();        
        }
        else{
            sendOwnerReport();
            throw new IllegalArgumentException(INVALID_SUMMARY_MSG);
        }
        
    }
    
    public final void resetTotals(){
        this.parkedHoursToday = 0;
        this.feeCollectedToday = 0;
    } 
        
    private void sendOwnerReport(){
       
        String outputStr = "\n----------TOTALS FOR GARAGE TODAY----------\n" ;
        outputStr  += this.parkingGarageName + "\n";
        outputStr  += "Hours charged : " + this.parkedHoursToday + "\n";
        outputStr  += "Fee collected : $" + formatter.format(this.feeCollectedToday) + "\n" ;
        
        runningTotalOpStrategy.outputData(outputStr);
    }
    
    //SETTER
    public final void setRunningTotalOpStrategy(final OutputStrategy outputStrategy) throws IllegalArgumentException {
        if( outputStrategy != null){
            this.runningTotalOpStrategy = outputStrategy ;
        }
        else{
           throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
        } 
    }
    
    public final void setParkingGarageName(final String parkingGarageName)throws IllegalArgumentException {
        
        if((parkingGarageName != null) && (parkingGarageName.length() > 0)){
            this.parkingGarageName = parkingGarageName;
        }else{
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
    }
        
    //GETTERS
    public final String getParkingGarageName() {
        return parkingGarageName;
    }
    
    public final double getParkedHoursToday() {
        return parkedHoursToday;
    }

    public final double getFeeCollectedToday() {
        return feeCollectedToday;
    }
    
   
    
    
    

    
    
    
}
