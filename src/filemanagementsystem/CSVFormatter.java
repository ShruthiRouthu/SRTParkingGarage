package filemanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements <code>FileFormatStrategy</code> Encodes and Decodes
 * running totals data to and from CSV format
 *
 * @author Shruthi Routhu
 */
public class CSVFormatter implements FileFormatStrategy {

    private static final String DATE_TIME = "DateTime";
    private static final String TOTAL_HOURS = "TotalHours";
    private static final String TOTAL_FEES = "TotalFees";

    private static final String INVALID_DATA_MSG = "CSVFormatter : No Data !";

    /**
     * This method Encodes data to CSV format
     *
     * @param myFormatData - Data in format
     * <code>List<Map<String, Object>></code>
     * @returns encoded data in form of  <code>List<String></code>
     */
    @Override
    public final List<String> encode(final List<Map<String, Object>> myFormatData) {
        if (myFormatData == null || myFormatData.isEmpty()) {

            System.out.println(INVALID_DATA_MSG);
            return null;

        }

        List<String> writeData = new ArrayList<>();
        int no = myFormatData.size();
        Map<String, Object> tempMap;
        StringBuilder line;

        for (int i = 0; i < no; i++) {

            tempMap = myFormatData.get(i);
            line = new StringBuilder();

            line.append(tempMap.get(DATE_TIME));
            line.append(",");

            line.append(tempMap.get(TOTAL_HOURS));
            line.append(",");

            line.append(tempMap.get(TOTAL_FEES));

            writeData.add(line.toString());

            //System.out.println(writeData.toString());
        }

        return writeData;
    }

    /**
     * This method Decodes data from CSV format
     *
     * @param rawData - encoded data in form of  <code>List<String></code>
     * @returns Decoded Data in format <code>List<Map<String, Object>></code>
     */
    @Override
    public final List<Map<String, Object>> decode(final List<String> rawData) {
        if (rawData == null || rawData.isEmpty()) {

            System.out.println(INVALID_DATA_MSG);
            return null;

        }

        List<Map<String, Object>> decodedData = new ArrayList<>();

        // Removing first line from rawData
        rawData.remove(0);

        // Logic to get value from each line and  to store them in a map
        String[] lineParts;
        Map<String, Object> map;

        for (String totalLine : rawData) {

            map = new HashMap<>();
            totalLine = totalLine.trim();

            if (!totalLine.isEmpty()) {
                lineParts = totalLine.split(",");

                if (lineParts.length == 3) {
                    map.put(DATE_TIME, lineParts[0]);
                    map.put(TOTAL_HOURS, lineParts[1]);
                    map.put(TOTAL_FEES, lineParts[2]);
                }
                decodedData.add(map);
            }
        }

        return decodedData;
    }

    //MANDATORY METHODS
    @Override
    public String toString() {
        return "CSVFormatter{" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final CSVFormatter other = (CSVFormatter) obj;
        return true;
    }

}
