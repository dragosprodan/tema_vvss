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
    private static  PaymentRepository payRepoEmpty;
    private static  PizzaService serviceEmpty;
    private static  PaymentRepository payRepo;
    private static  PizzaService service;
    private static  PaymentRepository payRepoFalse;
    private static  PizzaService serviceFalse;

    @BeforeAll
    @DisplayName("init")
   static void init(){
        menuRepo=new MenuRepository();
        payRepoEmpty=new PaymentRepository("data/paymentsTest1.txt");
        serviceEmpty=new PizzaService(menuRepo,payRepoEmpty);
        payRepo=new PaymentRepository();
        service=new PizzaService(menuRepo,payRepo);
        payRepoFalse=new PaymentRepository("data/paymentsTest1.txt",false);
        serviceFalse=new PizzaService(menuRepo,payRepoFalse);
    }

    @Test
    void getTotalAmount1_0() {

        Double result=serviceFalse.getTotalAmount(PaymentType.CASH);
        assertEquals(0,result);
    }
    @Test
    void getTotalAmount1_1() {

        Double result=serviceFalse.getTotalAmount(PaymentType.CARD);
        assertEquals(0,result);
    }
    @Test
    void getTotalAmount2_0() {

        Double result=serviceEmpty.getTotalAmount(PaymentType.CASH);

        assertEquals(0,result);
    }
    @Test
    void getTotalAmount2_1() {

        Double result=serviceEmpty.getTotalAmount(PaymentType.CARD);
        assertEquals(0,result);
    }    @Test
    void getTotalAmount3_0() {
        service.addPayment(1,PaymentType.CASH,1.0);
        Double result=service.getTotalAmount(PaymentType.CASH);
        assertNotEquals(0,result);
    }
    @Test
    void getTotalAmount3_1() {
        service.addPayment(1,PaymentType.CARD,1.0);
        Double result=service.getTotalAmount(PaymentType.CARD);
        assertNotEquals(0,result);
    }
//    @Disabled
//    @DisplayName("ECPTest2")
//    @org.junit.jupiter.api.Test
//    void addPaymentecp1() {
//        //service.addPayment("Asd", PaymentType.CASH,"Asd");
//        //this test can not be run because have wrong parameters
//        System.out.println("test nerulat");
//    }
//
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("ECPTest1")
//    void addPaymentecp2() {
//        int n=service.getPayments().size();
//        service.addPayment(1,PaymentType.CASH,1.0);
//        int m=service.getPayments().size();
//        assertEquals(n+1,m);
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("ECPTest3")
//    void addPaymentecp3() {
//
//        assertThrows(IllegalArgumentException.class,()->{service.addPayment(10,PaymentType.CASH,-1.0);});
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("bvaTest1")
//    @ParameterizedTest
//    @ValueSource(ints = {-1,0,1})
//    void addPaymentecp4(int k) {
//
//            assertThrows(IllegalArgumentException.class,()->{service.addPayment(k,PaymentType.CASH,-1.0);});
//
//    }
//    @org.junit.jupiter.api.Test
//    @DisplayName("bvaTest2")
// @RepeatedTest(3)
//    void addPaymentecp5(RepetitionInfo repetitionInfo) {
//        if(repetitionInfo.getCurrentRepetition()>=2){
//        int n=service.getPayments().size();
//        service.addPayment(1,PaymentType.CASH,-2+repetitionInfo.getCurrentRepetition());
//        int m=service.getPayments().size();
//        assertEquals(n+1,m);}
//        else{
//            assertThrows(IllegalArgumentException.class,()->{service.addPayment(1,PaymentType.CASH,-2+repetitionInfo.getCurrentRepetition());});
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("bvaTest3")
//    @RepeatedTest(3)
//    void addPaymentecp6(RepetitionInfo repetitionInfo) {
//        int n=service.getPayments().size();
//        service.addPayment(6+repetitionInfo.getCurrentRepetition(),PaymentType.CASH,-2+repetitionInfo.getCurrentRepetition()+Double.MAX_VALUE);
//        int m=service.getPayments().size();
//        assertEquals(n+1,m);
//    }

}