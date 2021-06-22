# ser316-summer2021-C-jrbruce1-DP

# Code-a-mon Simulator

This project is a simple Pokémon style simulation ran with java through the terminal.
It is built and tested with Gradle using JUnit4 tests, checkstyle and spotbugs.
This project is intended to reinforance good software design practices (whitebox and 
blackbox testing, etc.) and to introduce Design Patterns.

The design patterns that will be incorporated into this application include:

-Decorator Pattern: used to evolve a basic Code-a-mon into a more powerful Code-a-mon
with new operations and attributes. This retains all the functionality of the original
Code-a-mon while adding new functionality

-Singleton: This allows for a unique single instance of a special class: a legendary 
Code-a-mon. Only 1 can exist with only 1 chance to catch it.

-Memento: This returns an object to a previous state. This is how we will heal and cancel
out any buffs/debuffs/special traits, etc. Code-a-mon Centers allow a trainer to heal their
team up and remove any harmful traits.

Other Design Patterns from the Gang of Four may later be implemented but these are the initial 3
selected to build a well-rounded (if somewhat rudimentary) application.