package filemanagementsystem;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import srtparkinggarage.CustomIllegalArgumentException;


/**
 *
 * @author Shruthi Routhu
 */
public class StartUp  {
    
    public static void main(String[] args) throws IOException,  CustomIllegalArgumentException {
        
        FileService fileService = new FileService(new TextReader() , new TextWriter() );
        
        // File myFile = new File("F:/WCTC2015Fall/AdvancedJavaF/finalProject/SRTParkingGarageCurrent/SRTParkingGarage/src/PipeSVRunningTotals.txt");
     
        

    File myFile = new File("F:" + File.separatorChar + "WCTC2015Fall" +File.separatorChar + "AdvancedJavaF" + 
            File.separatorChar +  "finalProject" + File.separatorChar + "SRTParkingGarageCurrent" + 
            File.separatorChar + "SRTParkingGarage" + File.separatorChar+ "src" + File.separatorChar + "RunningTotals.csv");



//        File myFile = new File("F:" + File.separatorChar + "WCTC2015Fall" +File.separatorChar + "AdvancedJavaF" + 
//                File.separatorChar +  "finalProject" + File.separatorChar + "SRTParkingGarageCurrent" + 
//                File.separatorChar + "SRTParkingGarage" + File.separatorChar+ "src" + File.separatorChar + "PipeSVRunningTotals.txt");

               
        if(myFile.exists()){
            System.out.println(myFile.getCanonicalPath());
            System.out.println("YAy");
        }
    
        List<Map<String,Object>> data = fileService.read( myFile , new CSVFormatter());
        
        System.out.println(fileService.read( myFile , new CSVFormatter()));

//        for(Map m : data ){
//           System.out.println(m.toString()); 
//        }
       
        Map<String,Object> lastLine = fileService.getLastLine(myFile, new CSVFormatter());
        
        System.out.println(fileService.getLastLine(myFile, new CSVFormatter()));
       
       
        fileService.write(myFile, new CSVFormatter() , data);
        
        
    } 
    
}
