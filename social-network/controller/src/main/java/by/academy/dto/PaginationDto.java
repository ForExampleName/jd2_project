package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaginationDto {
    private boolean showPagination;
    private boolean showLeftArrow;
    private boolean showRightArrow;
    private int pageNumber;
}
