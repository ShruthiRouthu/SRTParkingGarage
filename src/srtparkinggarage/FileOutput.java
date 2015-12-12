
package srtparkinggarage;

import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class FileOutput implements OutputStrategy {
    
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
    private static final String FAKING_FILEOP = "Data has been saved to file" ;
    private static final String INVALID_FILE_NAME_MSG = "File name not valid" ;
    
    private String fileName;

    //CONSTRUCTOR
    public FileOutput(final String fileName) throws IllegalArgumentException{
        setFileName(fileName);
    }
        
    @Override
    public final void outputData(final String opString) throws IllegalArgumentException {
        // Faking output to some file
        if(opString == null || opString.length() == 0){
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG );
        }
        System.out.println(FAKING_FILEOP);
    }
    
    //SETTER
    public final void setFileName(final String fileName) throws IllegalArgumentException{
        if((fileName != null) && (fileName.length() > 0)){
            this.fileName = fileName;
        }else{
            throw new IllegalArgumentException(INVALID_FILE_NAME_MSG);
        }
        
    }
    
    //GETTER
    public final String getFileName() {
        return fileName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.fileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileOutput other = (FileOutput) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FileOutput{" + "fileName=" + fileName + '}';
    }
    
   
    
    
    
}
