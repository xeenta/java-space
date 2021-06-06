package xinta.examples.mapper;

public interface ServiceInterface {
    public void insertValue(String key, Value value);
    public void deleteValue(String key);
    public Value getValue(String key);
}
