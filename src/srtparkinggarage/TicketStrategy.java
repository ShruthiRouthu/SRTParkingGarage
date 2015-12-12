package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 */
public interface TicketStrategy {
  
    void setCarID(String carID) throws IllegalArgumentException ;
        
    double getHoursParked()throws Exception ;
    
    int getTicketID() ;
  
    String getCarID() ;
       

}
