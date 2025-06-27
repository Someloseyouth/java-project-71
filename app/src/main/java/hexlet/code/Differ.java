package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Читаем файлы и парсим в Map
        Map<String, Object> map1 = objectMapper.readValue(Files.readString(Paths.get(filepath1)), Map.class);
        Map<String, Object> map2 = objectMapper.readValue(Files.readString(Paths.get(filepath2)), Map.class);

        // Все ключи из двух файлов
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : allKeys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);

            Object val1 = map1.get(key);
            Object val2 = map2.get(key);

            if (inFirst && !inSecond) {
                // Удалено во втором
                result.append(String.format("  - %s: %s%n", key, val1));
            } else if (!inFirst && inSecond) {
                // Добавлено во втором
                result.append(String.format("  + %s: %s%n", key, val2));
            } else {
                // Есть в обоих
                if (Objects.equals(val1, val2)) {
                    // Без изменений
                    result.append(String.format("  %s: %s%n", key, val1));
                } else {
                    // Значения отличаются
                    result.append(String.format("  - %s: %s%n", key, val1));
                    result.append(String.format("  + %s: %s%n", key, val2));
                }
            }
        }

        result.append("}");
        return result.toString();
    }
}

