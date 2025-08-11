package ex_5

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onError = { println("Error connecting to Broker: $it") },
        onSuccess = { println("Connected to Broker at $BROKER_ADDR as $BOB_ID") }
    )

}