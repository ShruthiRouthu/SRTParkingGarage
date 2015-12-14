package srtparkinggarage;

/**
 * Interface(strategy Interface) to enable usage of varying TicketStrategies
 * based on polymorphism
 *
 * @author Shruthi Routhu
 */
public interface TicketStrategy {

    /**
     * Method to set CarID
     *
     * @param carID - data type  <code>String</code>
     * @throws <code>  CustomIllegalArgumentException </code> which is a checked
     * Exception if the parameter carID is null
     */
    void setCarID(String carID) throws CustomIllegalArgumentException;

    /**
     * Method to get hours parked
     *
     * @returns <code>double</code>
     * @throws <code>  Exception </code> since <code>LocalDateTime</code>
     * manipulations are involved .
     */
    double getHoursParked() throws Exception;

    /**
     * Method to get TicketID
     *
     * @returns <code>int</code>
     */
    int getTicketID();

    /**
     * Method to get CarID
     *
     * @returns <code>String</code>
     */
    String getCarID();

}
