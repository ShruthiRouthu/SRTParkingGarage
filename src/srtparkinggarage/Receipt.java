package srtparkinggarage;

import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */

public class Receipt {
    
    private static final String PARAMETER_NULL_MSG = "Method Parameter cannot be null" ;
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
    private static final String INVALID_CHECKOUT = "Invalid checkout !";
            
    private String parkingGarageName;
    private ReceiptFormatStrategy receiptFormatStrategy;
    
    //CONSTRUCTOR
    public Receipt(final String parkingGarageName, final ReceiptFormatStrategy receiptFormatStrategy) 
            throws IllegalArgumentException {
       
        setParkingGarageName(parkingGarageName);
        setReceiptFormatStrategy(receiptFormatStrategy) ;
    }
 
    //METHODS
    public final void outputReceipt(final TicketStrategy ticket, final double parkingFee, final PaymentType payType) 
    throws Exception {
        //need to validate parameters properly before using
        if(parkingFee > 0){
            String receiptString = getRecieptString(ticket, parkingFee,payType);
            System.out.println(receiptString);
        }
        else{
            throw new IllegalArgumentException(INVALID_CHECKOUT);
        }
    }
    
    private String getRecieptString(final TicketStrategy ticket, final double parkingFee, final PaymentType payType)throws Exception{
        //need to validate parameters before using
        return receiptFormatStrategy.getReceiptString(parkingGarageName, ticket, parkingFee, payType);
    }
    
    // SETTER
    public final void setParkingGarageName(final String parkingGarageName) throws IllegalArgumentException {
        
        if((parkingGarageName != null) && (parkingGarageName.length() > 0)){
            this.parkingGarageName = parkingGarageName;
        }else{
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
    }
    
    public final void setReceiptFormatStrategy(final ReceiptFormatStrategy receiptFormatStrategy) 
            throws IllegalArgumentException {
        
        if(receiptFormatStrategy != null){
            this.receiptFormatStrategy = receiptFormatStrategy;
        }else{
            throw new IllegalArgumentException(PARAMETER_NULL_MSG);
        }
    }
   
    //GETTER
    public final String getParkingGarageName() {
        return parkingGarageName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.parkingGarageName);
        hash = 83 * hash + Objects.hashCode(this.receiptFormatStrategy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.parkingGarageName, other.parkingGarageName)) {
            return false;
        }
        if (!Objects.equals(this.receiptFormatStrategy, other.receiptFormatStrategy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receipt{" + "parkingGarageName=" + parkingGarageName + ", receiptFormatStrategy=" + receiptFormatStrategy + '}';
    }
    
    
    
    
 
}
