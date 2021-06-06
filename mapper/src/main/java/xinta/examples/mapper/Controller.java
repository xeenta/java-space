package xinta.examples.mapper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController /* implies @ResponseBody */
public class Controller implements ServiceInterface 
{
    final static Logger logger = LoggerFactory.getLogger(Controller.class);
    
    Map<String, Value> store;
    
    public Controller() {
        store = new HashMap<String, Value>();
    }
    
    @GetMapping(value = "/key/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Value getValue(@PathVariable String key) {
        if (store.containsKey(key)) {
            return store.get(key);
        }
        throw new KeyNotFound();
    }
    

    @PostMapping(value = "/key/{key}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insertValue(@PathVariable String key, @RequestBody Value value) {
        // we don't allow to overwrite a key
        if (store.containsKey(key)) {
            throw new KeyAlreadyExists();
        }
        store.put(key, value);
    }
    
    @DeleteMapping(value = "/key/{key}")
    /* let's try to use keyToBeDeleted as variable name, but make it
     * read the value from the {key} path variable */
    public void deleteValue(@PathVariable("key") String keyToBeDeleted) {
        if (store.containsKey(keyToBeDeleted)) {
            store.remove(keyToBeDeleted);
        } else {
            logger.info("key {} didn't exist", keyToBeDeleted);
            // if you delete non-existing keys, it's ok...
        }
    }
}
