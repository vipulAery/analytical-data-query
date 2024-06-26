package com.example.analyticaldataquery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Query {
    private String query;
    private int page;
    private int pageSize = 1;
}
