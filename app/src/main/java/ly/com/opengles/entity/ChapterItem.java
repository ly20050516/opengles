package ly.com.opengles.entity;

/**
 * @author：ly on 2019-11-09 23:08
 * @mail：liuyan@zhimei.ai
 */
public class ChapterItem {

    String name;
    Class<?> activity;

    public ChapterItem(String name, Class<?> activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }
}
