package com.example.root.myrxjava1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import info.androidhive.rxandroidexamples.R;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView1);

        // observable
        Observable<String> animalsObservable = getAnimalsObservable();

        // observer
        Observer<String> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable
        animalsObservable
//                .observeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);

//        Subscription subscription = animalsObservable
//                .subscribeOn(Schedulers.io())       //observable will run on IO thread.
//                .observeOn(AndroidSchedulers.mainThread())      //Observer will run on main thread.
//                .subscribe(observer);
    }


    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
                tv1.setText(" Item: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }



    private Observable<String> getAnimalsObservable() {
//        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
//        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox")
//                .filter(new Func1<String, Boolean>(){
//
//                });

        ArrayList<String> items = new ArrayList();
        items.add("red");
        items.add("orange");
        items.add("yellow");
        String[] myArray = {"helo", "there"};
        return Observable.fromArray(myArray);

    }

//    private Observable<String> getAnimalsObservable() {
////            new Observable.OnSubscribe<String>() {
//            return new ObservableOnSubscribe<String>() {
//
//                return public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                    emitter.onNext("x");
//                    emitter.onNext("z");
//                    emitter.onNext("y");
//
//                }
//            };
//    }

//    @Override
//                public void call(Subscriber<? super String> sub) {
//                    // "Emit" any data to the subscriber
//                    sub.onNext("a");
//                    sub.onNext("b");
//                    sub.onNext("c");
//                    // Trigger the completion of the event
//                    sub.onCompleted();
//                }
//            }
//    );
}
