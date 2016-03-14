package server.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-14.
 */
@Entity
public class CorrectedTests {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	// Identification
	@NotNull
	private int testId;
	@NotNull
	private long persNumber;
	@NotNull

	// Questions info
	private int totalNumberOfVgQuestion;
	@NotNull
	private int totalNumberOfGQuestions;
	@NotNull
	private int maxPoints;

	// Correction info
	private int vgPoints;
	private int gPoints;

	@NotNull
	private boolean completedCorrection;


	public CorrectedTests(){
	}

	public CorrectedTests(int testId, long persNumber, int totalNumberOfVgQuestion, int totalNumberOfGQuestions, int maxPoints, int vgPoints, int gPoints, boolean completedCorrection) {
		this.testId = testId;
		this.persNumber = persNumber;
		this.totalNumberOfVgQuestion = totalNumberOfVgQuestion;
		this.totalNumberOfGQuestions = totalNumberOfGQuestions;
		this.maxPoints = maxPoints;
		this.vgPoints = vgPoints;
		this.gPoints = gPoints;
		this.completedCorrection = completedCorrection;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public long getPersNumber() {
		return persNumber;
	}

	public void setPersNumber(long persNumber) {
		this.persNumber = persNumber;
	}

	public int getTotalNumberOfVgQuestion() {
		return totalNumberOfVgQuestion;
	}

	public void setTotalNumberOfVgQuestion(int totalNumberOfVgQuestion) {
		this.totalNumberOfVgQuestion = totalNumberOfVgQuestion;
	}

	public int getTotalNumberOfGQuestions() {
		return totalNumberOfGQuestions;
	}

	public void setTotalNumberOfGQuestions(int totalNumberOfGQuestions) {
		this.totalNumberOfGQuestions = totalNumberOfGQuestions;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
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

	public boolean isCompletedCorrection() {
		return completedCorrection;
	}

	public void setCompletedCorrection(boolean completedCorrection) {
		this.completedCorrection = completedCorrection;
	}
}
