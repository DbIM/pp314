package webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // любые поля, не связанные с полями класса, должны быть проигнорированы
public class User {

    private Long id;
    private String name;
    private String lastName;
    private byte age;

}