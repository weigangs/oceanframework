package com.lkyl.oceanframework.web.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuppressWarnings("all")
public class PaginatedResult implements Serializable {
    private static final long serialVersionUID = 6191745064790884707L;

    private int currentPage; // Current page number
    private int totalPage; // Number of total pages
    private Long totalCount; //Number of total items
    private Object data; // Paginated resources
    private String code;
    private String msg;
}
