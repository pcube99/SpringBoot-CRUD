package com.project.library.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String id;
    @JsonProperty("book_id")
    private String bookId;
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("book_author")
    private String bookAuthor;
    @JsonProperty("book_price")
    private String bookPrice;
}
