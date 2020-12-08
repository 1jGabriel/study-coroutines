package br.com.pagingcompose.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.pagingcompose.ui.composables.CharacterImage
import br.com.pagingcompose.ui.composables.CharacterName

@Composable
fun CharactersDetailScreen(viewModel: DetailViewModel, id: String?, navigateUp: () -> Unit) {
    val id = id ?: ""
    viewModel.dispatchAction(ViewAction.Init(id))
    val personage = viewModel.characterDetail.observeAsState()

    personage.value?.let { personage ->
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(personage.name)
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                }
            )
        }, bodyContent = {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CharacterImage(
                    imageUrl = personage.image,
                    modifier = Modifier.padding(end = 16.dp).preferredSize(90.dp)
                )
                CharacterName(
                    personage.name,
                    modifier = Modifier.weight(1f)
                )
            }
        })
    }
}
