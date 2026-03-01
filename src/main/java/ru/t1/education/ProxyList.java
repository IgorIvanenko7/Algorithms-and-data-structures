package ru.t1.education;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyList {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>(List.of(1,2,3,4,5));
        CountingList countingList = new CountingList(integerList);
        integerList = (List<Integer>) Proxy.newProxyInstance(integerList.getClass().getClassLoader(),
                integerList.getClass().getInterfaces(),
                countingList);
        test(integerList);

        System.out.println("Добавлено элементов: " + countingList.countAdd + " | " +
                "Удалено элементов: " + countingList.removeItem);
    }

    public static void test(List<Integer> integerList) {
        integerList.add(75);
        integerList.add(77);
        integerList.remove(5);
    }
}

class CountingList implements InvocationHandler {
    List<Integer> list;
    Integer countAdd = 0;
    Integer removeItem = 0;

    public CountingList(List<Integer> list){
        this.list = list;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contains("add")) {
            System.out.println("Action: Добавление элемента: " + args[0].toString());
            countAdd ++;
        } else if (method.getName().contains("remove")) {
            System.out.println("Action: Удаление элемента с индексом: " + args[0].toString());
            removeItem ++;
        }
        return method.invoke(list, args);
    }
}