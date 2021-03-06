package ubots;

import java.awt.Color;
import java.util.Random;

import robocode.*;

public class MambaNegra extends AdvancedRobot {

	private EnemyInfo enemy = new EnemyInfo();
	private byte moveDirection = 1;
	private Radar radar = new Radar(this, enemy);
	private Cannon gun = new Cannon(this, enemy);
	private BasicMovement movement = new BasicMovement();

	public void run() {

		setBodyColor(Color.BLACK);
        setGunColor(Color.BLACK);
        setRadarColor(Color.BLACK);
        setBulletColor(Color.BLACK);
        setScanColor(Color.BLACK);

		enemy.reset();
		radar.init();
		gun.init();

		setTurnRadarRight(360);

		while (true) {
			radar.scan();
			movement.move();
			gun.project();
			execute();

		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
		radar.onScannedRobot(e);
	}

	public void onRobotDeath(RobotDeathEvent e) {
		radar.onRobotDeath(e);
	}

	public void onHitWall(HitWallEvent e) { moveDirection *= -1; }

	public void onHitRobot(HitRobotEvent e) { moveDirection *= -1; }

	private class BasicMovement {

		public void move() {
			setTurnRight(enemy.getBearing() + 90 - (30 * moveDirection));
			setAhead(100 * moveDirection);
			if (getTime() % 20 == 0) {
				moveDirection *= -1;
				setAhead(150 * moveDirection);
			}
		}

	}

}
