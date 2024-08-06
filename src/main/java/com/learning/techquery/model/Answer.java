package com.learning.techquery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Answer {
    private String id;
    private String body;
    private String answeredBy;
    private String lastUpdatedTime;
    List<Comment> comments;

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            this.id = new ObjectId().toString();
        } else {
            this.id = id;
        }
    }
}
