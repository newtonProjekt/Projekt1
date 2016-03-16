package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity class that handles the tests thats been corrected for further reference.
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-14.
 *
 * id	 					= Auto generated id value.
 * testId					= Referencing what test with id.
 * persNumber				= Referencing the student id.
 * totalNumberOfVgQuestions	= The number of questions marked as VG-questions.
 * totalNumberOfGQuestions	= The number of questions marked as G-questions.
 * maxPoints				= How many points a maximal result on the test would amount to.
 * totalVgPoints			= Amount of points possible in VG-questions.
 * totalGPoints				= Amount of points possible in G-questions.
 * vgPoints					= How many points the student earned in VG-questions.
 * gPoints					= How many points the student earned in G-questions.
 * completedCorrection		= Boolean that notes if the test was completely corrected.
 *
 */

@NamedQueries({
		@NamedQuery(name="deletecorrected" +
				"testsfromstudent",
				query = "DELETE FROM CorrectedTest c WHERE c.persNumber=:pNumber" ),
		@NamedQuery(name="deletecorrectedtest",
				query = "DELETE FROM CorrectedTest c WHERE c.testId=:test" ),
		@NamedQuery(name="getcorrectedtestsfromstudent",
				query = "SELECT c FROM CorrectedTest c WHERE c.persNumber=:pNumber"),
		@NamedQuery(name="getcorrectedtestfromstudent",
				query = "SELECT c FROM CorrectedTest c WHERE c.persNumber=:pNumber AND c.testId=:test")
})

@Entity
public class CorrectedTest {

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
	private int totalVgPoins;
	private int totalGPoints;

	// Correction info
	private int vgPoints;
	private int gPoints;

	@NotNull
	private boolean completedCorrection;


	public CorrectedTest(){
	}

	public CorrectedTest(int testId, long persNumber, int totalNumberOfVgQuestion, int totalNumberOfGQuestions, int maxPoints, int totalVgPoins, int totalGPoints, int vgPoints, int gPoints, boolean completedCorrection) {
		this.testId = testId;
		this.persNumber = persNumber;
		this.totalNumberOfVgQuestion = totalNumberOfVgQuestion;
		this.totalNumberOfGQuestions = totalNumberOfGQuestions;
		this.maxPoints = maxPoints;
		this.totalVgPoins = totalVgPoins;
		this.totalGPoints = totalGPoints;
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

	public int getTotalVgPoins() {
		return totalVgPoins;
	}

	public void setTotalVgPoins(int totalVgPoins) {
		this.totalVgPoins = totalVgPoins;
	}

	public int getTotalGPoints() {
		return totalGPoints;
	}

	public void setTotalGPoints(int totalGPoints) {
		this.totalGPoints = totalGPoints;
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
