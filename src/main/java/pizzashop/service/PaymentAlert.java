package pizzashop.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentAlert implements PaymentOperation {
    String  pct="---------------------------";
    private PizzaService service;
    Logger logger=Logger.getLogger(PaymentAlert.class.getName());
    public PaymentAlert(PizzaService service){
        this.service=service;
    }

    @Override
    public void cardPayment() {
        logger.log(Level.INFO,pct);
        logger.log(Level.INFO,"Paying by card...");
        logger.log(Level.INFO,"Please insert your card!");
        logger.log(Level.INFO,pct);
    }
    @Override
    public void cashPayment() {
        logger.log(Level.INFO,pct);
        logger.log(Level.INFO,"Paying cash...");
        logger.log(Level.INFO,"Please show the cash...!");
        logger.log(Level.INFO,pct);
    }
    @Override
    public void cancelPayment() {
        logger.log(Level.INFO,pct);
        logger.log(Level.INFO,"Payment choice needed...");
        logger.log(Level.INFO,pct);
    }
      public void showPaymentAlert(int tableNumber, double totalAmount ) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.isPresent()&&result.get() == cardPayment) {
            cardPayment();
            service.addPayment(tableNumber, PaymentType.CARD,totalAmount);
        } else if (result.isPresent()&&result.get() == cashPayment) {
            cashPayment();
            service.addPayment(tableNumber, PaymentType.CASH,totalAmount);
        } else if (result.isPresent()&&result.get() == cancel) {
             cancelPayment();
        } else {
            cancelPayment();
        }
    }
}
