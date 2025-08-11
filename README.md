# examples-kotlin-crolang-p2p-node
Repository containing examples on how to use the [CrolangP2P library in a JVM (Kotlin) environment](https://github.com/crolang-p2p/crolang-p2p-node) for the [CrolangP2P project](https://github.com/crolang-p2p).

## Table of Contents
- [Kotlin Examples Introduction](#kotlin-examples-introduction)
- [Examples Cross-language Interoperability](#examples-cross-language-interoperability)
- [Kotlin Examples List](#kotlin-examples-list)
- [Adding the Library to Your Project](#adding-the-library-to-your-project)
- [Running the examples](#running-the-examples)
  - [Requirements](#requirements)
  - [Starting the CrolangP2P Broker](#starting-the-crolangp2p-broker)

## Kotlin Examples Introduction
This repository contains a collection of practical examples demonstrating how to use the [CrolangP2P](https://github.com/crolang-p2p) library in a JVM (Kotlin) environment. The official Kotlin/JVM implementation is available here: [crolang-p2p-node](https://github.com/crolang-p2p/crolang-p2p-node).

Each example is designed to illustrate a specific use case, ranging from basic node communication to advanced features such as authentication, connection management, and large data transfers.

> The main entry point for the CrolangP2P documentation is available at [https://github.com/crolang-p2p](https://github.com/crolang-p2p).

## Examples Cross-language Interoperability
[CrolangP2P](https://github.com/crolang-p2p) is a cross-language library, available for multiple programming languages. Each implementation has a dedicated repository with the same set of examples, allowing you to mix and match nodes written in different languages. For instance, you can run the Alice node from Example 1 in Kotlin and the Bob node from Example 1 in JavaScript, and they will interoperate seamlessly.

You can find the complete list of example repositories for all supported languages here: [CrolangP2P Examples List](https://github.com/crolang-p2p#usage-examples)

## Kotlin Examples List
The examples are organized in numbered folders, each with its own source code and a dedicated README explaining the context and execution instructions.

1. [Communication among Nodes with CrolangP2P](src/main/kotlin/ex_1/README.md)
2. [Connecting to the CrolangP2P Broker](src/main/kotlin/ex_2/README.md)
3. [Enabling/disabling incoming P2P connections](src/main/kotlin/ex_3/README.md)
4. [Different ways of establish P2P connections](src/main/kotlin/ex_4/README.md)
5. [Checking if other Nodes are connected to the Broker](src/main/kotlin/ex_5/README.md)
6. [Retrieving nodes currently connected to your local nodes](src/main/kotlin/ex_6/README.md)
7. [Disconnecting from the Broker](src/main/kotlin/ex_7/README.md)
8. [Sending messages via WebSocket](src/main/kotlin/ex_8/README.md)
9. [Nodes allowing incoming connections and initiating new connections simultaneously](src/main/kotlin/ex_9/README.md)
10. [Sending large amount of data over P2P](src/main/kotlin/ex_10/README.md)
11. [Customizing Local Node Behavior with BrokerConnectionAdditionalParameters](src/main/kotlin/ex_11/README.md)
12. [Authenticating to the Broker](src/main/kotlin/ex_12/README.md)

## Adding the Library to Your Project

To use the Kotlin/JVM implementation of CrolangP2P in your project, simply add the dependency to your build configuration. The library is available on Maven Central and can be integrated with Gradle (Kotlin or Groovy DSL) or Maven.

Refer to the [library's Maven Central page](https://central.sonatype.com/artifact/io.github.crolang-p2p/crolang-p2p-node/overview) for the latest version.

## Running the examples
Each example has its own README with instructions on how to run it. However, before running any example, you need to ensure that you satisfy the requirements.

### Requirements
- **Java 11 or higher**: Make sure the command `java -version` returns at least version 11.
- **Crolang Broker running**: Start the CrolangP2P Broker using one of the following methods.

### Starting the CrolangP2P Broker
To run the examples, you need to have a [CrolangP2P Broker](https://github.com/crolang-p2p/crolang-p2p-broker) running.
The Broker is a central component that facilitates the discovery and connection of nodes in the CrolangP2P network.

Run the CrolangP2P Broker, either by:
- [Using Docker](#starting-the-crolangp2p-broker-using-docker)
- [Using NodeJs](#starting-the-crolangp2p-broker-using-nodejs)

---

#### Starting the CrolangP2P Broker using Docker

```sh
docker run --rm --name CrolangP2PBroker -p 8080:8080 crolangp2p/broker
```

This will start the Broker on port 8080 (which is the default expected by the example nodes) using the official
[CrolangP2P Broker Docker image](https://hub.docker.com/r/crolangp2p/broker).

---

#### Starting the CrolangP2P Broker using NodeJs

1. Clone the [broker repository](https://github.com/crolang-p2p/crolang-p2p-broker):
   ```sh
   git clone https://github.com/crolang-p2p/crolang-p2p-broker.git
   cd crolang-p2p-broker
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Build the project:
   ```sh
   npm run build
   ```
4. Start the broker:
   ```sh
   npm start
   ```

The Broker will be available on port 8080 by default.
