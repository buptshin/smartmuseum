package com.example.smartmuseum.util.eventBus;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private final Subject<Object> subject = PublishSubject.create().toSerialized();

    private RxBus() {

    }

    public static RxBus getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final RxBus sInstance = new RxBus();
    }

    public void post(Object ob) {
        subject.onNext(ob);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return subject.ofType(eventType);
    }
}
