package tasks.task4;

import java.util.*;

public class Task_4 {

    public static <X extends Comparable<X>> ArrayList<X> sortLow(ArrayList<X> array) {
        array.sort((x, y) -> -x.compareTo(y));
        return array;
    }

    public static <X> void addInHeadAndTail(LinkedList<X> array, X elem) {
        array.addLast(elem);
        array.addFirst(elem);
    }

    public static <X> int find(ArrayList<X> array, X elem) {
        return array.indexOf(elem);
    }

    public static <K, V> boolean inMap(Map<K, V> kvMap, V elem) {
        return kvMap.containsValue(elem);
    }

//    public static <X extends Number> X sumElem(List<X> array) {
//        if (array.get(0) instanceof Integer) {
//            return  (X) Integer.valueOf(array.stream().mapToInt(X::intValue).sum());
//        } else if (array.get(0) instanceof Double) {
//            return (X) Double.valueOf(array.stream().mapToDouble(X::doubleValue).sum());
//        }
//        return (X) Integer.valueOf(0);
//    }

    public static Double sumElem(List<? extends Number> array) {
        return array.stream().mapToDouble(Number::doubleValue).sum();
    }


    public static void main(String[] args) {
        System.out.println(sortLow(new ArrayList<>(Arrays.asList("4", "3", "1", "8"))));
        System.out.println(sortLow(new ArrayList<>(Arrays.asList(4, 3, 1, 8))));
        System.out.println(sortLow(new ArrayList<>(Arrays.asList(4.5, 3.2, 1.7, 8.1))));


        System.out.println(sumElem(new ArrayList<>(Arrays.asList(4, 3, 1, 8))));


        Map<String, Integer> map = new TreeMap<>(Map.of("H", 1, "E", 2, "L", 3));
        System.out.println(inMap(map, 3));
        System.out.println(inMap(map, 6));


        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("1"));
        addInHeadAndTail(linkedList, "3");
        System.out.println(linkedList);


        ArrayList<Double> arrayList = new ArrayList<>(Arrays.asList(4.5, 3.2, 1.7, 8.1));
        System.out.println(find(arrayList, 4.5));
        System.out.println(find(arrayList, 69.0));

    }
}

