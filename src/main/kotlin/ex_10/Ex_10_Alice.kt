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
    val content: String = object {}.javaClass.getResourceAsStream(resourcePath)?.use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).readText()
    } ?: error("File not found: $resourcePath")

    println("File read successfully. Bytes: ${content.toByteArray().size}")

    var toSend = ""
    for( i in 0 until 10) { // Repeat the content 10 times to simulate a ~1 GB file
        toSend += content
    }

    println("Bytes to send: ${toSend.toByteArray().size}")

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.connectToSingleNode(BOB_ID, OutgoingCrolangNodeCallbacks(
                onConnectionSuccess = {
                    println("Connected to Node ${it.id} successfully")
                    println("Sending large data to Node ${it.id}...")
                    val sendResult = it.send("LARGE_DATA_TRANSFER", toSend)
                    println("Data sent result: $sendResult")
                }
            ))
        }
    )
}
