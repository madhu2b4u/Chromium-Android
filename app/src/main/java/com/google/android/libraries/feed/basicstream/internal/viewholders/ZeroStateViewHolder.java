// Copyright 2018 The Feed Authors.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.android.libraries.feed.basicstream.internal.viewholders;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.libraries.feed.common.ui.LayoutUtils;
import com.google.android.libraries.feed.host.stream.CardConfiguration;

import java.util.ArrayList;
import java.util.List;

import news.NewsAdapter;
import news.NewsApi;
import news.model.Article;
import news.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.chromium.base.ContextUtils.getApplicationContext;

/** {@link android.support.v7.widget.RecyclerView.ViewHolder} for zero state. */
public class ZeroStateViewHolder extends FeedViewHolder {
  @VisibleForTesting static final int MORNING_HOUR_START = 0;
  @VisibleForTesting static final int AFTERNOON_HOUR_START = 12;
  @VisibleForTesting static final int EVENING_HOUR_START = 17;

  private final View zeroStateView;
  private final View loadingSpinner;
  private final View actionButton;
  private final TextView bodyText;
  private final CardConfiguration cardConfiguration;
  private NewsAdapter newsAdapter;
  RecyclerView recyclerView;
  private NewsResponse newsResponse;

  public ZeroStateViewHolder(
      Context context, FrameLayout frameLayout, CardConfiguration cardConfiguration) {
    super(frameLayout);
    View view = LayoutInflater.from(context).inflate(R.layout.zero_state, frameLayout);

    loadingSpinner = view.findViewById(R.id.loading_spinner);
    recyclerView = view.findViewById(R.id.rv_child);
    zeroStateView = view.findViewById(R.id.zero_state);
    actionButton = view.findViewById(R.id.action_button);
    bodyText = view.findViewById(R.id.body_text);
    getNews();

    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    this.cardConfiguration = cardConfiguration;
  }

  public void bind(OnClickListener onClickListener, int hour, boolean showSpinner) {
    ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
    if (layoutParams == null) {
      layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
      itemView.setLayoutParams(layoutParams);
    } else if (!(layoutParams instanceof MarginLayoutParams)) {
      layoutParams = new LayoutParams(layoutParams);
      itemView.setLayoutParams(layoutParams);
    }
    LayoutUtils.setMarginsRelative(
        (MarginLayoutParams) layoutParams,
        cardConfiguration.getCardStartMargin(),
        0,
        cardConfiguration.getCardEndMargin(),
        cardConfiguration.getCardBottomMargin());

    bodyText.setText(getBodyText(hour));
    actionButton.setOnClickListener(onClickListener);
    showSpinner(showSpinner);
  }

  @Override
  public void unbind() {
    // Clear OnClickListener to null to allow for GC.
    actionButton.setOnClickListener(null);

    // Set clickable to false as setting OnClickListener to null sets clickable to true.
    actionButton.setClickable(false);
  }

  public void showSpinner(boolean showSpinner) {
    loadingSpinner.setVisibility(showSpinner ? View.VISIBLE : View.GONE);
    zeroStateView.setVisibility(showSpinner ? View.GONE : View.VISIBLE);
  }

  @StringRes
  private int getBodyText(int hour) {
    if (hour >= MORNING_HOUR_START && hour < AFTERNOON_HOUR_START) {
      return R.string.zero_state_text_morning;
    } else if (hour >= AFTERNOON_HOUR_START && hour < EVENING_HOUR_START) {
      return R.string.zero_state_text_afternoon;
    } else {
      return R.string.zero_state_text_evening;
    }
  }

  private List<String> generateData(NewsResponse heroList) {
    List<String> data = new ArrayList<>();
    List<Article> articles;
    articles = heroList.getArticles();
    for (int i = 0; i < articles.size(); i++) {
      data.add(articles.get(i).getTitle());
    }
    return data;
  }


  private void getNews() {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();

    NewsApi api = retrofit.create(NewsApi.class);

    Call<NewsResponse> call = api.getNewsResponse();

    call.enqueue(new Callback<NewsResponse>() {
      @Override
      public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        newsResponse = response.body();
        recyclerView.setAdapter(new NewsAdapter(generateData(newsResponse)));
      }

      @Override
      public void onFailure(Call<NewsResponse> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
