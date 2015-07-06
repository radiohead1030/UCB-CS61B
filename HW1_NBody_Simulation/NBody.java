public class NBody{
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		int planetNum = 0;
		double universeRad = 0;
		double t = 0;
		int tPause = 10;
		
		In in = new In(filename);
		if (!in.isEmpty()){
			planetNum = in.readInt();
			universeRad = in.readDouble();
		}
		
		String bgImg = "images/starfield.jpg";
		StdDraw.setScale(-universeRad, universeRad);
		StdDraw.picture(0, 0, bgImg);

		Planet[] planets = new Planet[planetNum];
		for(int i = 0; i< planetNum; i++){
			planets[i] = getPlanet(in);
			planets[i].drawPlanet();
		}
		
		StdAudio.play("audio/2001.mid");

		for(t = 0; t <= T; t = t+dt){
			StdDraw.picture(0, 0, bgImg);
			for(int i = 0; i< planetNum; i++){
				planets[i].setNetForce(planets);
				planets[i].update(dt);
				planets[i].drawPlanet();
			}
			StdDraw.show(tPause);
		}
		
		System.out.println(planetNum);
		System.out.println(universeRad);
		for(int i = 0; i< planetNum; i++){
			System.out.println(planets[i].x + " " + planets[i].y + " " + planets[i].xVelocity + " " + planets[i].yVelocity + " " + planets[i].mass + " " + planets[i].img);
		}
	}

	public static Planet getPlanet(In in){
		double x = in.readDouble();
		double y = in.readDouble();
		double xVelocity = in.readDouble();
		double yVelocity = in.readDouble();
		double mass = in.readDouble();
		String img = in.readString();
		return new Planet(x, y, xVelocity, yVelocity, mass, img);
	}
}
