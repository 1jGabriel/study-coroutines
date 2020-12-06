package br.com.pagingcompose.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import br.com.pagingcompose.ui.list.CharactersScreen
import br.com.pagingcompose.ui.list.ListCharactersViewModel
import br.com.pagingcompose.ui.ui.StudycoroutinesTheme
import org.koin.android.ext.android.inject

class CharactersActivity : AppCompatActivity() {
    private val mainViewModel: ListCharactersViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudycoroutinesTheme {
                CharactersScreen(listCharactersViewModel = mainViewModel)
            }
        }
    }
}
