package com.comrade.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsModel implements Serializable {

    private Long newsId;

    private String newsTitle;

    private String newsAuthor;

    private Timestamp createdAt;

    private Timestamp modifiedAt;
}
