package com.example.iceandfirecharacters.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * CharacterItemDecoration
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public class CharacterItemDecoration extends RecyclerView.ItemDecoration {

    private final int margin;

    public CharacterItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(margin + margin, margin, margin + margin, margin);
        int position = parent.getChildAdapterPosition(view);
        if (position == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = margin + margin;
        }
        if (position == 0) {
            outRect.top = margin + margin;
        }
    }
}
