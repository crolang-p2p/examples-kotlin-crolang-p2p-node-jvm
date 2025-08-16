package ex_9

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main(){

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, OutgoingCrolangNodeCallbacks(
                onNewStringMsg = mapOf(
                    "REDIRECT_TO_ALICE" to { node, msg -> println("[REDIRECT_TO_ALICE][${node.id}]: $msg") }
                ),
                onConnectionSuccess = {
                    println("Connected successfully to Node ${it.id}")
                    it.sendString("CONNECT_TO_CAROL", "")
                }
            ))
        }
    )

}