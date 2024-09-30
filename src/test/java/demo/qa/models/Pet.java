package demo.qa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private String color;
    private int age;
    private String[] traits;
}
