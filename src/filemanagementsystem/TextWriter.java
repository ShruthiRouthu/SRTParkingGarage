package filemanagementsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import srtparkinggarage.CustomIllegalArgumentException;

/**
 * This is a Class which provides Text File writing services.
 *
 * @author Shruthi Routhu
 */
public class TextWriter {

    private static final String PARAMETER_NULL_MSG = "TextWriter : Input argument cannot be null";
    private static final String INVALID_FILE_MSG = "TextWriter : File not valid";
    private static final String INVALID_DATA_MSG = "FileService :  No data to write !";

    /**
     * Method to write data to file
     *
     * @param file - <code>File</code> object to which data is written.
     * @param fileFormatStrategy <code>FileFormatStrategy</code> object used to
     * encode data
     * @param myData data to write in format
     * <code>List<Map<String, Object>></code>
     * @throws <code>CustomIllegalArgumentException</code> if input parameters
     * are null or if File doesnot exist.
     */
    public final void write(final File file, FileFormatStrategy fileFormatStrategy, final List<Map<String, Object>> myFormatData)
            throws CustomIllegalArgumentException {

        if ((file == null) || (fileFormatStrategy == null)) {

            throw new CustomIllegalArgumentException(PARAMETER_NULL_MSG);

        } else if (!file.exists()) {

            throw new CustomIllegalArgumentException(INVALID_FILE_MSG);

        } else if ((myFormatData == null) || (myFormatData.isEmpty())) {

            System.out.println(INVALID_DATA_MSG);
            return;

        }

        // send  data to formatStrategy.encode() to convert data to  desired format
        List<String> writeData = fileFormatStrategy.encode(myFormatData);

        //write this data to file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println("\n");
            for (String line : writeData) {
                writer.println(line);
            }

        } catch (IOException ioe) {
            System.out.println("TextWriter : " + ioe.getMessage());
        } finally {
            writer.close();
        }

    }

    //MANDATORY METHODS
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
        final TextWriter other = (TextWriter) obj;
        return true;
    }

    @Override
    public String toString() {
        return "TextWriter{" + '}';
    }

}
