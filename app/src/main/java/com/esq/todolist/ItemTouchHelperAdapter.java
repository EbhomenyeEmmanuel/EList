package com.esq.todolist;
//Interface added to listen for event callbacks
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
