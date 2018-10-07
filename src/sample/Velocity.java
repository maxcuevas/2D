package sample;

public class Velocity {

    private double velocityX = 0;
    private double velocityY = 0;
    private double sprintFactor;
    private double baseVelocity;


    public Velocity(double sprintFactor, double baseVelocity) {
        this.sprintFactor = sprintFactor;
        this.baseVelocity = baseVelocity;
    }

    public void setVelocityYPos() {
        velocityY = baseVelocity;
    }

    public void setVelocityXPos() {
        velocityX = baseVelocity;
    }

    public void setVelocityYNeg() {
        velocityY = -baseVelocity;
    }

    public void setVelocityXNeg() {
        velocityX = -baseVelocity;
    }

    public void resetVelocityY() {
        velocityY = 0;
    }

    public void resetVelocityX() {
        velocityX = 0;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void clearVelocities() {
        resetVelocityX();
        resetVelocityY();
    }

    public void setRunning() {
        velocityX *= sprintFactor;
        velocityY *= sprintFactor;
    }
}
