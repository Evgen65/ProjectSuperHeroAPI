package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RequestDto {
    int id;
    String birthDate;
    String city;
    String fullName;
    String gender;
    String mainSkill;
    String phone;
}
