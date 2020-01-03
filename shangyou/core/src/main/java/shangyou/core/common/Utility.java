package shangyou.core.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Utility {

    private static ObjectMapper objMapper;
    static {
        objMapper = new ObjectMapper();
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objMapper.registerModule(javaTimeModule);
    }

    public static String objToJsonString(Object obj) {
        String ret;
        try {
            ret = objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            ret = null;
            log.error(e.getMessage());
        }
        return ret;
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> clazz) {
        T ret;
        try {
            ret = objMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            ret = null;
            log.error("解析对象失败:{}", e.getMessage());
        }
        return ret;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T jsonStringToComplexObject(String jsonString, Class<T> clazz, Class<?>... elementClasses) {
        T ret;
        try {
            JavaType javaType = getCollectionType(clazz, elementClasses);
            ret = objMapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            ret = null;
            log.error("解析对象失败:{}", e.getMessage());
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        Map<String, Object> ret;
        try {
            ret = objMapper.readValue(jsonString, HashMap.class);
        } catch (Exception e) {
            ret = null;
            log.error("解析对象失败:{}", e.getMessage());
        }
        return ret;
    }

}
