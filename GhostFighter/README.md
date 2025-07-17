# INF112 Project - GhostFighter Go

Team: (Gruppe 8-2) Martin S. Pedersen, Lyder Samnøy, Brage Merkesdal, Siren Bjorøy, Kristian B. E. Elmer, Marion M. Haugland

Built from a simple skeleton with [libGDX](https://libgdx.com/).

## Game Description

GhostFighter is a 2D adventure game where players explore an open world, collect power-ups, and engage in battles against wandering ghosts. Inspired by classic Pokémon mechanics, encounters with enemies trigger a dedicated battle scene where health points and strategy determine the outcome. Players must survive, gather hearts to maintain health, and defeat as many ghosts as possible. The game features dynamic camera movement, interactive terrain, and a simple fight system with increasing challenge.

## Documentation

### Obligs

* [Oblig1](docs/oblig1.md)
* [Oblig2](docs/oblig2.md)
* [Oblig3](docs/oblig3.md)
* [Oblig4](docs/oblig4.md)

### Meeting reports

*Stored as markdown file in [docs/meetingReports](docs/meetingReports/) with the following format: `meeting-<year>-<month>-<date>.md`*

Example: `meeting-25-01-31.md`

This is a simple way to store and view our reports chronologically , because the files are ordered alphabetically.

**Meeting reports should include:**

* Who attended
* What has been done since last meeting
* What should be done before next meeting

### Wiki

Personal wiki for our tools and workflow

* [Git](docs/wiki/git.md)

## Class diagram

Can be found in [docs/classDiagram](docs/classDiagram/classDiagram.png)

## Running

You can run the project with Maven using `mvn exec:java`.

running the program should open a window with a menu screen and a clickable "PLAY" button. Clicking the button should start the game and you should be able to move a player in a virtual world. Exit by closing the window or pressing the exit button in menu.

You may have to compile first, with `mvn compile` – or in a single step, `mvn compile exec:java`.

## Credits

* `src/main/resources/images/ui/dialogBox.png` - [Raphael Migaud (Pinterest)](https://no.pinterest.com/pin/783204191438031497/)
* `src/main/resources/music/LuigisMansionGhostTheme.mid` - [Link music (Author unknown)](https://bitmidi.com/luigis-mansion-main-theme-mid)
* `src/main/resources/music/LuigisMansionGhostTheme.mid` - [Link music (Author unknown)](https://bitmidi.com/luigis-mansion-main-theme-mid)

## License

This project is licensed under the MIT License.
