package guru.springframework.services;

import guru.springframework.domain.Payment;
import guru.springframework.domain.PaymentEvent;
import guru.springframework.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {
    Payment createNewPayment(Payment payment);
    StateMachine<PaymentState, PaymentEvent> preAuthorize(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> declineAuthorization(Long paymentId);
}
