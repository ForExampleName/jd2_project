package by.academy.dto;

import by.academy.pojo.record.Record;
import by.academy.util.BlobPictureUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class RecordDto {
    private String id;
    private String header;
    private String description;
    private String picture;
    private String time;

    public RecordDto(Record record) {
        this.id = record.getId();
        this.header = record.getHeader();
        this.description = record.getDescription();
        byte[] picture = record.getPicture();
        if (picture != null) {
            this.picture = BlobPictureUtil.getJspImageSrcUsingByteArray(picture);
        }
        if (record.getCreationTime().toLocalDate().isEqual(LocalDate.now())) {
            this.time = record.getCreationTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            this.time = record.getCreationTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }
    }
}
