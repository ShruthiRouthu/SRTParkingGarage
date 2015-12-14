package filemanagementsystem;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class TextReader {
    
    private static final String PARAMETER_NULL_MSG = "TextReader : Input argument cannot be null" ;
    private static final String INVALID_FILE_MSG = "TextReader  : File not valid";
    private static final String INVALID_DATA_MSG = "TextReader : No Data !" ;
   
    
    public final List<Map<String,Object>> read(final File file, final FileFormatStrategy fileFormatStrategy)
            throws IllegalArgumentException {
      
        if( (file == null) || (fileFormatStrategy == null) ){
           
            throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
            
        }else if( !file.exists() ){
            
            throw new IllegalArgumentException(INVALID_FILE_MSG);
            
        }
                
        // Reading from  file 
        List<String> rawData = new ArrayList<>();
        BufferedReader reader = null;
       
            try{
                
                reader = new BufferedReader( new FileReader(file));
                String line = reader.readLine();
                while(line != null){
                    rawData.add(line);
                    line = reader.readLine();
                }
                
//                System.out.println("Reading Test");
//                System.out.println(rawData.toString());
                
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
            finally{
                
                try { 
                    reader.close();
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }
            
        // converting data to my custom format 
        if( (rawData == null) || (rawData.isEmpty()) ) {
            
             System.out.println(INVALID_DATA_MSG);  
             return null;
             
        } 
        return fileFormatStrategy.decode(rawData);
        
    }

    public final Map<String,Object> getLastLine(final File file, final FileFormatStrategy fileFormatStrategy) 
            throws IllegalArgumentException {
       
        if( (file == null) || (fileFormatStrategy == null) ){
           
            throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
            
        }else if( !file.exists() ){
            
            throw new IllegalArgumentException(INVALID_FILE_MSG);
            
        }
        
        List<Map<String,Object>> allLines = this.read(file, fileFormatStrategy);
        
        if(  (allLines == null) || ( allLines.isEmpty() )  ){
            
            System.out.println(INVALID_DATA_MSG);  
            return null;
            
        }
        
        Map<String,Object> lastLine  = allLines.get(allLines.size()-1);
        return lastLine;
    }
    
    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 3;
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
        final TextReader other = (TextReader) obj;
        return true;
    }

    @Override
    public String toString() {
        return "TextReader{" + '}';
    }
    
}
