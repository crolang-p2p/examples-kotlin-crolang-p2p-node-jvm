package ex_4

import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main(){

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        CAROL_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $CAROL_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
                onConnectionSuccess = { node ->
                    println("Connected to Node ${node.id} successfully")
                    node.send("CHANNEL_ANIMALS", "Unicorns")
                },
                onNewMsg = mapOf(
                    "GREETINGS_CHANNEL" to { node, msg ->
                        println("Received a message on GREETINGS_CHANNEL from Node ${node.id}: $msg")
                    },
                )
            ))
        }
    )

}