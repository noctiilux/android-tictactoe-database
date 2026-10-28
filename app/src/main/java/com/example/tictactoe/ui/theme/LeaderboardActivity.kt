package com.example.tictactoe.ui.theme

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R


class LeaderboardActivity : AppCompatActivity() {
    private var playersDB: PlayersDatabaseHelper? = null // Экземпляр помощника базы данных игроков
    private var gameResultsTextView: TextView? = null // TextView для отображения результатов игры

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard) // Установка макета для этой активности

        playersDB = PlayersDatabaseHelper(this) // Инициализация помощника базы данных
        gameResultsTextView = findViewById(R.id.gameResultsTextView) // Нахождение TextView по идентификатору

        displayPlayersInfo() // Отображение информации об игроках

        val clearDataButton: Button = findViewById(R.id.clearDataButton) // Нахождение кнопки по идентификатору
        clearDataButton.setOnClickListener {
            clearAllPlayerData() // Очистка данных всех игроков
            displayPlayersInfo() // Повторное отображение информации об игроках после очистки
        }
    }

    // Метод для отображения информации об игроках в TextView
    private fun displayPlayersInfo() {
        val playersInfo = playersDB?.readAllPlayers() // Получение информации об игроках из базы данных
        val stringBuilder = StringBuilder()

        // добавление в массив информации об игроках из базы данных
        playersInfo?.let {
            for (player in it) {
                stringBuilder.append("Player: ${player.name}, Wins: ${player.wins}\n")
            }
        }

        gameResultsTextView?.text = stringBuilder.toString() // Установка текста в TextView
    }

    // Метод для очистки всех данных об игроках в базе данных
    private fun clearAllPlayerData() {
        playersDB?.clearAllPlayers() // Вызов метода для очистки данных игроков
    }
}
