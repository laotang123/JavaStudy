package com.golaxy.indi.chapter14;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2020/11/24 22:56
 * @description: 反射获取相关属性
 * @modified By:
 * @version: $ 1.0
 */

@Slf4j
public class ObjectUtil {
    /**
     * 将静态属性和transient关键字修饰的属性过滤掉
     *
     * @param field：属性值
     */
    public static boolean needFilterField(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers);
//        return Modifier.isTransient(modifiers);
//        return false;
    }

    /**
     * 获取对象的属性值，包括父类的接口
     *
     * @param obj：传入的对象
     */
    public static Map<String, Field> getFieldList(Object obj) {
        //1.获取当前对象
        Map<String, Field> fieldMap = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (!needFilterField(field)) {
                fieldMap.put(field.getName(), field);
            }
        }

        //2.递归获取父类的属性
        getParentFiled(clazz, fieldMap);
        return fieldMap;
    }

    /**
     * 递归的访问父类的属性
     *
     * @param clazz：子类
     * @param fieldMap：属性map
     */
    public static void getParentFiled(Class<?> clazz, Map<String, Field> fieldMap) {
        Class<?> superclass = clazz.getSuperclass();

        if (superclass != null) {
            Field[] declaredFields = superclass.getDeclaredFields();
            for (Field field : declaredFields) {
                if (!needFilterField(field)) {
                    fieldMap.put(field.getName(), field);
                }
            }

            //递归获取类接口的属性值
            Class<?>[] interfaces = superclass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                Field[] fields = anInterface.getDeclaredFields();
                for (Field field : fields) {
                    if (!needFilterField(field)) {
                        fieldMap.put(field.getName(), field);
                    }
                }
                getParentFiled(anInterface, fieldMap);
            }
            getParentFiled(superclass, fieldMap);
        }


    }

    public static void main(String[] args) {
        System.out.println("hello");
//        log.info("i am slf4j");


        Map<String, Field> fieldList = getFieldList(String.class);
        fieldList.entrySet().forEach(System.out::println);
    }
}
