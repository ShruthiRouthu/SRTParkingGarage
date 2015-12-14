package filemanagementsystem;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class FileService {
    
    private static final String PARAMETER_NULL_MSG = "FileService : Input argument cannot be null" ;
    private static final String INVALID_FILE_MSG = "FileService : File not valid" ;
    private static final String INVALID_DATA_MSG = "FileService :  No data to write !" ;
    
    
    private TextReader textReader;
    private TextWriter textWriter;

    // CONSTRUCTOR
    public FileService(final TextReader textReader, final TextWriter textWriter) throws IllegalArgumentException {
        setTextReader(textReader);
        setTextWriter(textWriter);
    }
    
    // METHODS
    public final List<Map<String,Object>> read(final File file, final FileFormatStrategy fileFormatStrategy) throws IllegalArgumentException {
        
        if( (file == null) || (fileFormatStrategy == null )){
            
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
            
        }else if( ! file.exists() ){     
            
            throw new IllegalArgumentException(INVALID_FILE_MSG);
            
        }
        
        return textReader.read(file, fileFormatStrategy);
        
        
    }
    
    public final void write(final File file, final FileFormatStrategy fileFormatStrategy , final List<Map<String,Object>> myData) 
            throws IllegalArgumentException{
        
        if( (file == null) || (fileFormatStrategy == null ) ){
            
            throw new IllegalArgumentException(PARAMETER_NULL_MSG); 
            
        } else if( ! file.exists() ){ 
            
            throw new IllegalArgumentException(INVALID_FILE_MSG);
            
        } else if( (myData == null) || (myData.isEmpty())  ){
            
            System.out.println(INVALID_DATA_MSG);  
            return ;
            
        }  

        textWriter.write(file, fileFormatStrategy, myData);
        
    }
  
    public final Map<String,Object> getLastLine(final File file, final FileFormatStrategy fileFormatStrategy) 
            throws IllegalArgumentException{
        
        if( (file == null) || (fileFormatStrategy == null )){
            
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
            
        }else if( ! file.exists() ){     
            
            throw new IllegalArgumentException(INVALID_FILE_MSG);
            
        }     
        
        Map<String,Object> lastLine = textReader.getLastLine(file, fileFormatStrategy);
        return lastLine;
       
 
    }
    
    // SETTERS
    public final void setTextReader(final TextReader textReader) throws IllegalArgumentException {
        if(textReader != null){
            this.textReader = textReader;
        }
        else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }

    public final void setTextWriter(final TextWriter textWriter) throws IllegalArgumentException {
        if(textWriter != null){
            this.textWriter = textWriter;
        }
        else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }

    // GETTERS  
    public final TextReader getTextReader() {
        return textReader;
    }
    
    public final TextWriter getTextWriter() {
        return textWriter;
    }
    
    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.textReader);
        hash = 97 * hash + Objects.hashCode(this.textWriter);
        return hash;
    }

    @Override
    public  boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileService other = (FileService) obj;
        if (!Objects.equals(this.textReader, other.textReader)) {
            return false;
        }
        return Objects.equals(this.textWriter, other.textWriter);
    }

    @Override
    public String toString() {
        return "FileService{" + "textReader=" + textReader + ", textWriter=" + textWriter + '}';
    }
    
   
}
