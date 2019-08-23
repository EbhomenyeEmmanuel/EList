package com.esq.todolist;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public interface OnButtonClickListener {
    void onButtonClick(Button button, int position);
}
