package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class PlainFormatter {

    public static String format(List<Node> diffTree) {
        StringBuilder result = new StringBuilder();
        buildFormattedString(diffTree, result, ""); // Начинаем с пустого пути
        // Убираем последний символ новой строки, если он есть
        if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    private static void buildFormattedString(List<Node> nodes, StringBuilder result, String parentPath) {
        for (Node node : nodes) {
            String currentKey = node.getKey();
            String fullPath = parentPath.isEmpty() ? currentKey : parentPath + "." + currentKey;
            Node.Status status = node.getStatus();

            switch (status) {
                case Node.Status.ADDED:
                    result.append(String.format("Property '%s' was added with value: %s%n", fullPath, formatValue(node.getValueAfter())));
                    break;
                case Node.Status.REMOVED:
                    result.append(String.format("Property '%s' was removed%n", fullPath));
                    break;
                case Node.Status.CHANGED:
                    result.append(String.format("Property '%s' was updated. From %s to %s%n", fullPath, formatValue(node.getValueBefore()), formatValue(node.getValueAfter())));
                    break;
                case Node.Status.UNCHANGED:
                    // Не выводим неизмененные свойства в формате plain
                    break;
                case Node.Status.NESTED:
                    // Рекурсивно обрабатываем вложенные изменения
                    buildFormattedString(node.getChildren(), result, fullPath);
                    break;
            }
        }
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            // Обычно строковые значения в plain оборачивают в кавычки, но в примере они без.
            // Следуя примеру: Property 'key2' was added with value: 'value2'
            // и Property 'setting3' was updated. From true to 'none'
            // Видим, что 'value2' и 'none' - строки, но 'none' без кавычек, 'value2' с кавычками.
            // В примере вывода для 'none' кавычки есть: Property 'setting3' was updated. From true to 'none'
            // А в примере кода: value: 'value2'. Видимо, строка всегда в кавычках.
            // Проверим: 'Some value' -> 'Another value', 'none' -> 'none' (в примере вывода). Да, строка в кавычках.
            // Но в примере кода: value: 'value2'. Тоже строка в кавычках.
            // И в примере вывода: From 'Some value' to 'Another value'.
            // И в примере вывода: From true to 'none'.
            // Вывод: строку нужно оборачивать в кавычки.
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof java.util.Map) {
            // Для составных значений (объектов, массивов) выводим [complex value]
            return "[complex value]";
        } else {
            // Для остальных типов (числа, boolean) просто toString
            return value.toString();
        }
    }
}
