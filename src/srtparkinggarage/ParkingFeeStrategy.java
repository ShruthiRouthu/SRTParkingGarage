package srtparkinggarage;

/**
 * Interface(strategy Interface) to enable usage of various ParkingFeeStrategies
 * based on polymorphism
 *
 * @author Shruthi Routhu
 */
public interface ParkingFeeStrategy {

    /**
     * abstract method to calculate fee based on hours
     *
     * @param hrs - datatype <code>double</code>
     * @returns parking fees of datatype <code>double</code>
     */
    double calculateParkingFee(double hours);

}
