# Лабораторна робота №1 — Java та Data-Oriented Programming

## Опис проєкту
Модель сповіщень на основі `sealed interface` + `record` (незмінні дані) та окремий
клас-обробник `NotificationProcessor`, який через `switch expression` з pattern matching
(без `default`) формує текстовий звіт за допомогою Text Blocks.

Пакет: `ua.kpi.comsys.dop`

- `Notification` — sealed interface
  - `TextMsg(String sender, String text)`
  - `ImageMsg(String sender, String url, int sizeKb)`
  - `AudioMsg(String sender, int durationSeconds)`
- `NotificationProcessor.process(Notification)` — бізнес-логіка

## Розрахунок варіанта
Варіант: **V = 14 mod 3 = 2**.

## Запуск тестів
Потрібно: JDK 21+ та Maven.

mvn test

