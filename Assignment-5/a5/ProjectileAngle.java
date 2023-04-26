package a5;

/**
 * The ideal projectile angle function.
 *
 */
public class ProjectileAngle implements Function1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION

	public static final double G = 9.80665; // gravity
	private double dist; // distance
	private double vel; // speed

	public ProjectileAngle(double distance, double speed) {
		// exception handling
		if (distance < 0)
			throw new IllegalArgumentException(
					"Distance must be greater than or equal to 0; given distance is less than 0");
		// exception handling
		if (speed < 0)
			throw new IllegalArgumentException("Speed must be greater than or equal to 0; given speed is less than 0");
		this.dist = distance;
		this.vel = speed;
	}

	@Override
	public double eval(double x) {
		return ((getDistance() * G) / 2) * Math.pow(Math.tan(x), 2) - Math.pow(getSpeed(), 2) * Math.tan(x)
				+ ((getDistance() * G) / 2);
	}

	// method to get the distance
	public double getDistance() {
		return this.dist; // return the distance
	}

	// method to get the speed
	public double getSpeed() {
		return this.vel; // return the speed
	}

	// method to change the distance
	public double setDistance(double distance) {
		double distOld = this.dist; // store the old distance
		this.dist = distance; // change the distance
		return distOld; // return the old distance
	}

	// method to change the speed
	public double setSpeed(double speed) {
		double velOld = this.vel; // store the old speed
		this.dist = speed; // change the speed
		return velOld; // return the old speed
	}

	/**
	 * Small example program that finds the two possible aiming angles for a
	 * projectile launched at 32.0 m/s at a target located 100 m horizontally from
	 * the launch point.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// target located 100 m horizontally from the launch point
		// launched at 50 m/s
		ProjectileAngle f = new ProjectileAngle(100.0, 50.0);

		// root finding using the bisection method
		Bisection n = new Bisection();

		// first angle is between 0 and 45 degrees
		Root x0 = n.root(f, 0.0, Math.PI / 4.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}

		// second angle is between 45 and 90 degrees
		x0 = n.root(f, Math.PI / 4.0, Math.PI / 2.0);
		if (x0 != Root.NONE) {
			System.out.println(Math.toDegrees(x0.value()));
		}
	}

}
