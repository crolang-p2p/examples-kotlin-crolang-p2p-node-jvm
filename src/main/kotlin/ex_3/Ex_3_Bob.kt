package ex_3

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main() {

    fun isConnectionAttemptAuthorized(id: String, platform: String, version: String): Boolean {
        println("Connection attempt from Node $id, platform: $platform, version: $version")
        return id == ALICE_ID
    }

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
                onConnectionAttempt = { id, platform, version -> isConnectionAttemptAuthorized(id, platform, version) },
                onConnectionSuccess = { node -> println("Connected successfully to Node ${node.id}") },
                onConnectionFailed = { id, reason -> println("Failed to connect to Node $id: $reason") },
                onDisconnection = { id -> println("Disconnected from Node $id") },
                onNewMsg = mapOf(
                    "CHANNEL_NUMBERS" to { node, msg ->
                        println("Received a message on CHANNEL_NUMBERS from Node ${node.id}: $msg")
                    },
                    "CHANNEL_DISCONNECT" to { node, _ ->
                        println("Received a message on CHANNEL_DISCONNECT from Node ${node.id}")
                        CrolangP2PJvm.Kotlin.stopIncomingConnections{
                            println("Stopped incoming connections")
                            CrolangP2PJvm.Kotlin.areIncomingConnectionsAllowed {
                                println("Are incoming connections allowed: $it")
                                println("Disconnecting from ${node.id}")
                                node.disconnect()
                            }
                        }
                    }
                )
            ))
        }
    )

}