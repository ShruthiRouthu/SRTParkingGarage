package srtparkinggarage;

import filemanagementsystem.CSVFormatter;
import filemanagementsystem.FileService;
import filemanagementsystem.FileFormatStrategy;
import filemanagementsystem.PipeSVFormatter;
import filemanagementsystem.TextReader;
import filemanagementsystem.TextWriter;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 *
 * @author Shruthi Routhu
 */
public class RunningTotalManager {
    
    private static final String PARAMETER_NULL_MSG = "RunningTotalManager : Method Parameter cannot be null" ;
    private static final String INVALID_FILE_MSG = "RunningTotalManager : File not valid" ;
    private static final String INVALID_SUMMARY_MSG = "RunningTotalManager : Invalid checkout summary !";
    private static final String INVALID_STRING_PARAMETER_MSG = "RunningTotalManager : String parameter not valid" ;
    private static final String DATE_TIME = "DateTime";
    private static final String TOTAL_HOURS = "TotalHours";
    private static final String TOTAL_FEES = "TotalFees";
   
    private String parkingGarageName ;
    private final FileService fileService ;
    private File totalsFile;
    private FileFormatStrategy fileFormatStrategy;
    
    private double parkedHoursToday ;
    private double feeCollectedToday ;
    private final DecimalFormat formatter = new DecimalFormat("#0.00");
    
    
    
    //CONSTRUCTOR
    public RunningTotalManager(final String parkingGarageName, final File totalsFile , 
            final FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {
       
        setParkingGarageName(parkingGarageName);
        this.fileService = new FileService(new TextReader(), new TextWriter());
        setFileSystem(totalsFile, fileFormatStrategy);
        setTotals();
        
        
    }

    //METHODS
    public final void updateTotal( final double hours, final double fee ) 
            throws CustomIllegalArgumentException{
        
        if((hours > 0) && (fee > 0)){
            
             this.parkedHoursToday += hours;
             this.feeCollectedToday += fee;
             sendOwnerReport();        
        }
        else{
         
            throw new IllegalArgumentException(INVALID_SUMMARY_MSG);
        }
        
    }
    
    
    
    public final void setTotals() throws CustomIllegalArgumentException ,  DateTimeParseException{
        
        Map<String,Object> latestTotals = fileService.getLastLine(this.totalsFile, this.fileFormatStrategy);
      
        if( (latestTotals != null) && (latestTotals.get(DATE_TIME) != null) && (latestTotals.get(DATE_TIME).toString().length() > 0) ){
          
            // Getting and converting date string from file to LocalDateTime
            Object tempDate = latestTotals.get(DATE_TIME);
            LocalDateTime dateFrmFile = LocalDateTime.parse(tempDate.toString());
           
            // getting current time
            LocalDateTime dateNow = LocalDateTime.now();
            
          
            // checking to see if it's the same day
            if(  (dateNow.getYear() == dateFrmFile.getYear()) &&
                 (dateNow.getDayOfYear() == dateFrmFile.getDayOfYear())  ) {
               
              
                // getting running totals from file
                Object tempHrs = latestTotals.get(TOTAL_HOURS);
                Object tempFees = latestTotals.get(TOTAL_FEES);
               
               
               
                // checking to see if running totals from file are valid i.e greater than zero 
                if( (tempHrs != null) && (tempFees != null) && (Double.parseDouble(tempHrs.toString()) > 0) && (Double.parseDouble(tempFees.toString()) > 0) ){
                  
                    this.feeCollectedToday = Double.parseDouble(tempFees.toString());
                    this.parkedHoursToday = Double.parseDouble(tempHrs.toString());
               
                }else{ // Running totals captured from file are invalid so new totals
                   
                    System.out.println("Previous Totals captured from file are invalid ! Resetting totals to Zero");
                    this.parkedHoursToday = 0;
                    this.feeCollectedToday = 0;
                    
                }
               
           }else{ // It's a new day, so new totals
                
               System.out.println("It's a new Day ! Resetting totals to Zero");
               this.parkedHoursToday = 0;
               this.feeCollectedToday = 0; 
           }
           
       }else{ // Data from file is not available
            
           System.out.println("Previous Totals from file are not available ! Resetting totals to Zero");
           this.parkedHoursToday = 0;
           this.feeCollectedToday = 0;
       }

    } 
    
    
        
    private void sendOwnerReport() throws  CustomIllegalArgumentException {
       
        Map<String,Object> totalsMap = new HashMap<>();
                totalsMap.put(DATE_TIME, LocalDateTime.now());
                totalsMap.put(TOTAL_HOURS, this.parkedHoursToday);
                totalsMap.put(TOTAL_FEES, formatter.format(this.feeCollectedToday));
        
        List<Map<String,Object>> updatedTotals = new ArrayList<>();
        updatedTotals.add(totalsMap);
        
        this.fileService.write(totalsFile, fileFormatStrategy, updatedTotals);
    }

    //SETTERS
    public final void setFileSystem(File totalsFile , FileFormatStrategy fileFormatStrategy)throws IllegalArgumentException {
        
        if( (totalsFile != null) && fileFormatStrategy != null ){
            if( totalsFile.exists()){
                this.fileFormatStrategy = fileFormatStrategy;
                this.totalsFile = totalsFile;
            }
            else{
               throw new IllegalArgumentException(INVALID_FILE_MSG); 
            }
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
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.parkingGarageName);
        hash = 41 * hash + Objects.hashCode(this.fileService);
        hash = 41 * hash + Objects.hashCode(this.fileFormatStrategy);
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
        final RunningTotalManager other = (RunningTotalManager) obj;
        if (!Objects.equals(this.parkingGarageName, other.parkingGarageName)) {
            return false;
        }
        if (!Objects.equals(this.fileService, other.fileService)) {
            return false;
        }
        if (!Objects.equals(this.fileFormatStrategy, other.fileFormatStrategy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RunningTotalManager{" + "parkingGarageName=" + parkingGarageName + ", parkedHoursToday=" + parkedHoursToday + ", feeCollectedToday=" + feeCollectedToday + '}';
    }
 
    
 // UNIT TESTING 
 
/*    
    public static void main(String[] args) {
        
    
        File myFile = new File("F:" + File.separatorChar + "WCTC2015Fall" +File.separatorChar + "AdvancedJavaF" + 
                File.separatorChar +  "finalProject" + File.separatorChar + "SRTParkingGarageCurrent" + 
            File.separatorChar + "SRTParkingGarage" + File.separatorChar+ "src" + File.separatorChar + "RunningTotals.csv");
      
//  File myFile = new File("F:" + File.separatorChar + "WCTC2015Fall" +File.separatorChar + "AdvancedJavaF" + 
//                File.separatorChar +  "finalProject" + File.separatorChar + "SRTParkingGarageCurrent" + 
//    File.separatorChar + "SRTParkingGarage" + File.separatorChar+ "src" + File.separatorChar + "PipeSVRunningTotals.txt");    
    
        RunningTotalManager rtm = new RunningTotalManager("Shruthi" , myFile , new CSVFormatter()) ;
        
        rtm.updateTotal(100.00, 100.00);
        
//        LocalDateTime time = LocalDateTime.now();
//        System.out.println(time);
//        
//        
//        LocalDateTime dateFrmFile = LocalDateTime.parse(time.toString());
//        System.out.println(dateFrmFile);
    }
    
*/    
    
}
