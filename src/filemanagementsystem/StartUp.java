package filemanagementsystem;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class StartUp {
    
    public static void main(String[] args) throws IOException {
        
        FileService fileService = new FileService(new TextReader() , new TextWriter() );
        
        //File myFile = new File("mailList.json");
        File myFile = new File("/WCTC 2015 Fall/AdvancedJavaF/FileManagementSystem/src/filemanagementsystem/mailList.csv");
        
        
//        if(myFile.exists()){
//            System.out.println(myFile.getCanonicalPath());
//            System.out.println("YAy");
//        }
//    
        List<Map<String,Object>> data = fileService.read( myFile , new CSVFormatter());
        
        for(Map m : data ){
           System.out.println(m.toString()); 
        }
        
        fileService.write(myFile, new CSVFormatter() , data);
        
        
    } 
    
}
