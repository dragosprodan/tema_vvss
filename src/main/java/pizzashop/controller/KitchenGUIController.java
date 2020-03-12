package pizzashop.controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.util.Calendar;


public class KitchenGUIController {
    Logger logger = Logger.getLogger(KitchenGUIController.class.getName());

    @FXML
    private ListView kitchenOrdersList;
    @FXML
    public Button cook;
    @FXML
    public Button ready;

    protected static final  ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    private String extractedTableNumberString = "";
    private int extractedTableNumberInteger;
    //thread for adding data to kitchenOrderList
    private Thread addOrders = new Thread(()-> {
        while (true) {
                Platform.runLater(()-> {
                            kitchenOrdersList.setItems(order);
                        });
                try {
                    Thread.sleep(100);
                  } catch (InterruptedException ex) {
                    
                    break;
                }
            }
    });

    public void initialize() {
        //starting thread for adding data to kitchenOrderList
        addOrders.setDaemon(true);
        addOrders.start();
        //Controller for Cook Button
        cook.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            kitchenOrdersList.getItems().remove(selectedOrder);
            kitchenOrdersList.getItems().add(selectedOrder.toString()
                     .concat(" Cooking started at: ").toUpperCase()
                     .concat(now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)));
        });
        //Controller for Ready Button
        ready.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            kitchenOrdersList.getItems().remove(selectedOrder);
            extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
            extractedTableNumberInteger = Integer.valueOf(extractedTableNumberString);
            logger.log(Level.INFO,"--------------------");
            logger.log(Level.INFO,"Table " + extractedTableNumberInteger +" ready at: " + now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE));
            logger.log(Level.INFO,"--------------------");
        });
    }
}