package org.crolangP2P.ex_8

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2PJvm
import org.crolangP2P.errors.SendSocketMsgError

fun main(){

    CrolangP2PJvm.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onSuccess = {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2PJvm.Kotlin.sendSocketMsg(
                BOB_ID,
                "GREETINGS_CHANNEL",
                "Hello from ${ALICE_ID}!",
                onMsgSent = {
                    println("Message sent successfully to Broker to be relayed to $BOB_ID on GREETINGS_CHANNEL")
                },
                onError = { error ->
                    when(error){
                        SendSocketMsgError.TRIED_TO_SEND_MSG_TO_SELF -> {
                            println("Error: Tried to send a message to myself. This is not allowed.")
                        }
                        SendSocketMsgError.EMPTY_ID -> {
                            println("Error: The ID to send the message to is empty.")
                        }
                        SendSocketMsgError.EMPTY_CHANNEL -> {
                            println("Error: The channel to send the message to is empty.")
                        }
                        SendSocketMsgError.NOT_CONNECTED_TO_BROKER -> {
                            println("Error: Not connected to the broker. Please connect first.")
                        }
                        SendSocketMsgError.DISABLED -> {
                            println("Error: Sending messages through WebSocket is disabled on the Broker.")
                        }
                        SendSocketMsgError.REMOTE_NODE_NOT_CONNECTED_TO_BROKER -> {
                            println("Error: The remote node is not connected to the broker.")
                        }
                        SendSocketMsgError.UNAUTHORIZED_TO_CONTACT_REMOTE_NODE -> {
                            println("Error: Unauthorized to contact the remote node. Check permissions.")
                        }
                        SendSocketMsgError.UNKNOWN_ERROR -> {
                            println("Error: An unknown error occurred while sending the message.")
                        }
                    }
                }
            )

            CrolangP2PJvm.Kotlin.sendSocketMsg(BOB_ID, "SECRET_CHANNEL", "42")
        }
    )

}