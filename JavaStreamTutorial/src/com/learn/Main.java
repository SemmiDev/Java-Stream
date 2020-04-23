package com.learn;

import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // imperative approach
        System.out.println("-------------- | IMPERATIVE APPROACH");
        List<Person> females = new ArrayList<>();
        for(Person person : people){
            if(person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        System.out.println("FEMALE : ");
        females.forEach(System.out::println);

        // declarative approuch


        // filter
        System.out.println("\n\n-------------- | FILTER");
        List<Person> male = people.stream()
                .filter(Person -> Person.getGender().equals(Gender.MALE))
                .collect(Collectors.toList());
        System.out.println("MALE");
        male.forEach(System.out::println);

        // sort
        System.out.println("\n\n-------------- | SORT");
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println("SORTING");
        sorted.forEach(System.out::println);

        // all match
        System.out.println("\n\n-------------- | ALL MATCH");
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 17);
        System.out.println(allMatch);

        // any match
        System.out.println("\n\n-------------- | ANY MATCH");
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 95);
        System.out.println(anyMatch);

        // none match
        System.out.println("\n\n-------------- | NONE MATCH");
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equalsIgnoreCase("Sammi ALdhi Yanto")); // false
        System.out.println(noneMatch);

        // max
        System.out.println("\n\n-------------- | MAX");
        people.stream()
                // check age max
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // min
        System.out.println("\n\n-------------- | MIN");
        people.stream()
                // check age min
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // group
        System.out.println("\n\n-------------- | GROUP");
        Map<Gender,List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        groupByGender.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        }));

        // check umur tertua jenis kelamin LAKI-LAKI
        System.out.println();
        Optional<String> oldestMaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestMaleAge.ifPresent(System.out::println);
    }



    public static List<Person> getPeople(){
        return List.of(
            new Person ("Sammi ALdhi Yanto",18,Gender.MALE),
            new Person ("Abdul Rauf",20,Gender.MALE),
            new Person ("Ayatullah Ramadhan Jacoeb",19,Gender.FEMALE),
            new Person ("Dandi Arnanda",22,Gender.FEMALE),
            new Person ("Gusnur",100,Gender.MALE),
            new Person ("Aditya Andika Putra",21,Gender.FEMALE)
        );
    }
}
