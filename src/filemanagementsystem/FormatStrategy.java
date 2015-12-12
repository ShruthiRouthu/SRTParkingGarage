
package filemanagementsystem;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public interface FormatStrategy {
    
    public List<String> encode(final List<Map<String,Object>> myFormatData);
    
    public List<Map<String,Object>> decode(final List<String> rawData);
    
}
