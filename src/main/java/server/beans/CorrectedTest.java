package server.beans;

import server.datamodel.AnswerCorrected;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean used to submit corrected answers.
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-16.
 */
public class CorrectedTest {

	private long studentId;
	List<AnswerCorrected> corrected;

	public CorrectedTest() {
		corrected = new ArrayList<>();
	}

	public CorrectedTest(long studentId) {
		corrected = new ArrayList<>();
		this.studentId = studentId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public List<AnswerCorrected> getCorrected() {
		return corrected;
	}

	public void setCorrected(List<AnswerCorrected> corrected) {
		this.corrected = corrected;
	}

	public void addAnswer(AnswerCorrected currAnswer){
		corrected.add(currAnswer);
	}

	public void removeAnswer(AnswerCorrected currAnswer){
		corrected.remove(currAnswer);
	}
}
