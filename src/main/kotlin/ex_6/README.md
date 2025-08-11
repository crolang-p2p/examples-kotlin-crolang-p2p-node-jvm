# Example 6: Retrieving nodes currently connected to your local node
## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Involved Files](#involved-files)
- [Example Overview](#example-overview)
- [Expected Output](#expected-output)
- [Running the example](#running-the-example)
## Learning Objectives

In previous examples, access to a connected node instance was always provided through callback parameters (e.g., `onConnectionSuccess`, `onNewMsg`). This meant you could only interact with a node within the scope of those callbacks. In this example, you learn a more generic and flexible way: you can retrieve any node currently connected to your local node at any time, using `getConnectedNode` or `getAllConnectedNodes`, regardless of how or when the connection was established.

This example shows how a node can:
- Retrieve a node that is currently connected to your local node by its ID using `getConnectedNode(nodeId)`.
- Retrieve all nodes that are currently connected to your local node using `getAllConnectedNodes()`.
- Interact with these directly connected nodes (e.g., send messages) at any point in your code, not just inside callbacks.
- Benefit from a decoupled, callback-independent way to manage and interact with connections.

## Involved Files

- Ex_6_Alice.kt: Node Alice logic for retrieving and interacting with nodes currently connected to her.
- Ex_6_Bob.kt: Node Bob logic (connects and listens for messages).
- Ex_6_Carol.kt: Node Carol logic (connects and listens for messages).
- Constants.kt: Common constants (IDs, broker address).

## Example Overview

- Bob and Carol each connect to the Broker and allow incoming connections/messages.
- Alice connects to the Broker and establishes P2P connections to Bob and Carol.
- Alice uses:
    - `getConnectedNode(BOB_ID, onResult)` to check if Bob is currently connected to her and send a message if so.
    - `getAllConnectedNodes(onResult)` to retrieve all nodes currently connected to her and send a message to Carol if connected.
- The example demonstrates both targeted and bulk retrieval of nodes that are directly connected to your local node (not just those connected to the broker), and shows how this approach is more flexible than relying solely on callback parameters.

## Expected Output

When Bob and Carol are connected to Alice, Alice will see output like:
```
Connected to Broker at http://localhost:8080 as Alice
Node Bob is connected
Node Carol is connected
```

Bob and Carol will see messages like:
```
Received a message on GREETINGS_CHANNEL from Node Alice: Hello Bob!
Received a message on GREETINGS_CHANNEL from Node Alice: Hello Carol!
```

## Running the example
1. **Start Bob and Carol**
    - In two separate terminals, from the root of the example project, run:
      ```sh
      ./gradlew runEx6Bob
      ./gradlew runEx6Carol
      ```
2. **In a separate terminal, run**:
   ```sh
   ./gradlew runEx6Alice
   ```
