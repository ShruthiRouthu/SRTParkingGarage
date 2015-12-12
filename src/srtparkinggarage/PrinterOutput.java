package srtparkinggarage;

public class PrinterOutput implements OutputStrategy {
    
    @Override
    public void outputData(String opString) {
        if(opString == null || opString.length() == 0){
            throw new IllegalArgumentException("String parameter to outputReciept() method is inavalid ");
        }
        
        // Faking It !!!
        System.out.println("Printer has printed a reciept!");
    }
    
    
}
