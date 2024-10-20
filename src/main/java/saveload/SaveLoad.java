package saveload;

import model.WortTrainer;

import java.io.IOException;


/**
 * @author Ivan Milev
 * @version 2024-10-20
 * Dieses Interface dient dazu da um vorzugeben die die SaveLoad Strategien auszusehen haben
 */
public interface SaveLoad {
    public void save(WortTrainer wt) throws IOException;
    public WortTrainer load() throws IOException,ClassNotFoundException;
}