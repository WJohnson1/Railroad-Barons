package student;

import model.*;
import model.Card;
import model.Pair;
import model.Route;
import java.util.*;

/**
 * This class is for each individual player in the Railroads Baron game.
 * @authors Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class RailroadBaronPlayer implements Player {
    private Baron baron;
    private model.Pair lastTwoCards = new student.Pair(model.Card.NONE, model.Card.NONE);
    private ArrayList<model.Card> hand = new ArrayList<>();
    private  int score;
    private int trainPieces;
    private boolean alreadyClaimed = false;
    private Set<model.PlayerObserver> observers = new HashSet<>();
    private Collection<model.Route> claimedRoutes = new ArrayList<>();
    private model.RailroadMap map;
    private boolean NtoS = true;
    private boolean WtoE = true;
    /**
     * Constructor for RailroadBaronPlayer. This determines what type of baron this is.
     * @param i type of case
     */
    public  RailroadBaronPlayer(int i){
        switch (i){
            case 0:
                this.baron = Baron.BLUE;
                break;
            case 1:
                this.baron = Baron.GREEN;
                break;
            case 2:
                this.baron = Baron.RED;
                break;
            case 3:
                this.baron = Baron.YELLOW;
                break;
        }
        this.trainPieces = 45;
        this.score = 0;
    }


    /**
     * Sets the map for the player that will be used for the bonus score system in the Railroad Barons game
     * @param map map of the current game.
     */
    public void setMap(model.RailroadMap map) {
        this.map = map;
    }

    /**
     * This is called at the start of every game to reset the player to its
     * initial state:
     * <ul>
     *     <li>Number of train pieces reset to the starting number of 45.</li>
     *     <li>All remaining {@link Card cards} cleared from hand.</li>
     *     <li>Score reset to 0.</li>
     *     <li>Claimed {@link Route routes} cleared.</li>
     *     <li>Sets the most recently dealt {@link Pair} of cards to two
     *     {@link Card#NONE} values.</li>
     * </ul>
     *
     * @param dealt The hand of {@link Card cards} dealt to the player at the
     *              start of the game. By default this will be 4 cards.
     */
    @Override
    public void reset(model.Card... dealt) {
        trainPieces = 45;
        hand = new ArrayList<>();
        for (int i = 0; i<dealt.length-1; i++){
            hand.add(dealt[i]);
        }
        score = 0;
        claimedRoutes = new ArrayList<>();
        lastTwoCards = new student.Pair(Card.NONE,Card.NONE);

    }

    /**
     * Adds an {@linkplain PlayerObserver observer} that will be notified when
     * the player changes in some way.
     *
     * @param observer The new {@link PlayerObserver}.
     */
    @Override
    public void addPlayerObserver(model.PlayerObserver observer) {
        observers.add(observer);

        observer.playerChanged(this);
    }

    /**
     * Removes an {@linkplain PlayerObserver observer} so that it is no longer
     * notified when the player changes in some way.
     *
     * @param observer The {@link PlayerObserver} to remove.
     */
    @Override
    public void removePlayerObserver(model.PlayerObserver observer) {
        observers.remove(observer);
    }

    /**
     * The {@linkplain Baron baron} as which this player is playing the game.
     *
     * @return The {@link Baron} as which this player is playing.
     */
    @Override
    public Baron getBaron() {
        return this.baron;
    }

    /**
     * Used to start the player's next turn. A {@linkplain Pair pair of cards}
     * is dealt to the player, and the player is once again able to claim a
     * {@linkplain Route route} on the {@linkplain RailroadMap map}.
     *
     * @param dealt a {@linkplain Pair pair of cards} to the player. Note that
     * one or both of these cards may have a value of {@link Card#NONE}.
     */
    @Override
    public void startTurn(Pair dealt) {
        this.lastTwoCards = dealt;
        hand.add(dealt.getFirstCard());
        hand.add(dealt.getSecondCard());
        this.alreadyClaimed = false;
    }

    /**
     * Returns the most recently dealt {@linkplain Pair pair of cards}. Note
     * that one or both of the {@linkplain Card cards} may have a value of
     * {@link Card#NONE}.
     *
     * @return The most recently dealt {@link Pair} of {@link Card Cards}.
     */
    @Override
    public Pair getLastTwoCards() {
        return lastTwoCards;
    }

    /**
     * Returns the number of the specific kind of {@linkplain Card card} that
     * the player currently has in hand. Note that the number may be 0.
     *
     * @param card The {@link Card} of interest.
     * @return The number of the specified type of {@link Card} that the
     * player currently has in hand.
     */
    @Override
    public int countCardsInHand(model.Card card) {
            int count = 0;
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i) == card) {
                    count++;
                }
            }
            return count;
    }

    /**
     * Returns the number of game pieces that the player has remaining. Note
     * that the number may be 0.
     *
     * @return The number of game pieces that the player has remaining.
     */
    @Override
    public int getNumberOfPieces() {
        return trainPieces;
    }

    /**
     * Returns true iff the following conditions are true:
     *
     * <ul>
     *     <li>The {@linkplain Route route} is not already claimed by this or
     *     some other {@linkplain Baron baron}.</li>
     *     <li>The player has not already claimed a route this turn (players
     *     are limited to one claim per turn).</li>
     *     <li>The player has enough {@linkplain Card cards} (including ONE
     *     {@linkplain Card#WILD wild card, if necessary}) to claim the
     *     route.</li>
     *     <li>The player has enough train pieces to claim the route.</li>
     * </ul>
     *
     * @param route The {@link Route} being tested to determine whether or not
     *              the player is able to claim it.
     * @return True if the player is able to claim the specified
     * {@link Route}, and false otherwise.
     */
    @Override
    public boolean canClaimRoute(Route route) {
        if (route.getBaron() == Baron.UNCLAIMED){
            if (trainPieces>=route.getLength()) {
                if (!alreadyClaimed) {
                    if ((this.countCardsInHand(Card.BLACK)) >= route.getLength() || ((this.countCardsInHand(Card.BLACK) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.BLUE)) >= route.getLength() || ((this.countCardsInHand(Card.BLUE) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.GREEN)) >= route.getLength() || ((this.countCardsInHand(Card.GREEN) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.ORANGE)) >= route.getLength() || ((this.countCardsInHand(Card.ORANGE) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.PINK)) >= route.getLength() || ((this.countCardsInHand(Card.PINK) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.RED)) >= route.getLength() || ((this.countCardsInHand(Card.RED) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.WHITE)) >= route.getLength() || ((this.countCardsInHand(Card.WHITE) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    } else if ((this.countCardsInHand(Card.YELLOW)) >= route.getLength() || ((this.countCardsInHand(Card.YELLOW) + 1) >= route.getLength() && this.countCardsInHand(Card.WILD) > 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Claims the given {@linkplain Route route} on behalf of this player's
     * {@linkplain Baron Railroad Baron}. It is possible that the player has
     * enough cards in hand to claim the route by using different
     * combinations of {@linkplain Card card}. It is up to the implementor to
     * employ an algorithm that determines which cards to use, but here are
     * some suggestions:
     * <ul>
     *     <li>Use the color with the lowest number of cards necessary to
     *     match the length of the route.</li>
     *     <li>Do not use a wild card unless absolutely necessary (i.e. the
     *     player has length-1 cards of some color in hand and it is the most
     *     numerous card that the player holds).</li>
     * </ul>
     *
     * @param route The {@link Route} to claim.
     *
     * @throws RailroadBaronsException If the {@link Route} cannot be claimed,
     * i.e. if the {@link #canClaimRoute(Route)} method returns false.
     */
    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {
        boolean completed = false;
        int w = 0;
        int a = 0;
        for (Card card: this.hand) {
            if (card!=Card.NONE && (this.countCardsInHand(card) >= route.getLength() && !card.equals(Card.WILD))) {
                for (int i = 0; i < this.hand.size(); i++) {
                    if (this.hand.get(i).equals(card) && a < route.getLength()) {
                        this.hand.set(i, Card.NONE);
                        a++;
                    }
                }
                completed = true;
            }
        }
        a = 0;
        if (!completed) {
            for (Card c : hand) {
                if (c!=Card.NONE && (c!=Card.WILD&&(this.countCardsInHand(c) + this.countCardsInHand(Card.WILD)) >= route.getLength())) {
                    for (int j = 0; j < hand.size(); j++) {
                        if (this.hand.get(j).equals(c) && a < route.getLength()) {
                            this.hand.set(j, Card.NONE);
                            a++;

                        } else if (this.hand.get(j).equals(Card.WILD) && w < 1) {
                            this.hand.set(j, Card.NONE);
                            w++;
                            a++;
                        }
                    }
                    completed = true;
                }
            }
        }
        if (!completed){
            throw new RailroadBaronsException("Route cannot be claimed");
        }
        this.trainPieces = this.trainPieces-route.getLength();
        this.score+=route.getPointValue();
        claimedRoutes.add(route);
        route.claim(this.baron);
        alreadyClaimed = true;
        for(model.Route startpoint: claimedRoutes){
            if ((((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.NORTHWESTERNMOST ||
                    ((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.NORTHEASTERNMOST ||
                    ((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.NORTHERNMOST)&NtoS){
                for (model.Route endpoint:claimedRoutes){
                    if (((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.SOUTHWESTERNMOST ||
                            ((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.SOUTHEASTERNMOST ||
                            ((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.SOUTHERNMOST) {
                        List<model.Station> path = buildPathDFS(startpoint.getOrigin(), endpoint.getDestination());
                        if (path != null) {
                            score += 5*map.getRows();
                            NtoS = false;
                        }
                    }
                }
            }
            else if ((((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.WESTERNMOST ||
                    ((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.NORTHWESTERNMOST ||
                    ((student.Station)(startpoint.getOrigin())).getStationLoc()==StationLocation.SOUTHWESTERNMOST) && WtoE){
                for (model.Route endpoint:claimedRoutes){
                    if (((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.NORTHEASTERNMOST ||
                            ((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.SOUTHEASTERNMOST ||
                            ((student.Station)(endpoint.getDestination())).getStationLoc()==StationLocation.EASTERNMOST) {
                        List<model.Station> path = buildPathDFS(startpoint.getOrigin(), endpoint.getDestination());
                        if (path != null) {
                            score += 5*map.getRows();
                            WtoE = false;
                        }
                    }
                }
            }
        }
        for (PlayerObserver playerObserver: observers){
            playerObserver.playerChanged(this);
        }
    }

    /**
     * Returns the {@linkplain Collection collection} of {@linkplain Route
     * routes} claimed by this player.
     *
     * @return The {@link Collection} of {@linkplain Route Routes} claimed by
     * this player.
     */
    @Override
    public Collection<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    /**
     * Returns the players current score based on the
     * {@linkplain Route#getPointValue() point value} of each
     * {@linkplain Route route} that the player has currently claimed.
     *
     * @return The player's current score.
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * Returns true iff the following conditions are true:
     *
     * <ul>
     *     <li>The player has enough {@linkplain Card cards} (including
     *     {@linkplain Card#WILD wild cards}) to claim a
     *     {@linkplain Route route} of the specified length.</li>
     *     <li>The player has enough train pieces to claim a
     *     {@linkplain Route route} of the specified length.</li>
     * </ul>
     *
     * @param shortestUnclaimedRoute The length of the shortest unclaimed
     *                               {@link Route} in the current game.
     *
     * @return True if the player can claim such a {@link Route route}, and
     * false otherwise.
     */
    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        for (Card c: hand){
            if ((this.countCardsInHand(c) + this.countCardsInHand(Card.WILD))>=shortestUnclaimedRoute){
                if (trainPieces >= shortestUnclaimedRoute){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Changes the value of whether the player has already played
     */
    public void changeAlreadyClaimed() {
         alreadyClaimed = !alreadyClaimed;
    }

    /**
     * Returns string representation of the baron
     * @return the string representation of the baron
     */
    @Override
    public String toString(){
        return baron + " Baron";
    }

    /**
     * Create a path from a starting node to a finishing node if such
     * a path exists.
     * Uses depth-first search to determine if a path exists.
     * This pair of public and private methods uses a path list.
     * Another approach, shown below in the BFS code, is associating a
     * predecessor with each node.
     *
     * @rit.pre the arguments correspond to nodes in the graph
     * @param startNode  with starting node
     * @param finishNode finishing node
     * @return an iteration from start to finish, or null if none exists
     */
    private List< model.Station > buildPathDFS( model.Station startNode, model.Station finishNode ) {
        // assumes input check occurs previously
        // Construct a visited set of all nodes reachable from the start
        Set< model.Station > visited = new HashSet<>();
        //visited.add( startNode );
        return buildPathDFS( startNode, finishNode, visited );
    }

    /**
     * Recursive function that visits all nodes reachable from the given node
     * in depth-first search fashion. Visited list is updated in the process.
     *
     * @param start the node from which to search
     * @param finish the node for which to search
     * @param visited the list of nodes that have already been visited
     * @return the path from start to finish as a List,
     *         or if no path, an empty List.
     */
    private List< model.Station > buildPathDFS( model.Station start, model.Station finish, Set< model.Station > visited ) {
        List<model.Station> path = null;
        if (start.equals(finish)) {
            path = new LinkedList<>();
            path.add(start);
            return path;
        }
        for (model.Station nbr : ((student.Station) start).getOutneighbors()) {
            if (!visited.contains(nbr)) {
                if (((student.RailroadMap) map).checkifRouteClaimed(start,nbr,this.baron)) {
                    visited.add(nbr);
                    path = buildPathDFS(nbr, finish, visited);
                    if (path != null) {
                        path.add(0, start);
                        return path;
                    }
                }
            }
        }
        return null;
    }
}
