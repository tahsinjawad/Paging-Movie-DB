package com.darkray.pagingmoviedb.utils.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private final int mItemOffset;

    private ItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }


    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override

    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        int left = mItemOffset;
        int right = mItemOffset;
        int top = (mItemOffset / 2);
        int bottom = mItemOffset / 2;

        if (parent.getChildAdapterPosition(view) == 0) {
            top = mItemOffset;
        } else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            bottom = mItemOffset;
        }

        outRect.set(left, top, right, bottom);

    }

}