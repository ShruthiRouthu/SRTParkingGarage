package srtparkinggarage;

/**
 * This class implements <code> ParkingFeeStrategy </code> and calculates
 * parking fee based on hours parked. hours are rounded to upper limit ie 2.1
 * hrs = 3 hrs.
 * minimumCharge,minimumChargeHours,additionalHourlyCharge,maximumCharge are
 * also involved in the calculations
 *
 * @author Shruthi Routhu
 */
public class MinMaxParkingFeeCalculator implements ParkingFeeStrategy {

    private static final String MIN_CHARGE_MSG = "Minimum charge is invalid";
    private static final String MIN_CHARGE_HRS_MSG = "Minimum charge hours are invalid";
    private static final String ADD_HRLY_CHARGE_MSG = "Additional hourly charge is invalid";
    private static final String MAX_CHARGE = "Maximum charge is invalid";

    private double minimumCharge = 0;
    private double minimumChargeHours = 0;
    private double additionalHourlyCharge = 0;
    private double maximumCharge = 0;

    // CONSTRUCTOR
    /**
     * Creates an object of <code>MinMaxParkingFeeCalculator </code>
     *
     * @param minimumCharge - datatype <code>double</code>
     * @param minChargeHours - datatype <code>double</code>
     * @param additionalHourlyCharge - datatype <code>double</code>
     * @param maximumCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if parameters <= 0
     */
    public MinMaxParkingFeeCalculator(final double minimunCharge, final double minChargeHours,
            final double additionalHourlyCharge, final double maximumCharge) throws CustomIllegalArgumentException {
        setMinimumCharge(minimunCharge);
        setMinimumChargeHours(minChargeHours);
        setAdditionalHourlyCharge(additionalHourlyCharge);
        setMaximumCharge(maximumCharge);
    }

    // SETTERS
    /**
     * Method to set minimum fee
     *
     * @param minimumCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if hourly
     * minimumCharge <= 0
     */
    public final void setMinimumCharge(final double minimumCharge) throws CustomIllegalArgumentException {
        if (minimumCharge > 0) {
            this.minimumCharge = minimumCharge;
        } else {
            throw new CustomIllegalArgumentException(MIN_CHARGE_MSG);
        }
    }

    /**
     * Method to set minimum charge hours
     *
     * @param minChargeHours - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if minChargeHours <=
     * 0
     */
    public final void setMinimumChargeHours(final double minChargeHours) throws CustomIllegalArgumentException {
        if (minChargeHours > 0) {
            this.minimumChargeHours = minChargeHours;
        } else {
            throw new CustomIllegalArgumentException(MIN_CHARGE_HRS_MSG);
        }
    }

    /**
     * Method to set additionalHourlyCharge
     *
     * @param additionalHourlyCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if
     * additionalHourlyCharge <= 0
     */
    public final void setAdditionalHourlyCharge(final double additionalHourlyCharge) throws CustomIllegalArgumentException {
        if (additionalHourlyCharge > 0) {
            this.additionalHourlyCharge = additionalHourlyCharge;
        } else {
            throw new CustomIllegalArgumentException(ADD_HRLY_CHARGE_MSG);
        }
    }

    /**
     * Method to set setMaximumCharge
     *
     * @param setMaximumCharge - datatype <code>double</code>
     * @throws <code>CustomIllegalArgumentException</code> if setMaximumCharge
     * <= 0
     */
    public final void setMaximumCharge(final double maximumCharge) throws CustomIllegalArgumentException {
        if (maximumCharge > 0) {
            this.maximumCharge = maximumCharge;
        } else {
            throw new CustomIllegalArgumentException(MAX_CHARGE);
        }
    }

    // GETTERS
    /**
     * Method to get Minimum fee
     *
     * @returns Minimum fee of datatype <code>double</code>
     */
    public final double getMinimumCharge() {
        return minimumCharge;
    }

    /**
     * Method to get Minimum charge hours
     *
     * @returns Minimum charge hours of datatype <code>double</code>
     */
    public final double getMinChargeHours() {
        return minimumChargeHours;
    }

    /**
     * Method to get AdditionalHourlyCharge
     *
     * @returns AdditionalHourlyCharge of datatype <code>double</code>
     */
    public final double getAdditionalHourlyCharge() {
        return additionalHourlyCharge;
    }

    /**
     * Method to get MaximumCharge
     *
     * @returns MaximumCharge of datatype <code>double</code>
     */
    public final double getMaximumCharge() {
        return maximumCharge;
    }

    /**
     * This method calculates fee if (hours > 0) and (hours <= 24), else returns
     * a fee of $0
     *
     * @param hrs - datatype <code>double</code>
     * @returns parking fees of datatype <code>double</code>
     */
    @Override
    public final double calculateParkingFee(final double hours) {

        double fee = 0;

        // validating input
        if ((hours > 0) && (hours <= 24)) {

            if (hours <= minimumChargeHours) {
                fee = minimumCharge;
            } else if (hours < 24) {
                int additionalHours = (int) (Math.ceil(hours - minimumChargeHours));
                fee = (minimumCharge) + (additionalHours * additionalHourlyCharge);
            } else if (hours == 24) {
                fee = maximumCharge;
            }

        }
        return fee;
    }

    // MANDATORY METHODS
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.minimumCharge) ^ (Double.doubleToLongBits(this.minimumCharge) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.minimumChargeHours) ^ (Double.doubleToLongBits(this.minimumChargeHours) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.additionalHourlyCharge) ^ (Double.doubleToLongBits(this.additionalHourlyCharge) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.maximumCharge) ^ (Double.doubleToLongBits(this.maximumCharge) >>> 32));
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
        final MinMaxParkingFeeCalculator other = (MinMaxParkingFeeCalculator) obj;
        if (Double.doubleToLongBits(this.minimumCharge) != Double.doubleToLongBits(other.minimumCharge)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minimumChargeHours) != Double.doubleToLongBits(other.minimumChargeHours)) {
            return false;
        }
        if (Double.doubleToLongBits(this.additionalHourlyCharge) != Double.doubleToLongBits(other.additionalHourlyCharge)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maximumCharge) != Double.doubleToLongBits(other.maximumCharge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MinMaxParkingFeeCalculator{" + "minimumCharge=" + minimumCharge + ", minimumChargeHours=" + minimumChargeHours + ", additionalHourlyCharge=" + additionalHourlyCharge + ", maximumCharge=" + maximumCharge + '}';
    }

}
