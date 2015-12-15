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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class manages the running totals of parking garage application It has
 * methods to read previous totals from file and to write current totals to file
 *
 * @author Shruthi Routhu
 */
public class RunningTotalManager {

    private static final String PARAMETER_NULL_MSG = "RunningTotalManager : Method Parameter cannot be null";
    private static final String INVALID_FILE_MSG = "RunningTotalManager : File not valid";
    private static final String INVALID_SUMMARY_MSG = "RunningTotalManager : Invalid checkout summary hours and fees cannot be less than 0 !";
    private static final String INVALID_STRING_PARAMETER_MSG = "RunningTotalManager : String parameter cannot be null or empty";

    private static final String DATE_TIME = "DateTime";
    private static final String TOTAL_HOURS = "TotalHours";
    private static final String TOTAL_FEES = "TotalFees";

    private String parkingGarageName;
    private final FileService fileService;
    private File totalsFile;
    private FileFormatStrategy fileFormatStrategy;

    private double parkedHoursToday;
    private double feeCollectedToday;
    private final DecimalFormat formatter = new DecimalFormat("#0.00");

    //CONSTRUCTOR
    /**
     * Creates an object of <code>RunningTotalManager</code>
     *
     * @param parkingGarageName of data type <code> String</code>
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throw <code> CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public RunningTotalManager(final String parkingGarageName, final File totalsFile,
            final FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {

        setParkingGarageName(parkingGarageName);
        this.fileService = new FileService(new TextReader(), new TextWriter());
        setFileSystem(totalsFile, fileFormatStrategy);
        setTotals();

    }

    //METHODS
    /**
     * Method to update Running totals when a car is checked out
     *
     * @param fee of data type <code>double</code>
     * @param hours of data type <code>double</code>
     * @throw <code> CustomIllegalArgumentException</code> if input parameters
     * are less than or equal to zero
     */
    public final void updateTotal(final double hours, final double fee)
            throws CustomIllegalArgumentException {

        if ((hours > 0) && (fee > 0)) {

            this.parkedHoursToday += hours;
            this.feeCollectedToday += fee;
            sendOwnerReport();
        } else {

            throw new CustomIllegalArgumentException(INVALID_SUMMARY_MSG);
        }

    }

    /**
     * Method to set FileSystem ie to set the file in which totals will be
     * stored and the format in which the totals should be stored.
     *
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throw <code>CustomIllegalArgumentException</code> and
     * <code>DateTimeParseException</code>
     */
    public final void setTotals() throws CustomIllegalArgumentException, DateTimeParseException {

        Map<String, Object> latestTotals = fileService.getLastLine(this.totalsFile, this.fileFormatStrategy);

        if ((latestTotals != null) && (latestTotals.get(DATE_TIME) != null) && (latestTotals.get(DATE_TIME).toString().length() > 0)) {

            // Getting and converting date string from file to LocalDateTime
            Object tempDate = latestTotals.get(DATE_TIME);
            LocalDateTime dateFrmFile = LocalDateTime.parse(tempDate.toString());

            // getting current time
            LocalDateTime dateNow = LocalDateTime.now();

            // checking to see if it's the same day
            if ((dateNow.getYear() == dateFrmFile.getYear())
                    && (dateNow.getDayOfYear() == dateFrmFile.getDayOfYear())) {

                // getting running totals from file
                Object tempHrs = latestTotals.get(TOTAL_HOURS);
                Object tempFees = latestTotals.get(TOTAL_FEES);

                // checking to see if running totals from file are valid i.e greater than zero 
                if ((tempHrs != null) && (tempFees != null) && (Double.parseDouble(tempHrs.toString()) > 0) && (Double.parseDouble(tempFees.toString()) > 0)) {

                    this.feeCollectedToday = Double.parseDouble(tempFees.toString());
                    this.parkedHoursToday = Double.parseDouble(tempHrs.toString());

                } else { // Running totals captured from file are invalid so new totals

                    System.out.println("Previous Totals captured from file are invalid ! Resetting totals to Zero");
                    this.parkedHoursToday = 0;
                    this.feeCollectedToday = 0;

                }

            } else { // It's a new day, so new totals

                System.out.println("It's a new Day ! Resetting totals to Zero");
                this.parkedHoursToday = 0;
                this.feeCollectedToday = 0;
            }

        } else { // Data from file is not available

            System.out.println("Previous Totals from file are not available ! Resetting totals to Zero");
            this.parkedHoursToday = 0;
            this.feeCollectedToday = 0;
        }

    }

    /**
     * Method to write Running totals to a file
     *
     * @throw <code>CustomIllegalArgumentException</code> with regards to file
     * manipulation
     */
    private void sendOwnerReport() throws CustomIllegalArgumentException {

        Map<String, Object> totalsMap = new HashMap<>();
        totalsMap.put(DATE_TIME, LocalDateTime.now());
        totalsMap.put(TOTAL_HOURS, formatter.format(this.parkedHoursToday));
        totalsMap.put(TOTAL_FEES, formatter.format(this.feeCollectedToday));

        List<Map<String, Object>> updatedTotals = new ArrayList<>();
        updatedTotals.add(totalsMap);

        this.fileService.write(totalsFile, fileFormatStrategy, updatedTotals);
    }

    //SETTERS
    /**
     * Method to set FileSystem ie to set the file in which totals will be
     * stored and the format in which the totals should be stored.
     *
     * @param totalsFile of data type <code>File</code>
     * @param fileFormatStrategy of data type <code>FileFormatStrategy</code>
     * @throw <code>CustomIllegalArgumentException</code> if input parameters
     * are null or empty(in case of strings)
     */
    public final void setFileSystem(File totalsFile, FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {

        if ((totalsFile != null) && fileFormatStrategy != null) {
            if (totalsFile.exists()) {
                this.fileFormatStrategy = fileFormatStrategy;
                this.totalsFile = totalsFile;
            } else {
                throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
            }
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }

    }

    /**
     * Method to set ParkingGarageName
     *
     * @param parkingGarageName of data type <code> String</code>
     * @throw <code>CustomIllegalArgumentException</code> if input parameter is
     * null or empty(in case of strings)
     */
    public final void setParkingGarageName(final String parkingGarageName) throws CustomIllegalArgumentException {

        if ((parkingGarageName != null) && (parkingGarageName.length() > 0)) {
            this.parkingGarageName = parkingGarageName;
        } else {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }

    }

    //GETTERS
    /**
     * Method to get ParkingGarageName
     *
     * @return <code>String</code> object
     */
    public final String getParkingGarageName() {
        return parkingGarageName;
    }

    /**
     * Method to get ParkedHoursToday
     *
     * @return <code>double</code> value
     */
    public final double getParkedHoursToday() {
        return parkedHoursToday;
    }

    /**
     * Method to get FeeCollectedToday
     *
     * @return <code>double</code> value
     */
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
