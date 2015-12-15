
package srtparkinggarage;

import java.util.Objects;

/**
 * This class implements the interface <code>OutputStrategy</code> and has
 * methods to output specified string data to File
 *
 * @author srouthu
 * 
*/
public class FileOutput implements OutputStrategy {

    private static final String INVALID_STRING_PARAMETER_MSG = "FileOutput: Output data String cannot be null or empty ! ";
    private static final String FAKING_FILEOP = "FileOutput: Data has been saved to file";
    private static final String INVALID_FILE_NAME_MSG = "FileOutput: File name(String) cannot be null or empty";

    private String fileName;

    /**
     * Constructor returns an object of FileOutput Class
     *
     * @param fileName - data type  <code>String</code> which is the name of file
     * you want to output to
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    public FileOutput(final String fileName) throws CustomIllegalArgumentException {
        setFileName(fileName);
    }

    /**
     * Method to output data
     *
     * @param opString - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    @Override
    public final void outputData(final String opString) throws CustomIllegalArgumentException {
        // Faking output to some file
        if (opString == null || opString.length() == 0) {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        System.out.println(FAKING_FILEOP);
    }

    /**
     * Method to set fileName
     *
     * @param fileName - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    public final void setFileName(final String fileName) throws CustomIllegalArgumentException {
        if ((fileName != null) && (fileName.length() > 0)) {
            this.fileName = fileName;
        } else {
            throw new CustomIllegalArgumentException(INVALID_FILE_NAME_MSG);
        }

    }

    /**
     * Method to get fileName
     *
     * @returns fileName - data type  <code>String</code>
     */
    public final String getFileName() {
        return fileName;
    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.fileName);
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
