package com.developer.ted.composetutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.developer.ted.composetutorial.compose.Grid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Grid(
                totalColumns = 3,
                totalRows = 3,
                contentPadding = 5.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                Box(
                    modifier = Modifier
                        .span(1, 1, "main")
                        .background(color = colorResource(id = R.color.purple_200))
                ) {
                    Text(text = "2 x 2 (main)", modifier = Modifier.align(Alignment.Center))
                }

                (0..7).forEach { idx ->
                    val colorRes = when (idx % 3) {
                        0 -> R.color.teal_200
                        1 -> R.color.purple_500
                        else -> androidx.core.R.color.androidx_core_ripple_material_light
                    }
                    Box(
                        modifier = Modifier
                            .span(1, 1, idx.toString())
                            .background(color = colorResource(colorRes))
                    ) {
                        Text(
                            text = "1 x 1 ($idx)",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

    fun onItemClicked(name: String) {
        Toast.makeText(this.baseContext, name, Toast.LENGTH_SHORT).show()
    }

    fun onPhoneClicked(number: String) {
        Toast.makeText(this.baseContext, number, Toast.LENGTH_SHORT).show()
    }
}