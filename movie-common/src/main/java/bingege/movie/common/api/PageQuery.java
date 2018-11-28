package bingege.movie.common.api;

import bingege.movie.common.constant.AppConstants;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageQuery {

    @Range(min = 0, message = "页码不能小于零")
    private Integer page;

    @Range(max = 50, message = "页数不能超过50")
    private Integer size;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page == null ? AppConstants.DEFAULT_PAGE : page;
    }

    public Integer getSize() {
        return size == null ? AppConstants.DEFAULT_SIZE : size;
    }

    public Pageable page() {
        return PageRequest.of(page, size);
    }
}