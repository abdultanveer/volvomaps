package com.next.volvomaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    public static String TAG = RxActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        Observable<String> animalsObservable = Observable.just("Ant", "Bee", "Cat", "Dog", "Fox"); //data source
        Observer<String> animalsObserver = getAnimalsObserver();

        animalsObservable //subscription
                .subscribeOn(Schedulers.io())  //we're querying data on io thread
                .observeOn(AndroidSchedulers.mainThread()) //after querying the data put it on mainThread conveyor belt
                .subscribe(animalsObserver); // animalobserver will be standing at the other end of conveyor belt to receive the data

    }


    private Observer<String> getAnimalsObserver() {

        Observer<String> salimAli  = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "Name: " + s);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");

            }
        };

        return salimAli;
    }

    }