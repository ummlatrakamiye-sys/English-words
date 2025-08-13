package com.englishword.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.englishword.data.Word
import com.englishword.srs.SrsCalculator
import com.englishword.ui.theme.EnglishWordTheme

@Composable
fun ReviewScreen(
    word: Word?, // The current word to review, nullable for empty state
    onAnswer: (SrsCalculator.AnswerQuality) -> Unit
) {
    if (word == null) {
        // Show a message when there are no words to review
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No words to review right now!", style = MaterialTheme.typography.titleLarge)
        }
        return
    }

    var showAnswer by remember { mutableStateOf(false) }

    // Reset visibility when the word changes
    LaunchedEffect(word) {
        showAnswer = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // The flashcard
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = word.arabicText,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                AnimatedVisibility(visible = showAnswer) {
                    Text(
                        text = word.englishText,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Control buttons
        if (showAnswer) {
            // Evaluation buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onAnswer(SrsCalculator.AnswerQuality.AGAIN) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("Again")
                }
                Button(onClick = { onAnswer(SrsCalculator.AnswerQuality.HARD) }) {
                    Text("Hard")
                }
                Button(onClick = { onAnswer(SrsCalculator.AnswerQuality.GOOD) }) {
                    Text("Good")
                }
                Button(onClick = { onAnswer(SrsCalculator.AnswerQuality.EASY) }) {
                    Text("Easy")
                }
            }
        } else {
            // "Show Answer" button
            Button(onClick = { showAnswer = true }) {
                Text("Show Answer")
            }
        }
    }
}

@Preview(showBackground = true, name = "Question State")
@Composable
fun ReviewScreenPreview_Question() {
    EnglishWordTheme {
        ReviewScreen(
            word = Word(0, "Hello", "مرحبا", 0, 0, 0, false, "Greetings"),
            onAnswer = {}
        )
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
fun ReviewScreenPreview_Empty() {
    EnglishWordTheme {
        ReviewScreen(
            word = null,
            onAnswer = {}
        )
    }
}
