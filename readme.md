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

