package com.sdoward.barstewarddarts.android.java;

public class Player {

    private final String name;
    private final int number;
    private int lives = 3;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void incrementLife() {
        lives++;
    }

    public void decrementLife() {
        lives--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (number != player.number) return false;
        if (lives != player.lives) return false;
        return name.equals(player.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + number;
        result = 31 * result + lives;
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", lives=" + lives +
                '}';
    }
}
