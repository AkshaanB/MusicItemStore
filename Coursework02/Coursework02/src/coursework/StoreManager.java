package coursework;

import java.util.List;

public interface StoreManager {
    void addItem(MusicItem item) throws WestminsterMusicStoreManager.StoreFullException;
    boolean deleteItem(MusicItem item);
    void printItems();
    void sortItem(List<MusicItem> list, SortCateogory c);
    void buyItem(MusicItem item) throws WestminsterMusicStoreManager.NotAvailable;
    void generateReport();

}
