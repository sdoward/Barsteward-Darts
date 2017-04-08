package com.sdoward.barstewarddarts.android.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Game {

    private final GameInstructions gameInstructions;
    private final List<Player> players;
    private final List<Turn> turns = new ArrayList(100);
    private final TurnValidator turnValidator = new TurnValidator();

    private int pointer = 0;

    public Game(List<Player> players, GameInstructions gameInstructions) {
        this.players = players;
        this.gameInstructions = gameInstructions;
    }

    public void start() {
        displayCurrentPlayer();
        pointer++;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void acceptThrow(Throw thro) {
        turnValidator.acceptThrow(thro);
        if (turnValidator.turnIsComplete()) {
            acceptTurn(turnValidator.getTurn());
        }
    }

    private void acceptTurn(Turn turn) {
        reconsileLives(turn.getFirstThrow());
        reconsileLives(turn.getSecondThrow());
        reconsileLives(turn.getThirdThrow());
        turns.add(turn);
        displayDrinkingInstructions(turn);
        displayCurrentPlayer();
        if (hasGameEnded()) {
            gameInstructions.displayWinner(players.get(pointer).getName());
        }
        if (pointer == players.size() - 1) {
            pointer = 0;
        } else {
            pointer++;
        }
    }

    private void displayCurrentPlayer() {
        gameInstructions.displayCurrentPlayer(players.get(pointer).getName());
    }

    private void reconsileLives(Throw thro) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (i == pointer) {
                if (thro.getNumber() == player.getNumber())
                    player.setLives(player.getLives() - thro.getLifeValue());
            } else {
                if (thro.getNumber() == player.getNumber())
                    player.setLives(player.getLives() + thro.getLifeValue());
            }
        }
    }

    private void displayDrinkingInstructions(Turn turn) {
        Map<Player, Integer> drinkers = new HashMap();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            addDrinking(drinkers, turn.getFirstThrow(), player);
            addDrinking(drinkers, turn.getSecondThrow(), player);
            addDrinking(drinkers, turn.getThirdThrow(), player);
        }
        List<String> drinkingInstructions = new ArrayList(drinkers.size());
        for (Map.Entry<Player, Integer> entry: drinkers.entrySet()) {
            drinkingInstructions.add(entry.getKey().getName() + " drink " + entry.getValue());
        }
        gameInstructions.displayDrinkingInstructions(drinkingInstructions);
    }

    private void addDrinking(Map<Player, Integer> drinkers, Throw thro, Player player) {
        if (thro.getNumber() == player.getNumber()) {
            if (drinkers.containsKey(player)) {
                int newDrinkValue = drinkers.get(player) + thro.getLifeValue();
                drinkers.remove(player);
                drinkers.put(player, newDrinkValue);
            } else {
                drinkers.put(player, thro.getLifeValue());
            }
        }
    }

    private boolean hasGameEnded() {
        for (Player player : players) {
            if (player.getLives() < 1)
                return true;
        }
        return false;
    }

    interface GameInstructions {

        void displayCurrentPlayer(String name);

        void displayWinner(String name);

        void displayDrinkingInstructions(List<String> drinkers);

    }

}
