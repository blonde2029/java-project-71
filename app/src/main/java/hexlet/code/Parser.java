package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String content, String extension) throws Exception {
        ObjectMapper mapper;
        if (extension.equals("json")) {
            mapper = new ObjectMapper();
        } else if (extension.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Unknown format: '" + extension + "'");
        }
        Map<String, Object> data;
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<>() { };
        try {
            data = mapper.readValue(content, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
