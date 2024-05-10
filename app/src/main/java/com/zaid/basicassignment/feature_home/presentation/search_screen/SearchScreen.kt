package com.zaid.basicassignment.feature_home.presentation.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.zaid.basicassignment.core.presentation.navigation.Screen
import com.zaid.basicassignment.core.utils.AppIcons
import com.zaid.basicassignment.feature_home.presentation.data.VideoResponseLocal
import com.zaid.basicassignment.feature_home.presentation.home_screen.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    uiState: HomeUiState,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {

    var videoResponse by rememberSaveable { mutableStateOf(uiState.videoResponse) }

    LaunchedEffect(key1 = uiState) {
        if (uiState.videoResponse != emptyArray<VideoResponseLocal>()) {
            videoResponse = uiState.videoResponse
        }
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage, null, SnackbarDuration.Short)
        }
    }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        var searchText by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(true) }
        val historyItem by remember { mutableStateOf(mutableListOf<String>()) }

        Box(
            modifier = Modifier
                .padding(start = 52.dp, top = 8.dp, end = 50.dp)
                .height(40.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
        )

        Column {
            DockedSearchBar(
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                colors = SearchBarDefaults.colors(containerColor = Color.Transparent),
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = {
                    active = false
                    historyItem.add(searchText)
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text(text = "Search Youtube") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back Button",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate(Screen.HomeScreen.route)
                            }
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                    )
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            modifier = Modifier.clickable {
                                active = false
                            })
                    }
                }
            ) {
                historyItem.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                searchText = it
                                active = false
                            }
                            .padding(all = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcons.HistoryIcon),
                            contentDescription = "history Icon",
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = it)
                    }
                }

            }
            Box(modifier = Modifier.fillMaxSize()) {
                if (searchText.isNotEmpty() && !active) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 1000.dp)
                    ) {
                        items(items = videoResponse.filter { video ->
                            video.title.contains(searchText, ignoreCase = true) ||
                                    video.channelName.contains(searchText, ignoreCase = true)
                        }, key = { it.id }) { video ->
                            Column(Modifier.fillMaxWidth()) {
                                AsyncImage(
                                    model = video.thumbnailUrl,
                                    contentDescription = video.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(215.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    AsyncImage(
                                        model = video.userPicUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                    )
                                    Spacer(modifier = Modifier.size(15.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = video.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                                        )
                                        Text(
                                            text = video.channelName,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                            lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "${video.likes} views",
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                            )
                                            Text(
                                                text = " • ",
                                            )
                                            Text(
                                                text = video.createAt,
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                            )
                                        }
                                    }
                                    Icon(
                                        painter = painterResource(id = AppIcons.OptionIcon),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                } else {
                    if (!active) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Result Found",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = NavController(LocalContext.current), uiState = HomeUiState(),
        onShowSnackBar = { _, _, _ -> false })
}