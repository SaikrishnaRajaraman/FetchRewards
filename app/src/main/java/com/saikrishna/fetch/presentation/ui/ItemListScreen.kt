package com.saikrishna.fetch.presentation.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saikrishna.fetch.R
import com.saikrishna.fetch.presentation.viewmodel.ItemViewModel
import com.saikrishna.fetch.ui.theme.fetchOrange
import com.saikrishna.fetch.ui.theme.fetchPurple
import com.saikrishna.fetch.ui.theme.fetchWhite
import com.saikrishna.fetch.utils.NetworkUtils.isNetworkConnected


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(viewModel: ItemViewModel = hiltViewModel()) {

    val listItems by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val isNetworkAvailable by rememberUpdatedState(newValue = isNetworkConnected(LocalContext.current))

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                Image(
                    painter = painterResource(R.drawable.fetch_logo),
                    contentDescription = stringResource(R.string.app_name)
                )
            },
            modifier = Modifier.background(fetchPurple),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = fetchPurple,
                titleContentColor = fetchOrange
            )
        )
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(fetchPurple)
        ) {

            when {
                !isNetworkAvailable -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(fetchPurple),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Filled.WifiOff,
                            contentDescription = "No Network",
                            modifier = Modifier.size(64.dp),
                            tint = fetchOrange
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(R.string.network_not_available), color = fetchOrange)
                    }
                }

                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = fetchOrange
                        )
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.background(fetchPurple)) {
                        listItems.forEach { (listId, items) ->
                            stickyHeader {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(fetchOrange)
                                ) {
                                    Text("$listId", modifier = Modifier.padding(16.dp))
                                }
                            }

                            items(items) { item ->
                                ListItem(
                                    headlineContent = {
                                        Text(item.name ?: "")
                                    },
                                    modifier = Modifier.background(fetchPurple),
                                    colors = ListItemDefaults.colors(
                                        containerColor = fetchPurple,
                                        headlineColor = fetchWhite
                                    )
                                )
                            }
                        }
                    }

                }
            }
        }
    }


}