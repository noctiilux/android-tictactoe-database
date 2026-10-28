# 📱 Android Tic-Tac-Toe Game with Persistent Database

📋 *[Читать на русском языке](README_RU.md)*

---

A modern Android application for the classic Tic-Tac-Toe game. It features local pass-and-play multiplayer, interactive state updates, and an integrated local database to maintain cross-session leaderboards and win-statistics.

## 🛠️ Tech Stack & Hybrid Architecture

The application implements a clean, decoupled architecture leveraging modern Android frameworks and hybrid languages:

*   **Core UI Components:** Developed using **Java** alongside **Jetpack Compose** (HorizontalPager) and XML Layout Editor for responsive views.
*   **Database & Storage:** Powered by **Kotlin** and native **SQLite** helpers to efficiently handle structural user properties.
*   **Architecture Pattern:** Follows an MVVM-oriented flow featuring `UI Controllers`, `LiveData` streams, and data repositories.

## 📸 Application Interface / Screenshots

<details>
<summary><b>Click to expand / Нажмите, чтобы открыть скриншоты</b></summary>

### 🔑 Players Registration & Setup
<img src="auth.png" alt="Registration Screen" width="300" />

### 🕹️ Active Gameplay View
<img src="game.png" alt="Gameplay Screen" width="300" />

### 🏆 Match Results Dialog
<img src="result.png" alt="Result Dialog" width="300" />

### 📊 Persistent Database Leaderboard
<img src="leaderboard.png" alt="Leaderboard Screen" width="300" />

</details>

---

## 📁 Repository Structure

*   `/app/src/main/java` — Core application logic containing Java classes (`AddPlayers`, `MainActivity`, `ResultDialog`).
*   `/app/src/main/kotlin` — Database威 implementations, helpers, and Kotlin models (`PlayersDatabaseHelper`, `Player`).
*   `/app/src/main/res` — Layout asset xml files, theme structures, custom color schemas, and graphical border styles.
