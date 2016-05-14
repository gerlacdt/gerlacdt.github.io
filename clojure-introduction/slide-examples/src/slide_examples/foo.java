// java 1.4 and before

for (int i = 0; i < list.size(); i++) {
    String s = (String) list.get(i);
    // do something with list item...
}

// java 1.5  (8 year later)

for (String s : list) {
    // do something with list item
}


// 2 dates in domain class
Calendar arrivalTime;
Calender expectedArrivalTime;

// get time from somewhere
Calendar cal = Calender.getInstance()// value of remote http call
int delay = 2; // delay 2 min

// set arrivalTime
arrivalTime = cal
expectedArrivalTime = cal;

// 2 minutes delay for expectedArrivalTime
expectedArrivalTime.add(Calendar.MINUTE, delay);

// ??? buggy

// happy debugging :)
// old moovel backend ~ 200,000 lines of code
REST-Layer   --> Object Mapper
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
