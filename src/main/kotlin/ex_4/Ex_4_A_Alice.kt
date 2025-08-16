package ex_4

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main(){

    val callbacks = OutgoingCrolangNodeCallbacks(
        onConnectionSuccess = { node ->
            println("Connected to Node ${node.id} successfully")
            node.sendString("GREETINGS_CHANNEL", "Hello there!")
        },
        onConnectionFailed = { id, reason ->
            println("Failed to connect to Node $id: $reason")
        },
        onDisconnection = { id -> println("Node $id disconnected") },
        onNewStringMsg = mapOf(
            "CHANNEL_LETTERS" to { node, msg ->
                println("Received a message on CHANNEL_LETTERS from Node ${node.id}: $msg")
            },
            "CHANNEL_NUMBERS" to { node, msg ->
                println("Received a message on CHANNEL_NUMBERS from Node ${node.id}: $msg")
            }
        )
    )

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")
            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, callbacks)
        }
    )

}