package persistence;

import org.json.JSONObject;

// interface from JsonSerializationDemo project provided
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
