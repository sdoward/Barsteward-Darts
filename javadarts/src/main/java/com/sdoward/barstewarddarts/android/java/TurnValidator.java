package com.sdoward.barstewarddarts.android.java;

public class TurnValidator {

    private Throw firstThrow = null;
    private Throw secondThrow = null;
    private Throw thirdThrow = null;

    public void acceptThrow(Throw frow) {
        if (firstThrow == null) {
            firstThrow = frow;
        } else if (secondThrow == null) {
            secondThrow = frow;
        } else if (thirdThrow == null) {
            thirdThrow = frow;
        } else {
            throw new IllegalStateException("3 throws are complete. ");
        }
    }

    public boolean turnIsComplete() {
        return firstThrow != null && secondThrow != null && thirdThrow != null;
    }


    public Turn getTurn(){
        if (turnIsComplete()) {
            Turn turn = Turn.create(firstThrow, secondThrow, thirdThrow);
            resetTurn();
            return turn;
        } else {
            throw new IllegalStateException("3 throws haven't been completed");
        }

    }

    private void resetTurn() {
        firstThrow = null;
        secondThrow = null;
        thirdThrow = null;
    }

}
