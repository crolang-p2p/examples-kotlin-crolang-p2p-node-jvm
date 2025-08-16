# Example 10: Sending Large Amount of Data over P2P
## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Important details](#important-details)
- [Involved Files](#involved-files)
- [Example Overview](#example-overview)
- [Expected Output](#expected-output)
- [Running the Example](#running-the-example)

## Learning Objectives

This example shows how:
- Send a P2P message containing an array of bytes instead of a simple string.
- Handling the callbacks for sending and receiving large messages transporting arrays of bytes.
- CrolangP2P overcomes the message size limitations of WebRTC (the underlying technology) by allowing the transfer of arbitrarily large strings between nodes.
- Large messages are automatically split into smaller chunks and reassembled on the receiving side, making the process transparent to the user.
- You can reliably send and receive very large payloads (e.g., files or long texts) over a P2P connection.
- **Note:** Physical machine limits still apply. If you attempt to send or receive data that exceeds the available RAM, the process may fail due to out-of-memory errors. Similarly, if many nodes send large amounts of data to the same node simultaneously (without the receiver having time to process them), memory exhaustion or performance issues may occur.

## Important details
- You don't necessarily need to send large messages to use the sendBytes method.
- The sendString method works in the same way under the hood (the strings gets converted to a byte array from the sender and back to a string from the receiver), but it has an important limitation: some targets (like javascript) will crash trying to convert the byte array back to a string if the message is larger than 100 MB.
- The performances while sending large messages vary depending on the target platform where the node is running. Javascript is slow while receiving large messages, but in a manageable way; the real problem is in the large data sending, which is very slow.
- As the example 11 will show, you can specify how much time to wait before a multipart message has a timeout, which can be important if you are planning to send large messages.

## Involved Files

- Ex_10_Alice.kt: Alice reads a ~100MB text file and repeats its content 10 times to simulate sending ~1GB of data to Bob over P2P.
- Ex_10_Bob.kt: Bob receives the large message and prints its size.
- Constants.kt: Common constants (IDs, broker address).
- large_file.txt: Example file containing ~100MB of text (due to GitHub limitations, files larger than 100MB cannot be included).

## Example Overview

- Alice reads the content of a large file (`large_file.txt`, ~100MB) from resources.
- To simulate a 1GB transfer, Alice repeats the file content 10 times, creating a string of nearly 1GB.
- Alice connects to the Broker and then to Bob via P2P.
- Alice sends the entire data as a single string message to Bob using the `LARGE_DATA_TRANSFER` channel.
- CrolangP2P automatically splits the message into chunks, sends them, and reassembles the message on Bob's side.
- Bob receives the message and prints the total number of bytes received, demonstrating that the transfer was successful and complete.

## Expected Output

During execution, you will see output similar to the following. Each line is prefixed with the node that prints it:

```
Alice: Reading large file...
Alice: File read successfully. Bytes: 99786570
Alice: Repeating file content 10 times to simulate ~1GB transfer
Alice: Total bytes to send: 997865700
Alice: Connected to Broker at http://localhost:8080 as Alice
Alice: Connected to Node Bob successfully
Alice: Sending large byte array data to Node Bob...
Bob: Connected to Broker at http://localhost:8080 as Bob
Bob: [msgId: 0] Received byte array msg part 1/X from node Alice (xx.xxx%)
Bob: ...
Bob: [msgId: 0] Received complete byte array msg of 997865700 bytes from Node Alice
Bob: Elapsed time since connection ready: XXXms (x bytes/ms)
```

This demonstrates that CrolangP2P can handle the transfer of very large messages, overcoming the typical size limits of WebRTC. However, the actual limit is determined by the available memory on your machine.

## Running the example
### 1: Start Node Bob

In the project root, run:

```sh
./gradlew runEx10Bob
```

### 2: Start Node Alice

In a separate terminal, run:

```sh
./gradlew runEx10Alice
```
