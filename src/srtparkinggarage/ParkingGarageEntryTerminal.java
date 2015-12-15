package srtparkinggarage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class manages the parked car list ie list of tickets and has methods for
 * managing this list Also has a method to park car .
 *
 * @author Shruthi Routhu
 */
public class ParkingGarageEntryTerminal {

    private static final String CAR_NOT_FOUND_MSG = "Car not found";
    private static final String CAR_IN_GARAGE = "Sorry ! Cannot park ! Car is already parked .";
    private static final String INVALID_TICKET_STRATEGY = " Ticket Strategy cannot be null! ";
    private static final String INVALID_STRING_MSG = "Sorry ! Input String cannot be null or empty ! ";

    private List<TicketStrategy> ticketList;

    //CONSTRUCTOR
    /**
     * Creates an object of <code>ParkingGarageEntryTerminal</code> and
     * initializes ticket list
     */
    public ParkingGarageEntryTerminal() {
        this.ticketList = new ArrayList<>();
    }

    // METHODS
    /**
     * Method to park car.
     *
     * @param ticket  <code>TicketStrategy </code> object
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if ticket is null or
     * if the car associated with the ticket is already parked
     */
    public final void parkCar(final TicketStrategy ticket) throws CustomIllegalArgumentException {

        if (ticket == null) {
            throw new CustomIllegalArgumentException(INVALID_TICKET_STRATEGY);
        }

        String carID = ticket.getCarID();

        // if the carId is already in the ticketList , exception is thrown 
        if (findCar(carID) == -1) {
            this.addTicketToList(ticket);
        } else {
            throw new CustomIllegalArgumentException(carID + ":  " + CAR_IN_GARAGE);
        }
    }

    /**
     * Method returns ticket associated with the given carID
     *
     * @param carID  <code>String</code> object
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @return <code>TicketStrategy </code> object
     * @throw <code>CustomIllegalArgumentException</code> if carID is null or
     * if that ticket is not in ticketList
     */
    public final TicketStrategy getTicket(final String carID) throws CustomIllegalArgumentException {

        if (carID == null) {
            throw new CustomIllegalArgumentException(INVALID_STRING_MSG);
        }

        int index = findCar(carID);

        // if Car not in list throw exception
        if (index == -1) {
            throw new CustomIllegalArgumentException(carID + " " + CAR_NOT_FOUND_MSG);
        }

        return ticketList.get(index);
    }

    /**
     * Method to delete ticket associated with given carID from ticket list
     *
     * @param carID  <code>String</code> object
     * @throws srtparkinggarage.CustomIllegalArgumentException
     * @throw <code>CustomIllegalArgumentException</code> if carID is null or
     * if that ticket is not in ticketList
     */
    public final void deleteTicketFromList(final String carID) throws CustomIllegalArgumentException {

        if (carID == null) {
            throw new CustomIllegalArgumentException(INVALID_STRING_MSG);
        }

        int index = findCar(carID);

        if (index == -1) {
            throw new CustomIllegalArgumentException(CAR_NOT_FOUND_MSG);
        } else {
            this.ticketList.remove(index);
        }
    }

    /**
     * Method to empty the ticket list
     *
     */
    public final void clearTicketList() {
        this.ticketList.clear();
    }

    // PRIVATE METHODS
    /**
     * Method to add the ticket to list
     *
     * @param ticket  <code>TicketStrategy </code> object
     * @throws <code>CustomIllegalArgumentException</code> if ticket is null
     */
    private void addTicketToList(final TicketStrategy ticket) throws CustomIllegalArgumentException {
        if (ticket == null) {
            throw new CustomIllegalArgumentException(INVALID_TICKET_STRATEGY);
        }
        this.ticketList.add(ticket);
    }

    /**
     * Method to find ticket associated with the given carID
     *
     * @param carID  <code>String</code> object
     * @returns position of carID/ticket in ticket list
     * @throws <code>CustomIllegalArgumentException</code> if carID is null
     */
    private int findCar(final String carID) throws CustomIllegalArgumentException {

        if (carID == null) {
            throw new CustomIllegalArgumentException(INVALID_STRING_MSG);
        }

        int index = -1; // Flag

        // looping through ticketList Array
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getCarID().equals(carID)) {
                index = i;
                break;
            }
        }

        return index;
    }

    // MANDATORY METHODS
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
    public boolean equals(final Object obj) {
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
