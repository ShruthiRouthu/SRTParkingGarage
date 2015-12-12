
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
    
}
