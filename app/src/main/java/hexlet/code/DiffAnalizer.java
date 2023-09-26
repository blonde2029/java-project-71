package hexlet.code;

public class DiffAnalizer {
    private String key;
    private Object value;
    private String difference;
    DiffAnalizer(String key, Object value, String difference) {
        this.key = key;
        this.value = value;
        this.difference = difference;
    }
    public String getKey() {
        return key;
    }
    public  Object getValue() {
        return value;
    }
    public String getDifference() {
        return  difference;
    }


}
