package ex_1

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main(){

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onError = { println("Error connecting to Broker: $it") },
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(
                BOB_ID,
                OutgoingCrolangNodeCallbacks(
                    onNewMsg = mapOf(
                        "GREETINGS_CHANNEL" to { node, msg ->
                            println("Received a message on GREETINGS_CHANNEL from Node ${node.id}: $msg")
                        }
                    ),
                    onConnectionFailed = { nodeId, error -> println("Failed to connect to Node $nodeId: $error") },
                    onConnectionSuccess = {
                        println("Connected to Node ${it.id}, platform: ${it.platform}, version: ${it.version}")
                        it.send("GREETINGS_CHANNEL", "Hello from Node $ALICE_ID")
                    }
                )
            )
        }
    )
}