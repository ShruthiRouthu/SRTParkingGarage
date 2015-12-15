package srtparkinggarage;

import filemanagementsystem.CSVFormatter;
import filemanagementsystem.FileFormatStrategy;
import java.io.File;
import java.util.Random;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;

/**
 *
 * @author Professional
 */
public class StartUp {

    public static void main(String[] args) {

        try {

            //Creating objects of different output Strategies - 
            //this strategy is currently being applied to Receipt
            OutputStrategy guiOP = new GUIOutput();
            OutputStrategy consoleOP = new ConsoleOutput();
            OutputStrategy fileOP = new FileOutput("businessTotals.txt");

            //Creating objects of different Parking Fee Strategies 
            ParkingFeeStrategy hourlyFee = new FlatHourlyRateParkingFeeCalculator(5);
            ParkingFeeStrategy minMaxFee = new MinMaxParkingFeeCalculator(2.0, 3.0, 0.50, 10.00);
            ParkingFeeStrategy minNoMaxFee = new MinNoMaxParkingFeeCalculator(1.50, 2.0, 0.75);

            // Setting strategies using factory class
            String garageName = ParkingGarageConfigFactory.getParkingGarageName();
            File totalsFile = ParkingGarageConfigFactory.getTotalsFile();
            FileFormatStrategy fileFormatStrategy = ParkingGarageConfigFactory.getFileFormatStrategyInstance();
            ReceiptFormatStrategy receiptformatStrategy = ParkingGarageConfigFactory.getReceiptFormatStrategyInstance();

            ParkingGarageManager manager = new ParkingGarageManager(garageName, hourlyFee, receiptformatStrategy, consoleOP,
                    totalsFile, fileFormatStrategy);

            // Parking cars
            manager.parkCar(new NoTimeTicket("C100"));
            manager.parkCar(new NoTimeTicket("C200"));
            manager.parkCar(new NoTimeTicket("C300"));
            manager.parkCar(new NoTimeTicket("C400"));
            manager.parkCar(new NoTimeTicket("C500"));
            manager.parkCar(new NoTimeTicket("C600"));

            // Checking out cars
            //manager.setParkingFeeStrategy(minNoMaxFee);
            
            try {
                Thread.sleep(200);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C100", PaymentType.CASH);
            
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C200", PaymentType.CARD);
            
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C300", PaymentType.CASH);
            
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C400", PaymentType.CASH);
            
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C500", PaymentType.CARD);
            
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            manager.checkOutCar("C600", PaymentType.CASH);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

//       //Creating objects of different Receipt Format Strategies
//       ReceiptFormatStrategy simpleReceipt = new SimpleReceiptFormat();
//       ReceiptFormatStrategy detailedReceipt = new DetailedReceiptFormat();  
//       //Creating objects of different TicketStrategies -
//       TicketStrategy type1 = new HourTicket("C101",6); // needs input of carId and hoursPArked
//        
//       // needs input of carID , doesmot need hours ,claculates parking time based on checkin and check out times
//       // Note : will not work properly here since checkin and checkout time will vary only in milliseconds
//       TicketStrategy type2 = new NoTimeTicket("C102");
//       
//       // needs input of carID, and entrytime as LocalDateTime, claculates parking time based on check out time
//       // which is now.
//       LocalDateTime entryTime = LocalDateTime.of(2015, Month.DECEMBER, 14, 7, 0);
//       TicketStrategy type3 = new EntryTimeTicket("C103",entryTime);
//       
//       // needs input of carID, and exittime as LocalDateTime, claculates parking time based on exitTime given
//       //and entryTime when u parkcar(), exitTime has to be later than entryTime, else exception will be thrown
//       LocalDateTime exitTime = LocalDateTime.of(2015, Month.DECEMBER, 14, 12, 30);
//       TicketStrategy type4 = new ExitTimeTicket("C104",exitTime);
//---------------------------------------------------------------------------------------------------------------- 