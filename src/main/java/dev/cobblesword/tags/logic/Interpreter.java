package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.Node;

import java.util.ArrayList;
import java.util.List;

public class Interpreter
{
    public static List<Taggable> extractMatchingTags(Node node, List<Taggable> allAnimals)
    {
        List<Taggable> filteredAnimals = new ArrayList<>();
        for (Taggable taggableEntity : allAnimals)
        {
            if (node.match(taggableEntity))
            {
                filteredAnimals.add(taggableEntity);
            }
        }

        return filteredAnimals;
    }
}
