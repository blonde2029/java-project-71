package hexlet.code;

public class DiffAnalizer {
    private String key;
    private Object value1;
    private Object value2;
    private String difference;
    DiffAnalizer(String key, Object value1, Object value2, String difference) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.difference = difference;
    }
    public String getKey() {
        return key;
    }
    public Object getValue1() {
        return value1;
    }
    public Object getValue2() {
        return value2;
    }
    public String getDifference() {
        return  difference;
    }


}
