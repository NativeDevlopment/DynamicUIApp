Android Assignment 
=====================================

Introduction
-------------------------------------
This app is given as a task to Build an app to render a custom UI. The app will have 2 modules:
Network Module - Create the Jar/Aar file
App Modules - render dynamic ui from Api.

Requirements with comments in line :
● The minimum SDK supported for this application is API 21.
● The app is written strictly in Kotlin programming language.
● Error handling.

● There are 2 screens one render Ui from Api and Second to show Details on next Screen.
● All the data is loaded from the provided API calls.

● Images are cached efficiently.
● API Requests/data loading and showing is done in a way to give the user the best experience.
    Kept in mind the JSON provided might have some data missing in some instances, 
    so these cases are handled gracefully to provide a good UX
● Responsive to all phone sizes.
● Used version control system (Git)

## Implemented things
Mvvm Architecture
view design is using via `Jetpack compose`
Repository is written in `Kotlin` and is based on `MVVM(ViewModel, LiveData)`
Dependency Injection is implemented using `Koin`
SOLID design principles are followed religiously - The Project is separated in app(presentation), network layer
Network layer used Repository Design pattern



### Libraries
* [Android Support Library][support-lib]
* [Jetpack compose][compose]
* [Android Architecture Components][arch]
* [Koin][koin] for dependency injection
* [Coroutine][coroutine] coroutine for concurrency and background process call
* [Retrofit][retrofit] for REST api communication


