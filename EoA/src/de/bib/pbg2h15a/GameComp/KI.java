package de.bib.pbg2h15a.GameComp;

import java.util.LinkedList;
import java.util.List;

import de.bib.pbg2h15a.Config.InputConfig;
import de.bib.pbg2h15a.Interface.Player_Frames;
import de.bib.pbg2h15a.Uitl.Point;

/**
 * (Kommentiert von Julian Zameit / pbg2h15aza) Diese Klasse stellt eine KI in
 * der LocalGameState dar. Die KI reagiert auf Basis der jeweiligen State in der
 * er sich befindet.
 * 
 * @author pbg2h15aza
 * @author pbg2h15asu
 */

public class KI extends Player {

	private KIStates currentState;
	private Point nextPosition;
	private Point escapePoint;
	private Point goToPoint;

	// Zwischenspeicher
	// mapData.add(collision_objects);
	// mapData.add(explosions);
	// mapData.add(bombs);
	// mapData.add(walls);

	/**
	 * Erstellt ein KI-Objekt und weist ihm zu Beginn die Walk-State zu.
	 * 
	 * @param name
	 *            Der Name der KI
	 * @param pos
	 *            Die Spawn-Position der KI
	 * @param spritesheet
	 *            Das Blatt auf dem das Bild "gemalt" wird
	 * @param controls
	 *            Vererbt durch den Player und definiert die Steuerung
	 * @param stage
	 *            Die Stage in der sich das Spiel befindet
	 */
	public KI(String name, Point pos, Player_Frames spritesheet, InputConfig controls, Stage stage) {
		super(name, pos, spritesheet, controls, stage);
		currentState = KIStates.WALK_STATE;
	}

	/**
	 * Die KI reagiert auf Basis seines States und den Informationen des
	 * Spielfeldes.
	 * 
	 * @param mapData
	 *            S�mtliche Informationen des Spielfeld im aktuellen Frame um
	 *            reagieren zu k�nnen
	 */
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

	/**
	 * W�hlt eine zuf�llige Richtung von den �bergebenen m�glichen Richtungen.
	 * 
	 * @param possibleDirections
	 *            M�gliche Richtungen, in die die KI laufen kann.
	 * @return eine zuf�llige Richtung.
	 */
	private Point getRandomRichtung(List<Point> possibleDirections) {
		int rnd = (int) (Math.random() * possibleDirections.size());
		return possibleDirections.get(rnd);
	}

	/**
	 * Die KI �berpr�ft alle 4 Bewegungsrichtungen und setzt die n�chste
	 * Position, zu der sich die KI bewegen will.
	 * 
	 * @param collisionData
	 *            S�mtliche Objekte mit denen der Spieler kollidieren kann
	 */
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

	/**
	 * �berpr�ft, ob sich die KI auf die Position bewegen k�nnte.
	 * 
	 * @param p
	 *            Die zu �berpr�fende Position
	 * @param collisionData
	 *            S�mtliche Objekte mit denen der Spieler kollidieren kann
	 * @return kollidiert oder nicht.
	 */
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

	/**
	 * �berpr�ft alle m�glichen Bewegungsrichtungen und setzt die Position auf
	 * das Feld, welches sich am weitesten von dem Objekt befindet, von dem die
	 * KI flieht.
	 * 
	 * @param collisionData
	 *            S�mtliche Objekte mit denen der Spieler kollidieren kann
	 */
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

	/**
	 * Alle Richtungen werden auf ihre Entfernung zu der zu fliehenden Position
	 * �berpr�ft.
	 * 
	 * @param possibleDirections
	 *            M�gliche Richtungen, in die die KI laufen kann.
	 * @return Die am weitesten entfernte Richtung.
	 */
	private Point getMaxDistanceRichtung(List<Point> possibleDirections) {
		Point farAway = escapePoint;

		for (Point point : possibleDirections) {
			if (farAway.PointDistance(escapePoint) < point.PointDistance(escapePoint)) {
				farAway = point;
			}
		}

		return farAway;
	}

	/**
	 * Die am n�chsten sich zum Spieler befindene Position wird gesucht.
	 * 
	 * @param objPositions
	 *            Liste mit relevanten Positionen
	 * @return die Position, die am dichtesten am Spieler ist.
	 */
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

	/**
	 * Es wird �berpr�ft, welcher Zustand als n�chstes f�r die KI gilt.
	 * 
	 * @param mapData
	 *            Die kompletten Informationen der Objekte auf dem Spielfeld.
	 */
	private void check(List<List<GameObject>> mapData) {
		escapePoint = null;
		goToPoint = null;
		Point x = getMostImportantAxisPoint("x", mapData);
		Point y = getMostImportantAxisPoint("y", mapData);
	}

	/**
	 * Es werden alle Daten der Karte �berpr�ft und der wichtigste auf der
	 * jeweiligen Achse ausgew�hlt.
	 * 
	 * @param axis
	 *            Die Achse, die �berpr�ft wird.
	 * @param mapData
	 *            Die kompletten Informationen der Objekte auf dem Spielfeld.
	 * @return den wichtigsten Punkt der Achse.
	 */
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
				currentState = KIStates.ESCAPE_STATE;
				Point near = getNearestPoint(possiblePoints);
				if (escapePoint == null) {
					escapePoint = near;
				} else {
					if (getPos().PointDistance(escapePoint) > getPos().PointDistance(near))
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
				return goToPoint;
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
				currentState = KIStates.ESCAPE_STATE;
				Point near = getNearestPoint(possiblePoints);
				if (escapePoint == null) {
					escapePoint = near;
				} else {
					if (getPos().PointDistance(escapePoint) > getPos().PointDistance(near))
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
				return goToPoint;
			}
			return null;
		}
	}

}