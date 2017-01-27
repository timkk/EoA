package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;

public class KI extends Player{

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15asu
	 */
	
	private KIStates currentState;
	private Point nextPosition;
	private GameObject escapeFrom;
	
	// Zwischenspeicher
	//mapData.add(collision_objects);
	//mapData.add(explosions);
	//mapData.add(bombs);
	//mapData.add(walls);
	
	
	public KI(String name, Point pos, Player_Frames spritesheet, InputConfig controls, Stage stage) {
		super(name, pos, spritesheet, controls, stage);
		currentState = KIStates.WALK_STATE;
	}
	
	public void react(List<List<GameObject>> mapData) {
		
		switch (currentState.getValue()) {
		case 1: 
			if(pos.equals(nextPosition))
				walk(mapData.get(0));
			break;
		case 2: 
			escape();
			break;
		case 3: 
		
			break;
		case 4: 
		
			break;
		default:
			break;
		}
	}
	
	private Point getRandomRichtung(List<Point> possibleDirections) {
		int rnd = (int)(Math.random()*possibleDirections.size());
		return possibleDirections.get(rnd);
	}
	
	private void walk(List<GameObject> collisionData) {
		List<Point> pd = new LinkedList<>();
		Point richtung;
		
		Point p = new Point(getPos().getX() - 50, getPos().getY());
		if(checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX() + 50, getPos().getY());
		if(checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() - 50);
		if(checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() + 50);
		if(checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		
		richtung = getRandomRichtung(pd);
		nextPosition = new Point (pos.getX() + richtung.getX(), pos.getY() + richtung.getY());
	}
	
	private boolean checkWalkCollision(Point p, List<GameObject> collisionData) {
		boolean collides = false;
		CollisionDetector cd = new CollisionDetector(p, 50, 50, 3);
		for (GameObject g : collisionData) {
			if(cd.collidesWith(g)) {
				collides = true;
				break;
			}
		}
		return collides;
	}

	private void escape()
	{
		
	}
	
	private Point getMaxDistanceRichtung(List<Point> possibleDirections)
	{
		Point farAway = escapeFrom.pos;
		
		for (Point point : possibleDirections) {
			if(farAway.PointDistance(escapeFrom.pos) < point.PointDistance(escapeFrom.pos))
			{
				farAway = point;
			}
		}
		
		return farAway;
	}
	
	
	
}