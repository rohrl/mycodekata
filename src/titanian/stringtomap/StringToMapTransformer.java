package titanian.stringtomap;

import java.util.Map;

public interface StringToMapTransformer {
    Map<String, Integer> parseNumbers(String csvString);
}
