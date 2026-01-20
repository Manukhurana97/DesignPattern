     /*
 PaymentGatewayExample.java
 Compact LLD example showing:
 - PaymentRequest/Response
 - PaymentProvider (Strategy)
 - ProviderFactory / Registry
 - RetryDecorator (Decorator)
 - PaymentState (State pattern)
 - PaymentContext & PaymentGateway (Facade)
 - InMemory PaymentRepository
*/


import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

enum ProviderType {PAYTM, PHONEPE};
enum PaymentStatus {CREATED, VALIDATED, PROCESSING, SUCCESS, FAILED};

class PaymentRequest {
    private final String transactionId;
    private final String userId;
    private final long amount;
    private final String currency;
    private PaymentStatus state;
    private final ProviderType providertype;
    private final String message;
    private final Instant createdAt;

    public PaymentRequest(String userId, long amount, String currency, ProviderType providertype, String message) {
        this.transactionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.state = PaymentStatus.CREATED;
        this.providertype = providertype;
        this.message = message;
        this.createdAt = Instant.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public ProviderType getProvidertype() {
        return providertype;
    }

    public void setState(PaymentStatus state) {
        this.state = state;
    }
}

class PaymentResponse {
    private final String transactionId;
    private final long amount;
    private final String currency;
    private final PaymentStatus state;
    private final String message;

    public PaymentResponse(String transactionId, long amount, String currency, PaymentStatus state, String message) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.state = state;
        this.message = message;
    }
}


// ---------------- Repository ----------------
interface PaymentRepository {
    void savePayment(PaymentRequest request);
    List<PaymentRequest> getAllPayment();
}

class InMemoryPaymentRepository implements PaymentRepository {
    private final Map<String, PaymentRequest> payments = new ConcurrentHashMap<>();

    public void savePayment(PaymentRequest request) {
        payments.put(request.getTransactionId(), request);
    }
    public List<PaymentRequest> getAllPayment() {
        return List.copyOf(payments.values());
    }
}

// ---------------- Strategy: PaymentProvider ----------------
interface PaymentProvider {
    PaymentResponse charge(PaymentRequest paymentRequest) throws Exception;
}

class Paytym implements PaymentProvider {
    public PaymentResponse charge(PaymentRequest paymentRequest) throws InterruptedException {
        Thread.sleep(50);
        boolean ok = ThreadLocalRandom.current().nextInt(100) < 99;
        if(ok) {
            return new PaymentResponse(paymentRequest.getTransactionId(), paymentRequest.getAmount(), paymentRequest.getCurrency(), PaymentStatus.SUCCESS, "OK");
        }else{
            throw new RuntimeException("PAYTM_NET_ERR");
        }
    }
}

class Phone implements PaymentProvider {
    public PaymentResponse charge(PaymentRequest paymentRequest) throws InterruptedException {
        Thread.sleep(50);
        boolean ok = ThreadLocalRandom.current().nextInt(100) < 95;
        if(ok) {
            return new PaymentResponse(paymentRequest.getTransactionId(), paymentRequest.getAmount(), paymentRequest.getCurrency(), PaymentStatus.SUCCESS, "Phonepe payment successful");
        }else{
            throw new RuntimeException("PHONEPE_NET_ERR");
        }
    }
}


// ---------------- Decorator: Retry ----------------
abstract class PaymentProviderDecorator implements PaymentProvider {
    PaymentProvider inner;
    int maxRetry;
    
    public PaymentProviderDecorator(PaymentProvider inner, int maxRetry) {
        this.inner = inner;
        this.maxRetry = maxRetry;
    }
}

class RetryPaymentDecorator extends PaymentProviderDecorator {

    public RetryPaymentDecorator(PaymentProvider provider, int maxRetry) {
        super(provider, maxRetry);
    }

