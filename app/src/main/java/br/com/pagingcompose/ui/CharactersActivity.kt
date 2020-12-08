package br.com.pagingcompose.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.pagingcompose.navigation.Actions
import br.com.pagingcompose.navigation.Navigation.CHARACTERS_SCREEN
import br.com.pagingcompose.navigation.Navigation.CHARACTER_DETAIL_SCREEN
import br.com.pagingcompose.navigation.Navigation.CharacterDetailArgs.CHARACTER_ID
import br.com.pagingcompose.ui.detail.CharactersDetailScreen
import br.com.pagingcompose.ui.detail.DetailViewModel
import br.com.pagingcompose.ui.list.CharactersScreen
import br.com.pagingcompose.ui.list.ListCharactersViewModel
import br.com.pagingcompose.ui.ui.StudyComposeTheme
import org.koin.android.ext.android.inject

class CharactersActivity : AppCompatActivity() {
    private val mainViewModel: ListCharactersViewModel by inject()
    private val detailViewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val actions = remember(navController) { Actions(navController) }

            StudyComposeTheme {
                NavHost(
                    navController = navController,
                    startDestination = CHARACTERS_SCREEN
                ) {
                    composable(route = CHARACTERS_SCREEN) {
                        CharactersScreen(
                            listCharactersViewModel = mainViewModel,
                            actions.openDetail
                        )
                    }

                    composable(
                        route = "${CHARACTER_DETAIL_SCREEN}/{$CHARACTER_ID}",
                        arguments = listOf(navArgument(CHARACTER_ID) {
                            type = NavType.StringType
                        })
                    ) {
                        CharactersDetailScreen(
                            viewModel = detailViewModel,
                            id = it.arguments?.getString(CHARACTER_ID),
                            navigateUp = actions.navigateBack
                        )
                    }
                }
            }
        }
    }
}
