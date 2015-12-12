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
    
    
    
}
