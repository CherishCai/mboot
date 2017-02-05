package cn.cherish.mboot.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * 完成Map to Object 及　Object to Map 复制
 * ObjectA to ObjectB 简单复制（不支持继承属性复制）
 *
 * @author yangyufa
 */
public class ObjectConvertUtil {
    /**
     * 把map 转换成对象
     * map.key==>object.field;map.value==>object.field.value
     * (包含继承属性)
     *
     * @param map 类属性值
     * @param cls clazz
     * @return <T>
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> cls) {
        return mapToObject(map, cls, true);
    }

    /**
     * 把map 转换成对象
     * map.key==>object.field;map.value==>object.field.value
     *
     * @param map       类属性值
     * @param cls       clazz
     * @param isInherit 是否赋值继承自父类的属性
     * @return <T>
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> cls, boolean isInherit) {
        if (map == null || map.isEmpty()) return null;

        Field[] fields = cls.getDeclaredFields();
        T obj;
        try {
            obj = cls.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> clsType = field.getType();
                String name = field.getName();
                String strSet = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                Method methodSet = cls.getDeclaredMethod(strSet, clsType);
                if (map.containsKey(name)) {
                    methodSet.invoke(obj, map.get(name));
                }
            }
            //把继承直接父类的属性也赋值
            if (isInherit) obj = getGenericSuperclassFields(map, obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把对象的属性及属性的值轮换为map的key=value形式(不包含继承属性)
     *
     * @param obj 目标对象
     * @return Map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        return objectToMapExcludeNullVal(obj, false);
    }

    /**
     * 把对象的属性及属性的值轮换为map的key=value形式
     *
     * @param obj       目标对象
     * @param isInherit 是否获取父类属性值
     * @return Map
     */
    public static Map<String, Object> objectToMapExcludeNullVal(Object obj, boolean isInherit) {
        if (obj == null) return null;
        Map<String, Object> mapValue = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //如果是final或static属性则跳过
            int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)){
                continue;
            }

            String name = field.getName();
            String method = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            String strGet = "get" + method;
            Method methodGet;
            try {
                methodGet = obj.getClass().getDeclaredMethod(strGet);
                Object object = methodGet.invoke(obj);
                if (object != null) {
                    mapValue.put(name, object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isInherit) mapValue = getGenericSuperclassFieldsToMapKeys(mapValue, obj);

        return mapValue;
    }

    /**
     * 把源对象的值复制到目标对象中
     *
     * @param targetObject 目标对象
     * @param sourceObject 临时对象(不支持继承属性拷贝)
     * @return 目标对象
     */
    public static <T> T objectCopy(T targetObject, Object sourceObject) {
        Class<?> cls = sourceObject.getClass();
        Class<?> cls2 = targetObject.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //如果是final或static属性则跳过
            int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)){
                continue;
            }

            Class<?> clsType = field.getType();
            String name = field.getName();
            String method = name.substring(0, 1).toUpperCase()
                    + name.substring(1, name.length());
            String strGet = "get" + method;
            try {
                Method methodGet = cls.getDeclaredMethod(strGet);
                Object object = methodGet.invoke(sourceObject);
                if (object != null) {
                    String strSet = "set" + method;
                    Method methodSet;
                    try {
                        methodSet = cls2.getDeclaredMethod(strSet, clsType);
                    } catch (NoSuchMethodException e) {
                        continue;
                    }
                    methodSet.invoke(targetObject, object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetObject;
    }

    /**
     * 判断对象的所有非静态属性是否为空或""
     * @param obj 分析的对象
     * @return 对象的所有非静态属性是否为空或""
     */
    public static boolean objectFieldIsBlank(Object obj) {
        if (obj == null) return true;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //如果是final或static属性则跳过
            int modifiers = field.getModifiers();
            if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)){
                continue;
            }

            String name = field.getName();
            String method = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            String strGet = "get" + method;
            Method methodGet;
            try {
                methodGet = obj.getClass().getDeclaredMethod(strGet);
                Object object = methodGet.invoke(obj);
                if (object == null ||
                        (object instanceof String &&
                        org.apache.commons.lang3.StringUtils.isBlank((String) object))
                        ){
                    continue;
                }
                //除了以上的null 和String的""空串，则非Blank
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 为对象（obj）继承直接父类的属性赋值
     *
     * @param <T> map
     * @param obj 目标对象
     * @return <T>
     */
    private static <T> T getGenericSuperclassFields(Map<String, Object> map, T obj) {
        if (obj.getClass().getGenericSuperclass() != null) {
            Class<?> superClass = obj.getClass().getSuperclass();// 父类
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                Class<?> clsType = field.getType();
                String name = field.getName();
                String strSet = "set" + name.substring(0, 1).toUpperCase()
                        + name.substring(1, name.length());
                Method methodSet;
                try {
                    methodSet = superClass
                            .getDeclaredMethod(strSet, clsType);
                    if (map.containsKey(name)) {
                        methodSet.invoke(obj, map.get(name));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 获取对象（obj）继承直接父类的属性值
     *
     * @param mapValue map储存
     * @param obj      目标对象
     * @return Map
     */
    private static Map<String, Object> getGenericSuperclassFieldsToMapKeys(Map<String, Object> mapValue, Object obj) {
        if (obj.getClass().getGenericSuperclass() != null) {
            Class<?> superClass = obj.getClass().getSuperclass();// 父类
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                String strSet = "get" + name.substring(0, 1).toUpperCase()
                        + name.substring(1, name.length());
                Method methodSet;
                try {
                    methodSet = superClass.getDeclaredMethod(strSet);
                    mapValue.put(name, methodSet.invoke(obj));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mapValue;
    }

}
