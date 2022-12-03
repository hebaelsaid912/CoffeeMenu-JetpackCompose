# Coffee Menu
Coffee Menu App is an app for collecting most of coffee recipe in one app. this application using the API from https://sampleapis.com/api-list/coffee

[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

## Code Installation

you can clone code and run it using :

``
  IDE : Android Studio
  kotlinCompilerExtensionVersion: 1.2.0-rc02
  Compile Sdk : 33
``
## App Screen Recording

![Screenshot_2022-12-03-15-38-22-459_com hebaelsaid android coffeemenu_jetbackcompose](https://user-images.githubusercontent.com/72816466/205443723-adfc4fcc-c9fc-490d-8065-796f41f5033f.jpg)
![Screenshot_2022-12-03-15-38-32-503_com hebaelsaid android coffeemenu_jetbackcompose](https://user-images.githubusercontent.com/72816466/205443730-ee3b2b6a-0a03-4b3f-86a0-cc0b3b1c7ec0.jpg)
![Screenshot_2022-12-03-15-38-35-626_com hebaelsaid android coffeemenu_jetbackcompose](https://user-images.githubusercontent.com/72816466/205443732-962d4653-5081-4578-b163-120bc704dea8.jpg)
![Screenshot_2022-12-03-15-38-48-173_com hebaelsaid android coffeemenu_jetbackcompose](https://user-images.githubusercontent.com/72816466/205443740-9ac7923d-4218-4a09-9cc3-558fdb7054ed.jpg)
![Screenshot_2022-12-03-15-38-56-690_com hebaelsaid android coffeemenu_jetbackcompose](https://user-images.githubusercontent.com/72816466/205443746-cbad0cfd-e7db-4688-92e4-e4adbb117747.jpg)


## Architecture
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture. 
![1 9fa8VrWQyNtpxvgGXghMPQ](https://user-images.githubusercontent.com/72816466/202196876-39bb8b5d-aa81-4693-8a5e-b1b588133975.jpeg)

## Tech stack & Open-source libraries
- Minimum SDK level 26
- Kotlin based.
- Jetpack Compose.
- Android Gradle plugin requires Java 11 to run.
- StateFlow - emit state updates and emit values to consumers.
- ViewModel - UI related data holder, lifecycle aware.
- Coroutines for asynchronous.
- Architecture
    - MVVM Architecture ( DataBinding - ViewModel ).
    - Single Activity
       - multiple screens handled together using Navigation.
    - Repository Design Pattern
- Clean code, Use Cases
- Retrofit2 & Gson - construct the REST APIs.
- Dependency Injection (dagger-hilt).
