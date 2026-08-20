# Mobile UI Automation Project

Проект автоматизации мобильного тестирования Android-приложения Wikipedia.

Автотесты реализованы на Java с использованием Appium и JUnit 5.  
Удалённый запуск мобильных тестов выполняется на реальных устройствах BrowserStack.  
Для CI используется Jenkins, результаты тестирования формируются в Allure Report.

## Technology Stack

- Java 17
- JUnit 5
- Appium
- BrowserStack
- Owner
- Allure Report
- Gradle
- Jenkins
- Git
- GitHub

## Implemented Tests

В проекте реализованы следующие мобильные UI-тесты:

1. Открытие экрана поиска Wikipedia
2. Поиск статьи в Wikipedia
3. Открытие статьи из результатов поиска

Тесты запускаются на Android-устройстве в BrowserStack.

## Project Structure

```text
src/test
├── java
│   ├── config
│   │   └── MobileConfig.java
│   ├── drivers
│   │   └── BrowserstackDriver.java
│   ├── screens
│   │   └── WikipediaScreen.java
│   ├── tests
│   │   └── WikipediaTests.java
│   └── utils
│       └── Attach.java
│
└── resources
    └── config
        └── browserstack.properties
```

## BrowserStack

Мобильные тесты выполняются удалённо с использованием BrowserStack App Automate.

Основные параметры устройства задаются в:

```text
src/test/resources/config/browserstack.properties
```

Пример конфигурации:

```properties
device=Google Pixel 7
osVersion=13.0
app=bs://...
```

Для авторизации используются переменные окружения:

```text
BROWSERSTACK_USERNAME
BROWSERSTACK_ACCESS_KEY
```

Секретные значения не хранятся в исходном коде проекта.

При запуске в Jenkins приложение предварительно загружается в BrowserStack, после чего полученный `app_url` передаётся тестам через переменную:

```text
BROWSERSTACK_APP_URL
```

## Local Test Run

Для локального запуска необходимо задать переменные окружения BrowserStack:

```text
BROWSERSTACK_USERNAME
BROWSERSTACK_ACCESS_KEY
```

После этого тесты можно запустить командой:

```bash
./gradlew clean test
```

Для Windows PowerShell:

```powershell
.\gradlew clean test
```

## Jenkins CI

Проект интегрирован с Jenkins.

При запуске сборки Jenkins:

1. получает исходный код проекта из GitHub;
2. использует BrowserStack credentials из Jenkins Credentials;
3. загружает APK в BrowserStack;
4. получает актуальный `app_url`;
5. запускает мобильные тесты через Gradle;
6. формирует Allure Report.

Успешная CI-сборка выполняет все 3 мобильных теста в BrowserStack.

## Allure Report

Для формирования отчётов используется Allure.

В отчёте отображаются:

- результаты выполнения тестов;
- шаги тестов;
- Owner;
- Severity;
- тег `mobile`;
- Screenshot;
- Page Source.

Allure Report автоматически формируется после выполнения тестов в Jenkins.

## Test Results

На текущей конфигурации:

```text
3 tests
3 passed
100% successful
```

Тесты успешно выполняются локально и через Jenkins на BrowserStack.

## Allure Attachments

После выполнения теста в Allure автоматически добавляются:

- Screenshot — скриншот состояния приложения;
- Page Source — XML-структура текущего экрана.

Это позволяет анализировать состояние приложения после выполнения теста.

## Run

Запуск всех тестов:

```bash
./gradlew clean test
```

Проверка компиляции тестового проекта:

```bash
./gradlew clean testClasses
```