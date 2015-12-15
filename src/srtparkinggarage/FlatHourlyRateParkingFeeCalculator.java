package srtparkinggarage;

/**
 *
 * This class implements <code> ParkingFeeStrategy </code> and calculates
 * parking fee based on hours parked. Hours are rounded to upper limit ie 2.1
 * hrs = 3 hrs
 *
 * @author Shruthi Routhu
 */
public class FlatHourlyRateParkingFeeCalculator implements ParkingFeeStrategy {

    private static final String INVALID_HOURLY_CHARGE = "Horly charge has to be greater than 0";
    private double hourlyCharge;

    //CONSTRUCTOR
    /**
     * Creates an object of <code>FlatHourlyRateParkingFeeCalculator </code>
     *
     * @param hourlyCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if hourly charge <= 0
     */
    public FlatHourlyRateParkingFeeCalculator(final double hourlyCharge) throws CustomIllegalArgumentException {
        setHourlyCharge(hourlyCharge);
    }

//SETTER   
    /**
     * Method to set Hourly charge
     *
     * @param hourlyCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if hourly charge <= 0
     */
    public final void setHourlyCharge(final double hourlyCharge) throws CustomIllegalArgumentException {
        if (hourlyCharge > 0) {
            this.hourlyCharge = hourlyCharge;
        } else {
            throw new CustomIllegalArgumentException(INVALID_HOURLY_CHARGE);
        }
    }

    //GETTER
    /**
     * Method to get Hourly charge
     *
     * @returns hourly charge of datatype <code>double</code>
     */
    public final double getHourlyCharge() {
        return hourlyCharge;
    }

    /**
     * This method calculates fee if (hours > 0) and (hours <= 24), else returns
     * a fee of $0
     *
     * @param hrs - datatype <code>double</code>
     * @returns parking fees of datatype <code>double</code>
     */
    @Override
    public final double calculateParkingFee(final double hrs) {
        double fee = 0;
        double hours = Math.ceil(hrs);
        // validating input
        if (hours > 0 && hours <= 24) {
            fee = hours * hourlyCharge;
        }
        return fee;
    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.hourlyCharge) ^ (Double.doubleToLongBits(this.hourlyCharge) >>> 32));
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
        final FlatHourlyRateParkingFeeCalculator other = (FlatHourlyRateParkingFeeCalculator) obj;
        if (Double.doubleToLongBits(this.hourlyCharge) != Double.doubleToLongBits(other.hourlyCharge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FlatHourlyRateParkingFeeCalculator{" + "hourlyCharge=" + hourlyCharge + '}';
    }

    
    
}
