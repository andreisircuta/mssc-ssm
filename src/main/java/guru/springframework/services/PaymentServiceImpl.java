package guru.springframework.services;

import guru.springframework.domain.Payment;
import guru.springframework.domain.PaymentEvent;
import guru.springframework.domain.PaymentState;
import guru.springframework.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;

    @Override
    public Payment createNewPayment(Payment payment) {
        payment.setState(PaymentState.NEW);
        return paymentRepository.save(payment);
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> preAuthorize(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);

        return null;
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);

        return null;
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> declineAuthorization(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);

        return null;
    }

    private StateMachine<PaymentState, PaymentEvent> build(Long paymentId){
        Payment payment = paymentRepository.getOne(paymentId);
        StateMachine<PaymentState, PaymentEvent> stateMachine = stateMachineFactory.getStateMachine(Long.toString(payment.getId()));

        stateMachine.stop();
        //set the state machine state to be the state from the databese
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(stateMachineAccessor ->{
                    stateMachineAccessor.resetStateMachine(new DefaultStateMachineContext<>(payment.getState(), null, null, null));
                });
        stateMachine.start();

        return stateMachine;
    }
}
