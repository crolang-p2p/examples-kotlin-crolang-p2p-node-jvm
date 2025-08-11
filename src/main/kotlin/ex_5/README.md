# Example 5: Checking if other Nodes are connected to the Broker
## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Involved Files](#involved-files)
- [Example Overview](#example-overview)
- [Expected Output](#expected-output)
- [Running the example](#running-the-example)

## Learning Objectives

This example shows how a node can:
- Check if a specific remote node (by ID) is connected to the Broker using `isRemoteNodeConnectedToBroker(nodeId)`. This is useful to verify the availability of a peer before attempting a P2P connection or for monitoring purposes.
- Check the connection status of multiple remote nodes at once using `areRemoteNodesConnectedToBroker(Set.of(nodeId1, nodeId2, ...))`. This allows you to efficiently monitor the presence of several peers in the network with a single call.
- Handle possible errors that may occur during these checks.

## Involved Files

- Ex_5_Alice.kt: Node Alice logic for checking the connection status of other nodes and handling possible errors.
- Ex_5_Bob.kt: Node Bob logic (simply connects to the Broker).
- Constants.kt: Common constants (IDs, broker address).

## Example Overview

- Bob connects to the Broker as usual.
- Alice connects to the Broker and uses the following methods:
    - `isRemoteNodeConnectedToBroker(BOB_ID, onResult, onError)` to check if Bob is connected.
    - `areRemoteNodesConnectedToBroker(Set.of(BOB_ID, CAROL_ID), onResult, onError)` to check the status of Bob and Carol.
- These methods can be used with any node ID or set of node IDs, not just Bob and Carol, to generically check the connectivity of any peer(s) in the network.
- Alice prints the results of these checks and handles any errors that may occur, printing appropriate error messages.

## Expected Output

When Bob is connected, Alice will see:
```
Is Bob connected to the Broker: true
Is Bob connected to the Broker: true
Is Carol connected to the Broker: false
```

## Running the example
### 1: Start Node Bob

In the project root, run:

```sh
./gradlew runEx5Bob
```

### 2: Start Node Alice

In a separate terminal, run:

```sh
./gradlew runEx5Alice
```
