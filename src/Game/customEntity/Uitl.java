package Game.customEntity;

import java.lang.reflect.Field;

public class Uitl {
    public static Field getPrivateField(String fieldName,Class clazz){
        Field field = null;
        
        try {
            field=clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }

    public static Object getPrivateField(String fieldName, Class clazz,
                                         Object object) {
        Field field;
        Object o = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
