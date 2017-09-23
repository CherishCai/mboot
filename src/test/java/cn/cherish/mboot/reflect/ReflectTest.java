package cn.cherish.mboot.reflect;

import java.lang.reflect.*;

/**
 * 反射使用
 * @author Cherish
 * @version 1.0
 * @date 2017/9/16 13:18
 */
public class ReflectTest {

    public static int i;
    public double d = 0.666;

    private float f = 0.1f;

    public void setF(float f) {
        this.f = f;
    }

    protected static void print(){
        System.out.println("i = " + i);
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        
        ReflectTest subTest = new SubTest(4.0f);
        Class<? extends ReflectTest> clazz = subTest.getClass();
        Class<SubTest> clazz2 = SubTest.class;
        System.out.println("clazz.getName() = " + clazz.getName());
        System.out.println("clazz2.getName() = " + clazz2.getName());
        try {
            Class<?> forName = Class.forName(SubTest.class.getName());
            System.out.println("forName.getName() = " + forName.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = SubTest.class.getClassLoader();
        try {
            Class<?> loadClass = classLoader.loadClass(SubTest.class.getName());

            System.out.println("loadClass.getName() = " + loadClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();



        Field[] fields = clazz.getFields();
        System.out.println("fields = " + fields);
        for (int j = 0; j < fields.length; j++) {
            System.out.println("getDeclaringClass() = " + fields[i].getDeclaringClass());;
            System.out.print(fields[i] + " : ");
            try {
                Object o = fields[i].get(subTest);
                System.out.println("o = " + o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println("=============================");
        Field[] declaredFields2 = clazz2.getDeclaredFields();
        System.out.println("declaredFields2 = " + declaredFields2);
        for (int j = 0; j < declaredFields2.length; j++) {
            System.out.print(declaredFields2[i] + " : ");
            Type genericType = declaredFields2[i].getGenericType();
            System.out.println("genericType = " + genericType);
            try {
                Object o = declaredFields2[i].get(subTest);
                System.out.println("o = " + o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            Constructor<SubTest> constructor = clazz2.getConstructor(float.class);
            try {
                SubTest subTest1 = constructor.newInstance(66);
                System.out.println("subTest1.f = " + subTest1.f);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        Class<PrivateClass> privateClass = PrivateClass.class;
        try {
            Constructor<PrivateClass> constructor = privateClass.getDeclaredConstructor();
            System.out.println("constructor = " + constructor);
            constructor.setAccessible(true);
            PrivateClass newInstance = constructor.newInstance();
            newInstance.print();

            Method pp = privateClass.getDeclaredMethod("pp");
            Parameter[] parameters = pp.getParameters();
            pp.setAccessible(true);
            Object invoke = pp.invoke(newInstance);
            System.out.println("invoke = " + invoke);

            Method ppp = privateClass.getDeclaredMethod("ppp");
            System.out.println("ppp = " + ppp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
