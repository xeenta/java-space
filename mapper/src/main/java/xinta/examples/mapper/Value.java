package xinta.examples.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {
    /* I am not using private, as usually is done, because I don't
     * want to pollute the code with trivial getters and setters (and
     * I don't want dependencies like Lombok and annotation processing...)
     * In real life, you won't write code like this... maybe! */
    public String name;
    public String surname;
    
    @JsonProperty(value = "appendices")
    public int numberOfAppendices;
}
