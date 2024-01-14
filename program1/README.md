# Stable matching

First, post an introduction on the course message board in the “Introductions” thread.

The main part of the assignment is to implement the Gale-Shapley algorithm for stable matching with an O(n2) running time. In our version, people will be adopting pets. Each person will adopt exactly one pet and each pet will be adopted by exactly one person. Both people and pets have preference lists that you will read from a file. The file will be formatted as follows:

- Line 1: Number of people/pets (n)
- Lines 2 to n+1: Names of people
- Lines n+2 to 2n+1: Preference lists of people using indices, not names (n preferences per line)
- Lines 2n+2 to 3n+1: Names of pets
- Lines 3n+2 to 4n+1: Preference lists of pets using indices, not name (n preferences per line)

Your code should implement the algorithm to be people-optimal (each person is given their best valid match). The file you read from must be named `program1data.txt`. Here is an example (also linked here Download linked here):

```shell
5
Archie
Betty
Clark
Deborah
Earl
2 1 4 5 3
4 2 1 3 5
2 5 3 4 1
1 4 3 2 5
2 4 1 5 3
Buster
Mittens
Princess
Fluffy
Felix
5 1 2 4 3
3 2 4 1 5
2 3 4 5 1
1 5 4 3 2
4 2 5 3 1
```

Your output should be pairs of names: person / pet (in the order the people are listed in the data file).

```shell
Archie / Buster
Betty / Princess
Clark / Mittens
Deborah / Felix
Earl / Fluffy
```
