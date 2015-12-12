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
    
    private final static String VALIDATION_MSG = "Input argument cannot be null" ;
    
    public final List<Map<String,Object>> read(final File file, final FormatStrategy formatStrategy) throws IllegalArgumentException {
        
        if( !file.exists() || (formatStrategy == null)){
            throw new IllegalArgumentException(VALIDATION_MSG); 
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
        return formatStrategy.decode(rawData);
        
    }

    // MANDATORY METHODS
    @Override
    public final int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
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
    public final String toString() {
        return "TextReader{" + '}';
    }
    
}
