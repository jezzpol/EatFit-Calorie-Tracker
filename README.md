# Описание проекта

## Приложение для отслеживания калорий. Позволяет устанавливать цели, отслеживать потребление пищи в течение дня и года, рассчитывать количество потребленных калорий, а также определять, укладываетесь ли вы в установленные для себя показатели потребления калорий.

# Инструкция для запуска

Шаг 1:

    git clone https://github.com/jezzpol/EatFit-Calorie-Tracker

Шаг 2:

    cd EatFit-Calorie-Tracker

Шаг 3:

    Создайте файл .env и заполните следующие данные:
    DB_NAME=
    DB_USERNAME=
    DB_PASSWORD=
    DB_URL=
    DB_HOST=postgres


Шаг 3:

    docker-compose up -d

Загрузка образа Docker может занять некоторое время. После успешной сборки образов и запуска контейнеров вы можете перейти по адресу
http://localhost:8080/swagger-ui/index.html#/, чтобы попасть на главную страницу сайта.

<img width="1440" height="784" src="https://github.com/user-attachments/assets/ef6fab87-a54b-456a-a2d4-3ec2a2db521e" />
