package cn.cherish.mboot.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/9/17 10:31
 */
public class IntrospectorTest {

    public static void main(String[] args) {
        try {
            SubTest subTest = new SubTest(6.0f);
            BeanInfo beanInfo = Introspector.getBeanInfo(SubTest.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod != null) {
                    Object invoke = readMethod.invoke(subTest);
                    System.out.println(readMethod.getName() + " = " + invoke);
                }
            }

            PropertyDescriptor fDescriptor = new PropertyDescriptor("f", SubTest.class, "getF", null);
            Method readMethod = fDescriptor.getReadMethod();
            Object invoke = readMethod.invoke(subTest);
            System.out.println(readMethod.getName() + " = " + invoke);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
