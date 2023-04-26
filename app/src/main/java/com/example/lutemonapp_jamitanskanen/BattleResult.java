package com.example.lutemonapp_jamitanskanen;

// The BattleResult class represents the outcome of a lutemon battle.
public class BattleResult {
    private String battleLog;
    private Lutemon winnerLutemon;
    private Lutemon loserLutemon;

    // Constructor initializes the battle log, winner, and loser lutemon.
    public BattleResult(String battleLog, Lutemon winnerLutemon, Lutemon loserLutemon) {
        this.battleLog = battleLog;
        this.winnerLutemon = winnerLutemon;
        this.loserLutemon = loserLutemon;
    }

    public String getBattleLog() {
        return battleLog;
    }

    public Lutemon getWinnerLutemon() {
        return winnerLutemon;
    }

    public Lutemon getLoserLutemon() {
        return loserLutemon;
    }
}
