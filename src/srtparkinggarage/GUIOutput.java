
package srtparkinggarage;

import javax.swing.JOptionPane;

public class GUIOutput implements OutputStrategy{
    
    private static final String INVALID_STRING_PARAMETER_MSG = "String parameter not valid" ;
    
    @Override
    public final void outputData(final String opString) throws IllegalArgumentException {
        
        if(opString == null || opString.length() == 0){
            throw new IllegalArgumentException(INVALID_STRING_PARAMETER_MSG);
        }
        
        JOptionPane.showMessageDialog(null, opString);
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
        final GUIOutput other = (GUIOutput) obj;
        return true;
    }

    @Override
    public String toString() {
        return "GUIOutput{" + "INVALID_STRING_PARAMETER_MSG=" + INVALID_STRING_PARAMETER_MSG + '}';
    }
    
    
    
}
