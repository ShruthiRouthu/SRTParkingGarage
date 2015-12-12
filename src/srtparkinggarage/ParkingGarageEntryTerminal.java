package srtparkinggarage; 

/**
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageEntryTerminal {
    private static final String CAR_NOT_FOUND_MSG = "Car not found" ;
    private static final String CAR_IN_GARAGE = "Sorry ! Car is already parked ." ;
    private static final String INVALID_TICKET_STRATEGY = " Ticket Strategy is invalid" ;
    
  //  private TicketStrategy ticketStrategy ;
    private TicketStrategy[] ticketList ; 
    
    //CONSTRUCTOR
    public ParkingGarageEntryTerminal()throws IllegalArgumentException {
      //  setTicketStrategy(ticketStrategy);
        clearTicketList();
        
    }
    
    // METHODS
    public final void parkCar(TicketStrategy ticket) throws IllegalArgumentException {
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
        return ticketList[index]; 
    }
    
    // deletes ticket if that ticket is in ticketList else throws exception
    public final void deleteTicketFromList(final String carID) throws IllegalArgumentException {
        int index = findCar(carID);
        if(index == -1){
            throw new IllegalArgumentException(CAR_NOT_FOUND_MSG);
        }
        else {
            TicketStrategy[] temp = new TicketStrategy[ticketList.length - 1] ; //Creating temporary array

            //  logic to copy lineItems array to temp array after leaving out selected item
            int j = 0;
            for (int i=0; i<ticketList.length; i++){
                if(i != index){
                    temp[j]= ticketList[i];
                    j++;
                }
            }
            // Swapping arrays
            ticketList = temp; 
            temp = null; 
        }
    }
    
    public final void clearTicketList(){
         this.ticketList = new HourTicket[0];
    }
    
    // PRIVATE METHODS
    private void addTicketToList(final TicketStrategy ticket){
        //Creating temporary array
        TicketStrategy[] temp = new TicketStrategy[ticketList.length + 1] ; 
        
        // Copying ticketList array to temp array
        System.arraycopy(ticketList, 0, temp, 0, ticketList.length ); 
        
        // Swapping arrays
        ticketList = temp; 
        temp = null;
        
        // Adding new ticket to array
        ticketList[ticketList.length-1] = ticket; 
        
    }
 
    private int findCar(final String carID) {
        int index = -1; // Flag
               
        // looping through ticketList Array
        for (int i = 0; i < ticketList.length; i++) {
            if (ticketList[i].getCarID().equals(carID) ) {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    //SETTER
//    public void setTicketStrategy(TicketStrategy ticketStrategy) {
//         if(ticketStrategy != null){
//            this.ticketStrategy = ticketStrategy;
//         }else{
//             throw new IllegalArgumentException(INVALID_TICKET_STRATEGY);
//         }
             
        
//    }
    
    
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
//        System.out.println( et.findCar("C100"))
//        et.parkCar("C200", 4);
//        et.parkCar("C300", 5);
//        
//        System.out.println( et.findCar("C100"));
//        et.deleteTicketFromList("C100");
//        System.out.println( et.isCarInGarage("C100"));
//    }

   
    

}