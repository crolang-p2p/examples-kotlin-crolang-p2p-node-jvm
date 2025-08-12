package ex_7

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks

fun main(){

    val COUNTER_TRESHOLD = 20

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, OutgoingCrolangNodeCallbacks(
                onDisconnection = { id ->
                    println("Disconnected from Node $id")
                },
                onNewMsg = mapOf(
                    "COUNT_CHANNEL" to { node, msg ->
                        println("[COUNT_CHANNEL][${node.id}]: $msg")
                        val i = msg.toInt()
                        if(i >= COUNTER_TRESHOLD){
                            println("Counter threshold exceeded, disconnecting from Node ${node.id}")
                            node.disconnect()
                        } else {
                            node.send("COUNT_CHANNEL", (i + 1).toString())
                        }
                    }
                ),
                onConnectionSuccess = {
                    println("Connected successfully to Node ${it.id}")
                    println("Disconnecting from Broker...")
                    CrolangP2PJvm.Kotlin.disconnectFromBroker(
                        onError = { error -> println("Error disconnecting from Broker: $error") },
                        onSuccess = {
                            CrolangP2PJvm.Kotlin.isLocalNodeConnectedToBroker { isConnected ->
                                println("Is local Node connected to the Broker: $isConnected")
                            }
                        }
                    )
                }
            ))
        }
    )

}