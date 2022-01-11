package com.developer.ted.composetutorial.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.developer.ted.composetutorial.model.Message
import com.developer.ted.composetutorial.ui.theme.ComposeTutorialTheme

@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageList() {
    ComposeTutorialTheme {
        MessageList(
            messages = listOf(
                Message("Ted", "Hi :)"),
                Message("Alice", "Hi!", false),
                Message(
                    "Ted",
                    "And after talking about self-esteem, a lot of CARAT said good things and worried, so you don't have to worry\uD83D\uDC8E\n" +
                            "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT."
                ),
                Message(
                    "Alice",
                    "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT.",
                    false
                ),
                Message("Ted", "We're rock stars\uD83D\uDE0E"),
                Message("Ted", "Hi :)"),
                Message("Alice", "Hi!", false),
                Message(
                    "Ted",
                    "And after talking about self-esteem, a lot of CARAT said good things and worried, so you don't have to worry\uD83D\uDC8E\n" +
                            "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT."
                ),
                Message(
                    "Alice",
                    "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT.",
                    false
                ),
                Message("Ted", "We're rock stars\uD83D\uDE0E"),
                Message("Ted", "Hi :)"),
                Message("Alice", "Hi!", false),
                Message(
                    "Ted",
                    "And after talking about self-esteem, a lot of CARAT said good things and worried, so you don't have to worry\uD83D\uDC8E\n" +
                            "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT."
                ),
                Message(
                    "Alice",
                    "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT.",
                    false
                ),
                Message("Ted", "We're rock stars\uD83D\uDE0E"),
                Message("Ted", "Hi :)"),
                Message("Alice", "Hi!", false),
                Message(
                    "Ted",
                    "And after talking about self-esteem, a lot of CARAT said good things and worried, so you don't have to worry\uD83D\uDC8E\n" +
                            "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT."
                ),
                Message(
                    "Alice",
                    "We're all the best, pride and love to someone.❤\uD83D\uDDA4 Of course, CARAT for me and SEVENTEEN for CARAT.",
                    false
                ),
                Message("Ted", "We're rock stars\uD83D\uDE0E")
            )
        )
    }
}