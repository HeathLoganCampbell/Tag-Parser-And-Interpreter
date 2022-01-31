package dev.cobblesword.tags;

import dev.cobblesword.tags.dummy.Animal;
import dev.cobblesword.tags.logic.Evaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * proof of concept: Complex tagging system,
 * lets say you have multi dimensional data points
 * such as farm animals and you want to easily query them
 * from a big list of farm animals. but you want to enable
 * complex pattern matching such as
 *
 * I want all the farm animals with 4 legs
 * (farm & 4legs)
 *
 * I want animals with milk or wool
 * (milk | wool)
 *
 * I want all farm animals without 4 legs
 * (!4legs)
 */
public class Main
{
    public static void main(String[] args)
    {
        List<Taggable> animals = new ArrayList<>();

        Animal sheep = new Animal();
        sheep.addTag("wool");
        sheep.addTag("4legs");
        sheep.addTag("farm");
        animals.add(sheep);

        Animal cow = new Animal();
        cow.addTag("milk");
        cow.addTag("4legs");
        cow.addTag("farm");
        animals.add(cow);

        Animal chicken = new Animal();
        chicken.addTag("egg");
        chicken.addTag("2legs");
        chicken.addTag("farm");
        animals.add(chicken);

        Animal dog = new Animal();
        dog.addTag("fur");
        dog.addTag("4legs");
        dog.addTag("woof");
        dog.addTag("pet");
        animals.add(dog);

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs | 2legs)", animals);

        // 2
        System.out.println(filteredAnimals.size());
    }
}
