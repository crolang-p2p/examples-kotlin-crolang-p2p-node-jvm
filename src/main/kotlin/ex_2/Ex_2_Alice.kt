package ex_2

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.errors.ConnectionToBrokerError

fun main() {

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onError = { error ->
            when (error) {
                ConnectionToBrokerError.LOCAL_CLIENT_ALREADY_CONNECTED -> {
                    println("Local client is already connected to Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.ALREADY_PERFORMING_CONNECTION -> {
                    println("Already performing a connection to Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.CLIENT_WITH_SAME_ID_ALREADY_CONNECTED -> {
                    println("A client with the same ID $ALICE_ID is already connected to Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.UNSUPPORTED_ARCHITECTURE -> {
                    println("This client version is not supported by Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.UNAUTHORIZED -> {
                    println("Unauthorized connection attempt to Broker at $BROKER_ADDR with ID $ALICE_ID")
                }
                ConnectionToBrokerError.SOCKET_ERROR -> {
                    println("Socket error while connecting to Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.ERROR_PARSING_RTC_CONFIGURATION -> {
                    println("Error parsing RTC configuration for Broker at $BROKER_ADDR")
                }
                ConnectionToBrokerError.UNKNOWN_ERROR -> {
                    println("Unknown error while connecting to Broker at $BROKER_ADDR")
                }
            }
        },
        onSuccess = { println("Connected to Broker at $BROKER_ADDR as $ALICE_ID") }
    )

}