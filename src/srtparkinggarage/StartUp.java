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

    public static void main(String[] args) throws CustomIllegalArgumentException {
        
       //Creating objects of different Parking Fee Strategies 
       ParkingFeeStrategy hourlyFee = new  FlatHourlyRateParkingFeeCalculator(5);
       ParkingFeeStrategy minMaxFee = new  MinMaxParkingFeeCalculator(2.0, 3.0, 0.50, 10.00);
       ParkingFeeStrategy minNoMaxFee = new MinNoMaxParkingFeeCalculator(1.50, 2.0, 0.75) ; 
       
       //Creating objects of different Receipt Format Strategies
       ReceiptFormatStrategy simpleReceipt = new SimpleReceiptFormat();
       ReceiptFormatStrategy detailedReceipt = new DetailedReceiptFormat(); 
       
       //Creating objects of different output Strategies - 
       //this strategy is currently being applied to RunningTotals
       OutputStrategy guiOP = new GUIOutput();
       OutputStrategy consoleOP = new ConsoleOutput();
       OutputStrategy fileOP = new FileOutput("businessTotals.txt");
      
       
       //Creating objects of different TicketStrategies -
       TicketStrategy type1 = new HourTicket("C101",6); // needs input of carId and hoursPArked
        
       // needs input of carID , doesmot need hours ,claculates parking time based on checkin and check out times
       // Note : will not work properly here since checkin and checkout time will vary only in milliseconds
       TicketStrategy type2 = new NoTimeTicket("C102");
       
       // needs input of carID, and entrytime as LocalDateTime, claculates parking time based on check out time
       // which is now.
       LocalDateTime entryTime = LocalDateTime.of(2015, Month.DECEMBER, 14, 7, 0);
       TicketStrategy type3 = new EntryTimeTicket("C103",entryTime);
       
       // needs input of carID, and exittime as LocalDateTime, claculates parking time based on exitTime given
       //and entryTime when u parkcar(), exitTime has to be later than entryTime, else exception will be thrown
       LocalDateTime exitTime = LocalDateTime.of(2015, Month.DECEMBER, 14, 12, 30);
       TicketStrategy type4 = new ExitTimeTicket("C104",exitTime);
       
//----------------------------------------------------------------------------------------------------------------       
     
       String garageName = ParkingGarageConfigFactory.getParkingGarageName();
       File totalsFile = ParkingGarageConfigFactory.getTotalsFile();
       FileFormatStrategy fileFormatStrategy = ParkingGarageConfigFactory.getFileFormatStrategyInstance();
       ReceiptFormatStrategy receiptformatStrategy = ParkingGarageConfigFactory.getReceiptFormatStrategyInstance();
       
         
       File myFile = new File("F:" + File.separatorChar + "WCTC2015Fall" +File.separatorChar + "AdvancedJavaF" + 
            File.separatorChar +  "finalProject" + File.separatorChar + "SRTParkingGarageCurrent" + 
            File.separatorChar + "SRTParkingGarage" + File.separatorChar+ "src" + File.separatorChar + "RunningTotals.csv");

       ParkingGarageManager manager = new ParkingGarageManager(garageName, hourlyFee, receiptformatStrategy, consoleOP,
                                       totalsFile, fileFormatStrategy );
       
       
       manager.parkCar(new HourTicket("C100", 1));
       manager.parkCar(new HourTicket("C200", 2.5));
       manager.parkCar(new HourTicket("C300", 4));
       manager.parkCar(new HourTicket("C400", 24));
       manager.parkCar(new HourTicket("C500", 4.5));
       manager.parkCar(new HourTicket("C600", 2.01));
       manager.parkCar(type1);
       manager.parkCar(type2);
       manager.parkCar(type3);
       manager.parkCar(type4);
// 
//////Changing strategies
//       
//      manager.setReceiptFormatStrategy(detailedReceipt);
//      manager.setReceiptOutputStrategy(guiOP);
       
       manager.setParkingFeeStrategy(minNoMaxFee);
       
       manager.checkOutCar("C100", PaymentType.CASH);
      
       manager.checkOutCar("C200", PaymentType.CARD);
              
       manager.checkOutCar("C300", PaymentType.CASH);
                   
       manager.checkOutCar("C400", PaymentType.CASH);
      
       manager.checkOutCar("C500", PaymentType.CARD);
       
       manager.checkOutCar("C600", PaymentType.CASH);
       
//       manager.setReceiptFormatStrategy(simpleReceipt);
//       
       manager.checkOutCar("C101", PaymentType.CASH);
       
       manager.checkOutCar("C102", PaymentType.CASH);
       
       manager.checkOutCar("C103", PaymentType.CASH);
        
       manager.checkOutCar("C104", PaymentType.CASH);
      
     
       
        

       
    }
    
}
