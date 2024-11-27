# Тестирование веб-приложения

*   Тестирование веб-приложения сайта https://stellarburgers.nomoreparties.site.
*   Документация к API https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf

## Задание

### Регистрация

* Успешная регистрация.
* Ошибка для некорректного пароля. Минимальный пароль — шесть символов.

### Вход

* Вход по кнопке «Войти в аккаунт» на главной,
* Вход через кнопку «Личный кабинет»,
* Вход через кнопку в форме регистрации,
* Вход через кнопку в форме восстановления пароля.

### Переход в личный кабинет

* Переход по клику на «Личный кабинет».

### Переход из личного кабинета в конструктор

* Переход по клику на «Конструктор» и на логотип Stellar Burgers.

### Выход из аккаунта

* Выход по кнопке «Выйти» в личном кабинете.

### Раздел «Конструктор»

* Переходы к разделам:
  * «Булки»,
  * «Соусы»,
  * «Начинки».

## Используемые библиотеки

*   **JUnit**: для написания и запуска тестов
*   **Selenium**: для тестирования пользовательского интерфейса веб-приложения
*   **Allure**: для генерации отчетов о тестировании
*   **Rest-Assured**: для тестирования RESTful API
*   **Gson**: для работы с JSON-данными
*   **JavaFaker**: для генерации фейковых данных для тестирования
