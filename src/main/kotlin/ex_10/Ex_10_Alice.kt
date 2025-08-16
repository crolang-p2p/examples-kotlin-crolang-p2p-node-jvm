package ex_10

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.OutgoingCrolangNodeCallbacks
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val resourcePath = "/large_file.txt" // ~100 MB file in resources

    println("Reading large file...")
    val content: ByteArray = object {}.javaClass.getResourceAsStream(resourcePath)?.use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).readText().toByteArray()
    } ?: error("File not found: $resourcePath")

    println("File read successfully. Bytes: ${content.size}")

    println("Repeat the content 10 times to simulate a ~1 GB file")
    var toSend = ByteArray(0)
    for( i in 0 until 10) {
        toSend += content
    }

    println("Total bytes to send: ${toSend.size}")

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, OutgoingCrolangNodeCallbacks(
                onConnectionSuccess = {
                    println("Connected to Node ${it.id} successfully")

                    println("Sending large byte array data to Node ${it.id}...")
                    val sendResultBytes = it.sendBytes("LARGE_DATA_TRANSFER", toSend)
                    println("Byte array data sent result: $sendResultBytes")
                }
            ))
        }
    )
}
