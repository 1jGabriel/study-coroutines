package br.com.pagingcompose.ui.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import br.com.pagingcompose.ui.composables.CharacterList

@Composable
fun CharactersScreen(listCharactersViewModel: ListCharactersViewModel) {
    Scaffold(
        bodyContent = {
            CharacterList(characters = listCharactersViewModel.getCharacters())
        }
    )
}