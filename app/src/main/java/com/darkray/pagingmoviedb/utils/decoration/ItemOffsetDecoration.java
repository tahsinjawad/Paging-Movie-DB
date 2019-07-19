package com.darkray.pagingmoviedb.utils.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

   private int mItemOffset;

   private int top;
   private int bottom;
   private int left;
   private int right;

   public ItemOffsetDecoration(int itemOffset) {
       mItemOffset = itemOffset;
   }



   public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
       this(context.getResources().getDimensionPixelSize(itemOffsetId));
   }

   @Override

   public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

       super.getItemOffsets(outRect, view, parent, state);

       left = mItemOffset;
       right = mItemOffset;
       top = (mItemOffset/2);
       bottom = mItemOffset/2;

       if(parent.getChildAdapterPosition(view) == 0){
           top = mItemOffset;
       }

       else if(parent.getChildAdapterPosition(view) == state.getItemCount()-1){
           bottom = mItemOffset;
       }

       outRect.set(left, top, right, bottom);

   }

}