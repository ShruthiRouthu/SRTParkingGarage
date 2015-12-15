package srtparkinggarage;

import filemanagementsystem.FileFormatStrategy;
import filemanagementsystem.PipeSVFormatter;
import filemanagementsystem.CSVFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
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
    
    public static  final  FileFormatStrategy getFileFormatStrategyInstance() throws CustomIllegalArgumentException {
        
        FileFormatStrategy fileFormatStrategy = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if( !file.exists() ){
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
            fileFormatStrategy = (FileFormatStrategy)clazz.newInstance();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return fileFormatStrategy;
    }

    public static  final ReceiptFormatStrategy getReceiptFormatStrategyInstance() throws CustomIllegalArgumentException {
        ReceiptFormatStrategy receiptFormatStrategy = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if( !file.exists() ){
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
            receiptFormatStrategy = (ReceiptFormatStrategy)clazz.newInstance();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); 
        }
        
        return receiptFormatStrategy;
    }
    
    public static final String getParkingGarageName() throws CustomIllegalArgumentException{
        
        String  parkingGarageName = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if( !file.exists() ){
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
        
        return parkingGarageName ;
        
    }
    
    public  static final File getTotalsFile() throws CustomIllegalArgumentException{
        
        File  totalsFile = null;

        // Reading config file 
        File file = new File(CONFIG_FILE_URL);
        if( !file.exists() ){
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
        
        if( ! totalsFile.exists() ){
            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);
        }
        return totalsFile ;
        
    }
    
}
