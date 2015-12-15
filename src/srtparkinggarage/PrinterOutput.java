package srtparkinggarage;

/**
 * This class implements the interface <code>OutputStrategy</code> and has
 * methods to output specified string data to printer
 *
 * @author srouthu
 * 
*/
public class PrinterOutput implements OutputStrategy {

    private static final String INVALID_STRING_PARAMETER_MSG = "PrinterOutput: Output data String cannot be null or empty ! ";

    /**
     * Method to output data to printer
     *
     * @param opString - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    @Override
    public void outputData(String opString) throws CustomIllegalArgumentException {

        if (opString == null || opString.length() == 0) {
            throw new CustomIllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }

        // Faking It !!!
        System.out.println("Printer has printed a reciept!");
    }

    // MANDATORY METHODS
    @Override
    public String toString() {
        return "PrinterOutput{" + '}';
    }

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
        final PrinterOutput other = (PrinterOutput) obj;
        return true;
    }

}
