package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DifferTreeBuilder {

    public static List<Node> build(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Node> nodes = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            boolean hasInFirst = map1.containsKey(key);
            boolean hasInSecond = map2.containsKey(key);

            if (hasInFirst && !hasInSecond) {
                nodes.add(new Node(key, Node.Status.REMOVED, value1, null));
            } else if (!hasInFirst && hasInSecond) {
                nodes.add(new Node(key, Node.Status.ADDED, null, value2));
            } else { // Оба ключа присутствуют
                // Проверяем, являются ли значения сложными типами (Map или List)
                boolean isValue1Complex = isComplexType(value1);
                boolean isValue2Complex = isComplexType(value2);

                if (isValue1Complex && isValue2Complex) {
                    // Оба значения - Map или List
                    if (Objects.equals(value1, value2)) {
                        // Значения равны как объекты (например, одинаковые списки или одинаковые мапы)
                        nodes.add(new Node(key, Node.Status.UNCHANGED, value1, value2));
                    } else {
                        // Значения не равны, но оба сложные. Рекурсивно строим диф для них, если оба Map.
                        if (value1 instanceof Map && value2 instanceof Map) {
                            List<Node> nestedDiff = build((Map<String, Object>) value1, (Map<String, Object>) value2);
                            nodes.add(new Node(key, Node.Status.NESTED, nestedDiff));
                        } else {
                            // Если типы разные (Map vs List) или оба List но разные - считаем как CHANGED
                            nodes.add(new Node(key, Node.Status.CHANGED, value1, value2));
                        }
                    }
                } else if (Objects.equals(value1, value2)) {
                    // Простые значения или разные типы (но равные по toString? Нет, equals проверяет типы)
                    // Если дошли сюда, значит либо оба простые, либо один сложный, другой простой, но они не равны по equals.
                    // equals для разных типов вернет false.
                    // Значит, если equals вернул true, они оба простые и равны.
                    nodes.add(new Node(key, Node.Status.UNCHANGED, value1, value2));
                } else {
                    // Простые значения не равны, или один сложный, другой простой (и не равны)
                    nodes.add(new Node(key, Node.Status.CHANGED, value1, value2));
                }
            }
        }
        return nodes;
    }

    private static boolean isComplexType(Object obj) {
        return obj instanceof Map || obj instanceof List;
    }
}