package ly.com.opengles.business.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ly.com.opengles.R;
import ly.com.opengles.entity.ChapterItem;

/**
 * @author：ly on 2019-11-09 23:12
 * @mail：liuyan@zhimei.ai
 */
public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    List<ChapterItem> chapterItemList = new ArrayList<>();
    ChapterAdapterListener chapterAdapterListener;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_chapter_title);
        }
    }

    @NonNull
    @Override
    public ChapterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ViewHolder holder, int position) {

        final ChapterItem chapterItem = chapterItemList.get(position);
        holder.textViewTitle.setText(chapterItem.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chapterAdapterListener != null) {
                    chapterAdapterListener.onItemClick(chapterItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterItemList == null ? 0 : chapterItemList.size();
    }

    public List<ChapterItem> getChapterItemList() {
        return chapterItemList;
    }

    public void setChapterItemList(List<ChapterItem> chapterItemList) {
        this.chapterItemList = chapterItemList;
        notifyDataSetChanged();
    }

    public ChapterAdapterListener getChapterAdapterListener() {
        return chapterAdapterListener;
    }

    public void setChapterAdapterListener(ChapterAdapterListener chapterAdapterListener) {
        this.chapterAdapterListener = chapterAdapterListener;
    }

    public interface ChapterAdapterListener {

        /**
         * 点击 Item
         * @param chapterItem
         */
        void onItemClick(ChapterItem chapterItem);
    }
}
