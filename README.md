## Games VR API
Projeto responsável por expor APIs RESTFul para manipular usuários, terapeutas e seus pacientes em uma dinâmica de jogos.

## Motivação
Terapeutas que utilizam jogos VR em tratamento de pessoas com problema de mobilidade, precisam definir rotinas e ordens para o pacientes executarem os jogos, logo este projeto foi proposto como uma solução para auxiliar os terapeutas a propor ordem de execução dos jogos e analisar o desenvolvimento dos mesmos em um determinado tempo. 

## Build status
Build status of continus integration i.e. travis, appveyor etc. Ex. - 

[![Build Status](https://travis-ci.org/akashnimare/foco.svg?branch=master)](https://travis-ci.org/akashnimare/foco)
[![Windows Build Status](https://ci.appveyor.com/api/projects/status/github/akashnimare/foco?branch=master&svg=true)](https://ci.appveyor.com/project/akashnimare/foco/branch/master)

## Code style
If you're using any code style like xo, standard etc. That will help others while contributing to your project. Ex. -

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat)](https://github.com/feross/standard)
 
## Screenshots
![Games VR API](https://raw.githubusercontent.com/japanett/clashAPI/master/app_v2.png)

## Stack
| Tecnologia | Versão |
| ---        | ---    |
| Java       | 11     |
| Gradle     | 5      |
| Spring-boot| 2.1.3 
| java-jwt | 3.4.0 |
| java-jwt | 2.3.0 |
| lombok | |
| mapstruct-jdk8 | 1.2.0 |

## Features
Uso de tecnologias web em java para auxílio na área de saúde

## Installation
1. Instalar java 11
2. Instalar postman
3. Instalar ultima versao disponivel do eclipse IDE
4. Instalar lombok no eclipse IDE
5. Instalar Mysql 
6. Criar database gamesvr no mysql
7. Mudar senha do usuario root do mysql para root
8. Instalar gradle
9. Instalar gradle no eclipse IDE
10. Acessar pasta raiz do projeto e rodar gradle build
11. Acessar o projeto no eclipse e rodar o gradle
	- Caso o eclipse mostre erro de baixar o gradle a causa pode ser o local em que esta salvo o gradle no sistema, um workaround seria:
		- Acessar o site [http://services.gradle.org/distributions/](http://services.gradle.org/distributions/)
		- Baixar a sua versão de gradle
		- Colar na pasta GRADLE_HOME/wrapper/dist
		- Remover o projeto do eclipse
		- Fechar e abrir novamente o eclipse
		- Importar o projeto novamente

## API Reference

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

## Tests
Describe and show how to run the tests with code examples.

## How to use?
If people like your project they’ll want to learn how they can use it. To do so include step by step guide to use your project.

## Contribuição
Para contrbuir com o projeto é possível entrar em contato com os autores do projeto ou abrir uma discussão no github apontando o problema com imagem.

## Autores 
- Gabriel Kenzo
- Adriano Rapussi

## License
Universidade Presbiteriana Mackenzie © 