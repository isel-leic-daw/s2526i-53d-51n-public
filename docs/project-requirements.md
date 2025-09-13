# Project for the 2025/2026 Fall Semester

## Introduction

The goal of the project is the development of a multi-user Poker Dice Game system.
This system will be composed by a _backend component_ and a _frontend component_.

The _backend component_ will be comprised by:
- A PostgreSQL Relational Database Management System (RDBMS), responsible for storing all the system's durable data, including the users and game state.
- A JVM-based application, responsible for:
    - Exposing an HTTP API, used by the _frontend component_, with all the functionality provided by the system.
    - Interacting with the RDBMS.
    - Ensuring all the domain requirements, namely data mutation integrity and access control.
- Other required sub-components, such as load balancers.

The _frontend component_ is comprised by an application that will run on the end user browsers and will be responsible for:
- Presenting a user interface.
- Interacting with the HTTP API exposed by the _backend component_, in order to implement all the functionalities of the system.

The system should also support _frontend components_ running on different platforms, such as Android or iOS.

## Requirements

The project requirements are based on Option C of the Mobile Devices Programming [Practical Assignment](https://github.com/isel-leic-pdm/2526i/blob/main/assignments/PDM-2526-1_Option_C.pdf).

### Users and authentication

The system will be used by multiple users, requiring user authentication for most of the provided functionalities.
User authentication will be based on _usernames and passwords_. 
Authenticated session management will be based on the use of _authentication tokens_.
These tokens can be carried via the HTTP `Authorization` header or via cookies, depending on the type of _frontend application_.

User _registration_ will be based on one-time use invitations, created by other users.
There will be a way to bootstrap user registration, when no user was yet created.

### Poker Dice Game

- The Poker Dice Game uses _poker dices_, which are dices with six faces containing: an Ace, a King, a Queen, a Jack, the number 10, and the number 9.
- A _hand_ is a _set_ of five dice faces. 
- A _match_ is played by two or more _players_.
- Matches are set up using a _lobby_. 
    - An user can either create a new lobby or join an existing one. The creator of a lobby is designated by the lobby host.
    - A lobby consists of an identifier, a name, a short description and all relevant match setup information, including the 
number of rounds and the number of expected players.
    - A match starts once the lobby:
        - Has the maximum amount of players.
        - Or has at least the minimum amount of players and a timeout as elapsed.
    - Lobbies that are not yet full are visible to all players, who can freely choose which one to enter. 
    - Players can leave the lobby at any time. If the host leaves the lobby and the match has not yet started, the lobby is closed and all other players are notified. 
- A match is played over the number of specified _rounds_.  
    - Each round consists of as many turns as the number of players, one per player.
    - Each round begins with all players paying an _ante_ (for example, 1 coin) from their _balance_ in order to participate. 
    - On their turn, players start by _rolling_ all five dice and may, up to two times, hold any subset and re-roll the remaining dices, for a maximum of three rolls per turn. 
    - At the end of the rolls, the player’s final dice faces define the players hand. 
    - Once all players have taken their turn, their hands are compared and the highest-ranking hand wins the round. 
    - The winner collects the total amount of coins paid as antes, and all balances are updated accordingly. 
    - The next round then begins, with the starting player alternating between rounds. 
    - Players who cannot pay the ante are excluded from the new round. 
    - The match ends when all rounds are completed, or earlier if only one player can afford to pay the ante. 

- _hands_ are ranked in descending order of strength as follows: 
    - Five of a Kind - five faces with the same value.
    - Four of a Kind - four faces with the same value.
    - Full House - three faces with the same value and two faces with the same value. 
    - Straight - a contiguous sequence of five faces. 
    - Three of a kind - three faces with the same value,  
    - Two Pairs of faces with the same value,  
    - One Pair of faces with the same value,  
    - and finally Bust (no combination, hand rank determined by the highest dice face). These rankings adhere to the traditional poker-style hierarchy adapted for dice-based gameplay, with Five of a Kind placed above all other hands and excluding Flushes.

### User interface

The user interface provided by the _frontend application_ should have the following screens:

- Registration - used to register into the system.
- Login - used start an authenticated session.
- Logout - used to end an authenticated session.
- Invitation - used to create an invitation code for other users.
- Home - providing links to all other screens.
* Lobbies - lists available lobbies.
* Lobby Creation - creates a new lobby, configuring the match settings, such as name, number of players, and number of rounds.
* Lobby - lists waiting players.
* Match - play turns, rounds and announce results.
* About - information regarding the game and the application authors.
* Player Profile - local player info and statistics.

### Phased development and delivery

The project will be developed and delivered in two phases:
- The first phase delivery is the _backend component_, and should be delivered until 2025-10-19.
- The second phase delivery is the _frontend component_, and should be delivered until 2025-12-07. During the second phase changes and improvements can be made to the _backend component_.
