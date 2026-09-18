package DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TropelRequestDTO {
    private String name;
    private String species;
    private Long sectorId;
    private Long guardianId;
}
