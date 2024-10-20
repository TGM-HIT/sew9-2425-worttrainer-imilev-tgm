package saveload;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.WortTrainer;

import java.io.File;
import java.io.IOException;

public class SaveLoadJson implements SaveLoad {
    private static final String FILE_PATH = "worttrainer.json";
    private ObjectMapper objectMapper;

    public SaveLoadJson() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(WortTrainer wt) throws IOException {
        // Serialize the WortTrainer object into a JSON file
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), wt);
        System.out.println("WortTrainer wurde als JSON gespeichert.");
    }

    @Override
    public WortTrainer load() throws IOException, ClassNotFoundException {
        // Deserialize the JSON file back into a WortTrainer object
        WortTrainer wortTrainer = objectMapper.readValue(new File(FILE_PATH), WortTrainer.class);
        System.out.println("WortTrainer wurde erfolgreich aus JSON geladen.");
        return wortTrainer;
    }
}
