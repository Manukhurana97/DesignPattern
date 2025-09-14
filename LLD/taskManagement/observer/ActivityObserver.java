package observer;

public class ActivityObserver implements TaskObserver{
    @Override
    public void update(String description) {
        System.out.println("-> " +description);
    }
}