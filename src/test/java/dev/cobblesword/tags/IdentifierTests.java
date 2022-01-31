package dev.cobblesword.tags;

import dev.cobblesword.tags.dummy.Animal;
import dev.cobblesword.tags.logic.Evaluator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IdentifierTests
{
    @Test
    public void singleSelector()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs)", animals);

        Assert.assertEquals(3, filteredAnimals.size());
    }
}
