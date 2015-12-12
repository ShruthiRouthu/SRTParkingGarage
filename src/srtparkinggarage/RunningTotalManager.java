package srtparkinggarage;

import filemanagementsystem.FileService;
import filemanagementsystem.FormatStrategy;
import filemanagementsystem.TextReader;
import filemanagementsystem.TextWriter;
import java.io.File;
import java.text.DecimalFormat;


/**
 *
 * @author Shruthi Routhu
 */
public class RunningTotalManager {
    
    private static final String PARAMETER_NULL_MSG = "Method Parameter cannot be null" ;
    private static final String INVALID_FILE_MSG = "File not valid" ;
    private static final String INVALID_SUMMARY_MSG = "Invalid checkout summary !";
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
   
    private String parkingGarageName ;
    private final FileService fileService ;
    private File totalsFile;
    private FormatStrategy formatStrategy;
    
    private double parkedHoursToday ;
    private double feeCollectedToday ;
    private final DecimalFormat formatter = new DecimalFormat("#0.00");
    
    //CONSTRUCTOR
    public RunningTotalManager(final String parkingGarageName, final File totalsFile , 
            final FormatStrategy formatStrategy) throws IllegalArgumentException {
       
        setParkingGarageName(parkingGarageName);
        setTotalsFile(totalsFile);
        setFormatStrategy(formatStrategy);
        this.fileService = new FileService(new TextReader(), new TextWriter());
        
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

    //SETTERS
    public void setTotalsFile(File totalsFile) throws IllegalArgumentException {
        
        if(  (totalsFile != null) && totalsFile.exists() ){
            this.totalsFile = totalsFile;
        }else{
            throw new IllegalArgumentException(INVALID_FILE_MSG); 
        }
        
    }

    public void setFormatStrategy(FormatStrategy formatStrategy)throws IllegalArgumentException {
        
        if( totalsFile != null ){
            this.formatStrategy = formatStrategy;
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

    //MANDATORY METHODS  
   
   
    
    
    

    
    
    
}
