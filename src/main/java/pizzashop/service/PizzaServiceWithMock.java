package pizzashop.service;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class PizzaServiceWithMock {
    private PaymentRepository payRepo;
     private MenuRepository menuRepo;
       private PizzaService serv;
    @BeforeEach
    void init(){
        payRepo=mock(PaymentRepository.class);
        menuRepo=mock(MenuRepository.class);
        serv=new PizzaService(menuRepo,payRepo);
    }
    @Test void testgetMenuData(){
        MenuDataModel m1=new MenuDataModel("1",1,1.0);
        Mockito.when(menuRepo.getMenu()).thenReturn(Arrays.asList(m1));
        assertEquals(1,serv.getMenuData().size());

    }
    @Test void testgetPayments(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        Mockito.when(payRepo.getAll()).thenReturn(Arrays.asList(p));
        assertEquals(1,serv.getPayments().size());

    }
    @Test void testaddPayment(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        Mockito.when(payRepo.getAll()).thenReturn(Arrays.asList(p));
        Mockito.doNothing().when(payRepo).add(p);

        serv.addPayment(1,PaymentType.CASH,12.3);
        serv.addPayment(1,PaymentType.CASH,12.3);
        Mockito.verify(payRepo,times(2)).add(p);
        assertEquals(1,serv.getPayments().size());

    }
    @Test void testgetTotalAmount(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        Mockito.when(payRepo.getAll()).thenReturn(Arrays.asList(p));
        double d=serv.getTotalAmount(PaymentType.CASH);
        assertEquals(d,12.3);
    }
}
