package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserPageRecordDto {
    private boolean showControls;
    private List<RecordDto> records;
    private PaginationDto pagination;
}
