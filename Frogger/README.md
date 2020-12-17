# COMP2042 Frogger

Prerequisites
- Java15
- Gradle

Javadocs can be found in Frogger.build.docs.javadoc folder

> Due to word limit, this README focuses mainly on what and why refactoring/additions are done. Please refer to Javadocs of mentioned classes for more details on how it's done. Titles and additional formatting are used to make this more presentable, thus leading to slightly exceeding word count. Excluding formatting and disclaimers before this sentence, word count is only 499.


## General Refactoring  
  
### categorize files and paths  
- categorize files in packages  
- extract resource file paths into `FilePath` class for easy modification  
  
### improve encapsulation  
- fields, methods of all classes (except `FilePath`) are private/protected, with public getter and setter methods where required.   
  
### introduction of constants  
- replace hardcoded values with private static final fields / methods, e.g. hardcoded speed in original collision check replaced by `getSpeed()`  
  
###  use lambda expressions & method referencing  
- improve code readability  
- `GameController`, `World`, button classes etc  
  
  
## Specific Refactoring  
  
### `Animal` -> `PlayerAvatar`  
The original `Animal` makes redundant calls to inefficient code in `AnimationTimer` to check/handle out of bounds, collision and death animation logic  
  
#### Changes  
-  _optimized out of bounds check_  
Only needs to be checked when player changes its position thus it is called in `move()`.  
  
-   _improved collision detection_  
Compared to original method which gets objects from different classes and checks for intersection, the improved method first checks for ANY intersection of `NPC` with the given `PlayerAvatar`, then only the identify its subclass and handle accordingly. This code is moved to `World`to be carried out at the same time as the animation.  
  
- _key event handlers moved to `World`_  
enable different key control options and improve code reusability   
  
- _simplified movement logic_  
used Boolean flag `keypress` to combine the original code for key press and release (DRY)  
  
- _extract death animation logic_  
create `DeathAnimation`, following single responsibility principle ([SRP](#srp-and-singleton))   
  
## Introducing Design Patterns  
  
### Factory  
`Lane` acts as `NPC` factory  
  
### MVC - Decouple UI from logic (see also [SRP](#srp-and-singleton))  so either can be easily replaced.   
- Increases maintainability and reduce code redundancy since model can have multiple views (one-to-many).   
- Example:   
  
|     Model     |      View     |        Controller             |  
| ------------- |:-------------:|:-----------------------:|  
|      `GameModel`    |  `GameScreen` |     `GameController`    |  
  
The above also implement [Observer](#Observer) pattern.   
  
Other models/views/controllers can be found:  
  
- ##### `src/main/java/model`  
- ##### `src/main/java/view`  
- ##### `src/main/resources/view` - ##### `src/main/java/controller`   
  
### Observer  
- reduce the need for boolean flag  
- eliminates dependencies of concrete `Subject` on the concrete `Observer`.   
  
Implemented in 2 ways:  
- `Subject` and `Observer` interface, since `java.util.Observer` and `java.util.Observable` are deprecated. Example: `GameModel`(subject), `PlayerAvatar`(subject), `GameView`(observer)  
  
- Alternatively, interested classes can use predefined methods to add listeners to fields. See `addLifeListener()` and `addScoreListener()` in `PlayerAvatar`.  
  
### SRP and Singleton  
Following SRP, `util` classes such as `AudioPlayer` and `HighScoreFile` are introduced. The `util` classes and some `Controller` classes are also Singletons because only one instance is needed per JVM and it must be accessible by other classes at a well-known access point.   
  
### Template Original `Actor` is refactored to be extended by `PlayerAvatar` and `NPC`.   
Template pattern is used to increase code reusability, decrease redundancy. We can easily make different:   
  
- characters -- extend `PlayerAvatar` / `NPC` - levels -- extend `LevelTemplate` - animations -- extend `SpriteAnimation`  
- enum variations of NPC -- implement `NPCType`   
### Prototype  
`NPC` implements this with abstract `clone()` to be implemented by its subclasses. This method is used in `Lane` to duplicate `NPC` objects without knowing its type  
  
  
## Additions  
- 10 levels, generation of high score file upon completion of all levels.  
- Info screen, selection screen for controls (WASD, arrow keys),  progress screen
- Volume slider, life bar, sound effects, pause, play, back to menu buttons  
- Swamp animation (crocodiles, flies)