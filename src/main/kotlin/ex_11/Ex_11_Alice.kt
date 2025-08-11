package ex_11

import org.crolangP2P.BrokerConnectionAdditionalParameters
import org.crolangP2P.BrokerLifecycleCallbacks
import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.CrolangSettings
import org.crolangP2P.LoggingOptions

fun main() {
    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        additionalParameters = BrokerConnectionAdditionalParameters(
            logging = LoggingOptions(
                enableBaseLogging = true, //DEFAULT: false
                enableDebugLogging = true //DEFAULT: false
            ),
            lifecycleCallbacks = BrokerLifecycleCallbacks(
                onInvoluntaryDisconnection = { cause -> println("Involuntary disconnection from Broker: $cause") }, //DEFAULT: does nothing
                onReconnectionAttempt = { println("Attempting to reconnect to Broker") }, //DEFAULT: does nothing
                onSuccessfullyReconnected = { println("Successfully reconnected to Broker") }, //DEFAULT: does nothing
            ),
            settings = CrolangSettings(
                p2pConnectionTimeoutMillis = 5000, //DEFAULT: 30000
                multipartP2PMessageTimeoutMillis = 1000, //DEFAULT: 60000
                reconnection = true, //DEFAULT: true
                maxReconnectionAttempts = 5, //DEFAULT: Optional.empty()
                reconnectionAttemptsDeltaMs = 500 //DEFAULT: 2000
            )
        ),
        onSuccess = { println("Connected to Broker at $BROKER_ADDR as $ALICE_ID") },
        onError = { println("Failed to connect to Broker: $it") }
    )
}