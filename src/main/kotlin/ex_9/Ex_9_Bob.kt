package ex_9

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.IncomingCrolangNodesCallbacks
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(
                IncomingCrolangNodesCallbacks(
                    onConnectionSuccess = { node ->
                        println("Connected successfully to Node ${node.id}")
                    },
                    onNewMsg = mapOf(
                        "CONNECT_TO_CAROL" to { node, _ ->
                            println("[CONNECT_TO_CAROL][${node.id}]")
                            println("Connecting to Node $CAROL_ID")

                            connectToCarol()
                        }
                    )
                ),
                onSuccess = { println("Incoming connections allowed successfully") }
            )
        }
    )

}

fun connectToCarol(){
    CrolangP2PJvm.Kotlin.connectToSingleNode(
        CAROL_ID,
        OutgoingCrolangNodeCallbacks(
            onNewMsg = mapOf(
                "REDIRECT_TO_ALICE" to { node, msg ->
                    println("[REDIRECT_TO_ALICE][${node.id}]: $msg")
                    CrolangP2PJvm.Kotlin.getConnectedNode(ALICE_ID){
                        if(it != null){
                            val newMsg = "$msg, this message was redirected by Node $BOB_ID"
                            println("Redirecting to Node $ALICE_ID: $newMsg")
                            it.send("REDIRECT_TO_ALICE", newMsg)
                        }
                    }
                }
            ),
            onConnectionSuccess = { println("Connected successfully to Node ${it.id}") }
        ),
    )
}
