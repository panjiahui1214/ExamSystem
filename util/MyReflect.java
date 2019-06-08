package util;

import java.util.HashMap;

public class MyReflect {
    private static HashMap<String, Object> reflectBox = new HashMap<>();

    public static <T>T getClass(String className) {
        Object obj = null;
        try {
            obj = reflectBox.get(className);
            if (obj == null) {
                Class c = Class.forName(className);
                obj = c.newInstance();
                reflectBox.put(className, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }
}
