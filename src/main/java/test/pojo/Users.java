package test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;

@Data
@AllArgsConstructor
public class Users {
    private int id;
    private String name;
}
