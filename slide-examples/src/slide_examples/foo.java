// java 1.4 and before

for (int i = 0; i < list.size(); i++) {
    String s = (String) list.get(i);
    // do something with list item...
}

// java 1.5  (8 year later)

for (String s : list) {
    // do something with list item
}
