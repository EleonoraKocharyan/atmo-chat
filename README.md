# Atmo-Chat

## Introduction

Hi and welcome! What you see is a project making its first steps into the world of chat applications. Atmo-Chat is in intense development stage, so keep up with new upcoming features. The goal is to create lightweight and fast chat application that can suit anyone's needs.  

## Technologies Used

 - [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [Atmosphere Framework](https://github.com/Atmosphere/atmosphere)
 - [MongoDB](https://www.mongodb.com/)
 - [Gradle](https://gradle.org/) 
 - [Swagger2](https://swagger.io/)
 - [Atmosphere Javascript](https://github.com/Atmosphere/atmosphere-javascript) 

## How to get Started

 - Make sure you have installed Java8 and MongoDB. [Here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) is the download link for Java and [here's](https://docs.mongodb.com/manual/administration/install-on-linux/) MongoDB setup tutorial for Linux based Operating Systems.
 - Import the project to IDE of your choice (Eclipse, Intellij)
 


## How to create a group chat

If you managed to run the application successfully than you can proceed to following steps

 - **Add users to your chat application**
 You can add users through [your swagger](http://localhost:8090/swagger-ui.html)  in save-controller -> saveUser, by entering user's id and name.
 - **Create a group chat**
 You can create a group chat through [your swagger](http://localhost:8090/swagger-ui.html)  in save-controller -> saveChatRoom, by entering the name of the group and the ids of users, that were created in the previous step. As a result you'll get the ID of your chat room as a response.

## How to use the created group chat
Now everything is set and all that's left to do is to open http://localhost:8090/ , enter the room ID, enter the user's ID and chat. 
> You can open different browsers and connect as different users to feel the full experience of a group chat. As simple as that!
![enter image description here](https://scontent.fevn1-2.fna.fbcdn.net/v/t1.15752-9/47578606_2348864601822450_8196887012163190784_n.png?_nc_cat=106&_nc_ht=scontent.fevn1-2.fna&oh=ab7a729b219f258415a607c765a6011a&oe=5C933C09)

