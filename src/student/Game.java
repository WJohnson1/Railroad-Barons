package student;

import javafx.application.Application;
import model.Deck;
import model.Player;
import model.RailroadBaronsException;
import model.RailroadBaronsObserver;
import java.util.ArrayList;
import java.util.Collection;

public class Game implements model.RailroadBarons{
     //private MapMaker railroadMap;
     private Collection<Player> players = new ArrayList<>();
     public Game(){

     }


     @Override
     public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
         for (int i = 0; i<4;i++){
            Player p = new RailroadBaronPlayer(i);
            players.add(p);
        }
          observer.turnStarted(this,(Player) (players.toArray())[0]);
         //Player player = (Player) observer;
        //players.add(player);
     }

     @Override
     public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {

     }

     @Override
     public void startAGameWith(model.RailroadMap map) {

     }

     @Override
     public void startAGameWith(model.RailroadMap map, Deck deck) {

     }

     @Override
     public model.RailroadMap getRailroadMap() {
          return null;
     }

     @Override
     public int numberOfCardsRemaining() {
          return 0;
     }

     @Override
     public boolean canCurrentPlayerClaimRoute(int row, int col) {
          return false;
     }

     @Override
     public void claimRoute(int row, int col) throws RailroadBaronsException {

     }

     @Override
     public void endTurn() {

     }

     @Override
     public Player getCurrentPlayer() {
          return null;
     }

     @Override
     public Collection<Player> getPlayers() {
        return  this.players;
     }

     @Override
     public boolean gameIsOver() {
          return false;
     }


     public static void main(String[] args) {
          Application.launch(view.RailroadBaronsUI.class,args);
     }
}

