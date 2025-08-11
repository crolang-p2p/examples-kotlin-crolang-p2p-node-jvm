package ex_3

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks
import org.crolangP2P.errors.P2PConnectionFailedError

fun main(){

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, OutgoingCrolangNodeCallbacks(
                onConnectionFailed = { id, err -> println("Error while connecting to Node $id: $err") },
                onConnectionSuccess = { node ->
                    println("Connected successfully to Node ${node.id}")
                    node.send("CHANNEL_NUMBERS", "42")
                    node.send("CHANNEL_DISCONNECT")
                },
                onDisconnection = { id ->
                    println("Disconnected from Node $id, trying to reconnect...")
                    CrolangP2PJvm.Kotlin.connectToSingleNode(id, OutgoingCrolangNodeCallbacks(
                        onConnectionSuccess = { node ->
                            println("Connected successfully to Node ${node.id}")
                        },
                        onConnectionFailed = { failedId, error ->
                            if(error == P2PConnectionFailedError.CONNECTIONS_NOT_ALLOWED_ON_REMOTE_NODE){
                                println("Connections not allowed on remote Node $failedId")
                            } else {
                                println("Error connecting to Node $failedId: $error")
                            }
                        }
                    ))
                }
            ))
        }
    )

}