    @Override
    public PaymentResponse charge(PaymentRequest paymentRequest) throws Exception {
        int attempt = 0;
        Exception last = null;

        while(attempt<maxRetry) {
            attempt++;

            try {
                return inner.charge(paymentRequest);
            } catch (Exception e) {
                last = e;
                Thread.sleep(50L *attempt);
            }
        }
        throw last;
    }
}


// ---------------- Factory / Registry ----------------

class ProviderFactory {
    private final Map<ProviderType, PaymentProvider> providers = new HashMap<>();

    public void registerProvider(ProviderType type, PaymentProvider provider) {
        providers.put(type, provider);
    }

    public PaymentProvider getProvider(ProviderType type) {
        return providers.get(type);
    }
}


// ---------------- State pattern for payment flow ----------------

class PaymentContext {
    PaymentState state;
    PaymentResponse response;

    final PaymentRequest request;
    final ProviderFactory factory;
    final PaymentRepository repository;


    PaymentContext(PaymentRequest request, ProviderFactory factory, PaymentRepository repository) {
        this.request = request;
        this.factory = factory;
        this.repository = repository;
        this.state = new CreatedState();
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public void proceed() {
        this.state.handle(this);
    }
}

interface PaymentState {
    void handle(PaymentContext paymentContext);
}

class CreatedState implements PaymentState {

    @Override
    public void handle(PaymentContext paymentContext) {
        if(paymentContext.request.getAmount() <= 0) {
            paymentContext.request.setState(PaymentStatus.FAILED);
            paymentContext.response = new PaymentResponse(paymentContext.request.getTransactionId(), paymentContext.request.getAmount(), paymentContext.request.getCurrency(), PaymentStatus.FAILED, "Invalid Amound");
            paymentContext.repository.savePayment(paymentContext.request);
            paymentContext.setState(new FailedState());
        }

        paymentContext.setState(new ValidatedState());
        paymentContext.proceed();
    }
}

class ValidatedState implements PaymentState {

    @Override
    public void handle(PaymentContext paymentContext) {

        paymentContext.setState(new ProcessingState());
        paymentContext.proceed();
    }
}

class ProcessingState implements PaymentState {

    @Override
    public void handle(PaymentContext paymentContext){
        PaymentProvider provider = paymentContext.factory.getProvider(paymentContext.request.getProvidertype());
        if(provider == null) {
            paymentContext.request.setState(PaymentStatus.FAILED);
            paymentContext.response = new PaymentResponse(paymentContext.request.getTransactionId(), paymentContext.request.getAmount(), paymentContext.request.getCurrency(), PaymentStatus.FAILED, "Invalid gateway provider");
            paymentContext.repository.savePayment(paymentContext.request);
            paymentContext.setState(new FailedState());
        }
        try {
            paymentContext.request.setState(PaymentStatus.SUCCESS);
            paymentContext.response = provider.charge(paymentContext.request);
            paymentContext.repository.savePayment(paymentContext.request);
            paymentContext.setState(new CompletedState());
        } catch(Exception e) {
            paymentContext.request.setState(PaymentStatus.FAILED);
            paymentContext.response = new PaymentResponse(paymentContext.request.getTransactionId(), paymentContext.request.getAmount(), paymentContext.request.getCurrency(), PaymentStatus.FAILED, e.getMessage());
            paymentContext.repository.savePayment(paymentContext.request);
            paymentContext.setState(new FailedState());
        }
    }
}

class CompletedState implements PaymentState {

    @Override
    public void handle(PaymentContext paymentContext) {
    }
}

class FailedState implements PaymentState {

    @Override
    public void handle(PaymentContext paymentContext) {
    }
}

// Facade
class Paymentgateway {
    private final ProviderFactory factory;
    private final PaymentRepository repository;

    public Paymentgateway(ProviderFactory factory, PaymentRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }


    public PaymentResponse process(PaymentRequest request) {
        PaymentContext context = new PaymentContext(request, factory, repository);
        context.proceed();
        return context.response;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}