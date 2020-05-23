public class NBody {
    public static double readRadius(String radiusPath){
        In in = new In(radiusPath);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Planet[] readPlanets(String planetPath){
        In in = new In(planetPath);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[]  planents = new Planet[5];
        for (int i=0; i<number; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String imgFile = in.readString();
            Planet currentPlanet = new Planet(xP, yP, xV, yV, m, imgFile);
            planents[i] = currentPlanet;

        }
        return planents;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]); // from stackoverflow
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        String bgToDraw = "images/starfield.jpg";
        StdDraw.setScale(-2*radius, 2*radius);


        StdDraw.enableDoubleBuffering(); //a graphics technique to prevent flickering in the animation

        double time = 0;
        while(time<T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0;i<planets.length;i++){
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }

            for(int j=0;j<planets.length;j++) {
                Planet p = planets[j];
                p.update(dt, xForces[j], yForces[j]);
            }

            StdDraw.pause(10);

            StdDraw.picture(0, 0, bgToDraw);
            for(int k=0;k<planets.length;k++){
                planets[k].draw();
            }

            StdDraw.show();
            time = time + dt;


        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
