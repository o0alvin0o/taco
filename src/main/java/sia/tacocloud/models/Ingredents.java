package sia.tacocloud.models;

import lombok.Data;

@Data
public class Ingredents {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
