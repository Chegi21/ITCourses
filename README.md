# IT Курсы — Android приложение

Тестовое задание. Многомодульное Android-приложение на Java с Clean Architecture.

## Стек технологий

|Категория|Технология|
|-|-|
|Язык|Java 17|
|HTTP клиент|Retrofit 2 + OkHttp (MockInterceptor читает JSON из assets)|
|База данных|Room (избранные курсы)|
|DI|ServiceLocator в App + ViewModelProvider.Factory|
|Архитектура|MVVM + Clean Architecture|
|Навигация|Jetpack Navigation Component|
|Реактивность|AndroidX LiveData|
|Список|RecyclerView + AdapterDelegates4|
|Верстка|XML layouts|

## Структура модулей

```
ITCourses/
├── app/                      # App (ServiceLocator), LoginActivity, MainActivity, навигация, ресурсы
     ├── adaper/             
     ├── view model/         
     ├── view model factory/
     └── fragment/           

├── domain/                   # Course, UseCase-ы, интерфейс CourseRepository
├── data/                     # Retrofit, MockInterceptor, Room, CourseRepositoryImpl, CourseMapper
```

## Архитектура слоёв

```
Presentation (Fragment + ViewModel)
        ↕  \[UseCase]
Domain (Course, UseCase, интерфейс CourseRepository)
        ↕  \[Implementation]
Storage (CourseRepositoryImpl → Retrofit / Room)
```

### DI без Koin

`App.java` играет роль ServiceLocator — создаёт все зависимости один раз при старте:

* `OkHttpClient` с `MockInterceptor`
* `Retrofit` → `CourseApi`
* `AppDatabase` → `FavoriteCourseDao`
* `CourseRepositoryImpl` (singleton)

Фрагменты получают репозиторий через `App.getInstance().getCourseRepository()`
и передают его в `ViewModelFactory`, который создаёт ViewModel с нужными UseCase.

## Экраны

### Вход

* Email: маска `текст@текст.текст`, кириллица заблокирована через `InputFilter`
* Кнопка "Войти" активна только при корректных обоих полях (`MediatorLiveData`)
* ВКонтакте → `https://vk.com/`, Одноклассники → `https://ok.ru/`
* Любой корректный email + непустой пароль → вход

### Главный экран

* Курсы из `assets/courses.json` через Retrofit + `MockInterceptor`
* Сортировка по убыванию `publishDate`
* `hasLike = true` → зелёная закладка; клик → Room (добавить/удалить)
* Поиск и фильтр — нефункциональные заглушки
* Описание: maxLines=2, ellipsize=end

### Избранное

* LiveData из Room, обновляется реактивно
* Пустое состояние — текст

### Профиль — заглушка

