package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class RecordCommand {
    private String header;
    private String description;
    private MultipartFile picture;
}
