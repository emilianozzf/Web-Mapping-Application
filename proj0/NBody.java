/** Simulates a universe specified in one of the data files */
public class NBody {
    /** Returns the radius of the universe in that file */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    /** Returns an array of Bodys corresponding to the boides in the file */
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        Body[] bs = new Body[in.readInt()];
        in.readDouble();
        for (int i = 0; i < bs.length; i++) {
            bs[i] = new Body(in.readDouble(), in.readDouble(),
                             in.readDouble(), in.readDouble(),
                             in.readDouble(), in.readString());
        }
        return bs;
    }

    public static void main(String[] args) {
        /* Collects all needed input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bs = readBodies(filename);

        /* Draws the background */
        /* Sets the scale so that it matches the radius of the universe */
        StdDraw.setScale(-radius, radius);
        /* Draws the image starfield.jpg as the background */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Draws bodies */
        for (int i = 0; i < bs.length; i++) {
            bs[i].draw();
        }

        /* Animation */
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[bs.length];
            double[] yForces = new double[bs.length];
            for (int i = 0; i < bs.length; i++) {
                xForces[i] = bs[i].calcNetForceExertedByX(bs);
                yForces[i] = bs[i].calcNetForceExertedByY(bs);
            }
            for (int i = 0; i < bs.length; i++) {
                bs[i].update(dt ,xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < bs.length; i++) {
                bs[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", bs.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bs.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bs[i].xxPos, bs[i].yyPos, bs[i].xxVel,
                    bs[i].yyVel, bs[i].mass, bs[i].imgFileName);
        }
    }
}