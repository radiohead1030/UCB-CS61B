public class Planet{
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double xAccel;
	public double yAccel;
	public double mass;
	public double xNetForce;
	public double yNetForce;
	public String img;
	public static double constant_G = 6.67e-11;

	public Planet(double ini_x, double ini_y, double ini_xVelocity, double ini_yVelocity, double ini_mass, String ini_img){
		x = ini_x;	
		y = ini_y;	
 		xVelocity = ini_xVelocity;
 		yVelocity = ini_yVelocity;
		mass = ini_mass;
		img = ini_img;
	}
	
	public double calcDistance(Planet planet){
		return Math.sqrt(sqrDist(this.x, planet.x) + sqrDist(this.y, planet.y));	
	}
	
	private static double sqrDist(double x1, double x2){
		return (x1-x2) * (x1-x2);	
	}
	
	public double calcPairwiseForce(Planet planet){
		double distance = this.calcDistance(planet);
		return (constant_G * this.mass * planet.mass) / (distance * distance);
	}
	
	public double calcPairwiseForceX(Planet planet){
		double pairwiseForce = this.calcPairwiseForce(planet);
		double distance = this.calcDistance(planet);
		return (pairwiseForce * (planet.x - this.x)) / distance;
	}
	
	public double calcPairwiseForceY(Planet planet){
		double pairwiseForce = this.calcPairwiseForce(planet);
		double distance = this.calcDistance(planet);
		return (pairwiseForce * (planet.y - this.y)) / distance;
	}

	public void setNetForce(Planet[] planets){
		this.xNetForce = 0;
		this.yNetForce = 0;
		for (int i = 0; i < planets.length; i++){
			if (planets[i] != this){
				this.xNetForce += calcPairwiseForceX(planets[i]);
				this.yNetForce += calcPairwiseForceY(planets[i]);
			}	
		}
	}

	public void drawPlanet(){
		StdDraw.picture(this.x, this.y, "images/" + this.img);
	}

	public void update(double dt){
		this.xAccel = xNetForce / this.mass;
		this.yAccel = yNetForce / this.mass;
		this.xVelocity += this.xAccel * dt;
		this.yVelocity += this.yAccel * dt;
		this.x += xVelocity * dt;
		this.y += yVelocity * dt;
	}
}
