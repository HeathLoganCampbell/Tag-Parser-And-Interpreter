# `Tag-Parser-And-Interpreter`
Basically allow an easy way to filter out lists for a user
without giving them too much control.

This allows you to tag entites with infomation and filter it out with a string
for example

The List with Entities with tags
```
Sheep : Tags { 4legs, wool, farm }
Cow : Tags { 4legs, milk, farm }
Chicken : Tags { 2legs, egg, farm }
Dog : Tags { 4legs, fur, farm, pet }
```


#### Example 1
Query
``` 
4legs
```

Result
```
Sheep, Cow, Dog
````

#### Example 2
Query
``` 
4legs & pet
```

Result
```
Dog
````

#### Example 3
Query
``` 
(4legs & pet) | egg
```

Result
```
Dog, Chicken
````

## How it works
1. Convert infix notation to postfix notation in a stack with Shunting-yard algorithm
2. use the stack we have now produced and execute each op recursively to generate an AST  
3. loop thru our entity list and each if each one matches our tree structure

## How to use
Attach the `Taggable` interface to any class you would like to search on