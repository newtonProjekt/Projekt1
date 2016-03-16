package server.logic;

import server.beans.SubmittedTest;
import server.datamodel.*;

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



}

