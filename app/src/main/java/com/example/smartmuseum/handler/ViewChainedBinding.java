package com.example.smartmuseum.handler;

public interface ViewChainedBinding<T> {
    T bindView();
    T bindData();
    T bindEvent();
}