package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 */
public class MinMaxParkingFeeCalculator  implements ParkingFeeStrategy{
    
    private static final String MIN_CHARGE_MSG = "Minimum charge is invalid";
    private static final String MIN_CHARGE_HRS_MSG = "Minimum charge hours are invalid" ;
    private static final String ADD_HRLY_CHARGE_MSG = "Additional hourly charge is invalid";
    private static final String MAX_CHARGE = "Maximum charge is invalid";
    
    private double minimumCharge=0;
    private double minimumChargeHours=0;
    private double additionalHourlyCharge=0;
    private double maximumCharge=0;
   
    // CONSTRUCTOR
    public MinMaxParkingFeeCalculator(final double minimunCharge,final double minChargeHours, 
            final double additionalHourlyCharge, final double maximumCharge) throws IllegalArgumentException {
        setMinimumCharge(minimunCharge);
        setMinimumChargeHours(minChargeHours);
        setAdditionalHourlyCharge(additionalHourlyCharge);
        setMaximumCharge(maximumCharge);
    }
   
    // SETTERS
    public final void setMinimumCharge(final double minimumCharge) throws IllegalArgumentException{
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

    public final void setAdditionalHourlyCharge(final double additionalHourlyCharge)throws IllegalArgumentException {
        if(additionalHourlyCharge > 0){
            this.additionalHourlyCharge = additionalHourlyCharge;  
        }
        else throw new IllegalArgumentException(ADD_HRLY_CHARGE_MSG);
    }

    public final void setMaximumCharge(final double maximumCharge)throws IllegalArgumentException {
        if(maximumCharge > 0){
            this.maximumCharge = maximumCharge;
        }
        else throw new IllegalArgumentException(MAX_CHARGE);
    }
    
    // GETTERS
    public final double getMinimumCharge() {
        return minimumCharge;
    }

    public final double getMinChargeHours() {
        return minimumChargeHours;
    }

    public final double getAdditionalHourlyCharge() {
        return additionalHourlyCharge;
    }

    public final double getMaximumCharge() {
        return maximumCharge;
    }

    // This method calculates fee if (hours > 0 and hours <= 24), else returns a fee of $0
    @Override
    public final double calculateParkingFee(final double hours) {
        
        double fee=0;
        
        // validating input
        if((hours > 0) && (hours <= 24)){

            if(hours <= minimumChargeHours ){
                fee = minimumCharge; 
            }else if(hours < 24 ){
                int additionalHours = (int) (Math.ceil(hours-minimumChargeHours)) ;
                fee = (minimumCharge)+ (additionalHours*additionalHourlyCharge);  
            }else if(hours == 24){
                fee = maximumCharge; 
            } 
            
        }  
        return fee;   
    }

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
