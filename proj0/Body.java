public class Body {
    /* Its current x position */
    public double xxPos;
    /* Its current y position */
    public double yyPos;
    /* Its current velocity in the x direction */
    public double xxVel;
    /* Its current velocity in the y direction */
    public double yyVel;
    /* Its mass */
    public double mass;
    /* The name of the file that corresponds to the image that depicts the body */
    public String imgFileName;
    /* Graviataional factor */
    public static final double G = 6.67e-11;

    /** The first constructor */
    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** The second constructor */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Calculates the distance between this body and another body */
    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    /** Calculates the force exerted on this body by the given body */
    public double calcForceExertedBy(Body b) {
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
    }

    /** Calculates the force exerted in the X direction */
    public double calcForceExertedByX(Body b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    /** Calculates the force exerted in the Y direction */
    public double calcForceExertedByY(Body b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    /** Calculates the net X force exerted by all bodies in the array upon the current Body */
    public double calcNetForceExertedByX(Body[] bs) {
        double netForceX = 0;
        for (int i = 0; i < bs.length; i++) {
            if (! bs[i].equals(this)) {
                netForceX += this.calcForceExertedByX(bs[i]);
            }
        }
        return netForceX;
    }

    /** Calculates the net Y force exerted by all bodies in the array upon the current Body */
    public double calcNetForceExertedByY(Body[] bs) {
        double netForceY = 0;
        for (int i = 0; i < bs.length; i++) {
            if (! bs[i].equals(this)) {
                netForceY += this.calcForceExertedByY(bs[i]);
            }
        }
        return netForceY;
    }

    /** Updates the body's position and velocity instance variables */
    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        double vX = this.xxVel + aX * dt;
        double vY = this.yyVel + aY * dt;
        double pX = this.xxPos + vX * dt;
        double pY = this.yyPos + vY * dt;
        this.xxVel = vX;
        this.yyVel = vY;
        this.xxPos = pX;
        this.yyPos = pY;
    }

    /** Draws the Body's image at the Body's position */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }

}