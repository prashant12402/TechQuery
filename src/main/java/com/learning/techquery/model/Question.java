package com.learning.techquery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Document("question")
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    private String id;
    private String title;
    private String body;
    private String tag;
    private String lastUpdatedTime;
    List<Answer> answers;
}

