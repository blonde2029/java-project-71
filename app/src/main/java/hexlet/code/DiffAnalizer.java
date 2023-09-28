package hexlet.code;

public class DiffAnalizer {
    private final String key;
    private final Object value1;
    private final Object value2;
    private final String difference;
    DiffAnalizer(String key, Object value1, Object value2, String difference) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.difference = difference;
    }
    public final String getKey() {
        return key;
    }
    public final Object getValue1() {
        return value1;
    }
    public final Object getValue2() {
        return value2;
    }
    public final String getDifference() {
        return  difference;
    }


}
