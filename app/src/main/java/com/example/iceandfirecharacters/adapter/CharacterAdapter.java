package com.example.iceandfirecharacters.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iceandfirecharacters.R;
import com.example.iceandfirecharacters.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * CharacterAdapter
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;

    @NonNull
    private List<Character> characters = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            default:
            case ITEM:
                View itemView = inflater.inflate(R.layout.character_item, viewGroup, false);
                viewHolder = new CharacterHolder(itemView);
                break;
            case LOADING:
                View loadingView = inflater.inflate(R.layout.loading_item, viewGroup, false);
                viewHolder = new LoadingViewHolder(loadingView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            default:
            case ITEM:
                ((CharacterHolder) viewHolder).bind(characters.get(i));
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == (characters.size() - 1) && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void add(@NonNull Character character) {
        characters.add(character);
        notifyItemInserted(characters.size() - 1);
    }

    public void addAll(@NonNull List<Character> characters) {
        for (Character character : characters) {
            add(character);
        }
    }

    public void remove(@NonNull Character character) {
        int position = characters.indexOf(character);
        if (position > -1) {
            characters.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Character());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = characters.size() - 1;
        Character item = getItem(position);
        if (item != null) {
            characters.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Character getItem(int position) {
        return characters.get(position);
    }

    class CharacterHolder extends RecyclerView.ViewHolder {

        private static final String NO_VALUE = "-";
        private static final String EMPTY_VALUE = " ";

        @NonNull
        private TextView name;
        @NonNull
        private TextView culture;
        @NonNull
        private TextView titles;
        @NonNull
        private TextView aliases;

        public CharacterHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            culture = itemView.findViewById(R.id.culture);
            titles = itemView.findViewById(R.id.titles);
            aliases = itemView.findViewById(R.id.aliases);
        }

        void bind(@NonNull Character character) {
            name.setText(TextUtils.isEmpty(character.name) ? NO_VALUE : character.name);
            culture.setText(TextUtils.isEmpty(character.culture) ? NO_VALUE : character.culture);
            bindStringsFromList(character.titles, titles);
            bindStringsFromList(character.aliases, aliases);
        }

        private void bindStringsFromList(@Nullable List<String> strings, @NonNull TextView view) {
            if (strings != null && !strings.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (String string : strings) {
                    if (string.trim().isEmpty()) {
                        builder.append(NO_VALUE);
                        break;
                    }
                    builder.append(string);
                    builder.append(EMPTY_VALUE);
                }
                view.setText(builder.toString());
            } else {
                view.setText(NO_VALUE);
            }
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
