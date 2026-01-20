package state;

public interface OrderState {
     void orderAccept();
     void orderPreparing();
     void outForDeliver();
     void delivered();
     void canceled();
}
