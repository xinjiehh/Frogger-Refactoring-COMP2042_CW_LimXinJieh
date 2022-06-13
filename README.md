# Frogger-Refactoring-COMP2042_CW_LimXinJieh

Prerequisites
- IntelliJ (after importing project, go to File -> Invalidate cache and restart)
- Java 15 (required to run project in cmd)
- Gradle (must be added to system variables to run project in cmd)

Javadocs can be found in `Frogger.build.docs.javadoc`
  

## Additions  
- 10 levels, generation of high score file (2 versions), random sprite generation
- Info screen, selection screen for controls,  progress screen (FXML and Java)
- Volume slider, life bar, sound effects, pause, play and back to menu buttons  
- Swamp animation (crocodiles, flies)
- JUnit testing, gradle build

## Fixes
- Fix bug in the original code where a new frog will respawn and jump forward at the same time as current frog entering the swamp, causing unnecessary death
- Match the color of the turtle background with game background

## General Refactoring  
  
### categorize files and paths  
- categorize files in packages  
- extract resource file paths into `FilePath` class  
  
### improve encapsulation  
- fields, methods of classes are private/protected and/or final, with public getter and setter methods where required.   
  
### introduction of constants and algorithms
- replace hardcoded values with private static final fields / methods, e.g. hardcoded speed in original collision check replaced by `getSpeed()`  
  
###  use lambda expressions & method referencing  
- improve code readability  (`GameController`, `World`, button classes etc)
  
  

## Specific Refactoring  
  
### `Animal` -> `PlayerAvatar`  
The original `Animal` makes redundant calls to inefficient code in `AnimationTimer` to check/handle out of bounds, collision and death animation logic  
  
#### Changes  
-  _optimized out of bounds check_  
Only needs to be checked when player changes its position thus called in `move()`.  
  
-   _improved collision detection_  
Compared to original method which gets objects from different classes and checks for intersection, the improved method first checks for ANY intersection of `NPC` with the given `PlayerAvatar`, then only the identify its subclass and handle accordingly. This code is moved to `World` to be carried out at the same time as the animation.  
  
- _key event handlers moved to `World`_  
enable different key control options, improve code reusability   
  
- _simplified movement logic_  
Boolean flag `keypress` to combine original code for key press and release (DRY)  
  
- _extract death animation logic_  
create `DeathAnimation` ([SRP](#srp-and-singleton))   
  



## Introducing Design Patterns  

### Factory  
- `Lane` acts as `NPC` factory  
  


### MVC 
- Decouple UI from logic (also [SRP](#srp-and-singleton))  so either can be easily replaced.   
- Increases maintainability and reduce code redundancy since model can have multiple views (one-to-many).   
- Example:   
  
|     Model     |      View     |        Controller             |  
| ------------- |:-------------:|:-----------------------:|  
|      `GameModel`    |  `GameScreen` |     `GameController`    |  
  
The above also implement [Observer](#Observer) pattern.   
  

Other models/views/controllers can be found:  
  
- ##### `src/main/java/model`  
- ##### `src/main/java/view`  
- ##### `src/main/resources/view` 
- ##### `src/main/java/controller`   
  


### Observer  
- reduce need of constantly checking state and boolean flag since changes will be updated in the subscribed Observer classes automatically
- eliminates dependencies of concrete `Subject` on concrete `Observer`.   
  
	Implemented in 2 ways:  
	- `Subject` and `Observer` interface, since `java.util.Observer` and `java.util.Observable` are deprecated. Example: `GameModel`(subject), `PlayerAvatar`(subject), `GameView`(observer)  
  
	- Alternatively, interested classes can use predefined methods to add listeners. See `addLifeListener()` and `addScoreListener()` in `PlayerAvatar`.  
  


### SRP and Singleton  
Following SRP, `util` classes such as `AudioPlayer` and `HighScoreFile` are introduced. Some `util` and `Controller` classes are also Singletons because only one instance is needed per JVM and it must be accessible by other classes at a well-known access point.   
 


### Template 
Original `Actor` is refactored to be extended by `PlayerAvatar` and `NPC`.   
Template pattern is used to increase code reusability, decrease redundancy. Can easily make different:   
  
- characters -- extend `PlayerAvatar` / `NPC` 
- levels -- extend `LevelTemplate` 
- animations -- extend `SpriteAnimation`  
- enum variations of NPC -- implement `NPCType`  


 
### Prototype  
`NPC` implements this with abstract `clone()` to be implemented by subclasses. This method is used in `Lane` to duplicate `NPC` objects without knowing its type.
