<h1 align="center">MyPokemonApp</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://app.circleci.com/pipelines/github/reyghifari/MyPokemonApp/12/workflows/cb71d02e-0bc7-496b-a2dd-9bb5bb168c9b/jobs/11"><img alt="Build Status" src="https://github.com/skydoves/Pokedex/workflows/Android%20CI/badge.svg"/></a> <br>
</p>

<p align="center">  
MyPokemonApp demonstrates modern Android development with Koin, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design 3 based on MVVM architecture.
</p>
</br>

## Open API

MyPokemonApp using the [PokeAPI](https://pokeapi.co/) for constructing RESTful API.<br>
PokeAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Pokémon.

### Continuous Integration

MyPokemonApp using Circle ci, Is a service to create continuous integration. Interestingly, apart from using your own server, you can also use the hosting that has been provided. For configuration you can also use YAML. In addition, there is also a ready-to-use docker image. This service can also be directly integrated with Github and Bitbucket quickly

![architecture](readphoto/circleci.png)

## Architecture
**MyPokemonApp** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

![architecture](readphoto/figure0.png)

### UI Layer

![architecture](readphoto/figure2.png)

### Data Layer

![architecture](readphoto/figure3.png)

### Encryption

Implement encryption on Room Database with SQLCipher on MyPokemonApp.

![architecture](readphoto/encription.png)


