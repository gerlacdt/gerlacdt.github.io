// java 1.4 and before

for (int i = 0; i < list.size(); i++) {
    String s = (String) list.get(i);
    // do something with list item...
}

// java 1.5  (8 year later)

for (String s : list) {
    // do something with list item
}


// 2 dates in a domain class
Calendar scheduledArrivalTime;
Calender expectedArrivalTime;

Calendar cal = calculateArrivalTime()  // --> Calender.getInstance()

// set arrivalTimes
scheduledArrivalTime = cal
expectedArrivalTime = cal;


// after some calculations......


// somewhere in the code: 2 minutes delay for expectedArrivalTime
expectedArrivalTime.add(Calendar.MINUTE, 2);

// ???

// happy debugging :)
// old moovel backend ~ 200,000 lines of code
REST-Layer   --> Object Mapper  --> Rest Object
|
|
Service Layer (Stateless Session Bean)   --> some Util class
|
|
DAO Layer
|
|
Object Factory ---> Domain object
|
|
HTTP Gateway Layer
