package com.developer.ted.composetutorial.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.ted.composetutorial.R
import com.developer.ted.composetutorial.model.Contact
import com.developer.ted.composetutorial.ui.theme.ComposeTutorialTheme

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ContactList(
    itemClickListener: (() -> Unit)? = null,
    phoneClickListener: (() -> Unit)? = null
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        Contact.getMockList().forEach { (index, contacts) ->
            stickyHeader { CharacterHeader(index) }
            items(contacts) { contact ->
                ContactItem(
                    contact = contact,
                    itemClickListener = itemClickListener,
                    phoneClickListener = phoneClickListener
                )
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    itemClickListener: (() -> Unit)? = null,
    phoneClickListener: (() -> Unit)? = null
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { itemClickListener?.invoke() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_default_profile),
            contentDescription = "profileImage",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = contact.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 15.dp)
        )

        Image(
            alignment = Alignment.CenterEnd,
            painter = painterResource(id = R.drawable.ic_phone_24),
            contentDescription = "PhoneIcon",
            modifier = Modifier
                .padding(all = 15.dp)
                .clickable { phoneClickListener?.invoke() }
        )
    }
}

@Composable
fun CharacterHeader(index: String) {
    Surface(
        color = colorResource(id = R.color.purple_200)
    ) {
        Text(
            text = index,
            fontWeight = FontWeight.Black,
            color = colorResource(id = R.color.purple_500),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun ContactListDemo() {
    ComposeTutorialTheme {
        ContactList()
    }
}