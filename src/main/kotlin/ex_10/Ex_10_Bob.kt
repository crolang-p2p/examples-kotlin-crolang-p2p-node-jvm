package ex_10

import org.crolangP2P.BrokerConnectionAdditionalParameters
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.CrolangSettings
import org.crolangP2P.IncomingByteArrayMsgCallbacks
import org.crolangP2P.IncomingCrolangNodesCallbacks
import kotlin.time.TimeMark
import kotlin.time.TimeSource

fun main() {
    var startTime: TimeMark? = null

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2PJvm.Kotlin.allowIncomingConnections(
                IncomingCrolangNodesCallbacks(
                    onConnectionSuccess = { node ->
                        startTime = TimeSource.Monotonic.markNow()
                        println("Connected to Node ${node.id} successfully, waiting for large data transfer...")
                    },
                    onNewByteArrayMsg = mapOf(
                        "LARGE_DATA_TRANSFER" to IncomingByteArrayMsgCallbacks(
                            onNewMsgPartReceived = { node, msgId, part, total ->
                                val percentage = (part.toDouble() / total) * 100
                                println("[msgId: $msgId] Received byte array msg part $part/$total from Node ${node.id} (${String.format("%.3f", percentage)}%)")
                            },
                            onNewCompleteMsgReceived = { node, msgId, msg ->
                                val duration = startTime?.elapsedNow()
                                val bytes = msg.size
                                println("[msgId: $msgId] Received complete byte array msg of ${msg.size} bytes from Node ${node.id}")
                                println("Elapsed time since connection ready: ${duration?.inWholeMilliseconds}ms (${bytes / (duration?.inWholeMilliseconds ?: 1)} bytes/ms)")
                            },
                            onMsgCorruption = { node, msgId ->
                                println("[msgId: $msgId] Corruption detected on byte array msg from Node ${node.id}")
                            }
                        )
                    )
                )
            )
        },
        additionalParameters = BrokerConnectionAdditionalParameters(
            settings = CrolangSettings(
                multipartP2PMessageTimeoutMillis = 600000 // 10 minutes timeout for large data transfers, 1 minute by default
            )
        )
    )
}