
## Welcome to Adi's basic matcher


# how to run?
First you need make sure you have java v11 install in your computer.
In order to run the application:
- rename gloat-0.0.1-SNAPSHOT.jarrrrr to gloat-0.0.1-SNAPSHOT.jar 
- java -jar gloat-0.0.1-SNAPSHOT.jar
- open PostMan (or any other rest tool) in order to send to search match candidate by job or add new candidate.
-- Attached PostMan collection for either SearchCandidate / AddCandidate actions.
  
# Consideration: 
- candidate.title value always be in lower case
- skill.name first letter always in lower case
- case of "developer" or "engineer" are the only possible duplicate match.
