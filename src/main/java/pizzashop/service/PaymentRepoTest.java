package pizzashop.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.Spy;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentRepoTest {
     List<Payment> paymentList;
     PaymentRepository payRepo;
    @BeforeEach
     void init(){
paymentList=new ArrayList<Payment>();
paymentList=spy(paymentList);
payRepo=new PaymentRepository(paymentList);
    }
    @Test void testaddPayment(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        payRepo.add1(p);
        assertEquals(1,payRepo.getAll().size());
        Mockito.verify(paymentList,times(1)).add(p);
    }
    @Test void testgetAll(){
        Payment p=new Payment(1, PaymentType.CASH,12.3);
        Payment p1=new Payment(2, PaymentType.CASH,12.2);
        payRepo.add1(p);
        payRepo.add1(p1);
        assertEquals(2,payRepo.getAll().size());
        Mockito.verify(paymentList,times(1)).add(p);
    }
}
