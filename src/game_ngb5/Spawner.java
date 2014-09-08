package game_ngb5;

import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Spawner{
	Integer myLevel;
	Hero myHero;
	Timeline mySpawning;
	
	public Spawner(Hero hero){
		myHero = hero;
		myLevel = hero.myLevel;
		Integer spawnRate = null;
		Queue<Integer> attackPattern = new LinkedList<Integer>();
		Integer[] firstLevelAttack = {50, 150, 250, 350, 450};
		Integer[] secondLevelAttack = {50, 450, 100, 400, 150, 350, 200, 300, 225, 275};
		if(myLevel==1){
			spawnRate = 1500;
			loadPattern(firstLevelAttack, attackPattern);
		}
		if(myLevel==2){
			spawnRate = 750;
			loadPattern(secondLevelAttack, attackPattern);
		}
		Timeline spawning = new Timeline();
		mySpawning = spawning;
		spawning.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(spawnRate), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Integer xLoc = attackPattern.poll();
				attackPattern.add(xLoc);
				if(myLevel == 1){
					Drone drone = new Drone(myHero, xLoc, -100, 10);
					drone.attack();
					myHero.myEnemies.add(drone);
				}
				if(myLevel == 2){
					Elite elite = new Elite(myHero, xLoc, -100, 20);
					elite.attack();
					myHero.myEnemies.add(elite);
				}
				
				
			}
		});
		spawning.getKeyFrames().add(kf);
	}
	public Queue<Integer> loadPattern(Integer[] pattern, Queue<Integer> q){
		for(Integer i : pattern){
			q.add(i);
		}
		return q;
	}
	public void killSpawning(){
		this.mySpawning.stop();
	}
	public void playSpawning(){
		this.mySpawning.play();
	}
}
