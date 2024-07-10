package com.kapps.bubblesort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kapps.bubblesort.presentation.SortViewModel
import com.kapps.bubblesort.ui.theme.BubbleSortTheme
import com.kapps.bubblesort.ui.theme.gray
import com.kapps.bubblesort.ui.theme.orange

class MainActivity : ComponentActivity() {

    private val sortViewModel = SortViewModel()
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleSortTheme {
                window.statusBarColor = orange.toArgb()
                window.navigationBarColor = orange.toArgb()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gray)
                        .padding(20.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Button(onClick = {
                        sortViewModel.startSorting()
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = orange)
                    ) {
                        Text(
                            "Sort List",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ){
                        items(
                            sortViewModel.listToSort,
                            key = {
                                it.id
                            }
                        ){
                            val borderStroke = if(it.isCurrentlyCompared){
                                BorderStroke(width = 3.dp,Color.White,)
                            }else{
                                BorderStroke(width = 0.dp,Color.Transparent)
                            }
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(it.color, RoundedCornerShape(15.dp))
                                    .border(borderStroke, RoundedCornerShape(15.dp))
                                    .animateItemPlacement(
                                        tween(300)
                                    ),
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    "${it.value}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
