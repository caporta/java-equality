public class Equality {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public static void explore(String assertion, boolean result) {

    StringBuilder sb = new StringBuilder();
    if (result) {
      sb.append(String.format("%sYAY!%s", ANSI_GREEN, ANSI_RESET));
    } else {
      sb.append(String.format("%sWHAT?!?!%s", ANSI_RED, ANSI_RESET));
    }
    sb.append("   ");
    sb.append(assertion);
    if (!result) {
      sb.append(" (Your assumption is incorrect)");
    }

    System.out.println(sb.toString());

  }

  public static void main(String[] argv) {

    int n1 = 12;
    int n2 = 12;
    explore("Primitives can be compared using double equals.", n1 == n2);

    Object obj1 = new Object(); //different location in memory than obj2
    Object obj2 = new Object(); //different location in memory than obj1
    //explore("Objects work just like primitives", obj1 == obj2);
    explore("Objects use double equals to check reference to the same object in memory, so two objects that seem equal may not be.",
             obj1 != obj2);
    Object obj3 = obj1; //same location in memory as obj1
    explore("Object references must refer to the same location in memory to effectively use double equals.",
             obj1 == obj3);

    String str1 = "Hodor";
    String str2 = "Hodor";
    //explore("Strings are objects and work like objects", str1 != str2);
    //the Java compiler optimizes the creation of string literals by creating a single instance of said string
    //and makes all variables refer to that single instance. This is called "String Interning",
    //and the compiler keeps these references in the "String Pool".
    explore("Disparate string literals are actually referring to the same objects.",
             str1 == str2);
    String str3 = new String("Hodor"); //using a constructor instead of a literal creates a new object in memory
    explore("String objects that contain the same characters but point to different objects cannot use double equals.",
             str1 != str3);
    explore("String Interning adds to the same String Pool where literals live, so you get back the same reference.",
             str1 == str3.intern());

    explore("All objects should use the equals() method to check for equality.",
             str1.equals(str3));

  }
}
