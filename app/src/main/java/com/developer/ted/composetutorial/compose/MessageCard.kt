package com.developer.ted.composetutorial.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.ted.composetutorial.R
import com.developer.ted.composetutorial.model.Message
import com.developer.ted.composetutorial.ui.theme.ComposeTutorialTheme

@Composable
fun MessageCard(msg: Message) {
    Row(
        horizontalArrangement = if (msg.me) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 7.5.dp)
            .fillMaxWidth()
    ) {
        if (msg.me) {
            MyChat(msg)
        } else {
            OtherChat(msg)
        }
    }
}

@Composable
fun MyChat(msg: Message) {
    ChatContent(msg)
    // Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun OtherChat(msg: Message) {
    ChatProfile()
    Spacer(modifier = Modifier.width(8.dp))
    ChatContent(msg)
}

@Composable
fun ChatProfile() {
    Image(
        painter = painterResource(R.drawable.img_profile),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colors.secondary, CircleShape) // colorResource(R.color.purple_200)
    )
}

@Composable
fun ChatContent(msg: Message) {
    Column {
        // nickName
        Text(
            text = msg.author,
            color = MaterialTheme.colors.secondaryVariant, // colorResource(R.color.purple_500),
            style = MaterialTheme.typography.subtitle2,
            modifier = if (msg.me) Modifier.align(Alignment.End) else Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(4.dp))
        // content
        Column {

            MessageBubble(msg.body)
        }
    }
}

@Composable
fun MessageBubble(content: String) {
    var isExpended by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(
        if (isExpended) MaterialTheme.colors.secondary else colorResource(id = R.color.white)
    )
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        color = surfaceColor,
        modifier = Modifier
            .clickable { isExpended = !isExpended }
            .animateContentSize(
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Text(
            text = content,
            color = if (isExpended) colorResource(R.color.white) else colorResource(R.color.black),
            style = MaterialTheme.typography.body2,
            maxLines = if (isExpended) Int.MAX_VALUE else 1,
            modifier = Modifier.padding(all = 4.dp),
            overflow = Ellipsis
        )
    }
}

@Preview(
    name = "My Chat",
    showBackground = true
)
@Composable
fun PreviewMyChat() {
    ComposeTutorialTheme {
        MessageCard(
            msg = Message("Ted", "First Compose Test :)", true)
        )
    }
}

@Preview(
    name = "Other Chat",
    showBackground = true
)
@Composable
fun PreviewOtherChat() {
    ComposeTutorialTheme {
        MessageCard(
            msg = Message("Alice", "Hi :)", false)
        )
    }
}

