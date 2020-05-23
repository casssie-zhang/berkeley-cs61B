public class Planet {
    public double xxPos; // current x Position
    public double yyPos; // current y Position
    public double xxVel; // current velocity on x direction
    public double yyVel; // current velocity in y direction
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    public double calcDistance(Planet supplied){
        double xDiff = xxPos - supplied.xxPos;
        double yDiff = yyPos - supplied.yyPos;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public double calcForceExertedBy(Planet given){
        double paramG = 6.67e-11;
        double distance = calcDistance(given);
        return paramG * (mass) * (given.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet given){
        double force = calcForceExertedBy(given);
        double distance = calcDistance(given);
        double xDiffAbs;
        if (given.xxPos > xxPos){
            xDiffAbs = given.xxPos - xxPos;
        }
        else{
            xDiffAbs = xxPos - given.xxPos;
        }

        return force * xDiffAbs / distance;
    }

    public double calcForceExertedByY(Planet given){
        double force = calcForceExertedBy(given);
        double distance = calcDistance(given);
        double yDiffAbs;
        if (given.yyPos > yyPos){
            yDiffAbs = given.yyPos - yyPos;
        }
        else{
            yDiffAbs = yyPos - given.yyPos;
        }
        return force * yDiffAbs / distance;

    }

    public double calcNetForceExertedByX(Planet [] allPlanets){
        int size = allPlanets.length;
        double netForceX= 0;

        for (int i=0;i<size;i++){
            if (allPlanets[i].equals(this)) {
                continue;
            }
            else{
                netForceX = netForceX + calcForceExertedByX(allPlanets[i]);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY (Planet [] allPlanets){
        int size = allPlanets.length;
        double netForceY= 0;

        for (int i=0;i<size;i++){
            if (allPlanets[i].equals(this)) {
                continue;
            }
            else{
                netForceY = netForceY + calcForceExertedByY(allPlanets[i]);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw(){
        String path = "images/";
        String imgFile = path + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgFile);
    }

}