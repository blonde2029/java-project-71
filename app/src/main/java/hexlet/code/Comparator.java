package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Comparator {
    public static List<Map<Object, Object>> compare(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);
        List<Map<Object, Object>> comparedValues = new ArrayList<>();
        allData.keySet()
                .forEach((i -> {
                    Map<Object, Object> elem = new LinkedHashMap<>();
                    elem.put("field", i);
                    if (!data2.containsKey(i)) {
                        elem.put("changeType", "removed");
                        elem.put("oldValue", data1.get(i));
                    } else if (!data1.containsKey(i) && data2.containsKey(i)) {
                        elem.put("changeType", "added");
                        elem.put("newValue", data2.get(i));
                    } else if (data1.containsKey(i) && data2.containsKey(i)) {
                        if (data1.get(i) == null || data2.get(i) == null) {
                            elem.put("changeType", "changed");
                        } else if (data1.get(i).equals(data2.get(i))) {
                            elem.put("changeType", "equal");
                        } else if (data1.get(i) == data2.get(i)) {
                            elem.put("changeType", "equal");
                        } else {
                            elem.put("changeType", "changed");
                        }
                        elem.put("oldValue", data1.get(i));
                        elem.put("newValue", data2.get(i));
                    }
                    comparedValues.add(elem);
                }));
        return  comparedValues;
    }
}
