package kotlincrash;

public class Observable<T> {
    interface Observer<T> {
        void onChanged(T value);
    }

    private T value = null;

    public void register(Observer<T> observer) {
        observer.onChanged(value);
    }
}