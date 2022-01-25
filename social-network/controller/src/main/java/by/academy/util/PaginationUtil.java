package by.academy.util;

import by.academy.dto.PaginationDto;

public final class PaginationUtil {
    public static PaginationDto getRecordPaginationDto(int page, int total) {
        PaginationDto paginationDto = new PaginationDto();
        if (total <= 1) {
            return paginationDto;
        }
        if (page != 0) {
            paginationDto.setShowLeftArrow(true);
        }
        if (page != total - 1) {
            paginationDto.setShowRightArrow(true);
        }
        paginationDto.setPageNumber(page);
        paginationDto.setShowPagination(true);
        return paginationDto;
    }
}
