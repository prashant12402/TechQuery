package com.learning.techquery.service;

import com.learning.techquery.model.Answer;
import com.learning.techquery.model.Question;
import com.learning.techquery.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getDataOfQuestion(String id) {
        return questionRepository.findById(id);
    }

    public Question createQuestionData(Question question) {

        return questionRepository.save(question);
    }

    public Question updateQuestionData(String id, Question requestQuestion) {
        Optional<Question> databaseQuestionOptional = questionRepository.findById(id);
        if (databaseQuestionOptional.isPresent()) {
            requestQuestion.setId(id);
            return questionRepository.save(requestQuestion);
        }
        return null;
    }

    public String deleteData(String id) {
        questionRepository.deleteById(id);
        return "deleted successfully ";
    }


    public Question editQuestionData(String id, Question requestQuestion) {
        Optional<Question> databaseQuestionOptional = questionRepository.findById(id);
        if (databaseQuestionOptional.isPresent()) {

            System.out.println("Data found");

            Question databaseQuestion = databaseQuestionOptional.get();
            if (requestQuestion != null && requestQuestion.getTitle() != null && !requestQuestion.getTitle().isBlank()) {
                databaseQuestion.setTitle(requestQuestion.getTitle());
            }

            if (requestQuestion != null && requestQuestion.getBody() != null && !requestQuestion.getBody().isBlank()) {
                databaseQuestion.setBody(requestQuestion.getBody());
            }

            updateAnswer(requestQuestion, databaseQuestion);

            return questionRepository.save(databaseQuestion);
        } else {
            System.out.println("No data found");
        }
        return null;
    }

    private void updateAnswer(Question requestQuestion, Question databaseQuestion) {
        if (requestQuestion != null && requestQuestion.getAnswers() != null && !requestQuestion.getAnswers().isEmpty()) {
            List<Answer> answerListRequest = requestQuestion.getAnswers();
            List<Answer> answerListDatabase = databaseQuestion.getAnswers();

            for (Answer answerRequest : answerListRequest) {
                boolean isNewId = true;
                if (answerRequest.getId() != null && !answerRequest.getId().isEmpty()) {
                    for (Answer answerDB : answerListDatabase) {
                        if (answerDB.getId().equals(answerRequest.getId())) {
                            answerDB.setBody(answerRequest.getBody());
                            answerDB.setLastUpdatedTime(answerRequest.getLastUpdatedTime());
                            isNewId = false;
                            break;
                        }
                    }
                }

                if (isNewId) {
                    answerListDatabase.add(answerRequest);
                }
            }
        }
    }
}
