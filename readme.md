# Calc FP Sample

## Intro

The following readme guide you through this repository.

You will dive into some experiences of me to getting a better understanding of FP in Scala.

I'll solve a simple issue, calculation of a "buisness case" and provide different interfaces for the user.


## Project itself

This application will have 3 kind of runnables in the end.

### CLI

The cli build will give you the interface of the console. You will be able to do different calculations and you will be able to correct your input before start the calculation.

### GUI

The gui build will show up you a graphical interface written in [scalafx](https://github.com/scalafx/scalafx).

You will be able to use it in a smooth and intiutive way. (TODO)

### API

The api build provide a web interface and a frontend to the backend implementation for calculation.

> The frontend will not be a pretty full implemented productive ready ui! It should just provide a form in simple html.

## Architecture

Every build use in base the [00-core](./00-core/package.md) package. It contains only the core buisness function "calcluation".

In [10-port-secondary](./10-port-secondary/package.md) you will find the `FormState` definition. Which provide a stable api internally.

[20-port-primary](./20-port-primary/package.md) contain the program routine itself. Here is the buisness case implemented. It will interact with the `FormState` via a given instance.

A real implementation of the secondary port is locate in [30-adapter-secondary](./30-adapter-secondary/package.md). Here is the api of `FormState` implemented.

The presentation to the user as an interface is in

- [40-adapter-primary-cli](./40-adapter-primary-cli/package.md)
- [41-adapter-primary-gui](./41-adapter-primary-gui/package.md)
- [42-adapter-primary-api](./42-adapter-primary-api/package.md)

(TODO)