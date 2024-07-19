package com.statrock.sdk.demo;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.statrock.sdk.StatRockListener;
import com.statrock.sdk.StatRockType;
import com.statrock.sdk.StatRockView;
import com.statrock.sdk.demo.util.DimensionUtils;

public class StatRockRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    private final RecyclerView.Adapter wrappedAdapter;
    @NonNull
    private final String placementId;
    @NonNull
    private final RecyclerView.AdapterDataObserver adapterDataObserver;

    private int adPosition = 2;
    private int adViewType = 100;

    public StatRockRecyclerViewAdapter(@NonNull RecyclerView.Adapter wrappedAdapter, @NonNull String placementId) {
        this.wrappedAdapter = wrappedAdapter;
        this.placementId = placementId;
        super.setHasStableIds(wrappedAdapter.hasStableIds());
        this.adapterDataObserver = new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                notifyItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                notifyDataSetChanged();
            }

        };
        wrappedAdapter.registerAdapterDataObserver(adapterDataObserver);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == adViewType) {
            Context context = parent.getContext();
            StatRockView statRockView = new StatRockView(context);
            statRockView.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtils.dp2px(context, 0)));
            statRockView.setListener(new StatRockListener() {
                @Override
                public void adLoaded() {
                    ViewGroup.LayoutParams params = statRockView.getLayoutParams();
                    params.height = DimensionUtils.dp2px(context, 200);
                    statRockView.setLayoutParams(params);
                }

                @Override
                public void adStarted() {
                }

                @Override
                public void adStopped() {
                    ViewGroup.LayoutParams params = statRockView.getLayoutParams();
                    params.height = 0;
                    statRockView.setLayoutParams(params);
                }

                @Override
                public void adError(Object message, Object error) {
                }
            });
            return new AdPlayerViewHolder(statRockView);
        }
        return wrappedAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == adPosition) {
            AdPlayerViewHolder myViewHolder = (AdPlayerViewHolder) holder;
            myViewHolder.load(placementId);
        } else {
            wrappedAdapter.onBindViewHolder(holder, wrappedPosition(position));
        }
    }

    @Override
    public int getItemCount() {
        if (wrappedAdapter.getItemCount() == 0) {
            return 0;
        }
        if (wrappedAdapter.getItemCount() < adPosition) {
            return wrappedAdapter.getItemCount();
        }
        return wrappedAdapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == adPosition) {
            return adViewType;
        } else {
            return wrappedAdapter.getItemViewType(wrappedPosition(position));
        }
    }

    @Override
    public long getItemId(int position) {
        if (position == adPosition) {
            return 1001;
        } else {
            return wrappedAdapter.getItemId(wrappedPosition(position));
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        wrappedAdapter.unregisterAdapterDataObserver(adapterDataObserver);
        wrappedAdapter.setHasStableIds(hasStableIds);
        wrappedAdapter.registerAdapterDataObserver(adapterDataObserver);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof AdPlayerViewHolder) {
            return super.onFailedToRecycleView(holder);
        } else {
            //noinspection unchecked
            return wrappedAdapter.onFailedToRecycleView(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof AdPlayerViewHolder) {
            super.onViewAttachedToWindow(holder);
        } else {
            //noinspection unchecked
            wrappedAdapter.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof AdPlayerViewHolder) {
            super.onViewDetachedFromWindow(holder);
        } else {
            //noinspection unchecked
            wrappedAdapter.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof AdPlayerViewHolder) {
            super.onViewRecycled(holder);
        } else {
            //noinspection unchecked
            wrappedAdapter.onViewRecycled(holder);
        }
    }

    public void destroy() {
        wrappedAdapter.unregisterAdapterDataObserver(adapterDataObserver);
    }

    private int wrappedPosition(int position) {
        if (position < adPosition) {
            return position;
        }
        return position - 1;
    }

    private static class AdPlayerViewHolder extends RecyclerView.ViewHolder {
        private final StatRockView adPlayerView;

        AdPlayerViewHolder(StatRockView adPlayerView) {
            super(adPlayerView);
            this.adPlayerView = adPlayerView;
        }

        void load(String placementId) {
            adPlayerView.load(placementId, StatRockType.IN_PAGE);
        }
    }
}
