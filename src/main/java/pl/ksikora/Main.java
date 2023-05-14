package pl.ksikora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Krystian", "Mikolaj", "Oliwia", "Jakub");
        Greeter greeter = new Greeter();
        greeter.greet("Krystian"); // -> Hello Krystian

        List<String> ladies = new ArrayList<String>();
        for (String name: names) {
            if (name.endsWith("a")) {
                ladies.add(name);
            }
        }

        System.out.println("----------------------------------------");
        names.stream()
                .filter(name -> name.endsWith("a"))
                .forEach(greeter::greet);

    }
}
