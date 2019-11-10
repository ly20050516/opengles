package ly.com.opengles.business.main;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ly.com.opengles.R;
import ly.com.opengles.business.main.adapter.ChapterAdapter;
import ly.com.opengles.business.texture.sample0702.Texture0702Activity;
import ly.com.opengles.business.texture.sample0701.Texture0701Activity;
import ly.com.opengles.business.texture.sample0703.Texture0703Activity;
import ly.com.opengles.business.texture.sample0704.Texture0704Activity;
import ly.com.opengles.entity.ChapterItem;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * @author ly
 */
public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ChapterAdapter chapterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_chapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chapterAdapter = new ChapterAdapter();
        recyclerView.setAdapter(chapterAdapter);
        initChapterItemList();
    }

    private void initChapterItemList() {

        List<ChapterItem> chapterItems = new ArrayList<>();

        ChapterItem chapterItem = new ChapterItem(getString(R.string.chapter_texture_sample_7_1), Texture0701Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_texture_sample_7_2), Texture0702Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_texture_sample_7_3), Texture0703Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_texture_sample_7_4), Texture0704Activity.class);
        chapterItems.add(chapterItem);


        chapterAdapter.setChapterItemList(chapterItems);
        chapterAdapter.setChapterAdapterListener(new ChapterAdapter.ChapterAdapterListener() {
            @Override
            public void onItemClick(ChapterItem chapterItem) {
                onItemClickChapter(chapterItem);
            }
        });
    }

    private void onItemClickChapter(ChapterItem chapterItem) {

        if(chapterItem.getActivity() != null) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),chapterItem.getActivity());
            startActivity(intent);
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



    }
}
