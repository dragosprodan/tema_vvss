package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {
    private static   MenuRepository menuRepo;
    private static  PaymentRepository payRepo;
    private static  PizzaService service;

    @BeforeAll
    @DisplayName("init")
   static void init(){
        menuRepo=new MenuRepository();
        payRepo=new PaymentRepository();
        service=new PizzaService(menuRepo,payRepo);
    }
    @Disabled
    @DisplayName("ECPTest2")
    @org.junit.jupiter.api.Test
    void addPaymentecp1() {
        //service.addPayment("Asd", PaymentType.CASH,"Asd");
        //this test can not be run because have wrong parameters
        System.out.println("test nerulat");
    }


    @org.junit.jupiter.api.Test
    @DisplayName("ECPTest1")
    void addPaymentecp2() {
        int n=service.getPayments().size();
        service.addPayment(1,PaymentType.CASH,1.0);
        int m=service.getPayments().size();
        assertEquals(n+1,m);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("ECPTest3")
    void addPaymentecp3() {
            int n=service.getPayments().size();
        service.addPayment(10,PaymentType.CASH,-1.0);
        int m=service.getPayments().size();
        assertEquals(n+1,m);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("bvaTest1")
    @ParameterizedTest
    @ValueSource(ints = {-1,0,1})
    void addPaymentecp4(int k) {
        int n=service.getPayments().size();
        service.addPayment(k,PaymentType.CASH,-1.0);
        int m=service.getPayments().size();
        assertEquals(n+1,m);
    }
    @org.junit.jupiter.api.Test
    @DisplayName("bvaTest2")
 @RepeatedTest(3)
    void addPaymentecp5(RepetitionInfo repetitionInfo) {
        int n=service.getPayments().size();
        service.addPayment(1,PaymentType.CASH,-2+repetitionInfo.getCurrentRepetition());
        int m=service.getPayments().size();
        assertEquals(n+1,m);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("bvaTest3")
    @RepeatedTest(3)
    void addPaymentecp6(RepetitionInfo repetitionInfo) {
        int n=service.getPayments().size();
        service.addPayment(6+repetitionInfo.getCurrentRepetition(),PaymentType.CASH,-2+repetitionInfo.getCurrentRepetition()+Double.MAX_VALUE);
        int m=service.getPayments().size();
        assertEquals(n+1,m);
    }

}