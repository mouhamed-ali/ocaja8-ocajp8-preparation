package com.certification.ocp.maps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapDemo2 {

    public static void main(String[] args) {

        Person david = new Person("David", 38, 50.000);
        Person michel = new Person("Michel", 12, 100.000);
        Person sara = new Person("Sara", 84, 150.000);
        Person mano = new Person("Mano", 27, 200.000);

        Map<Person, Double> personsMap = Stream.of(david, michel, sara, mano).collect(
                Collectors.toMap(Function.identity(), Person::getFortune)
        );  // returns a hashmap

        System.out.printf("David fortune is : %f%n", personsMap.get(david));
        System.out.printf("Michel fortune is : %f%n", personsMap.get(michel));
        System.out.printf("Sara fortune is : %f%n", personsMap.get(sara));
        System.out.printf("Mano fortune is : %f%n", personsMap.get(mano));
        // The result is ok, we get the good fortune each time we call the hashmap but what about this

        System.out.printf(" ---------------------------------- %n");

        System.out.printf("David fortune is : %f%n", personsMap.get(new Person("David", 38, 50.000)));
        // the result id null because we did not override the hashCode and equals methods, try to uncomment only the equals method
        // and re-run the program
        // the result is the same, because hashmap will compare hashcode at first if they are different it will not compare equals methods
        // it will pass to the next object instead
        // to make the example works uncomment the equals and hashCode methods


        System.out.printf(" ---------------------------------- %n");

        personsMap.keySet().forEach(person ->
                System.out.printf("The hash of %s is \t: (key : %10d , bucket : %d)%n", person.getName(), person.hashCode(), hash(person.hashCode())&15));

    }
    // I get this method from HashMap class so i can get the hash function used by it
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Person{
        String name;
        Integer age;
        Double fortune;
        /*
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return Objects.equals(getName(), person.getName()) && Objects.equals(getAge(), person.getAge()) && Objects.equals(getFortune(), person.getFortune());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getAge(), getFortune());
        }
        */
    }
}
