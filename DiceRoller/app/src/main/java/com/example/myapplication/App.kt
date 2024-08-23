package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun App(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFEEA7A7)),
    innerPadding: PaddingValues = PaddingValues()
) {

    var player1Score = remember { mutableStateOf(0) }
    var player2Score = remember { mutableStateOf(0) }

    val customFontFamily = FontFamily(Font(R.font.bungeetint))

    val isPlayer1Turn = remember { mutableStateOf(true) }

    val images = listOf(
        R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3,
        R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6
    )

    val currentImage = remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Roll the dice",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            fontFamily = customFontFamily,
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
        )

        Spacer(Modifier.height(80.dp))

        if (player1Score.value >= 20 || player2Score.value >= 20) {
            // Game Over Screen
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.congratulations), contentDescription = null, Modifier.size(250.dp))
                
                Spacer(modifier = Modifier.height(50.dp))
                
                if (player1Score.value > player2Score.value) {
                    Text(
                        text = "Player 1 Wins!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = customFontFamily
                    )
                    Text(
                        text = "Score: ${player1Score.value}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = customFontFamily
                    )
                } else {
                    Text(
                        text = "Player 2 Wins!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = customFontFamily
                    )
                    Text(
                        text = "Score: ${player2Score.value}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = customFontFamily
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))


                Button(
                    onClick = {
                        player1Score.value = 0
                        player2Score.value = 0
                        isPlayer1Turn.value = true
                        currentImage.value = 0
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Play Again")
                }
            }
        } else {
            // Game Ongoing Screen
            Row(
                modifier = Modifier.padding(bottom = 80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = if (currentImage.value == 0) painterResource(id = R.drawable.initial)
                    else painterResource(id = images[currentImage.value - 1]),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                            .background(
                                if (isPlayer1Turn.value) Color.Green else Color.Gray,
                                CircleShape
                            )
                    )

                    Text(
                        text = "Player 1 Score",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = customFontFamily
                    )
                    Text(
                        text = "${player1Score.value}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = customFontFamily
                    )
                    Button(
                        onClick = {
                            val random = Random.nextInt(6) + 1
                            currentImage.value = random
                            player1Score.value += random
                            isPlayer1Turn.value = !isPlayer1Turn.value
                        },
                        enabled = isPlayer1Turn.value,
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(text = "Player 1")
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                            .background(
                                if (isPlayer1Turn.value) Color.Gray else Color.Green,
                                CircleShape
                            )
                    )

                    Text(
                        text = "Player 2 Score",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = customFontFamily
                    )
                    Text(
                        text = "${player2Score.value}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = customFontFamily
                    )
                    Button(
                        onClick = {
                            val random = Random.nextInt(6) + 1
                            currentImage.value = random
                            player2Score.value += random
                            isPlayer1Turn.value = !isPlayer1Turn.value
                        },
                        enabled = !isPlayer1Turn.value,
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(text = "Player 2")
                    }
                }
            }
        }
    }
}