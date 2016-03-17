package server.logic;

import server.beans.CorrectedTestBean;
import server.beans.Results;
import server.beans.SubmittedTest;
import server.datamodel.*;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Auto correcting handler
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-15.
 */
public class CorrectionHandler {

	private ServerController controller;

	public CorrectionHandler(ServerController controller) {
		this.controller = controller;
	}

	/**
	 * Corrects a submitted test, persists the submitted answers and returns a CorrectedTest object for
	 * persistence in database.
	 *
	 * @param currStudent Student
	 * @param currSubmitted SubmittedTest
	 * @return CorrectedTest
	 */
	public CorrectedTest correctTest(Student currStudent, SubmittedTest currSubmitted) {
		boolean allCorrected = true;

		int vgQuestions = 0;
		int gQuestions = 0;
		int totalVgPoints = 0;
		int totalGPoints = 0;

		int vgPoints = 0;
		int gPoints = 0;
		int maxPoints = 0;

		// Loop through test
		for (AnswerSubmited currSubmittedAnswer : currSubmitted.getAnswersSubmited()) {
			// Get current question.
			Question currQuestion = controller.getQuestion(currSubmittedAnswer.getQuestionId());
			// Get basic test info
			if (currQuestion.isVgQuestion()) {
				vgQuestions++;
				totalVgPoints += currQuestion.getPoints();
			} else {
				gQuestions++;
				totalGPoints += currQuestion.getPoints();
			}
			maxPoints += currQuestion.getPoints();

			// If multi question check answers.
			if (currQuestion.isMultiQuestion()) {
				// Check all Question answers
				for (Answer currAnswer : currQuestion.getAnswers()) {
					// Check if correct answer and if it corresponds with submitted answer.
					if (currAnswer.isCorrectAnswer() && currAnswer.getAnswerText().equals(currSubmittedAnswer.getAnswerString())) {
						// Adds VG or G points
						if (currQuestion.isVgQuestion()) {
							vgPoints += currQuestion.getPoints();
						} else {
							gPoints += currQuestion.getPoints();
						}
						currSubmittedAnswer.setCorrectAnswer(true);
						break;
					}
				}
			} else {
				allCorrected = false;
			}
			currStudent.addAnswer(currSubmittedAnswer);
		}
		return new CorrectedTest(currSubmitted.getTestId(),currStudent.getPersNumber(),vgQuestions,gQuestions,maxPoints,totalVgPoints,totalGPoints,vgPoints,gPoints,allCorrected);
	}

	public CorrectedTest completeCorrection(CorrectedTest currCorrected, CorrectedTestBean currSubmitted){
		int vgPoints = currCorrected.getVgPoints();
		int gPoints = currCorrected.getgPoints();

		for(AnswerCorrected currAnswer: currSubmitted.getCorrected()){
			Question currQuestion = controller.getQuestion(currAnswer.getQuestionId());
			AnswerSubmited currAnswerSubmitted = controller.getAnswerSubmitted(currAnswer.getQuestionId(),currAnswer.getStudentId());
			if (currQuestion.isMultiQuestion()){
				vgPoints += currAnswer.getPointsAwarded();
			} else {
				gPoints += currAnswer.getPointsAwarded();
			}
			currAnswerSubmitted.setCorrected(true);
			controller.updateAnswer(currAnswerSubmitted);
		}
		currCorrected.setVgPoints(vgPoints);
		currCorrected.setgPoints(gPoints);
		currCorrected.setCompletedCorrection(true);

		return currCorrected;
	}

	// STATISTICS METHODS

	public Results singleTestStats(long persNumber, int testId){
		Results currResults = new Results();
		DecimalFormat decForm = new DecimalFormat(".##");

		// Student specific stats
		CorrectedTest studentCorrected = controller.getCorrectedTest(persNumber,testId);

		// Points
		currResults.setVgPoints(studentCorrected.getVgPoints());
		currResults.setgPoints(studentCorrected.getgPoints());
		currResults.setTotalPoints(studentCorrected.getVgPoints()+studentCorrected.getgPoints());

		// Percentage
		String vgPercent = Double.toString((currResults.getVgPoints()/studentCorrected.getTotalVgPoins())*100);
		String gPercent = Double.toString((currResults.getgPoints()/studentCorrected.getTotalGPoints())*100);
		String totalPercent = Double.toString(((currResults.getVgPoints()+currResults.getgPoints())/studentCorrected.getMaxPoints())*100);
		currResults.setPercentCorrectVG(Double.parseDouble(decForm.format(vgPercent)));
		currResults.setPercentCorrectG(Double.parseDouble(decForm.format(gPercent)));
		currResults.setPercentTotal(Double.parseDouble(decForm.format(totalPercent)));

		//All students statistics
		currResults.setAverageCorrectVG(controller.getAvgVGPoints(testId));
		currResults.setAverageCorrectG(controller.getAvgGPoints(testId));
		currResults.setAverageTotalPoints(controller.getAvgPoints(testId));

		return currResults;
	}
}

