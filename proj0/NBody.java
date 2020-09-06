public class NBody {
    public static double readRadius(String Path) {
        In input = new In(Path);
        input.readInt();
        return input.readDouble();
    }

    public static Planet[] readPlanets(String Path) {
        In input = new In(Path);
        final int numPlanets = input.readInt();
        Planet[] Planets = new Planet[numPlanets];
        input.readDouble();
        for (int i = 0; i < numPlanets; i++)
            Planets[i] = new Planet(
                    input.readDouble(),
                    input.readDouble(),
                    input.readDouble(),
                    input.readDouble(),
                    input.readDouble(),
                    input.readString());
        return Planets;
    }

    public static void main(String[] args) {
        final double T = Double.parseDouble(args[0]);
        final double dt = Double.parseDouble(args[1]);
        final Planet[] Planets = readPlanets(args[2]);
        final int numPlanets = Planets.length;
        final double Radius = readRadius(args[2]);

        StdDraw.setScale(-Radius, Radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : Planets)
            p.draw();

        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[numPlanets];
            double[] yForces = new double[numPlanets];
            for (int i = 0; i < numPlanets; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < numPlanets; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
                Planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", numPlanets);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < numPlanets; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
