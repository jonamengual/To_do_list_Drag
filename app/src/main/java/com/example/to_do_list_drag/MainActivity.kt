package com.example.to_do_list_drag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_do_list_drag.ui.theme.To_do_list_DragTheme
import com.example.to_do_list_drag.vmv.view.MainScreen
import com.example.to_do_list_drag.vmv.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            To_do_list_DragTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding).padding(8.dp))
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
     val viewModel = MainViewModel()
    To_do_list_DragTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(modifier = Modifier.padding(innerPadding).padding(8.dp))
        }
    }
}