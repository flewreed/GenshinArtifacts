package com.example.genshinartifacts.adapters.other;

import androidx.recyclerview.widget.RecyclerView;

public abstract class HiddenRecyclerView extends RecyclerView.OnScrollListener {

   int scrollDist = 0;
   boolean isVisible = true;
   static final float MINIMUM = 65;

   @Override
   public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);

      if (isVisible && scrollDist > MINIMUM) {
         hide();
         scrollDist = 0;
         isVisible = false;
      }
      else if (!isVisible && scrollDist < -MINIMUM) {
         show();
         scrollDist = 0;
         isVisible = true;
      }

      if (!isVisible && !recyclerView.canScrollVertically(1)) {
         show();
         scrollDist = 0;
         isVisible = true;
      }

      if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
         scrollDist += dy;
      }
   }

   public abstract void show();
   public abstract void hide();
}