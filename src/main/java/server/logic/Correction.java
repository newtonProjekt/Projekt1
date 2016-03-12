
package server.logic;

import java.util.ArrayList;
import java.util.List;
import server.beans.SubmittedTest;
import server.datamodel.AnswerSubmited;
import server.datamodel.Question;
import server.datamodel.SchoolTest;

/**
 * This class is for automatic correction of a certain test.
 *
 * 
 */
public class Correction {
    
    private int numberOfGPoints = 0; 
    private int numberOfVGPoints = 0;
    private List<Integer> listOfPoints = new ArrayList<Integer>();
    private boolean onlyMultiQuestions = true;
    private List<Question> questionList = new ArrayList<Question>();
    private List<AnswerSubmited> answersSubmited;
    
    /**
     * It takes a submitted test and compares the answers that the student 
     * has entered with the correct answer if the specified question is a multiquestion.
     * It returns a list of ints that has 2 elements, index 0 = number of G-points,
     * index 1 = number of VG-points.
     * This method also sets the onlyMultiQuestions to false if the schooltest has question
     * or questions that are text-questions.
     *
     * @param schoolTest SchoolTest
     * @param submittedTest SubmittedTest
     * @return List<Integer>
     */
    public List<Integer> correctTest(SchoolTest schoolTest, SubmittedTest submittedTest){
        
        questionList = schoolTest.getQuestions();
        answersSubmited = submittedTest.getAnswersSubmited();
        
        for(Question s : questionList){
            if(s.isMultiQuestion()){
                
            }
            else{
                onlyMultiQuestions = false;
                break;
            }
        }
        
        
        for(int outerLoopIndex = 0 ; outerLoopIndex < questionList.size() ; outerLoopIndex++){
            
            for(int innerLoopIndex = 0; innerLoopIndex< questionList.get(outerLoopIndex).getAnswers().size() ; innerLoopIndex++){
                
                if(questionList.get(outerLoopIndex).getAnswers().get(innerLoopIndex).getAnswerText().equals(answersSubmited.get(outerLoopIndex).getAnswerString())){
                    
                    numberOfGPoints += questionList.get(outerLoopIndex).getPoints();
                    
                    if(questionList.get(outerLoopIndex).isVgQuestion()){
                        numberOfVGPoints++;
                    }
                    
                }
            }
            
        }
        
        listOfPoints.add(numberOfGPoints);
        listOfPoints.add(numberOfVGPoints);
        
        return listOfPoints;
    }
    
    
    public boolean getonlyMultiQuestions(){
        return onlyMultiQuestions;
    }
    
}
