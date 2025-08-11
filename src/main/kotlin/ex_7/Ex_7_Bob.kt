package ex_7

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
                onConnectionSuccess = { node ->
                    println("Connected successfully to Node ${node.id}")
                    println("Disconnecting from Broker...")
                    CrolangP2PJvm.Kotlin.disconnectFromBroker(
                        onError = { error -> println("Error disconnecting from Broker: $error") }, //TODO dice sempre che è già disconnesso
                        onSuccess = {
                            CrolangP2PJvm.Kotlin.isLocalNodeConnectedToBroker {
                                println("Is local Node connected to the Broker: $it")
                                node.send("COUNT_CHANNEL", "0")
                            }
                        }
                    )
                },
                onDisconnection = { id ->
                    println("Disconnected from Node $id")
                },
                onNewMsg = mapOf(
                    "COUNT_CHANNEL" to { node, msg ->
                        println("[COUNT_CHANNEL][${node.id}]: $msg")
                        node.send("COUNT_CHANNEL", (msg.toInt() + 1).toString())
                    }
                )
            ))
        }
    )

}