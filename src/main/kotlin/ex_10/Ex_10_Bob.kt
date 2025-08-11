package ex_10

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
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
                    onNewMsg = mapOf(
                        "LARGE_DATA_TRANSFER" to { node, msg ->
                            val duration = startTime?.elapsedNow()
                            val bytes = msg.toByteArray().size
                            println("Received $bytes bytes of data on LARGE_DATA_TRANSFER from Node ${node.id}")
                            println("Elapsed time since connection ready: ${duration?.inWholeMilliseconds}ms (${bytes / (duration?.inWholeMilliseconds ?: 1)} bytes/ms)")
                        },
                    )
                )
            )
        }
    )
}