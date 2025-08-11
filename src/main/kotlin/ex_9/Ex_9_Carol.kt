package ex_9

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        CAROL_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $CAROL_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(
                IncomingCrolangNodesCallbacks(
                    onConnectionSuccess = { node ->
                        println("Connected successfully to Node ${node.id}")
                        val msg = "Hello $ALICE_ID, I'm Node $CAROL_ID"
                        println("Sending message to Node ${node.id}: $msg")
                        node.send("REDIRECT_TO_ALICE", msg)
                    }
                ),
                onSuccess = { println("Incoming connections allowed successfully") }
            )
        }
    )

}