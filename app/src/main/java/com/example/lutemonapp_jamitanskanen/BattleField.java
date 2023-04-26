package com.example.lutemonapp_jamitanskanen;

public class BattleField {
    private int attackCounter;

    public BattleField() {
        attackCounter = 0;
    }

    // The fight method simulates a battle between two lutemons and returns the result as a BattleResult object.
    public BattleResult fight(int lutemonIdA, int lutemonIdB, Storage storage) {
        Lutemon lutemonA = storage.getLutemon(lutemonIdA);
        Lutemon lutemonB = storage.getLutemon(lutemonIdB);

        if (lutemonA == null || lutemonB == null) {
            return null;
        }
        // StringBuilder object to store the battle log as the fight progresses
        StringBuilder battleLog = new StringBuilder();

        // Introduce both lutemons and show their stats
        battleLog.append("1: " + lutemonA.getStats() + "\n");
        battleLog.append("2: " + lutemonB.getStats() + "\n");

        Lutemon winner;

        // Main battle loop, continues until one lutemon's health reaches 0
        while (true) {
            battleLog.append(lutemonA.getName() + " 🗡️ attacks " + lutemonB.getName() + "\n");

            attackCounter++;
            int attackValue = lutemonA.getAttack();

            // If it's the third attack, use a random attack value
            if (attackCounter % 3 == 0) {
                attackValue = 1 + (int)(Math.random() * 10);
                battleLog.append("🎲 Random attack value: " + attackValue + "\n");
            }

            lutemonB.defense(attackValue);
            if (lutemonB.getHealth() <= 0) {
                battleLog.append(lutemonB.getName() + " 💀 gets killed.\nThe battle is over.\n");

                lutemonA.setExperience(lutemonA.getExperience() + 1);
                winner = lutemonA;
                break;
            } else {
                battleLog.append(lutemonB.getName() + " 🛡️ manages to escape death.\n");

                // Shows lutemon stats after managing to escape death
                battleLog.append("2: " + lutemonB.getStats() + "\n");
                battleLog.append("1: " + lutemonA.getStats() + "\n");
            }

            // Swaps lutemons
            Lutemon temp = lutemonA;
            lutemonA = lutemonB;
            lutemonB = temp;
        }

        return new BattleResult(battleLog.toString(), winner, lutemonB); // Return the battle log, the winner, and the loser lutemon
    }
}
