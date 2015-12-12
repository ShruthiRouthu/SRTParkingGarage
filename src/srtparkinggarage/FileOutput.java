
package srtparkinggarage;

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
    
   
    
    
    
}
