package game;

import resurces.Player;

public class StatsCommands {
    /**
     * total games played
     * @param p1 player one
     * @param p2 player two
     * @return sum of both players wins
     */
    public final int getTotalGamesPlayed(final Player p1, final Player p2) {
        return p1.getWins() + p2.getWins();
    }

    /**
     * retruns the number of wins of a player
     * @param player the player we get the wins from
     * @return number of wins of the player
     */
    public final int getPlayerWins(final Player player) {
        return player.getWins();
    }
}
