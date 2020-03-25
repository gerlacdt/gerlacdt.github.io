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

Calendar cal = getArrivalTimeFromRemote() // Calender.getInstance()

// set arrivalTimes
scheduledArrivalTime = cal
expectedArrivalTime = cal;


// many, many lines of code later......


// somewhere in the code: 2 minutes delay for expectedArrivalTime
expectedArrivalTime.add(Calendar.MINUTE, 2);

// ???

// happy debugging :)
