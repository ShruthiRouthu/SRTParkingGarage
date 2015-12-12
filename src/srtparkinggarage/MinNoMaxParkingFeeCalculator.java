package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 */
public class MinNoMaxParkingFeeCalculator implements ParkingFeeStrategy {
    
    private static final String MIN_CHARGE_MSG = "Minimum charge is invalid";
    private static final String MIN_CHARGE_HRS_MSG = "Minimum charge hours are invalid" ;
    private static final String ADD_HRLY_CHARGE_MSG = "Additional hourly charge is invalid";

    private double minimumCharge=0;
    private double minimumChargeHours=0;
    private double additionalHourlyCharge=0;
    
    //CONSTRUCTOR
    public MinNoMaxParkingFeeCalculator(final double minimunCharge, final double minChargeHours,
            final double additionalHourlyCharge) throws IllegalArgumentException{
        setMinimumCharge(minimunCharge);
        setMinimumChargeHours(minChargeHours);
        setAdditionalHourlyCharge(additionalHourlyCharge);
    }
    
    //SETTERS
    public final void setMinimumCharge(final double minimumCharge) throws IllegalArgumentException {
        if(minimumCharge > 0){
            this.minimumCharge = minimumCharge; 
        }
        else throw new IllegalArgumentException(MIN_CHARGE_MSG);  
    }

    public final void setMinimumChargeHours(final double minChargeHours)throws IllegalArgumentException {
        if(minChargeHours > 0){
            this.minimumChargeHours = minChargeHours;
        }
        else throw new IllegalArgumentException(MIN_CHARGE_HRS_MSG);
    }

    public final void setAdditionalHourlyCharge(final double additionalHourlyCharge) throws IllegalArgumentException {
        if(additionalHourlyCharge > 0){
            this.additionalHourlyCharge = additionalHourlyCharge;  
        }
        else throw new IllegalArgumentException(ADD_HRLY_CHARGE_MSG);
    }
    
    //GETTERS
    public final double getMinimumCharge() {
        return minimumCharge;
    }

    public final double getMinChargeHours() {
        return minimumChargeHours;
    }

    public final double getAdditionalHourlyCharge() {
        return additionalHourlyCharge;
    }
   
    // This method calculates fee if (hours > 0 and hours <= 24), else returns a fee of $0
    @Override
    public final double calculateParkingFee(final double hours) {
        double fee=0;
        // validating input
        if((hours > 0) && (hours <= 24)){
            if(hours <= minimumChargeHours ){
                fee = minimumCharge; 
            }else if(hours <= 24 ){
                int additionalHours = (int) (Math.ceil(hours-minimumChargeHours));
                fee = (minimumCharge)+ (additionalHours*additionalHourlyCharge);  
            }
        }  
        return fee;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.minimumCharge) ^ (Double.doubleToLongBits(this.minimumCharge) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.minimumChargeHours) ^ (Double.doubleToLongBits(this.minimumChargeHours) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.additionalHourlyCharge) ^ (Double.doubleToLongBits(this.additionalHourlyCharge) >>> 32));
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
        final MinNoMaxParkingFeeCalculator other = (MinNoMaxParkingFeeCalculator) obj;
        if (Double.doubleToLongBits(this.minimumCharge) != Double.doubleToLongBits(other.minimumCharge)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minimumChargeHours) != Double.doubleToLongBits(other.minimumChargeHours)) {
            return false;
        }
        if (Double.doubleToLongBits(this.additionalHourlyCharge) != Double.doubleToLongBits(other.additionalHourlyCharge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MinNoMaxParkingFeeCalculator{" + "minimumCharge=" + minimumCharge + ", minimumChargeHours=" + minimumChargeHours + ", additionalHourlyCharge=" + additionalHourlyCharge + '}';
    }
    
    
    
}
