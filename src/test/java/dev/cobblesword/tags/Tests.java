package dev.cobblesword.tags;

import dev.cobblesword.tags.dummy.Animal;
import dev.cobblesword.tags.logic.Evaluator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests
{
    @Test
    public void singleSelectorArg()
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

        Assert.assertTrue(filteredAnimals.contains(sheep));
        Assert.assertTrue(filteredAnimals.contains(cow));
        Assert.assertTrue(filteredAnimals.contains(dog));

        Assert.assertFalse(filteredAnimals.contains(chicken));
    }

    @Test
    public void noRepeatingEntries()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs | woof)", animals);

        Assert.assertEquals(3, filteredAnimals.size());

        Assert.assertTrue(filteredAnimals.contains(sheep));
        Assert.assertTrue(filteredAnimals.contains(cow));
        Assert.assertTrue(filteredAnimals.contains(dog));

        Assert.assertFalse(filteredAnimals.contains(chicken));
    }

    @Test
    public void orSelector()
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

        Assert.assertEquals(4, filteredAnimals.size());

        Assert.assertTrue(filteredAnimals.contains(sheep));
        Assert.assertTrue(filteredAnimals.contains(cow));
        Assert.assertTrue(filteredAnimals.contains(dog));
        Assert.assertTrue(filteredAnimals.contains(chicken));
    }

    @Test
    public void andSelector()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs & woof)", animals);

        Assert.assertEquals(1, filteredAnimals.size());

        Assert.assertTrue(filteredAnimals.contains(dog));

        Assert.assertFalse(filteredAnimals.contains(sheep));
        Assert.assertFalse(filteredAnimals.contains(cow));
        Assert.assertFalse(filteredAnimals.contains(chicken));
    }

    @Test
    public void andSelectorNoMatchFound()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs & egg)", animals);

        Assert.assertEquals(0, filteredAnimals.size());

        Assert.assertFalse(filteredAnimals.contains(dog));
        Assert.assertFalse(filteredAnimals.contains(sheep));
        Assert.assertFalse(filteredAnimals.contains(cow));
        Assert.assertFalse(filteredAnimals.contains(chicken));
    }

    @Test
    public void and3Selector()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(4legs & farm & milk)", animals);

        Assert.assertEquals(1, filteredAnimals.size());

        Assert.assertTrue(filteredAnimals.contains(cow));

        Assert.assertFalse(filteredAnimals.contains(dog));
        Assert.assertFalse(filteredAnimals.contains(sheep));
        Assert.assertFalse(filteredAnimals.contains(chicken));
    }

    @Test
    public void or3Selector()
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

        List<Taggable> filteredAnimals = Evaluator.evaluate("(egg | wool | milk)", animals);

        Assert.assertEquals(3, filteredAnimals.size());

        Assert.assertTrue(filteredAnimals.contains(cow));
        Assert.assertTrue(filteredAnimals.contains(sheep));
        Assert.assertTrue(filteredAnimals.contains(chicken));

        Assert.assertFalse(filteredAnimals.contains(dog));
    }
}
