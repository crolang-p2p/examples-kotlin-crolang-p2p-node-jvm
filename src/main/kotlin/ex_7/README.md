# Example 7: Disconnecting from the Broker
## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Involved Files](#involved-files)
- [Example Overview](#example-overview)
- [Expected Output](#expected-output)
- [Running the example](#running-the-example)

## Learning Objectives

This example shows how to:
- Disconnect the local node from the Broker using the dedicated method [`CrolangP2P.kt.disconnectFromBroker()`].
- Verify that P2P communication between two already connected nodes remains active even after disconnecting from the Broker.

## Involved Files

- Ex_7_Alice.kt: Alice's logic to establish the P2P connection, disconnect from the Broker, and continue communicating with Bob.
- Ex_7_Bob.kt: Bob's logic to receive messages and reply to Alice.
- Constants.kt: Common constants (IDs, broker address).

## Example Overview

- Alice and Bob both initially connect to the Broker.
- Alice establishes a direct P2P connection with Bob and sends a message.
- Alice disconnects from the Broker using the method [`CrolangP2P.kt.disconnectFromBroker(onSuccess, onError)`].
- Despite the disconnection from the Broker, Alice and Bob can continue exchanging messages through the already established P2P channel.

## Expected Output

During execution, you will see output similar to the following (truncated for brevity):
```
Connected to Broker at http://localhost:8080 as Bob
Connected to Broker at http://localhost:8080 as Alice
Connected successfully to Node Alice
Disconnecting from Broker...
Is local Node connected to the Broker: false
Connected successfully to Node Bob
Disconnecting from Broker...
Is local Node connected to the Broker: false
[COUNT_CHANNEL][Bob]: 0
[COUNT_CHANNEL][Alice]: 1
[COUNT_CHANNEL][Bob]: 2
[COUNT_CHANNEL][Alice]: 3
...
[COUNT_CHANNEL][Bob]: 18
[COUNT_CHANNEL][Alice]: 19
[COUNT_CHANNEL][Bob]: 20
Counter threshold exceeded, disconnecting from Node Bob
Disconnected from Node Bob
Disconnected from Node Alice
```

This demonstrates that after both nodes disconnect from the Broker, they continue to exchange messages over the P2P channel until the counter threshold is reached and the P2P connection is closed.

## Running the example
### 1: Start Node Bob

In the project root, run:

```sh
./gradlew runEx7Bob
```

### 2: Start Node Alice

In a separate terminal, run:

```sh
./gradlew runEx7Alice
```
