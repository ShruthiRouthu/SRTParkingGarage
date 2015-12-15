
package srtparkinggarage;

import javax.swing.JOptionPane;

/**
 * This class implements the interface <code>OutputStrategy</code> and has
 * methods to output specified string data to GUI
 *
 * @author srouthu
 * 
*/
public class GUIOutput implements OutputStrategy {

    private static final String INVALID_STRING_PARAMETER_MSG = "GUIOutput: Output data String cannot be null or empty ! ";

    /**
     * Method to output data to GUI
     *
     * @param opString - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    @Override
    public final void outputData(final String opString) throws CustomIllegalArgumentException {

        if (opString == null || opString.length() == 0) {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }

        JOptionPane.showMessageDialog(null, opString);
    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 7;
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
        final GUIOutput other = (GUIOutput) obj;
        return true;
    }

    @Override
    public String toString() {
        return "GUIOutput{" + "INVALID_STRING_PARAMETER_MSG=" + INVALID_STRING_PARAMETER_MSG + '}';
    }

}
