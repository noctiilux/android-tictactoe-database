package com.example.tictactoe.ui.theme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tictactoe.R;

public class AddPlayers extends AppCompatActivity {
    PlayersDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // базового класса для правильной инициализации активности.
        setContentView(R.layout.activity_add_players); // пользовательский интерфейс для активности из файла макета activity_add_players.xml.

        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button startGameButton = findViewById(R.id.startGameButton);
        Button homeButton = findViewById(R.id.homeButton); // Получаем ссылку на кнопку "HOME"

        // Устанавливаем обработчик нажатия для кнопки "HOME"
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем интент для перехода на домашнюю страницу (замените MainActivity.class на вашу домашнюю активность)
                Intent intent = new Intent(AddPlayers.this, LeaderboardActivity.class);
                startActivity(intent); // Запускаем активность
            }
        });
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_SHORT).show();
                } else {

                    db = new PlayersDatabaseHelper(AddPlayers.this);
                    db.addPlayer(getPlayerTwoName);// Добавление нового игрока в базу данных
                    db.addPlayer( getPlayerOneName);// Добавление нового игрока в базу данных

                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });
    }
}