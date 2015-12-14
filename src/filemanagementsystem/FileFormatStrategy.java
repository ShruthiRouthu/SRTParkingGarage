
package filemanagementsystem;

import java.util.List;
import java.util.Map;

/**
 * Interface(strategy Interface) to enable usage of varying FileFormatStartegies
 * based on polymorphism
 *
 * @author Shruthi Routhu
 */
public interface FileFormatStrategy {
    
    /**
     * This method Encodes data to required format
     *
     * @param myFormatData - Data in format
     * <code>List<Map<String, Object>></code>
     * @returns encoded data in form of  <code>List<String></code>
     */
    public List<String> encode(final List<Map<String,Object>> myFormatData);
    
    /**
     * This method Decodes data from specified format
     *
     * @param rawData - encoded data in form of  <code>List<String></code>
     * @returns Decoded Data in format <code>List<Map<String, Object>></code>
     */
    public List<Map<String,Object>> decode(final List<String> rawData);
    
}
