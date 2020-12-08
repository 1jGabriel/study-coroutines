package br.com.pagingcompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import br.com.pagingcompose.R
import br.com.pagingcompose.ui.model.CharacterUi
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.flow.Flow

@Composable
fun CharacterList(characters: Flow<PagingData<CharacterUi>>, onClick: (String) -> Unit) {

    // convert the Flow<PagingData<T>> into LazyPagingItems<T>
    val lazyCharacters: LazyPagingItems<CharacterUi> = characters.collectAsLazyPagingItems()

    /*
    * In Jetpack Compose we have LazyColumn and LazyRow as a substitute for RecyclerView to display a list of data.
    * The paging-compose library comes with extension functions on the LazyListScope
    * which will be used by the LazyColumn() and LazyRow() composables to display the PagingData into a list.
    * */
    val test = rememberLazyListState(
        initialFirstVisibleItemIndex = 0,
        initialFirstVisibleItemScrollOffset = 0,
        interactionState = null
    )
    LazyColumn(state = test) {

        items(lazyPagingItems = lazyCharacters) { character ->
            character?.let {
                CharacterItem(character = character, onClick = onClick)
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterUi, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { onClick(character.id.toString()) }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CharacterImage(
            imageUrl = character.image,
            modifier = Modifier.padding(end = 16.dp).preferredSize(90.dp)
        )
        CharacterName(
            character.name,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CharacterImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = imageUrl,
        modifier = modifier,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        loading = {
            Image(vectorResource(id = R.drawable.ic_photo), alpha = 0.45f)
        },
        error = {
            Image(vectorResource(id = R.drawable.ic_broken_img), alpha = 0.45f)
        }
    )
}

@Composable
fun CharacterName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        style = MaterialTheme.typography.h6
    )
}

// @Preview
// @Composable
// fun previewList() {
//     StudycoroutinesTheme {
//         Column(
//             modifier = Modifier.padding(16.dp)
//         ) {
//             CharacterItem(character = CharacterUi(123, "", "teste"), onClick = onClick)
//             CharacterItem(character = CharacterUi(123, "", "teste1"), onClick = onClick)
//             CharacterItem(character = CharacterUi(123, "", "teste2"), onClick = onClick)
//         }
//     }
// }