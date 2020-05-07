package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

public class testIntegraree {
    private PaymentRepository payRepo;
    private MenuRepository menuRepo;
    private PizzaService serv;
    private List<Payment> paymentList;
    @BeforeEach
    void init(){
        paymentList=new ArrayList<Payment>();
        paymentList=spy(paymentList);
        payRepo=new PaymentRepository(paymentList);;
        menuRepo=new MenuRepository();
        serv=new PizzaService(menuRepo,payRepo);
    }
    @Test
    void testgetAll(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        paymentList.add(p);

        assertEquals(1,serv.getPayments().size());
    }
    @Test void testgetTotalAmount(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        paymentList.add(p);
        p.setAmount(12.2);
        assertEquals(12.2,serv.getTotalAmount(PaymentType.CASH));
    }
}
