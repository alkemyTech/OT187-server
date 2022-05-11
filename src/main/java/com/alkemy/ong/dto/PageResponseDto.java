package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> {

    private Page<T> page;
    private String previousPage;
    private String nextPage;

    public PageResponseDto(Page page, String url) {
        this.page = page;
        this.previousPage = page.hasPrevious() ? url + (page.previousOrFirstPageable().getPageNumber() + 1)  : null;
        this.nextPage = page.hasNext() ? url + (page.nextOrLastPageable().getPageNumber() + 1) : null;
    }
}
