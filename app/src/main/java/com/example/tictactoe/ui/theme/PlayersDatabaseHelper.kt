package com.example.tictactoe.ui.theme

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PlayersDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2 // Версия базы данных
        private const val DATABASE_NAME = "PlayersDatabase" // Имя базы данных
        private const val TABLE_PLAYERS = "Players" // Название таблицы
        private const val KEY_ID = "id" // Идентификатор
        private const val KEY_NAME = "name" // Имя игрока
        private const val KEY_WINS = "wins" // Количество побед
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Создание таблицы игроков при первом запуске базы данных
        val createPlayersTable = ("CREATE TABLE $TABLE_PLAYERS($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT, $KEY_WINS INTEGER)")
        db?.execSQL(createPlayersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Удаление и пересоздание таблицы при обновлении схемы
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PLAYERS")
        onCreate(db)
    }

    // Метод для добавления игрока с именем playerName и инициализацией побед в нуль
    fun addPlayer(playerName: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, playerName)
        values.put(KEY_WINS, 0)
        db.insert(TABLE_PLAYERS, null, values)
        db.close()
    }

    // Метод для увеличения числа побед игрока с именем playerName
    fun incrementPlayerWins(playerName: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        val currentWins = getPlayerWins(playerName)
        values.put(KEY_WINS, currentWins + 1)
        db.update(TABLE_PLAYERS, values, "$KEY_NAME = ?", arrayOf(playerName))
        db.close()
    }

    // Метод для получения числа побед игрока по его имени playerName
    @SuppressLint("Recycle", "Range")
    fun getPlayerWins(playerName: String): Int {
        val db = this.readableDatabase
        val query = "SELECT $KEY_WINS FROM $TABLE_PLAYERS WHERE $KEY_NAME = ?"
        val cursor: Cursor? = db.rawQuery(query, arrayOf(playerName))

        var wins = 0
        cursor?.use {
            if (it.moveToFirst()) {
                wins = it.getInt(it.getColumnIndex(KEY_WINS))
            }
        }

        cursor?.close()
        return wins
    }

    // Метод для очистки всех записей об игроках в базе данных
    fun clearAllPlayers() {
        val db = this.writableDatabase
        db.delete(TABLE_PLAYERS, null, null)
        db.close()
    }

    // Метод для чтения всех игроков с уникальными именами и количеством побед
    @SuppressLint("Recycle", "Range")
    fun readAllPlayers(): List<Player> {
        val playersList = mutableListOf<Player>()
        val db = this.readableDatabase
        val selectQuery = "SELECT DISTINCT $KEY_NAME, $KEY_WINS FROM $TABLE_PLAYERS"

        val cursor = db.rawQuery(selectQuery, null)

        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndex(KEY_NAME))
                val wins = it.getInt(it.getColumnIndex(KEY_WINS))

                val player = Player(name, wins)
                playersList.add(player)
            }
        }

        cursor?.close()
        return playersList
    }
}
