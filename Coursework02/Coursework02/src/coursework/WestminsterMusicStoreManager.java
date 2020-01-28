package coursework;

import javafx.application.Application;

import java.time.LocalDateTime;
import java.util.*;

public class WestminsterMusicStoreManager implements StoreManager {  //implemented from the StoreManager
    protected List<MusicItem> storeItem = new ArrayList<MusicItem>();
    protected List<MusicItem> boughtItem = new ArrayList<MusicItem>();
    protected ArrayList<LocalDateTime> sellingTime = new ArrayList<>(1000);
    protected int space = 1000;
    protected int occurrence = 0;
    private static final int MAX_Count = 1000;
    Scanner input = new Scanner(System.in);
    @Override
    public void addItem(MusicItem item) throws StoreFullException {
            if (storeItem.size() < MAX_Count) {
                storeItem.add(item);
            } else {
                throw new StoreFullException("Store is full");
            }
        System.out.println(storeItem);
    }

    @Override
    public boolean deleteItem(MusicItem item) {
        return storeItem.remove(item);
    }

    public List<MusicItem> getStoreItem() {
        return storeItem;
    }

    @Override
    public void printItems() {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Application.launch(PrintItems.class);
                    }catch (IllegalStateException e){
                        System.out.println("Program Fails");
                    }
                }
            }.start();
            /*
        System.out.println("List of CDs");
        for (MusicItem item:storeItem) {
            if (item instanceof CD){
                System.out.println(item);
            }
        }

        System.out.println("List of Vinyls");
        for (MusicItem item:storeItem) {
            if (item instanceof Vinyl){
                System.out.println(item);
            }
        }

             */
    }

    @Override
    public void sortItem(List<MusicItem> list, SortCateogory c) {
        if (c == SortCateogory.PRICE){
            Collections.sort(list);
        }else if (c ==SortCateogory.TITLE){
            Collections.sort(list,new TitleComparator());
        }else {

        }
    }

    @Override
    public void buyItem(MusicItem item) throws NotAvailable {
        LocalDateTime time = LocalDateTime.now();
        sellingTime.add(time);
        if (storeItem.contains(item)){
            storeItem.remove(item);
            boughtItem.add(item);
            System.out.println("The time the item is bought "+time);
        }else {
            throw new NotAvailable("Item is not available");
        }
    }
    public List<MusicItem> getBoughtItem() {
        return boughtItem;
    }

    @Override
    public void generateReport() {
        System.out.println("The items that have been sold");
        System.out.println(boughtItem);
        System.out.println("And their respective times are "+sellingTime);
    }
    private static int getInteger(Scanner input){
        while (!(input.hasNextInt())){
            System.err.println("Invalid option");
            System.out.print("Please reenter: ");
            input.next();
        }
        return input.nextInt();
    }

    class TitleComparator implements Comparator<MusicItem>{
        @Override
        public int compare(MusicItem o1, MusicItem o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    static class StoreFullException extends Throwable {
        public StoreFullException() {
        }

        public StoreFullException(String store_is_full) {
            super();

        }
    }

    class NotAvailable extends Throwable {
        public NotAvailable(String item_is_not_available) {
            super();
        }
    }
}
