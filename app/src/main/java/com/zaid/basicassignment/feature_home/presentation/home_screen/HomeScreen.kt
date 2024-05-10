package com.zaid.basicassignment.feature_home.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import com.zaid.basicassignment.feature_home.presentation.data.VideoResponseLocal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppTopBar(
            leadingIcon = if (isSystemInDarkTheme()) AppIcons.AppLogoDark else AppIcons.AppLogoLight,
            trailingIcon = AppIcons.BroadCastIcon,
            trailingIcon2 = AppIcons.BellIcon,
            trailingIcon3 = AppIcons.SearchIcon,
            onClickTrailingIcon3 = { navController.navigate(Screen.SearchScreen.route) }
        )
        Divider(modifier = Modifier.fillMaxWidth())
        if (!uiState.loading) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp)
            ) {
                items(count = videoResponse.size) {
                    Column(Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = videoResponse[it].thumbnailUrl,
                            contentDescription = "",
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
                                model = videoResponse[it].userPicUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = videoResponse[it].title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                                )
                                Text(
                                    text = videoResponse[it].channelName,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${videoResponse[it].likes} views",
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                    )
                                    Text(
                                        text = " • ",
                                    )
                                    Text(
                                        text = videoResponse[it].createAt,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
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
           Box(modifier = Modifier.fillMaxSize()){
               CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
           }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    leadingIcon: Int?,
    trailingIcon: Int?,
    trailingIcon2: Int?,
    trailingIcon3: Int?,
    onClickTrailingIcon3: () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
) {
    TopAppBar(
        modifier = Modifier.height(55.dp),
        navigationIcon = {
            Image(
                painter = painterResource(id = leadingIcon!!),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp)
            )
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 7.dp)
            ) {
                Icon(
                    painter = painterResource(id = trailingIcon!!),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Icon(
                    painter = painterResource(id = trailingIcon2!!),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(
                    painter = painterResource(id = trailingIcon3!!),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onClickTrailingIcon3() }
                        .padding(8.dp)
                        .size(28.dp)

                )
            }

        },
        colors = colors,
        title = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavController(LocalContext.current),
        uiState = HomeUiState(),
        onShowSnackBar = { _, _, _ -> false }
    )
}