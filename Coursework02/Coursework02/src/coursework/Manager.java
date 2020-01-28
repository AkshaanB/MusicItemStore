package coursework;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.application.Application;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
    static WestminsterMusicStoreManager manager = new WestminsterMusicStoreManager();
    public static void main(String[] args) throws WestminsterMusicStoreManager.StoreFullException, WestminsterMusicStoreManager.NotAvailable {
        //Creating Mongo Client
        MongoClient mongoClient = new MongoClient("localhost",27017);

        //Creating credentials
        MongoCredential credential = MongoCredential.createCredential("Akshaan","MusicStoreDatabase","akshaan123456".toCharArray());

        System.out.println("Connected to Database successfully");

        //Accessing Database
        MongoDatabase database = mongoClient.getDatabase("MusicStoreDatabase"); //creates the database

        System.out.println("Credentials ::"+ credential);

        //database.createCollection("MusicStore");

        System.out.println("Connection created successfully");

        MongoCollection<Document> collection = database.getCollection("MusicStore");  //creates the collection

        System.out.println("Collection selected successfully");



        System.out.println();
        System.out.println("Welcome to the Online Music Store!");
        System.out.println();
        System.out.println("Please select");
        System.out.println("  1) Search an item");
        System.out.println("  2) Add a new item");
        System.out.println("  3) Delete an item");
        System.out.println("  4) Print the list of items");
        System.out.println("  5) Sort the items");
        System.out.println("  6) Generate report");
        System.out.println("  7) Buy an item");
        System.out.println("  8) Exit program");

        System.out.println("Enter option: ");
        System.out.println(">");
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        int option = getInteger(input);
        while (option <1 || option>8){
            System.err.println("Invalid input");
            System.out.print("Please reenter: ");
            option = getInteger(input);
        }
        while (option>=1 || option<=8) {
            switch (option) {
                case 1: //search item option
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Application.launch(Search.class);
                            }catch (IllegalStateException e){
                                System.out.println("Program Fails");
                            }
                        }
                    }.start();
                    System.out.print("Enter title to be found : ");
                    Scanner inputSearch = new Scanner(System.in);
                    String titleSearch = inputSearch.nextLine();
                    Document query = new Document();
                    query.put("title",titleSearch);
                    List<Document> list = (List<Document>) collection.find(query).into(
                            new ArrayList<Document>());
                        for (Document doc:list) {
                            System.out.println(doc);
                        }
                    break;
                case 2: //add item to the store option
                    System.out.print("Do you want to add CD or Vinyl ? (If CD enter 0 or 1 to add Vinyl)  ");
                    int choice = getInteger(input);
                    while (!(choice==0 || choice==1)){
                        System.err.println("Invalid response!!");
                        System.out.print("Please reenter : ");
                        choice = getInteger(input);
                    }
                    if (manager.occurrence < 1000) {
                    if (choice==0) {
                            System.out.println("------- Adding CDs -------");
                            System.out.print("Enter the no of items : ");
                            int noOfItems = getInteger(input);
                            for (int i = 1; i <= noOfItems; i++) {
                                System.out.print("Enter item Id : ");
                                int itemId = getInteger(input);
                                while (itemId<=0){
                                    System.err.println("Invalid item Id");
                                    System.out.print("Please reenter : ");
                                    itemId = getInteger(input);
                                }
                                System.out.print("Enter item title : ");
                                String title = input3.nextLine();
                                System.out.print("Enter item genre : ");
                                String genre = input2.nextLine();
                                System.out.print("Enter item release year : ");
                                int releaseYear = getInteger(input1);
                                while (releaseYear < 1900 || releaseYear > 2019) {
                                    System.err.println("Invalid year!!");
                                    System.out.print("Please reenter : ");
                                    releaseYear = getInteger(input1);
                                }
                                System.out.print("Enter item release month : ");
                                int releaseMonth = getInteger(input1);
                                while (0 >= releaseMonth || releaseMonth > 12) {
                                    System.err.println("Invalid month!!");
                                    System.out.print("Please reenter : ");
                                    releaseMonth = getInteger(input1);
                                }
                                System.out.print("Enter item release day : ");
                                int releaseDay = getInteger(input1);
                                if (releaseMonth % 2 == 0) {
                                    while (0 >= releaseDay || releaseDay > 30) {
                                        System.err.println("Invalid Day!!");
                                        System.out.print("Please reenter : ");
                                        releaseDay = getInteger(input1);
                                    }
                                } else {
                                    while (0 >= releaseDay || releaseDay > 31) {
                                        System.err.println("Invalid Day!!");
                                        System.out.print("Please reenter : ");
                                        releaseDay = getInteger(input1);
                                    }
                                }
                                String releaseDate = releaseYear + "/" + releaseMonth + "/" + releaseDay;
                                System.out.print("Enter artist name : ");
                                String artist = input2.nextLine();
                                System.out.print("Enter item price : ");
                                double price = getDouble(input1);
                                while (price<=0){
                                    System.err.println("Invalid price!");
                                    System.out.print("Please reenter : ");
                                    price = getDouble(input);
                                }
                                System.out.print("Enter duration : ");
                                int duration = getInteger(input);
                                while (duration<=0){
                                    System.err.println("Invalid duration!");
                                    System.out.print("Please reenter : ");
                                    duration = getInteger(input);
                                }
                                Document document1 = new Document("type", "CD");
                                document1.append("itemId", itemId);
                                document1.append("title", title);
                                document1.append("genre", genre);
                                document1.append("releaseDate", releaseDate);
                                document1.append("artist", artist);
                                document1.append("price", price);
                                document1.append("duration", duration);
                                collection.insertOne(document1); //adds items to the mongodb database

                                MusicItem cd = new CD(itemId, title, genre, releaseDate, artist, price, duration);
                                manager.addItem(cd);
                                manager.space -= 1;
                            }
                            manager.occurrence+=noOfItems;

                        } else {
                            System.out.print("Enter the no of items : ");
                            int noOfItems = getInteger(input);
                            for (int i = 1; i <= noOfItems; i++) {
                                System.out.println("------- Adding Vinyls -------");
                                System.out.print("Enter item Id : ");
                                int itemId = getInteger(input);
                                while (itemId<=0){
                                    System.err.println("Invalid item Id");
                                    System.out.print("Please reenter : ");
                                    itemId = getInteger(input);
                                }
                                System.out.print("Enter item title : ");
                                String title = input3.nextLine();
                                System.out.print("Enter item genre : ");
                                String genre = input2.nextLine();
                                System.out.print("Enter item release year : ");
                                int releaseYear = getInteger(input1);
                                while (releaseYear < 1900 || releaseYear > 2019) {
                                    System.err.println("Invalid year!!");
                                    System.out.print("Please reenter : ");
                                    releaseYear = getInteger(input1);
                                }
                                System.out.print("Enter item release month : ");
                                int releaseMonth = getInteger(input1);
                                while (0 >= releaseMonth || releaseMonth > 12) {
                                    System.err.println("Invalid month!!");
                                    System.out.print("Please reenter : ");
                                    releaseMonth = getInteger(input1);
                                }
                                System.out.print("Enter item release day : ");
                                int releaseDay = getInteger(input1);
                                if (releaseMonth % 2 == 0) {
                                    while (0 >= releaseDay || releaseDay > 30) {
                                        System.err.println("Invalid Day!!");
                                        System.out.print("Please reenter : ");
                                        releaseDay = getInteger(input1);
                                    }
                                } else {
                                    while (0 >= releaseDay || releaseDay > 31) {
                                        System.err.println("Invalid Day!!");
                                        System.out.print("Please reenter : ");
                                        releaseDay = getInteger(input1);
                                    }
                                }
                                String releaseDate = releaseYear + "/" + releaseMonth + "/" + releaseDay;
                                System.out.print("Enter artist name : ");
                                String artist = input2.nextLine();
                                System.out.print("Enter item price : ");
                                double price = getDouble(input1);
                                while (price<=0){
                                    System.err.println("Invalid price!");
                                    System.out.print("Please reenter : ");
                                    price = getDouble(input);
                                }
                                System.out.print("Enter speed : ");
                                double speed = getDouble(input);
                                while (speed<=0){
                                    System.err.println("Invalid speed!");
                                    System.out.print("Please reenter : ");
                                    speed = getDouble(input);
                                }
                                System.out.print("Enter diameter : ");
                                int diameter = getInteger(input);
                                while (diameter<=0){
                                    System.err.println("Invalid diameter!");
                                    System.out.print("Please reenter : ");
                                    diameter = getInteger(input);
                                }
                                Document document2 = new Document("type", "Vinyl");
                                document2.append("itemId", itemId);
                                document2.append("title", title);
                                document2.append("genre", genre);
                                document2.append("releaseDate", releaseDate);
                                document2.append("artist", artist);
                                document2.append("price", price);
                                document2.append("speed", speed);
                                document2.append("diameter", diameter);
                                collection.insertOne(document2); //adds items to the mongodb database
                                MusicItem vinyl = new Vinyl(itemId, title, genre, releaseDate, artist, price, speed, diameter);
                                manager.addItem(vinyl);
                                manager.space -= 1;
                            }
                            manager.occurrence+=noOfItems;
                        System.out.println("The spaces left are : "+manager.space);
                        }

                    }else {
                        throw new WestminsterMusicStoreManager.StoreFullException("Store is full!");
                    }
                    break;
                case 3: //delete items from the store option
                    if (manager.storeItem.size()>0) {
                        System.out.println("------- Delete an item -------");
                        System.out.print("Enter the no of items : ");
                        int noOfItems = getInteger(input);
                        while (noOfItems<=0){
                            System.err.println("Invalid no of items!");
                            System.out.print("Please reenter : ");
                            noOfItems = getInteger(input);
                        }
                        for (int i = 1; i <= noOfItems; i++) {
                            System.out.print("Enter the item Id : ");
                            int itemId = getInteger(input);
                            while (itemId<=0){
                                System.err.println("Invalid item Id!");
                                System.out.print("Please reenter : ");
                                itemId = getInteger(input);
                            }
                            collection.deleteOne(Filters.eq("itemId",itemId));
                            for (MusicItem item : manager.getStoreItem()) {
                                if (item.getItemID() == itemId) {
                                    manager.deleteItem(item);
                                    System.out.println("Delete item is successful");
                                    break;
                                }
                            }
                        }
                        System.out.println(manager.storeItem);
                        System.out.println("No of free spaces : " + (manager.space + noOfItems));
                    }else {
                        System.out.println("Store is empty!");
                    }
                    break;
                case 4: //print list of items in the store option
                    Document query1 = new Document();
                    List<Document> list1 = (List<Document>) collection.find(query1).into(
                            new ArrayList<Document>());
                    for (Document doc:list1) {
                        System.out.println(doc);
                    }
                    manager.printItems();
                    break;
                case 5: //sorting option
                    System.out.println("Before sorting ;");
                    System.out.println(manager.storeItem);
                    manager.sortItem(manager.storeItem, SortCateogory.TITLE);
                    System.out.println("After sorting ;");
                    System.out.println(manager.storeItem);
                    break;
                case 6:
                    manager.generateReport();
                    break;
                case 7: //buy items from the store option
                    System.out.println("------- Buy item/s -------");
                    System.out.print("Enter the no of items : ");
                    int noOfItems = getInteger(input);
                    for (int i = 1; i <=noOfItems ; i++) {
                        System.out.print("Enter the item Id : ");
                        int buyItemId = getInteger(input);
                        while (buyItemId<=0){
                            System.err.println("Invalid item Id!");
                            System.out.print("Please reenter : ");
                            buyItemId = getInteger(input);
                        }
                        for (MusicItem item : manager.getStoreItem()) {
                            if (item.getItemID() == buyItemId) {
                                manager.buyItem(item);
                                collection.deleteOne(Filters.eq("itemId",buyItemId));
                                System.out.println("You have bought the item!");
                                break;
                            }
                        }
                        manager.space += noOfItems;
                    }
                    System.out.println("The item/s you bought : "+manager.boughtItem);
                    System.out.println("The no of items are : "+noOfItems);
                    System.out.println("Thanks for buying our product/s !");
                    break;
                default: //exits the program option
                    System.out.println("------- The program is quiting -------");
                    System.exit(0);
            }
            System.out.println("Please select");
            System.out.println("  1) Search an item");
            System.out.println("  2) Add a new item");
            System.out.println("  3) Delete an item");
            System.out.println("  4) Print the list of items");
            System.out.println("  5) Sort the items");
            System.out.println("  6) Generate report");
            System.out.println("  7) Buy an item");
            System.out.println("  8) Exit program");
            try {
                System.out.println("Enter option: ");
                System.out.print(">");
                option = getInteger(input);
            }catch (Exception e){
                System.err.print("Please enter another value : ");
                option = getInteger(input);
            }
            while (option <1 || option>8){
                System.err.println("Invalid input");
                System.out.print("Please reenter: ");
                option = getInteger(input);
            }

        }
    }
    private static int getInteger(Scanner input){
        while (!(input.hasNextInt())){
            System.err.println("Invalid input");
            System.out.print("Please reenter: ");
            input.next();
        }
        return input.nextInt();
    }
    private static double getDouble(Scanner input){
        while (!(input.hasNextDouble())){
            System.err.println("Invalid input!!");
            System.out.print("Please reenter: ");
            input.next();
        }
        return input.nextDouble();
    }
}