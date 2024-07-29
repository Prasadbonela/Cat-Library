package com.epam.autotasks;

import java.util.*;
import java.util.stream.Collectors;

public class CatLibrary {

    public static final String EMPTY_STRING = "";

    public Map<String, Cat> mapCatsByName(List<Cat> cats) {
        return cats.stream()
                .filter(cat -> cat.getName() != null && !cat.getName().isEmpty()) // Ensure names are not null or empty
                .collect(Collectors.toMap(
                        Cat::getName,
                        cat -> cat,
                        (existing, replacement) -> existing)); // Handle duplicate names
    }

    public Map<Cat.Breed, Set<Cat>> mapCatsByBreed(List<Cat> cats) {
        return cats.stream()
                .filter(cat -> cat.getBreed() != null) // Ensure breed is not null
                .collect(Collectors.groupingBy(
                        Cat::getBreed,
                        Collectors.toSet()));
    }

    public Map<Cat.Breed, String> mapCatNamesByBreed(List<Cat> cats) {
        return cats.stream()
                .filter(cat -> cat.getBreed() != null && cat.getName() != null && !cat.getName().isEmpty()) // Ensure breed and name are not null or empty
                .collect(Collectors.groupingBy(
                        Cat::getBreed,
                        Collectors.mapping(Cat::getName, Collectors.joining(", ", "Cat names: ", "."))));
    }

    public Map<Cat.Breed, Double> mapAverageResultByBreed(List<Cat> cats) {
        return cats.stream()
                .filter(cat -> cat.getBreed() != null && cat.getContestResult() != null) // Ensure breed and contest result are not null
                .collect(Collectors.groupingBy(
                        Cat::getBreed,
                        Collectors.averagingDouble(cat -> cat.getContestResult().getSum())));
    }

    public SortedSet<Cat> getOrderedCatsByContestResults(List<Cat> cats) {
        return cats.stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparingInt(cat -> -cat.getContestResult().getSum())))); // Sort by contest result in descending order
    }
}
