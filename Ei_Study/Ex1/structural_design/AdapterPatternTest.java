package Ei_Study.Ex1.structural_design;

// Existing Payment Processor (Old system)
class OldPaymentProcessor {
    public void processOldPayment(double amount) {
        System.out.println("Processing payment in old system: " + amount);
    }
}

// New Payment Interface
interface NewPaymentProcessor {
    void processPayment(double amount);
}

// Adapter Class
class PaymentAdapter implements NewPaymentProcessor {
    private OldPaymentProcessor oldPaymentProcessor;

    public PaymentAdapter(OldPaymentProcessor oldPaymentProcessor) {
        this.oldPaymentProcessor = oldPaymentProcessor;
    }

    @Override
    public void processPayment(double amount) {
        oldPaymentProcessor.processOldPayment(amount);
    }
}

// Test
public class AdapterPatternTest {
    public static void main(String[] args) {
        OldPaymentProcessor oldPaymentProcessor = new OldPaymentProcessor();
        NewPaymentProcessor adapter = new PaymentAdapter(oldPaymentProcessor);

        adapter.processPayment(100.00); 
    }
}
