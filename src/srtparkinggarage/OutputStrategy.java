
package srtparkinggarage;

/**
 * Interface(strategy Interface) to enable usage of various OutputStrategies
 * based on polymorphism
 *
 * @author Shruthi Routhu
 */
public interface OutputStrategy {

    /**
     * Abstract Method to output data
     *
     * @param opString - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter is null or empty
     */
    void outputData(String opString) throws CustomIllegalArgumentException;
}
