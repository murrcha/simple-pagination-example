package com.example.iceandfirecharacters.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.iceandfirecharacters.App;
import com.example.iceandfirecharacters.adapter.PageScrollListener;
import com.example.iceandfirecharacters.R;
import com.example.iceandfirecharacters.adapter.CharacterAdapter;
import com.example.iceandfirecharacters.adapter.CharacterItemDecoration;
import com.example.iceandfirecharacters.network.IceAndFireApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MainActivity
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 214;
    private static final int PER_PAGE = 10;

    private int page = FIRST_PAGE;

    private boolean isLoading = false;
    private boolean isLastPage = false;

    private IceAndFireApi api;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private PageScrollListener listener = new PageScrollListener(manager) {
        @Override
        protected void loadMoreItems() {
            isLoading = true;
            loadCharacters(page++);
        }

        @Override
        public int getLastPage() {
            return LAST_PAGE;
        }

        @Override
        public boolean isLastPage() {
            return isLastPage;
        }

        @Override
        public boolean isLoading() {
            return isLoading;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initApi();
        loadCharacters(page++);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                if (!isLoading) {
                    swipeRefreshLayout.setRefreshing(true);
                    refresh();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CharacterItemDecoration(8));
        recyclerView.addOnScrollListener(listener);
        adapter = new CharacterAdapter();
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::refresh);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void initApi() {
        api = ((App) getApplication()).getApi();
    }

    private void loadCharacters(int page) {
        if (page == FIRST_PAGE) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            showProgressFooter();
        }
        api.getCharacters(page, PER_PAGE)
                .doOnSubscribe(disposable -> {
                    isLoading = true;
                    compositeDisposable.add(disposable);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        characters -> {
                            if (page == FIRST_PAGE) {
                                progressBar.setVisibility(View.GONE);
                            } else {
                                adapter.removeLoadingFooter();
                            }
                            isLoading = false;
                            adapter.addAll(characters);

                        },
                        error -> {
                            Log.e(TAG, "onCreate: error", error);
                            adapter.removeLoadingFooter();
                            progressBar.setVisibility(View.GONE);
                        }
                );
    }

    private void showProgressFooter() {
        if (page <= LAST_PAGE) {
            adapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    private void refresh() {
        progressBar.setVisibility(View.VISIBLE);
        adapter.clear();
        adapter.notifyDataSetChanged();
        page = FIRST_PAGE;
        loadCharacters(page++);
        swipeRefreshLayout.setRefreshing(false);
    }
}
