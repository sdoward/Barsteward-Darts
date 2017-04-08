package com.sdoward.barstewarddarts.android.java;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameTest {

    @Mock
    private Game.GameInstructions instructions;
    private Game game;
    private List<Player> players = new ArrayList<>(2);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        players.add(new Player("name1", 1));
        players.add(new Player("name2", 2));

        game = new Game(players, instructions);
    }

    @Test
    public void shouldDisplayPlayers() {
        game.start();
        verify(instructions).displayCurrentPlayer("name1");
        game.acceptThrow(Throw.innerBull());
        game.acceptThrow(Throw.innerBull());
        game.acceptThrow(Throw.innerBull());
        verify(instructions).displayCurrentPlayer("name2");
        game.acceptThrow(Throw.innerBull());
        game.acceptThrow(Throw.innerBull());
        game.acceptThrow(Throw.innerBull());
        verify(instructions, times(2)).displayCurrentPlayer("name1");
    }

    @Test
    public void shouldDecrementLifeWhenOwnNumberHit() {
        game.acceptThrow(Throw.single(1));
        game.acceptThrow(Throw.single(1));
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(0).getLives()).isEqualTo(1);
    }

    @Test
    public void shouldRemove2LivesWhenOwnNumberHit() {
        game.acceptThrow(Throw.duble(1));
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(0).getLives()).isEqualTo(1);
    }

    @Test
    public void shouldRemove3LivesWhenOwnNumberHit() {
        game.acceptThrow(Throw.triple(1));
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(0).getLives()).isEqualTo(0);
    }

    @Test
    public void shouldIncrementLifeWhenOtherPlayersNumberHit() {
        game.acceptThrow(Throw.single(2));
        game.acceptThrow(Throw.single(2));
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(1).getLives()).isEqualTo(5);
    }

    @Test
    public void shouldAdd2LivesWhenDoubleOtherOtherPlayer() {
        game.acceptThrow(Throw.duble(2));
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(1).getLives()).isEqualTo(5);
    }

    @Test
    public void shouldAdd3LivesWhenDoubleOtherOtherPlayer() {
        game.acceptThrow(Throw.triple(2));
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        assertThat(game.getPlayers().get(1).getLives()).isEqualTo(6);
    }

    @Test
    public void shouldEndGameWhenAnyLifeIsBelow1() {
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.triple(1));
        verify(instructions).displayWinner("name1");
    }

    @Test
    public void shouldNotEndGameWhenNoLivesAreBelow1() {
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        game.acceptThrow(Throw.miss());
        verify(instructions, never()).displayWinner(anyString());
    }

    @Test
    public void shouldDisplayListOfDrinkingInstructions() {
        game.acceptThrow(Throw.triple(1));
        game.acceptThrow(Throw.duble(2));
        game.acceptThrow(Throw.miss());

        List<String> expectingDrinkingInstructions = new ArrayList(2);
        expectingDrinkingInstructions.add("name1 drink 3");
        expectingDrinkingInstructions.add("name2 drink 2");

        verify(instructions).displayDrinkingInstructions(expectingDrinkingInstructions);
    }

}