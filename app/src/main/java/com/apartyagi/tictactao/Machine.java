package com.apartyagi.tictactao;

import java.util.Random;

public class Machine {

    private static final Random RANDOM = new Random();
    private char[] elts;
    private char currentPlayer;
    private boolean ended;

    public Machine() {
        elts = new char[9];
        newGame();
    }

    public boolean isEnded() {
        return ended;
    }

    public char play(int x, int y) {
        if (!ended  &&  elts[3 * y + x] == ' ') {
            elts[3 * y + x] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    public char getmah(int x, int y) {
        return elts[3 * y + x];
    }

    public void newGame() {
        for (int i = 0; i  < elts.length; i++) {
            elts[i] = ' ';
        }

        currentPlayer = 'X';
        ended = false;
    }

    public char checkEnd() {
        for (int i = 0; i < 3; i++) {
            if (getmah(i, 0) != ' ' &&
                    getmah(i, 0) == getmah(i, 1)  &&
                    getmah(i, 1) == getmah(i, 2)) {
                ended = true;
                return getmah(i, 0);
            }

            if (getmah(0, i) != ' ' &&
                    getmah(0, i) == getmah(1, i)  &&
                    getmah(1, i) == getmah(2, i)) {
                ended = true;
                return getmah(0, i);
            }
        }

        if (getmah(0, 0) != ' '  &&
                getmah(0, 0) == getmah(1, 1)  &&
                getmah(1, 1) == getmah(2, 2)) {
            ended = true;
            return getmah(0, 0);
        }

        if (getmah(2, 0) != ' '  &&
                getmah(2, 0) == getmah(1, 1)  &&
                getmah(1, 1) == getmah(0, 2)) {
            ended = true;
            return getmah(2, 0);
        }

        for (int i = 0; i < 9; i++) {
            if (elts[i] == ' ')
                return ' ';
        }

        return 'T';
    }

    public char com() {
        if (!ended) {
            int position = -1;

            do {
                position = RANDOM.nextInt(9);
            } while (elts[position] != ' ');

            elts[position] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

}
