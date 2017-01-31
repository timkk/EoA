package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;

public class KI extends Player {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15asu
	 */

	private KIStates currentState;
	private Point nextPosition;
	private Point escapePoint;
	private Point goToPoint;

	// Zwischenspeicher
	// mapData.add(collision_objects);
	// mapData.add(explosions);
	// mapData.add(bombs);
	// mapData.add(walls);

	public KI(String name, Point pos, Player_Frames spritesheet, InputConfig controls, Stage stage) {
		super(name, pos, spritesheet, controls, stage);
		currentState = KIStates.WALK_STATE;
	}

	public void react(List<List<GameObject>> mapData) {

		switch (currentState.getValue()) {
		case 1:
			if (pos.equals(nextPosition))
				walk(mapData.get(0));
			break;
		case 2:
			escape(mapData.get(0));
			break;
		case 3:
			check(mapData);
			break;
		case 4:

			break;
		default:
			break;
		}
	}

	private Point getRandomRichtung(List<Point> possibleDirections) {
		int rnd = (int) (Math.random() * possibleDirections.size());
		return possibleDirections.get(rnd);
	}

	private void walk(List<GameObject> collisionData) {
		List<Point> pd = new LinkedList<>();
		Point richtung;

		Point p = new Point(getPos().getX() - 50, getPos().getY());
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX() + 50, getPos().getY());
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() - 50);
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() + 50);
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}

		richtung = getRandomRichtung(pd);
		nextPosition = new Point(pos.getX() + richtung.getX(), pos.getY() + richtung.getY());
	}

	private boolean checkWalkCollision(Point p, List<GameObject> collisionData) {
		boolean collides = false;
		CollisionDetector cd = new CollisionDetector(p, 50, 50, 3);
		for (GameObject g : collisionData) {
			if (cd.collidesWith(g)) {
				collides = true;
				break;
			}
		}
		return collides;
	}

	private void escape(List<GameObject> collisionData) {
		List<Point> pd = new LinkedList<>();
		Point richtung;

		Point p = new Point(getPos().getX() - 50, getPos().getY());
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX() + 50, getPos().getY());
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() - 50);
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}
		p = new Point(getPos().getX(), getPos().getY() + 50);
		if (checkWalkCollision(p, collisionData)) {
			pd.add(p);
		}

		richtung = getMaxDistanceRichtung(pd);
		nextPosition = new Point(pos.getX() + richtung.getX(), pos.getY() + richtung.getY());
	}

	private Point getMaxDistanceRichtung(List<Point> possibleDirections) {
		Point farAway = escapePoint;

		for (Point point : possibleDirections) {
			if (farAway.PointDistance(escapePoint) < point.PointDistance(escapePoint)) {
				farAway = point;
			}
		}

		return farAway;
	}

	private Point getNearestPoint(List<Point> objPositions) {
		Point near = new Point(4242, 4242);
		Point playerPos = getPos();
		for (Point point : objPositions) {
			if (near.PointDistance(playerPos) > point.PointDistance(playerPos)) {
				near = point;
			}
		}

		return near;
	}

	private void check(List<List<GameObject>> mapData) {
		escapePoint = null;
		goToPoint = null;
		Point x = getMostImportantAxisPoint("x", mapData);
		Point y = getMostImportantAxisPoint("y", mapData);
	}

	private Point getMostImportantAxisPoint(String axis, List<List<GameObject>> mapData) {
		Point tmp = new Point(this.getPos());
		int tmpx = (int) tmp.getX();
		int tmpy = (int) tmp.getY() + 25;

		tmpx /= 50;
		tmpy /= 50;

		tmpx *= 50;
		tmpy *= 50;

		Point cp = new Point(tmpx + 25, tmpy);
		List<Point> possiblePoints = new LinkedList<Point>();

		if (axis.equals("x")) {
			// Befinden sich bomben auf der achse?
			for (GameObject bomb : mapData.get(2)) {
				if (bomb.getPos().getX() == cp.getX()) {
					possiblePoints.add(bomb.getPos());
				}
			}
			if (possiblePoints.size() > 0) {
				currentState = KIStates.ESPACE_STATE;
				Point near = getNearestPoint(possiblePoints);
				if(escapePoint == null)
				{
					escapePoint = near;
				} else {
					if(getPos().PointDistance(escapePoint) > getPos().PointDistance(near))
						escapePoint = near;
				}
				
				return escapePoint;
			}

			// wenn nicht, befinden sich powerUps auf der achse?
			for (GameObject collectable : mapData.get(2)) { // der
															// get()-parameter
															// muss noch passend
															// gesetzt werden
				if (collectable.getPos().getX() == cp.getX()) {
					if (collectable instanceof PowerUp) {
						possiblePoints.add(collectable.getPos());
					}
				}
			}

			if (possiblePoints.size() > 0) {
				currentState = KIStates.GOTO_STATE;
				goToPoint = getNearestPoint(possiblePoints); 
				return  goToPoint;
			}

			return null;
		} else {
			// Befinden sich bomben auf der achse?
			for (GameObject bomb : mapData.get(2)) {
				if (bomb.getPos().getY() == cp.getY()) {
					possiblePoints.add(bomb.getPos());
				}
			}
			if (possiblePoints.size() > 0) {
				currentState = KIStates.ESPACE_STATE;
				Point near = getNearestPoint(possiblePoints);
				if(escapePoint == null)
				{
					escapePoint = near;
				} else {
					if(getPos().PointDistance(escapePoint) > getPos().PointDistance(near))
						escapePoint = near;
				}
				
				return escapePoint;
			}
			// wenn nicht, befinden sich powerUps auf der achse?
			for (GameObject collectable : mapData.get(2)) { // der
															// get()-parameter
															// muss noch passend
															// gesetzt werden
				if (collectable.getPos().getY() == cp.getY()) {
					if (collectable instanceof PowerUp) {
						possiblePoints.add(collectable.getPos());
					}
				}
			}

			if (possiblePoints.size() > 0) {
				currentState = KIStates.GOTO_STATE;
				goToPoint = getNearestPoint(possiblePoints); 
				return  goToPoint;
			}
			return null;
		}
	}

}