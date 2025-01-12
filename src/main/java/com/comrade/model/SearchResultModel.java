package com.comrade.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchResultModel implements Serializable {

    private List<NewsModel> news;

    private Long totalElements;

    private int totalPages;

    private int pageSize;

    private int pageNumber;

}
