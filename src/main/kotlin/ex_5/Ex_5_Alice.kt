package ex_5

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.errors.RemoteNodesConnectionStatusCheckError

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onError = { println("Error connecting to Broker: $it") },
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2PJvm.Kotlin.isRemoteNodeConnectedToBroker(
                BOB_ID,
                onError = { error ->
                    when (error) {
                        RemoteNodesConnectionStatusCheckError.NOT_CONNECTED_TO_BROKER -> {
                            println("Local Node not connected to Broker")
                        }
                        RemoteNodesConnectionStatusCheckError.UNKNOWN_ERROR -> {
                            println("Unknown error checking connection to Broker: $error")
                        }
                    }
                },
                onResult = { isConnected -> println("Is $BOB_ID connected to the Broker: $isConnected") }
            )

            CrolangP2PJvm.Kotlin.areRemoteNodesConnectedToBroker(
                setOf(BOB_ID, CAROL_ID),
                onError = { println("Error checking connection to Broker: $it") },
                onResult = { result ->
                    result.forEach { entry -> println("Is ${entry.key} connected to the Broker: ${entry.value}") }
                }
            )
        }
    )

}