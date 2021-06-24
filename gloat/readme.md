
## Welcome to Adi's basic matcher


# how to run?
First you need make sure you have java v11 install in your computer.
In order to run the application:
- first clone the repository
- run the main class application which called "MatcherApplication.java" using your favorite IDE
- open PostMan (or any other rest tool) in order to send to search match candidate by job or add new candidate.

*Attached PostMan collection (MatcherApplication/GloatMatcher.postman_collection.json) for either SearchCandidate or AddCandidate actions.
  
# Consideration: 
- candidate.title value always be in lower case
- skill.name first letter always in lower case
- case of "developer" or "engineer" are the only possible duplicate match.
