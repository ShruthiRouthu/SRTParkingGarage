
package srtparkinggarage;

/**
 * This class implements the interface <code>OutputStrategy</code> and has
 * methods to output specified string data to Console
 *
 * @author srouthu
 * 
*/
public class ConsoleOutput implements OutputStrategy {

    private static final String INVALID_STRING_PARAMETER_MSG = "ConsoleOutput: Output data String cannot be null or empty ! ";

    /**
     * Method to output data to console
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

        System.out.println(opString);
    }

    // MANDATORY METHODS
    @Override
    public String toString() {
        return "ConsoleOutput{" + '}';
    }

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
        final ConsoleOutput other = (ConsoleOutput) obj;
        return true;
    }

}
