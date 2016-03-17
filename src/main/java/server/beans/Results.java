package server.beans;

/**
 * Bean that handles information on test results.
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-16.
 */
public class Results {

    // Individual stats
    private double percentCorrectVG, percentCorrectG, percentTotal;
    private int vgPoints, gPoints, totalPoints;

    private String gradeAwarded;

    // Group stats
    private double averageCorrectVG, averageCorrectG, averageTotalPoints;

    public Results() {
    }

    public Results(double percentCorrectVG, double percentCorrectG, double percentTotal, int vgPoints, int gPoints, int totalPoints, String gradeAwarded, double averageCorrectVG, double averageCorrectG, double averageTotalPoints) {
        this.percentCorrectVG = percentCorrectVG;
        this.percentCorrectG = percentCorrectG;
        this.percentTotal = percentTotal;
        this.vgPoints = vgPoints;
        this.gPoints = gPoints;
        this.totalPoints = totalPoints;
        this.gradeAwarded = gradeAwarded;
        this.averageCorrectVG = averageCorrectVG;
        this.averageCorrectG = averageCorrectG;
        this.averageTotalPoints = averageTotalPoints;
    }

    public double getPercentCorrectVG() {
        return percentCorrectVG;
    }

    public void setPercentCorrectVG(double percentCorrectVG) {
        this.percentCorrectVG = percentCorrectVG;
    }

    public double getPercentCorrectG() {
        return percentCorrectG;
    }

    public void setPercentCorrectG(double getPercentCorrectG) {
        this.percentCorrectG = getPercentCorrectG;
    }

    public int getVgPoints() {
        return vgPoints;
    }

    public void setVgPoints(int vgPoints) {
        this.vgPoints = vgPoints;
    }

    public int getgPoints() {
        return gPoints;
    }

    public void setgPoints(int gPoints) {
        this.gPoints = gPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getGradeAwarded() {
        return gradeAwarded;
    }

    public void setGradeAwarded(String gradeAwarded) {
        this.gradeAwarded = gradeAwarded;
    }

    public double getAverageCorrectVG() {
        return averageCorrectVG;
    }

    public void setAverageCorrectVG(double averageCorrectVG) {
        this.averageCorrectVG = averageCorrectVG;
    }

    public double getAverageCorrectG() {
        return averageCorrectG;
    }

    public void setAverageCorrectG(double averageCorrectG) {
        this.averageCorrectG = averageCorrectG;
    }

    public double getAverageTotalPoints() {
        return averageTotalPoints;
    }

    public void setAverageTotalPoints(double averageTotalPoints) {
        this.averageTotalPoints = averageTotalPoints;
    }

    public double getPercentTotal() {
        return percentTotal;
    }

    public void setPercentTotal(double percentTotal) {
        this.percentTotal = percentTotal;
    }
}
