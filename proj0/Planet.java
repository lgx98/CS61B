public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return 6.67e-11 * p.mass * this.mass / (dx * dx + dy * dy);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return 6.67e-11 * p.mass * this.mass * dx * Math.pow(dx * dx + dy * dy, -1.5);
    }

    public double calcForceExertedByY(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return 6.67e-11 * p.mass * this.mass * dy * Math.pow(dx * dx + dy * dy, -1.5);
    }

    public double calcNetForceExertedByX(Planet[] Planets) {
        double XForce = 0;
        for (Planet p : Planets)
            if (!this.equals(p)) XForce += this.calcForceExertedByX(p);
        return XForce;
    }

    public double calcNetForceExertedByY(Planet[] Planets) {
        double YForce = 0;
        for (Planet p : Planets)
            if (!this.equals(p)) YForce += this.calcForceExertedByY(p);
        return YForce;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel += dt * fX / this.mass;
        this.yyVel += dt * fY / this.mass;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}