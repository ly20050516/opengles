package ly.com.opengles.business.main;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ly.com.light.sample0602.Light0602Activity;
import ly.com.light.sample0603.Light0603Activity;
import ly.com.light.sample0604.Light0604Activity;
import ly.com.light.sample0605.Light0605Activity;
import ly.com.light.sample0606.Light0606Activity;
import ly.com.light.sample0607.Light0607Activity;
import ly.com.light.sample0608.Light0608Activity;
import ly.com.light.sample0609.Light0609Activity;
import ly.com.light.sample0610.Light0610Activity;
import ly.com.opengles.R;
import ly.com.light.sample0601.Light0601Activity;
import ly.com.opengles.business.main.adapter.ChapterAdapter;
import ly.com.project.sample0501.Project0501Activity;
import ly.com.project.sample0502.Project0502Activity;
import ly.com.project.sample0503.Project0503Activity;
import ly.com.project.sample0504.Project0504Activity;
import ly.com.texture.sample0702.Texture0702Activity;
import ly.com.texture.sample0701.Texture0701Activity;
import ly.com.texture.sample0703.Texture0703Activity;
import ly.com.texture.sample0704.Texture0704Activity;
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

        ChapterItem chapterItem = new ChapterItem(getString(R.string.chapter_project_sample_5_1), Project0501Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_project_sample_5_2), Project0502Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_project_sample_5_3), Project0503Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_project_sample_5_4), Project0504Activity.class);
        chapterItems.add(chapterItem);

        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_1), Light0601Activity.class);
        chapterItems.add(chapterItem);

        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_2), Light0602Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_3), Light0603Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_4), Light0604Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_5), Light0605Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_6), Light0606Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_7), Light0607Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_8), Light0608Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_9), Light0609Activity.class);
        chapterItems.add(chapterItem);
        chapterItem = new ChapterItem(getString(R.string.chapter_light_sample_6_10), Light0610Activity.class);
        chapterItems.add(chapterItem);


        chapterItem = new ChapterItem(getString(R.string.chapter_texture_sample_7_1), Texture0701Activity.class);
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
