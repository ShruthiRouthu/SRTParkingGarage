package srtparkinggarage;

import filemanagementsystem.FileFormatStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Abstract Factory class to configure ParkingGarage Application
 *
 *
 * @author Shruthi Routhu
 */
public abstract class ParkingGarageConfigFactory {

    private static final String CONFIG_FILE_URL = "F:\\WCTC2015Fall\\AdvancedJavaF\\finalProject\\SRTParkingGarageCurrent\\SRTParkingGarage\\src\\config.properties";
    private static final String RECEIPT_FORMAT_STRATEGY = "receiptFormatStrategy";
    private static final String FILE_FORMAT_STRATEGY = "fileFormatStrategy";
    private static final String PARKING_GARAGE_NAME = "parkingGarageName";
    private static final String TOTALS_FILE = "totalsFile";

    private static final String INVALID_FILE_MSG = "ConfigFactory : File not valid";

    /**
     * Factory method to generate <code>FileFormatStrategy</code> object
     *
     * @return  <code>FileFormatStrategy</code> object
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if it cannot access
     * the Config file
     */
    public static final FileFormatStrategy getFileFormatStrategyInstance() throws CustomIllegalArgumentException {

        FileFormatStrategy fileFormatStrategy = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if (!file.exists()) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }

        Properties props = new Properties();
        FileInputStream inFile;
        try {
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();

            //Using Java reflection to create instance
            String className = props.getProperty(FILE_FORMAT_STRATEGY);
            Class clazz = Class.forName(className);
            fileFormatStrategy = (FileFormatStrategy) clazz.newInstance();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fileFormatStrategy;
    }

    /**
     * Factory method to generate <code>ReceiptFormatStrategy</code> object
     *
     * @return <code>ReceiptFormatStrategy</code> object
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if it cannot access
     * the Config file
     */
    public static final ReceiptFormatStrategy getReceiptFormatStrategyInstance() throws CustomIllegalArgumentException {
        ReceiptFormatStrategy receiptFormatStrategy = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if (!file.exists()) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }

        Properties props = new Properties();
        FileInputStream inFile;

        try {

            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();

            // Using Java reflection to create instance
            String className = props.getProperty("receiptFormatStrategy");
            Class clazz = Class.forName(className);
            receiptFormatStrategy = (ReceiptFormatStrategy) clazz.newInstance();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return receiptFormatStrategy;
    }

    /**
     * Factory method to get Parking Garage Name data type - <code>String</code>
     *
     * @return Parking Garage Name, data type - <code>String</code>
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if it cannot access
     * the Config file
     */
    public static final String getParkingGarageName() throws CustomIllegalArgumentException {

        String parkingGarageName = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if (!file.exists()) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }

        Properties props = new Properties();
        FileInputStream inFile;

        try {

            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();

            parkingGarageName = props.getProperty(PARKING_GARAGE_NAME);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return parkingGarageName;

    }

    /**
     * Factory method to get TotalsFile data type - <code>File</code>
     *
     * @return It reads the url for TotalsFile and converts it into a
     * <code>File</code> object and returns it
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if it cannot access
     * the Config file
     */
    public static final File getTotalsFile() throws CustomIllegalArgumentException {

        File totalsFile = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if (!file.exists()) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }

        Properties props = new Properties();
        FileInputStream inFile;

        try {

            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();

            String fileUrl = props.getProperty(TOTALS_FILE);
            totalsFile = new File(fileUrl);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ((totalsFile == null) || (!totalsFile.exists())) {
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }
        return totalsFile;

    }

}
