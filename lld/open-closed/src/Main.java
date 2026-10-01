import java.math.BigDecimal;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("15994.04");
        PaymentMethod pm = PaymentType.val("UPI");
        pm.processPayment(amount);

        pm = PaymentType.val("Card");
        pm.processPayment(amount);
    }

    enum PaymentType  {
        CARD(new Card()), UPI(new Upi()), NET_BANKING(new NetBanking());

        PaymentMethod pm;

        PaymentType(PaymentMethod pm) {
            this.pm = pm;
        }

        public static PaymentMethod val(String type) {
            PaymentType pm = Arrays.stream(values()).filter(n -> n.name().equalsIgnoreCase(type)).findFirst().orElseThrow(() -> new RuntimeException("Payment Type Unsupported"));
            return pm.pm;
        }
    }

    interface PaymentMethod {
        void processPayment(BigDecimal amount);

    }

    static class Card implements PaymentMethod {
        @Override
        public void processPayment(BigDecimal amount) {
            System.out.println("Card payment....");
            System.out.println(amount.plus().multiply(new BigDecimal("3")));
        }
    }

    static class Upi implements PaymentMethod {
        @Override
        public void processPayment(BigDecimal amount) {
            System.out.println("Upi payment.....");
            System.out.println(amount.plus().multiply(new BigDecimal("1.5")));
        }
    }

    static class NetBanking implements PaymentMethod {
        @Override
        public void processPayment(BigDecimal amount) {
            System.out.println("NetBanking payment.....");
            System.out.println(amount.plus().multiply(new BigDecimal("0.05")));
        }
    }


}