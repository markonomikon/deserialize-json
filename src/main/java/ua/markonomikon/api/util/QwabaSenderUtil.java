package ua.markonomikon.api.util;

import java.util.Map;

public class QwabaSenderUtil {

    public static void textVerification(Map<String, String> map) throws Exception {
        if (map.get("text_body") == null || map.get("text_body").isEmpty()) {
            throw new Exception("text body cannot be empty");
        }
        if (map.get("recipient") == null || map.get("recipient").isEmpty()) {
            throw new Exception("recipient cannot be empty");
        }
    }

    public static void locationVerification(Map<String, String> map) throws Exception {
        if (map.get("latitude") == null || map.get("latitude").isEmpty()) {
            throw new Exception("latitude body cannot be empty");
        }
        if (map.get("longitude") == null || map.get("longitude").isEmpty()) {
            throw new Exception("latitude cannot be empty");
        }
        if (map.get("location_name") == null || map.get("location_name").isEmpty()) {
            throw new Exception("name cannot be empty");
        }
        if (map.get("recipient") == null || map.get("recipient").isEmpty()) {
            throw new Exception("recipient cannot be empty");
        }
    }

    public static void contactVerification(Map<String, String> map) throws Exception {
        if (map.get("phone") == null || map.get("phone").isEmpty()) {
            throw new Exception("phone body cannot be empty");
        }
        if (map.get("surname") == null || map.get("surname").isEmpty()) {
            throw new Exception("surname cannot be empty");
        }
        if (map.get("name") == null || map.get("name").isEmpty()) {
            throw new Exception("name cannot be empty");
        }
        if (map.get("recipient") == null || map.get("recipient").isEmpty()) {
            throw new Exception("recipient cannot be empty");
        }
        if (map.get("address_type") == null || map.get("address_type").isEmpty() ||
                (!map.get("address_type").equals("WORK") && !map.get("address_type").equals("HOME"))) {
            throw new Exception("address_type cannot be empty");
        }
    }

    public static void imageVerification(Map<String, String> map) throws Exception {
        if (map.get("link") == null || map.get("link").isEmpty()) {
            throw new Exception("link cannot be empty");
        }
        if (map.get("recipient") == null || map.get("recipient").isEmpty()) {
            throw new Exception("recipient cannot be empty");
        }
    }
}

