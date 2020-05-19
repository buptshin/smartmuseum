package com.example.smartmuseum.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookVisitViewModel extends ViewModel {
    private MutableLiveData<Integer> book_number;

    public MutableLiveData<Integer> getBook_number() {
        if (book_number == null) {
            book_number = new MutableLiveData<Integer>();
            book_number.setValue(0);
        }
        return book_number;
    }

    public void add(int n) {
        book_number.setValue(book_number.getValue() + n);
    }
}
