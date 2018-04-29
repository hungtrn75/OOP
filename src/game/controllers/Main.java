
package game.controllers;

import game.graphics.GameState;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.updateState(GameState.STATE.START_MENU);
    }
}
