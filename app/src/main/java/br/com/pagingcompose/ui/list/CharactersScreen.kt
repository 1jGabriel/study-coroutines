package br.com.pagingcompose.ui.list

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import br.com.pagingcompose.ui.composables.CharacterList

@Composable
fun CharactersScreen(listCharactersViewModel: ListCharactersViewModel, onClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Rick and Morty")
                }
            )
        },
        bodyContent = {
            CharacterList(characters = listCharactersViewModel.getCharacters(), onClick = onClick)
        }
    )
}
