# Cellular Automata & Genetic Algorithms
*Chris Dudley*

## Cellular Automata

A Cellular Automata are systems which use a set of rules to simulate the behaviour of a set of cells. In this implementation, a 1 dimensional Automaton is set up and run.

Here is a brief example of a 5 cell automaton:

<table>
  <tr>
  <td> 1 </td>
  <td> 0 </td>
  <td> 1 </td>
  <td> 0 </td>
  <td> 1 </td>
  <td> 0 </td>
  </tr>
</table>

To simulate a "tick" of the simulation, each cell is updated based on a given number of its neighbours. In the case of one neighbour, let us consider the central cell. Its "neighbourhood" is as follows:

<table>
  <tr>
    <td> 0 </td>
    <td> 1 </td>
    <td> 0 </td>
  </tr>
    <td> L1 </td>
    <td> i </td>
    <td> R1 </td>
</table>

Where *i* is the central cell, and L1 and R1 are the left and right neighbours respectively.

Let us consider the following rules table, with the relevant rule in bold:

| L1 | i | R1 | rule |
|:---: |:---:|:---:|:---:|
| 0 | 0 | 0 | 1 |
| 0 | 0 | 1 | 0 |
| **0** | **1** | **0** | **0** |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 0 |

This means that when the automaton is updated, the cell will take a value of 0; it is "turned off". This is done for each cell in the automaton.

In this application, the number of neighbours (neighbours wrap around the automaton if necessary), the initial state, number of ticks, and rule table can all be customised.

## Genetic Algorithms

A genetic algorithm is a program that will try to reproduce an evolutionary system to obtain the "best" set of variables for the system.

In the context of a cellular automaton, the set of variables, known as the genotype, would be represented by the rule table of the automaton. In this implementation, the goal is to develop an automaton that can categorise the density of cells in the initial condition. For example:

| Initial State | Final State |
| --- | --- |
| 1111100000 | 1111111111 |
| 1000000000 | 0000000000 |

The genetic algorithm runs a set of genotypes, and ranks them based on their performance, measured by the number of discrepancies in the expected final state and the actual final state after 100 ticks of the automaton.

The two best performing genotypes are used to create an offspring genotype, using a Uniform Crossover approach; each "gene" of the genotype is chosen from the parent based on a probability. This new offspring replaces one of the genotypes in the lower half of the ranked genotypes. This process is then repeated.

## How to use

To run this application, navigate to the `CellularAutomataNB/src` folder of the repository in your command line. Compile the java files (Note you may need to define the necessary environment path).

Once compiled, run the `MasterSimulation` class.

You will be prompted to select which simulation you want to run:

* Cellular Automata; selected by entering "ca"
* Genetic Algorithm; selected by entering "ga"

Once selected, you can tailor the simulations by entering the following when prompted:

* Cellular Automata:
  * The initial state of your automaton, as a string of 1's and 0's. The size will be inferred form this input.
  * The number of neighbours to consider for the rule set. Note that using more than 1 neighbour will lead to exponentially larger rule tables, since there are 2<sup>2n + 1</sup> rules to consider.
  * The outcomes for each rule. You will first be shown the incomplete rule table with placeholder values. Type the desired content of the "rule" column as a string of 1's and 0's.
  * The number of ticks the simulation should run for.

* Genetic Algorithm:
  * The number of genotypes to use. Using more gives you a better pool, but can increase simulation times.
  * The number of trials to run each genotype for. Using more trials will test a genotype over multiple initial conditions, and allow an average error rate to be calculated. Again, more trials means a more sophisticated process, but a slower one.
  * The number of generations to run. At the end of each generation, the best genotypes are used to create an offspring that will replace one of the worst genotypes.

For each of these user inputs, input validation is in place to catch erroneous inputs.


**Thank you for having a look at this implementation of a Cellular Automaton and Genetic Algorithm. Please report any errors and feel free to comment & criticise!**
