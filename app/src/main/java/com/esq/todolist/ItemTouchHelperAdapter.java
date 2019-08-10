package com.esq.todolist;
//Interface added to listen for event callbacks
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
