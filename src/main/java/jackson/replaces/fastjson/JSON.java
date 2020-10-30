package jackson.replaces.fastjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.replaces.fastjson.exception.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zjb
 */
public class JSON {

    public static String toJSONString(Object jsonString) {
        try {
            return new ObjectMapper().writeValueAsString(jsonString);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

    public static Object parse(String jsonString) {
        if (isJsonObj(jsonString)) {
            return parseObject(jsonString);
        }
        if (isJsonArray(jsonString)) {
            return parseArray(jsonString);
        }
        try {
            return new ObjectMapper().readValue(jsonString, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

    public static JSONObject parseObject(String jsonString) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final Map map = objectMapper.readValue(jsonString, Map.class);
            return mapToJsonObject(map);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }

    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

    public static JSONArray parseArray(String jsonString) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final List list = objectMapper.readValue(jsonString, List.class);
            return listConvertToJsonArray(list);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }


    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

    /**
     * 是否为JSON字符串，首尾都为大括号或中括号判定为JSON字符串
     *
     * @param str 字符串
     * @return 是否为JSON字符串
     */
    public static boolean isJson(String str) {
        return isJsonObj(str) || isJsonArray(str);
    }

    /**
     * 是否为JSONObject字符串，首尾都为大括号或中括号判定为JSON字符串
     *
     * @param str 字符串
     * @return 是否为JSON字符串
     */
    public static boolean isJsonObj(String str) {

        if (isBlank(str)) {
            return false;
        }
        return isWrap(str.trim(), '{', '}');
    }

    /**
     * 是否为JSONObject字符串，首尾都为大括号或中括号判定为JSON字符串
     *
     * @param str 字符串
     * @return 是否为JSON字符串
     */
    public static boolean isJsonArray(String str) {
        if (isBlank(str)) {
            return false;
        }
        return isWrap(str.trim(), '[', ']');
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    private static boolean isWrap(String str, char start, char end) {
        if (isBlank(str)) {
            return false;
        }
        return str.charAt(0) == start && str.charAt(str.length() - 1) == end;
    }

    private static JSONArray listConvertToJsonArray(List list) {
        List<Object> jsonObjects = new ArrayList<>(list.size());
        for (Object obj : list) {
            jsonObjects.add(mapToJsonObject((Map<String, Object>) obj));
        }
        return new JSONArray(jsonObjects);
    }

    /**
     * jackson parse出来的是map和list,所以把map和list转换为jsonObject和jsonArray
     * @param map
     * @return
     */
    private static JSONObject mapToJsonObject(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            final Object value = entry.getValue();
            if (value instanceof Map) {
                jsonObject.put(entry.getKey(), mapToJsonObject((Map<String, Object>) value));
            } else if (value instanceof List) {
                final List listVal = (List) value;
                JSONArray objects = new JSONArray(listVal.size());
                for (Object o : listVal) {
                    objects.add(mapToJsonObject((Map<String, Object>) o));
                }
                jsonObject.put(entry.getKey(), objects);
            } else {
                jsonObject.put(entry.getKey(), value);
            }
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        String str = "{\"code\":0,\"message\":\"OK\",\"result\":{\"appId\":380,\"accessToken\":\"6745db8f-5b62-4421-b32d-0b930fc0cf1b\",\"expireTime\":36624,\"refreshToken\":\"24a36da4-429a-433e-a531-4576bed3a76a\"}}";

        final Object parse = JSON.parse(str);

        System.out.println();
    }


}
