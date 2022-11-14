package com.apoorv.criteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Getter
@Setter
@AllArgsConstructor
public class StudentPageDTO {
    private String search;
    private Long id;
    private int pageNumber;
    private int pageSize;
}
