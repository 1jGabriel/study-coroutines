package br.com.pagingcompose.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.pagingcompose.navigation.Navigation.CHARACTERS_SCREEN
import br.com.pagingcompose.navigation.Navigation.CHARACTER_DETAIL_SCREEN

object Navigation {
    const val CHARACTERS_SCREEN = "characters"
    const val CHARACTER_DETAIL_SCREEN = "characterDetail/{id}"

    object CharacterDetailArgs {
        const val CHARACTER_ID = "id"
    }
}

class Actions(navController: NavHostController) {
    val openDetail: (String) -> Unit =
        { id -> navController.navigate("$CHARACTER_DETAIL_SCREEN/$id") }

    val openList: () -> Unit = {
        navController.navigate(CHARACTERS_SCREEN)
    }

    val navigateBack: () -> Unit = { navController.popBackStack() }
}