package saveload;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.WortTrainer;

import java.io.File;
import java.io.IOException;


/**
 * @author Ivan Milev
 * @version 2024-10-20
 * Diese Klasse implementiert die SaveLoad Interface, wodurch WortTrainer gespeichert werden können. In unserem Fall
 * handelt es sich um die Speicherung im JSON Format
 */
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
