# Infecto : A Computational Simulation of Virus Transmission

## Introduction

This is a simulation of how a virus spread over the population. The simulation is heavily inspired by the SIR model.
We have three different states of each agent (a person & virus). 
The first state is SUSCEPTIBLE, second one is SICK, and the last one is RECOVERED. We have also a DEAD state in this simulation.

## Technologies Used
* Repast Simphony
* Java
* Trello

## Screenshots
Network Flow Diagram: Defining the connection between the virus and those humans it infected over time.
![alt text](https://github.com/itsmekaran/simulation/blob/master/screenshots/1.png?raw=true)

Grid  View: A 2D grid plot of population with healthy humans plotted in each grid depending on the number of humans taken into consideration.
![alt text](https://github.com/itsmekaran/simulation/blob/master/screenshots/2.png?raw=true)

Graph Plot: A graph plot defines flow of healthy humans(Blue) and infected humans(Red) on the basis of affected humans per ticks.
![alt text](https://github.com/itsmekaran/simulation/blob/master/screenshots/3.png?raw=true)

## Future Scope
* Refine the system by adding public places like hospitals, shopping complex  and home and its effects on simulation
* Adding the concept of social distancing and self isolation in flattening the curve.
* Evolve the agents (both virus and human) to simulate second phase of infection
