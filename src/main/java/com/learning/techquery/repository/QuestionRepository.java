package com.learning.techquery.repository;

import com.learning.techquery.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query("{'answers' : {'$elemMatch': {'body': '?0'', 'answeredBy': '?1'}}}")
    Question findAnswerByBodyAndAnsweredBy(String body,String answeredBy);
}
