# Вычислитель отличий (Gendiff)

**Gendiff** — это консольная утилита на Java, предназначенная для определения и отображения разницы между двумя файлами конфигурации или структурами данных в форматах JSON и YAML.

## Hexlet tests and linter status:
[![Actions Status](https://github.com/Someloseyouth/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Someloseyouth/java-project-71/actions)
![CI](https://github.com/Someloseyouth/java-project-71/actions/workflows/ci.yml/badge.svg)


## Github Actions and SonarQube status:
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Someloseyouth_java-project-71&metric=coverage)](https://sonarcloud.io/dashboard?id=Someloseyouth_java-project-71)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Someloseyouth_java-project-71&metric=alert_status)](https://sonarcloud.io/dashboard?id=Someloseyouth_java-project-71)

## Возможности

*   **Сравнение файлов:** Сравнивает два файла в форматах JSON или YAML.
*   **Поддержка вложенных структур:** Корректно обрабатывает вложенные объекты и массивы.
*   **Множественные форматы вывода:** Поддерживает различные форматы отображения результата:
    *   `stylish` (по умолчанию) — показывает разницу в виде древовидной структуры с индикаторами (`+`, `-`, ` `).
    *   `plain` — выводит разницу в виде плоского списка изменений.
    *   `json` — сериализует внутреннее представление разницы в формате JSON.
*   **Интеграция с CI/CD:** Проект настроен с использованием Gradle, включает проверки стиля кода (Checkstyle), тестирование (JUnit) и анализ качества кода (SonarCloud).

## Установка

1.  Убедитесь, что у вас установлены Java 21 и Gradle.
2.  Клонируйте репозиторий:
    ```bash
    git clone <https://github.com/Someloseyouth/java-project-71>
    cd java-project-71/app # или имя вашей корневой папки проекта
    ```
3.  Соберите проект:
    ```bash
    ./gradlew installDist
    ```

## Использование

Запустите исполняемый скрипт из папки `build/install/app/bin/`:

```bash
./build/install/app/bin/app [OPTIONS] filepath1 filepath2
```

### Опции

*   `-f`, `--format FORMAT` — Устанавливает формат вывода результата (`stylish`, `plain`, `json`). По умолчанию используется `stylish`.
*   `-h`, `--help` — Показывает справку по использованию.
*   `--version` — Показывает версию программы.

### Примеры

Сравнение файлов с использованием формата по умолчанию stylish:

```bash
./build/install/app/bin/app filepath1.json filepath2.json
```

[![asciicast](https://asciinema.org/a/3YG8r9LVf9GYfjzQJVNWuVwmv.svg)](https://asciinema.org/a/3YG8r9LVf9GYfjzQJVNWuVwmv)

```bash
./build/install/app/bin/app filepath1.yaml filepath2.yaml
```

[![asciicast](https://asciinema.org/a/Jcfr6DEXF1K4dmbOgDvEB3L3a.svg)](https://asciinema.org/a/Jcfr6DEXF1K4dmbOgDvEB3L3a)

Cравнение файлов с использованием формата plain:

```bash
./build/install/app/bin/app --format plain json1.json json2.json
```

[![asciicast](https://asciinema.org/a/z1u0UQ54XvDUpyoZWejGVTzYe.svg)](https://asciinema.org/a/z1u0UQ54XvDUpyoZWejGVTzYe)

Сравнение файлов с вложенными структурами формат stylish:

```bash
./build/install/app/bin/app json1.json json2.json
```

[![asciicast](https://asciinema.org/a/34UQ9k1abrDvdK7gDvUoyqHBV.svg)](https://asciinema.org/a/34UQ9k1abrDvdK7gDvUoyqHBV)

Сравнение файлов с использованием формата json:

```bash
./build/install/app/bin/app -f json yaml1.yaml yaml2.yaml
```

[![asciicast](https://asciinema.org/a/QLljwTC9svZ7N6iKT4rJf3W9L.svg)](https://asciinema.org/a/QLljwTC9svZ7N6iKT4rJf3W9L)
