package srtparkinggarage; 

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageEntryTerminal {
    private static final String CAR_NOT_FOUND_MSG = "Car not found" ;
    private static final String CAR_IN_GARAGE = "Sorry ! Car is already parked ." ;
    private static final String INVALID_TICKET_STRATEGY = " Ticket Strategy is invalid" ;
    
  
    private List<TicketStrategy> ticketList ; 

  
    
    //CONSTRUCTOR
    public ParkingGarageEntryTerminal()throws IllegalArgumentException {
        this.ticketList = new ArrayList<TicketStrategy>();         
    }
    
    // METHODS
    public final void parkCar(final TicketStrategy ticket) throws IllegalArgumentException {
        //validate ticket before using
        String carID = ticket.getCarID();
        
        // if the carid is already in the ticketList , exception is thrown 
        if(findCar(carID) == -1){
            this.addTicketToList(ticket) ;
        }
        else{
           throw new  IllegalArgumentException( carID + ":  " + CAR_IN_GARAGE ); 
        }
    }

    //returns ticket if that ticket is in ticketList else throws exception
    public final TicketStrategy getTicket(final String carID) throws IllegalArgumentException{
        //validate input before using
        int index = findCar(carID);
        // if Car not in list throw exception
        if(index == -1){
           throw new  IllegalArgumentException( carID + " " + CAR_NOT_FOUND_MSG );
        }
        return ticketList.get(index); 
    }
    
    // deletes ticket if that ticket is in ticketList else throws exception
    public final void deleteTicketFromList(final String carID) throws IllegalArgumentException {
        int index = findCar(carID);
        if(index == -1){
            throw new IllegalArgumentException(CAR_NOT_FOUND_MSG);
        }
        else {
           this.ticketList.remove(index);
        }
    }
    
    public final void clearTicketList(){
         this.ticketList.clear();
    }
    
    // PRIVATE METHODS
    private void addTicketToList(final TicketStrategy ticket){
         this.ticketList.add(ticket);
    }
 
    private int findCar(final String carID) {
        int index = -1; // Flag
               
        // looping through ticketList Array
        for (int i = 0; i < ticketList.size(); i++) {
            if ( ticketList.get(i).getCarID().equals(carID) ) {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    @Override
    public String toString() {
        return "ParkingGarageEntryTerminal{" + "ticketList=" + ticketList + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.ticketList);
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
        final ParkingGarageEntryTerminal other = (ParkingGarageEntryTerminal) obj;
        return Objects.equals(this.ticketList, other.ticketList);
    }
    
//    //method to test array behaviour
//    public String getCarList(){
//        String cars = "" ;
//        for(int i=0; i< ticketList.length; i++){
//            cars += ticketList[i].getCarID();
//        }
//        return cars + "\n";
//    }
    
// UNIT TESTING 
//    public static void main(String[] args) {
//        ParkingGarageEntryTerminal et = new ParkingGarageEntryTerminal();
//        System.out.println( et.findCar("C100"));
//        et.parkCar("C100", 3);
//        System.out.println( et.findCar("C100"));
//        et.parkCar("C200", 4);
//        et.parkCar("C300", 5);
//        
//        System.out.println( et.findCar("C100"));
//        et.deleteTicketFromList("C100");
//        System.out.println( et.isCarInGarage("C100"));
//    }

   
    

}