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

[![asciicast](https://asciinema.org/a/sKrINbNsMHFXSq8AsxK6le6ex.svg)](https://asciinema.org/a/sKrINbNsMHFXSq8AsxK6le6ex)

```bash
./build/install/app/bin/app filepath1.yaml filepath2.yaml
```

[![asciicast](https://asciinema.org/a/RC7gafwUc3wRzJJR41f3PTEnU.svg)](https://asciinema.org/a/RC7gafwUc3wRzJJR41f3PTEnU)

Cравнение файлов с использованием формата plain:

```bash
./build/install/app/bin/app -f plain filepath1.json filepath2.json
```

[![asciicast](https://asciinema.org/a/W33B6sb9PLfxCPuoM8jXEpr1a.svg)](https://asciinema.org/a/W33B6sb9PLfxCPuoM8jXEpr1a)

Сравнение файлов с вложенными структурами формат stylish:

```bash
./build/install/app/bin/app filepath1.json filepath2.json
```

[![asciicast](https://asciinema.org/a/WISJh5wO1GGqJqs2njRPuepMv.svg)](https://asciinema.org/a/WISJh5wO1GGqJqs2njRPuepMv)

Сравнение файлов с использованием формата json:

```bash
./build/install/app/bin/app --format json filepath1.json filepath2.json
```

[![asciicast](https://asciinema.org/a/mnb87oK7sFiTn7onZhPDjTxBM.svg)](https://asciinema.org/a/mnb87oK7sFiTn7onZhPDjTxBM)
