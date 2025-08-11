# Example 4: Different ways of establishing P2P connections

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Involved Files](#involved-files)
- [Example Overview](#example-overview)
- [Connection Modes](#connection-modes)
- [Callbacks](#callbacks)
- [Sub-examples](#sub-examples)
    - [4A: Single node, asynchronous](#4a-single-node-asynchronous)
    - [4B: Multiple nodes, asynchronous](#4b-multiple-nodes-asynchronous)
- [Running the examples](#running-the-examples)

## Learning Objectives

- Learn how to connect to a single node or multiple nodes in both modes.
- Explore all the optional callbacks available for customizing node behavior during the P2P connection lifecycle.

## Involved Files

- `Ex_4_A_Alice.kt`: Alice implementation for single asynchronous node connection.
- `Ex_4_B_Alice.kt`: Alice implementation for multiple asynchronous nodes connection.
- `Ex_4_Bob.kt`: Bob implementation (shared across all sub-examples).
- `Ex_4_Carol.kt`: Carol implementation (shared across all sub-examples).
- `Constants.kt`: Common constants (IDs, broker address).

## Example Overview

All Alice implementations in this example aim to achieve the same goal: establish a P2P connection with Bob and/or Carol, exchange messages, and handle connection events using callbacks. The difference lies in how the connection is performed:
- 4A: Single node, asynchronous
- 4B: Multiple nodes, asynchronous

Bob and Carol have a single implementation each, as their logic is valid for all four scenarios.

The examples also illustrate all the optional callbacks that can be set when establishing connections; these callbacks allow you to customize the behavior of your node for every stage of the P2P connection lifecycle.

## Connection Modes

- **Asynchronous single node:** Initiate connection to one node and handle result via callbacks.
- **Asynchronous multiple nodes:** Initiate connections to multiple nodes in parallel and handle each result via callbacks.

## Callbacks

All connection methods support a rich set of optional callbacks, including:
- `onConnectionSuccess`: Called when a connection is successfully established.
- `onConnectionFailed`: Called when a connection attempt fails.
- `onDisconnection`: Called when a node disconnects.
- `onNewMsg`: Called when a message is received from a connected node.
- `onConnectionAttempt`: (for incoming connections) Called when another node attempts to connect.

You can provide only the callbacks you need, allowing for flexible and minimal implementations or full control over the connection lifecycle.

## Sub-examples

### 4A: Single node, asynchronous
Alice initiates a non-blocking connection to Bob. The result is handled via callbacks, allowing Alice to perform other tasks while waiting for the connection. This is ideal for applications where you want to continue other work while waiting for the connection to complete.

### 4B: Multiple nodes, asynchronous
Alice initiates non-blocking connections to both Bob and Carol in parallel. Each connection's result is handled independently via callbacks. This is useful for scalable or event-driven applications where you want to manage multiple connections concurrently.

## Running the examples
1. **Start Bob and Carol**
    - In two separate terminals, from the root of the example project, run:
      ```sh
      ./gradlew runEx4Bob
      ./gradlew runEx4Carol
      ```
2. **Start Alice for the desired sub-example**
    - In a new terminal, from the root of the example project, run one of:
      ```sh
      ./gradlew runEx4AAlice
      ./gradlew runEx4BAlice
      ```
