package srtparkinggarage;

public class PrinterOutput implements OutputStrategy {
    
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
    
    @Override
    public void outputData(String opString) {
        if(opString == null || opString.length() == 0){
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
        // Faking It !!!
        System.out.println("Printer has printed a reciept!");
    }

    @Override
    public String toString() {
        return "PrinterOutput{" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PrinterOutput other = (PrinterOutput) obj;
        return true;
    }
    
    
    
    
    
    
}
