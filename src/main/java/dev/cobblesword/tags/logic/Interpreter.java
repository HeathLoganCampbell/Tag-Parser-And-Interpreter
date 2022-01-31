package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.Node;

import java.util.ArrayList;
import java.util.List;

public class Interpreter
{
    public static List<Taggable> extractMatchingTags(Node node, List<Taggable> allEntities)
    {
        List<Taggable> filteredEntities = new ArrayList<>();

        for (Taggable taggableEntity : allEntities)
        {
            if (node.match(taggableEntity))
            {
                filteredEntities.add(taggableEntity);
            }
        }

        return filteredEntities;
    }
}
