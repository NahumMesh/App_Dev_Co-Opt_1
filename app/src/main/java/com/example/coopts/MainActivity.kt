// Opting in to this file so that we can use the Scaffold function.
@file:OptIn(ExperimentalMaterial3Api::class)

/*
 * Name: Nahum Meshesha and Matthew Thiessen
 * Program: Business Information Technology
 * Course: ADEV-3007 Mobile Application Development
 * Created: 2023-09-21
 * Updated: 2023-09-26
 */

package com.example.coopts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.coopts.ui.theme.CooptsTheme
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CooptsTheme {
                // Running the created Snackbar function
                Snackbar()
            }
        }
    }
}

// Snackbar function that will demo a snackbar pop up when a FAB button is pressed.
@Composable
fun Snackbar(){
    /*
    Assign the state of the SnackbarHost to a variable, this state controls the queue and current
    Snackbar being shown.
     */
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // Define the scope for future Coroutines to begin here.
    val scope = rememberCoroutineScope()

    /*
    Build the Scaffold which is the structure for a user interface, includes things like app bars
    floating action buttons, and content.
     */
    Scaffold(
        /*
        Create the host for the Snackbar to be used in the Scaffold, this host displays the Snackbar
        based on the current snackbarHostState.
         */
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        // Simple demo of the topBar function in Scaffold with a title and color assignment.
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top Bar")
                },
                colors =
                TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
            )
        },

        /*
        Create a floating action button which hovers in the bottom right and performs the main
        action of the app.
        */
        floatingActionButton = {
            // onClick event launches a new thread so this code is not in the main thread.
            // ExtendedFAB allows the button to be stretched when adding text to it.
            ExtendedFloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "A Card Has Been Added!",
                        actionLabel = "Undo",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                // Add text to the FAB
                Text(text = "Add A Card To Your Deck")
            }
        },

        // Add a simple phrase to the content section of the Scaffold with modifiers for the text.
        content = {innerpadding ->
            Text(
                text = "Co-opt #1",
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize()
                    .wrapContentSize(),
                fontSize = 30.sp
            )
        }
    )
}