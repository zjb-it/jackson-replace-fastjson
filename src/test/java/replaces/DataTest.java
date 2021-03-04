package replaces;

import jackson.replaces.fastjson.JSON;
import jackson.replaces.fastjson.JSONArray;
import jackson.replaces.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataTest {

    @Test
    public void testParseInnerArray() {
        String data = "{\n" +
                "  \"key\": \"value\"\n" +
                "}";
        JSONObject json = JSON.parseObject(data);
        try {
            JSONArray shouldBeNull = json.getJSONArray("somekeynotexists");
            Assertions.assertNull(shouldBeNull);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    public static void main(String[] args) {
        String a = "[\n" +
                "    {\n" +
                "        \"key\":1\n" +
                "    },\n" +
                "    {\n" +
                "        \"key1\":{\n" +
                "            \"objKey\":123\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"key2\":[\n" +
                "            {\n" +
                "                \"ArrKey\":123,\n" +
                "                \"keyy\":[\n" +
                "                    {\n" +
                "                        \"aa\":444\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        final JSONArray objects = JSON.parseArray(a);
        System.out.println(JSON.toJSONString(objects));
        for (int i = 0; i < objects.size(); i++) {
            final JSONObject jsonObject = objects.getJSONObject(i);
            if (jsonObject.containsKey("key2")) {
                final JSONArray key2 = jsonObject.getJSONArray("key2");
                System.out.println(key2.toJSONString());
            }
        }
    }
}
