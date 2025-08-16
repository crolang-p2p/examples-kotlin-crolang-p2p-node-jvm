package ex_6

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main() {
    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToMultipleNodes(
                targets = mapOf(
                    BOB_ID to OutgoingCrolangNodeCallbacks(),
                    CAROL_ID to OutgoingCrolangNodeCallbacks()
                ),
                onConnectionAttemptConcluded = { _, _ ->
                    CrolangP2PJvm.Kotlin.getConnectedNode(
                        BOB_ID,
                        onResult = { node ->
                            if(node != null){
                                println("Node $BOB_ID is connected")
                                node.sendString("GREETINGS_CHANNEL", "Hello ${node.id}!")
                            }
                        }
                    )

                    CrolangP2PJvm.Kotlin.getAllConnectedNodes{ nodes ->
                        nodes[CAROL_ID]?.let { node ->
                            println("Node $CAROL_ID is connected")
                            node.sendString("GREETINGS_CHANNEL", "Hello ${node.id}!")
                        }
                    }
                }
            )
        }
    )
}