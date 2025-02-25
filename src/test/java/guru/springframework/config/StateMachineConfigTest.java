package guru.springframework.config;

import guru.springframework.domain.PaymentEvent;
import guru.springframework.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBootApplication //????
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNewStateMachine(){
        StateMachine<PaymentState, PaymentEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());
        stateMachine.start();

        System.out.println(stateMachine.getState());
        stateMachine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println(stateMachine.getState());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        System.out.println(stateMachine.getState());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        System.out.println(stateMachine.getState());
    }
}