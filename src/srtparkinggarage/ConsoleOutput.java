
package srtparkinggarage;

public class ConsoleOutput implements OutputStrategy {
    
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
    
    @Override
    public final void outputData(final String opString) throws IllegalArgumentException {
        
        if(opString == null || opString.length() == 0){
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
        System.out.println(opString);
    }

    @Override
    public String toString() {
        return "ConsoleOutput{" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ConsoleOutput other = (ConsoleOutput) obj;
        return true;
    }
    
    
    
    
        
        
    
    
}
