###

In this project, I implement a multi-threaded simulation of a public transit system. In this simulation, passengers will ride trains between stations, boarding and deboarding (getting on and off) to complete their journey. Your simulation will generate a log showing the movements of passengers and trains

This simulation is multi-threaded, with a thread for each passenger and each train. That means if the simulation is run multiple times, a different results may result depending on the scheduler. To make testing and debugging easier, I built a verifier that checks that the simulation result is sensible, e.g., passengers can only deboard trains at the stations the trains are at, trains must move along their lines in sequence, etc
