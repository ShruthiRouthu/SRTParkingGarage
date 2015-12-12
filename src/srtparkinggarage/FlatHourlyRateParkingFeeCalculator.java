package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 * Calculates fee based hours parked. hours are rounded to upper limit ie 2.1 hrs = 3 hrs
 */
public class FlatHourlyRateParkingFeeCalculator  implements ParkingFeeStrategy{

    private static final String INVALID_HOURLY_CHARGE = "Horly charge has to be greater than 0";
    private double hourlyCharge;
    
    //CONSTRUCTOR
    public FlatHourlyRateParkingFeeCalculator(final double hourlyCharge) throws IllegalArgumentException {
        setHourlyCharge(hourlyCharge);
    }
    
    //SETTER
    public final void setHourlyCharge(final double hourlyCharge) throws IllegalArgumentException{
        if(hourlyCharge > 0){
            this.hourlyCharge = hourlyCharge;  
        }
        else throw new IllegalArgumentException(INVALID_HOURLY_CHARGE);
    }
    
    //GETTER
    public final double getHourlyCharge() {
        return hourlyCharge;
    }
    
    // This method calculates fee if (hours > 0), else returns a fee of $0
    @Override
    public final double calculateParkingFee(final double hrs) {
        double fee=0;
        double hours = Math.ceil(hrs);
        // validating input
        if(hours > 0 && hours <= 24){
           fee = hours*hourlyCharge;
        }  
        return fee;
    }

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
