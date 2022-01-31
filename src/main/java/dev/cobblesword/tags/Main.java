package dev.cobblesword.tags;

import dev.cobblesword.tags.dummy.Animal;
import dev.cobblesword.tags.node.BinaryOp;
import dev.cobblesword.tags.node.Identifier;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.op.AndOp;
import dev.cobblesword.tags.node.op.OrOp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        List<Animal> animals = new ArrayList<>();

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

        String selector = "(4legs | 2legs)";

        Node node = stringToAST(selector);

        List<Animal> filteredAnimals = extractMatchingTags(node, animals);

        // 2
        System.out.println(filteredAnimals.size());
    }

    public static List<Animal> extractMatchingTags(Node node, List<Animal> allAnimals)
    {
        List<Animal> filteredAnimals = new ArrayList<>();
        for (Animal animal : allAnimals)
        {
            if (node.match(animal))
            {
                filteredAnimals.add(animal);
            }
        }

        return filteredAnimals;
    }

    public static Node stringToAST(String selector)
    {
                Stack<String> executionStack = new Stack<>();
                Stack<String> operatorStack = new Stack<>();

                String buffer = "";
                for (int i = 0; i < selector.length(); i++)
                {
                    char c = selector.charAt(i);

                    if( c == '(' )
                    {
                        operatorStack.add("(");
                        continue;
                    }

                    if( c == '&' || c == '|' )
                    {
                        executionStack.add(buffer.trim());
                        buffer = "";
                    }

                    if( c == ')')
                    {
                if(buffer.length() > 0)
                {
                    executionStack.add(buffer.trim());
                    buffer = "";
                }
            }

            if( c == ')' )
            {
                while (true)
                {
                    if(operatorStack.isEmpty()) break;
                    String pop = operatorStack.pop();
                    if (pop != "(")
                    {
                        executionStack.add(pop);
                    }
                }
            }


            if( c == '&' || c == '|')
            {
                operatorStack.add("" + c);
                continue;
            }

            buffer += c;
        }


        Node processedNode = process(executionStack, executionStack.pop());
        return processedNode;
    }

    public static Node process(Stack<String> executionStack, String currentOp)
    {
        Node currentNode = null;
        if(currentOp.equals("&") || currentOp.equals("|"))
        {
            if(currentOp.equals("&")) currentNode = new AndOp();
            if(currentOp.equals("|")) currentNode = new OrOp();

            ((BinaryOp) currentNode).setRight(process(executionStack, executionStack.pop()));
            ((BinaryOp) currentNode).setLeft(process(executionStack, executionStack.pop()));
            return currentNode;
        }

        if(currentOp.equals("!"))
        {
        }

        return new Identifier(currentOp);
    }
}
