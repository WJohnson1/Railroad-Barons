package student;

import javafx.application.Application;
import model.*;
import model.Card;
import model.Deck;
import model.RailroadMap;
import model.Route;
import model.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class is what runs the RailroadBarons Game.
 * @authors Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Game implements model.RailroadBarons{
     private Collection<RailroadBaronsObserver> observers = new ArrayList<>();
     private Collection<Player> players = new ArrayList<>();
     private student.Deck deck = new student.Deck();
     private model.RailroadMap railroadMap;

    /**
     * Constructor for Game. This creates four players to play the game.
     */
    public Game() {
         for (int i = 0; i < 4; i++) {
             RailroadBaronPlayer p = new RailroadBaronPlayer(i);
             players.add(p);
         }
     }

    /**
     * Adds a new {@linkplain RailroadBaronsObserver observer} to the
     * {@linkplain Collection collection} of observers that will be notified
     * when the state of the game changes. Game state changes include:
     * <ul>
     *     <li>A player's turn begins.</li>
     *     <li>A player's turn ends.</li>
     *     <li>The game is over.</li>
     * </ul>
     *
     * @param observer The {@link RailroadBaronsObserver} to add to the
     *                 {@link Collection} of observers.
     */
     @Override
     public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
         observers.add(observer);
     }

    /**
     * Removes the {@linkplain RailroadBaronsObserver observer} from the
     * collection of observers that will be notified when the state of the
     * game changes.
     *
     * @param observer The {@link RailroadBaronsObserver} to remove.
     */
     @Override
     public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {
        observers.remove(observer);
     }

    /**
     * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the
     * specified {@linkplain model.RailroadMap map} and a default {@linkplain Deck
     * deck of cards}. If a game is currently in progress, the progress is
     * lost. There is no warning!
     *
     * By default, a new game begins with:
     * <ul>
     *     <li>A default deck that contains 20 of each color of card and 20
     *     wild cards.</li>
     *     <li>4 players, each of which has 50 train pieces.</li>
     *     <li>An initial hand of 4 cards dealt from the deck to each
     *     player</li>
     * </ul>
     *
     * @param map The {@link RailroadMap} on which the game will be played.
     */
     @Override
     public void startAGameWith(model.RailroadMap map) {
         railroadMap = map;
         deck.reset();
         model.Card[] hand1 = new model.Card[5];
         model.Card[] hand2 = new model.Card[5];
         model.Card[] hand3 = new model.Card[5];
         model.Card[] hand4 = new model.Card[5];
         for ( int i = 0;i<4;i++){
             model.Card c = deck.drawACard();
             model.Card c1 = deck.drawACard();
             model.Card c2 = deck.drawACard();
             model.Card c3 = deck.drawACard();
             hand1[i] = c;
             hand2[i] = c1;
             hand3[i] = c2;
             hand4[i] = c3;
         }
         ((Player)(players.toArray()[0])).reset(hand1);
         ((RailroadBaronPlayer)(players.toArray()[0])).setMap(railroadMap);
         ((Player)(players.toArray()[1])).reset(hand2);
         ((RailroadBaronPlayer)(players.toArray()[1])).setMap(railroadMap);
         ((Player)(players.toArray()[2])).reset(hand3);
         ((RailroadBaronPlayer)(players.toArray()[2])).setMap(railroadMap);
         ((Player)(players.toArray()[3])).reset(hand4);
         ((RailroadBaronPlayer)(players.toArray()[3])).setMap(railroadMap);
         Pair p = new Pair(deck.drawACard(),deck.drawACard());
         getCurrentPlayer().startTurn(p);
         for (RailroadBaronsObserver ob : this.observers){
             ob.turnStarted(this, getCurrentPlayer());
         }
     }

    /**
     * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the
     * specified {@linkplain RailroadMap map} and {@linkplain Deck deck of
     * cards}. This means that the game should work with any implementation of
     * the {@link Deck} interface (not just a specific implementation)!
     * Otherwise, the starting state of the game is the same as a
     * {@linkplain #startAGameWith(RailroadMap) normal game}.
     *
     * @param map The {@link RailroadMap} on which the game will be played.
     * @param deck The {@link Deck} of cards used to play the game. This may
     *             be ANY implementation of the {@link Deck} interface,
     *             meaning that a valid implementation of the
     *             {@link RailroadBarons} interface should use only the
     *             {@link Deck} interface and not a specific implementation.
     */
     @Override
     public void startAGameWith(model.RailroadMap map, Deck deck) {
        railroadMap = map;
        this.deck = (student.Deck) deck;
         model.Card[] hand1 = new model.Card[4];
         Arrays.fill(hand1,Card.NONE);

         model.Card[] hand2 = new model.Card[4];
         Arrays.fill(hand2,Card.NONE);

         model.Card[] hand3 = new model.Card[4];
         Arrays.fill(hand3,Card.NONE);

         model.Card[] hand4 = new model.Card[4];
         Arrays.fill(hand4,Card.NONE);
         for ( int i = 0;i<4;i++){
             model.Card c = this.deck.drawACard();
             model.Card c1 = this.deck.drawACard();
             model.Card c2 = this.deck.drawACard();
             model.Card c3 = this.deck.drawACard();
             hand1[i] = c;
             hand2[i] = c1;
             hand3[i] = c2;
             hand4[i] = c3;
         }
         ((Player)(players.toArray()[0])).reset(hand1);
         ((RailroadBaronPlayer)(players.toArray()[0])).setMap(railroadMap);
         ((Player)(players.toArray()[1])).reset(hand2);
         ((RailroadBaronPlayer)(players.toArray()[1])).setMap(railroadMap);
         ((Player)(players.toArray()[2])).reset(hand3);
         ((RailroadBaronPlayer)(players.toArray()[2])).setMap(railroadMap);
         ((Player)(players.toArray()[3])).reset(hand4);
         ((RailroadBaronPlayer)(players.toArray()[3])).setMap(railroadMap);
         Pair p = new Pair(deck.drawACard(),deck.drawACard());
         getCurrentPlayer().startTurn(p);
         for (RailroadBaronsObserver ob : this.observers){
             ob.turnStarted(this, getCurrentPlayer());
         }
     }

    /**
     * Returns the {@linkplain RailroadMap map} currently being used for play.
     * If a game is not in progress, this may be null!
     *
     * @return The {@link RailroadMap} being used for play.
     */
     @Override
     public model.RailroadMap getRailroadMap() {
          return railroadMap;
     }

    /**
     * Returns the number of {@linkplain Card cards} that remain to be dealt
     * in the current game's {@linkplain Deck deck}.
     *
     * @return The number of cards that have not yet been dealt in the game's
     * {@link Deck}.
     */
     @Override
     public int numberOfCardsRemaining() {
          return deck.numberOfCardsRemaining();
     }

    /**
     * Returns true if the current {@linkplain Player player} can claim the
     * {@linkplain model.Route route} at the specified location, i.e. the player has
     * enough cards and pieces, and the route is not currently claimed by
     * another player. Should delegate to the
     * {@link Player#canClaimRoute(model.Route)} method on the current player.
     *
     * @param row The row of a {@link model.Track} in the {@link model.Route} to check.
     * @param col The column of a {@link Track} in the {@link model.Route} to check.
     * @return True iff the {@link Route} can be claimed by the current
     * player.
     */
     @Override
     public boolean canCurrentPlayerClaimRoute(int row, int col) {
         Player player = this.getCurrentPlayer();
         return player.canClaimRoute(getRailroadMap().getRoute(row,col));
     }

    /**
     * Attempts to claim the {@linkplain Route route} at the specified
     * location on behalf of the current {@linkplain Player player}.
     *
     * @param row The row of a {@link Track} in the {@link Route} to claim.
     * @param col The column of a {@link Track} in the {@link Route} to claim.
     * @throws RailroadBaronsException If the {@link Route} cannot be claimed
     * by the current player.
     */
     @Override
     public void claimRoute(int row, int col) throws RailroadBaronsException {
        getCurrentPlayer().claimRoute(getRailroadMap().getRoute(row,col));
        getRailroadMap().routeClaimed(getRailroadMap().getRoute(row,col));
        for (int i = 0; i < 4; i++){
            ((RailroadBaronPlayer)(players.toArray()[i])).setMap(getRailroadMap());
        }
     }

    /**
     * Called when the current {@linkplain Player player} ends their turn.
     */
     @Override
     public void endTurn() {
         for (RailroadBaronsObserver ob : this.observers){
             ob.turnEnded(this, getCurrentPlayer());
         }
         Player p = getCurrentPlayer();
         ((RailroadBaronPlayer) getCurrentPlayer()).changeAlreadyClaimed();

         players.remove(getCurrentPlayer());
         players.add(p);
         if (!this.gameIsOver()) {
             Pair pair = new Pair(this.deck.drawACard(), this.deck.drawACard());
             getCurrentPlayer().startTurn(pair);
             for (RailroadBaronsObserver ob : this.observers) {
                 ob.turnStarted(this, getCurrentPlayer());
             }
         }
         else{
             for (RailroadBaronsObserver ob : this.observers) {
                 Player p1 = getCurrentPlayer();
                 for (Player p2 : players) {
                     if (p2.getScore()>p1.getScore()){
                         p1 = p2;
                     }
                 }
                 ob.gameOver(this, p1);
             }
         }
     }

    /**
     * Returns the {@linkplain Player player} whose turn it is.
     *
     * @return The {@link Player} that is currently taking a turn.
     */
     @Override
     public Player getCurrentPlayer() {
         return ((ArrayList<Player>) players).get(0);
     }

    /**
     * Returns all of the {@linkplain Player players} currently playing the
     * game.
     *
     * @return The {@link Player Players} currently playing the game.
     */
    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }


    /**
     * Indicates whether or not the game is over. This occurs when no more
     * plays can be made. Reasons include:
     * <ul>
     *     <li>No one player has enough pieces to claim a route.</li>
     *     <li>No one player has enough cards to claim a route.</li>
     *     <li>All routes have been claimed.</li>
     * </ul>
     *
     * @return True if the game is over, false otherwise.
     */
     @Override
     public boolean gameIsOver() {
         boolean a = true;
         boolean b = true;
         for (model.Route route: getRailroadMap().getRoutes()){
             if (route.getBaron()==Baron.UNCLAIMED){
                 a = false;
             }
         }
         for (Player player: players){
             if (player.canContinuePlaying(getRailroadMap().getLengthOfShortestUnclaimedRoute())){
                 if (deck.numberOfCardsRemaining()>0) {
                     b = false;
                 }
             }
         }
         if (a){
             return true;
         }
         else {
             if (!b){
                 return b;
             }
             return !b;
         }
     }

    /**
     * Application entry point for the Game.
     * It starts the JavaFX application.
     *
     * @param args Command line arguments.
     */
     public static void main(String[] args) {
         Application.launch(view.RailroadBaronsUI.class,args);
     }
}

