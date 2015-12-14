package filemanagementsystem;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import srtparkinggarage.CustomIllegalArgumentException;

/**
 * This is a Class which provides File manipulation services. It has
 * <code>TextReader</code> and <code>TextWriter</code> objects to which tasks
 * are delegated.
 *
 * @author Shruthi Routhu
 */
public class FileService {

    private static final String PARAMETER_NULL_MSG = "FileService : Input argument cannot be null";
    private static final String INVALID_FILE_MSG = "FileService : File not valid";
    private static final String INVALID_DATA_MSG = "FileService :  No data to write !";

    private TextReader textReader;
    private TextWriter textWriter;

    // CONSTRUCTOR
    /**
     * Constructor to create a <code>FileService</code> object
     *
     * @param textReader - <code>TextReader</code> object
     * @param textWriter <code>TextWriter</code> objects
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are null.
     */
    public FileService(final TextReader textReader, final TextWriter textWriter) throws CustomIllegalArgumentException {
        setTextReader(textReader);
        setTextWriter(textWriter);
    }

    // METHODS
    /**
     * Method to read data from file
     *
     * @param file - <code>File</code> object from which data is read.
     * @param fileFormatStrategy <code>FileFormatStrategy</code> object used to
     * decode data read from file
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are null or if File doesnot exist..
     */
    public final List<Map<String, Object>> read(final File file, final FileFormatStrategy fileFormatStrategy) throws CustomIllegalArgumentException {

        if ((file == null) || (fileFormatStrategy == null)) {

            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);

        } else if (!file.exists()) {

            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);

        }

        return textReader.read(file, fileFormatStrategy);

    }

    /**
     * Method to write data to file
     *
     * @param file - <code>File</code> object to which data is written.
     * @param fileFormatStrategy <code>FileFormatStrategy</code> object used to
     * encode data
     * @param myData data to write in format
     * <code>List<Map<String, Object>></code>
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are null or if File doesnot exist..
     */
    public final void write(final File file, final FileFormatStrategy fileFormatStrategy, final List<Map<String, Object>> myData)
            throws CustomIllegalArgumentException {

        if ((file == null) || (fileFormatStrategy == null)) {

            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);

        } else if (!file.exists()) {

            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);

        } else if ((myData == null) || (myData.isEmpty())) {

            System.out.println(INVALID_DATA_MSG);
            return;

        }

        textWriter.write(file, fileFormatStrategy, myData);

    }

    /**
     * Method to read last line of data from file
     *
     * @param file - <code>File</code> object from which data is read.
     * @param fileFormatStrategy <code>FileFormatStrategy</code> object used to
     * decode data read from file
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are null or if File doesnot exist..
     */
    public final Map<String, Object> getLastLine(final File file, final FileFormatStrategy fileFormatStrategy)
            throws CustomIllegalArgumentException {

        if ((file == null) || (fileFormatStrategy == null)) {

            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);

        } else if (!file.exists()) {

            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);

        }

        Map<String, Object> lastLine = textReader.getLastLine(file, fileFormatStrategy);
        return lastLine;

    }

    // SETTERS
    /**
     * Method to set TextReader
     *
     * @param textReader <code>TextReader</code> object
     * @throws <code>CustomIllegalArgumentException</code> if input parameter is
     * null.
     */
    public final void setTextReader(final TextReader textReader) throws CustomIllegalArgumentException {
        if (textReader != null) {
            this.textReader = textReader;
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }

    /**
     * Method to set TextWriter
     *
     * @param textWriter <code>TextWriter</code> object
     * @throws <code>CustomIllegalArgumentException</code> if input parameter is
     * null.
     */
    public final void setTextWriter(final TextWriter textWriter) throws CustomIllegalArgumentException {
        if (textWriter != null) {
            this.textWriter = textWriter;
        } else {
            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }

    /**
     * Method to get TextReader
     *
     * @returns textReader <code>TextReader</code> object
     *
     */
    // GETTERS  
    public final TextReader getTextReader() {
        return textReader;
    }

    /**
     * Method to get TextWriter
     *
     * @returns textWriter <code>TextWriter</code> object
     *
     */
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
    public boolean equals(final Object obj) {
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
