package app.lxfy.sectormenudemo;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView menuName;
    private TextView menuTag;
    private ViewPagerSlide viewPagerSlide;

    //二维数组存放了一些必要的资源
    private int[][] ids = {
            {R.id.menu_home_id, R.drawable.selector_menu_icon_home, R.string.nav_home, R.string.nav_home_tag}
            ,{R.id.menu_enterprise_id, R.drawable.selector_menu_icon_enterprise, R.string.nav_enterprise, R.string.nav_enterprise_tag}
            ,{R.id.menu_declare_id, R.drawable.selector_menu_icon_declare, R.string.nav_declare, R.string.nav_declare_tag}
            ,{R.id.menu_message_id, R.drawable.selector_menu_icon_message, R.string.nav_message, R.string.nav_message_tag}
            ,{R.id.menu_me_id, R.drawable.selector_menu_icon_me, R.string.nav_me, R.string.nav_me_tag}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTxtBlack(false);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private SimpleFragment[] fragments = new SimpleFragment[ids.length];

    private void initViews() {
        menuName = findViewById(R.id.menu_name);
        menuTag = findViewById(R.id.menu_tag);

        //初始化ViewPager
        viewPagerSlide = findViewById(R.id.view_pager_slide);

        for (int i = 0; i < ids.length; i++){
            fragments[i] = SimpleFragment.newInstance(getString(ids[i][2]));
        }

        viewPagerSlide.setSlide(false);
        viewPagerSlide.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        //设置底部菜单
        BottomSectorMenuView.Converter converter = new BottomSectorMenuView.Converter(findViewById(R.id.fab_view))
                .setToggleDuration(500, 800)
                .setSelectListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectMenuItem(v.getId());
                    }
                });
        for (int i = 0; i < ids.length; i++){
            converter.addMenuItem(createMenuItemView(ids[i][0],ids[i][1],ids[i][2]));
        }

        converter.apply();
    }

    //选中了某个ITEM 准切的来说，应该是切换到新的item才会触发
    public void selectMenuItem(@IdRes int id){
        for (int i = 0; i < ids.length; i++){
            if (ids[i][0] == id){
                menuName.setText(ids[i][2]);
                menuTag.setText(ids[i][3]);
                viewPagerSlide.setCurrentItem(i);
            }
        }
    }

    //创建菜单ITEM
    private View createMenuItemView(@IdRes int viewId, @DrawableRes int iconRes, @StringRes int resId){
        View itemView = LayoutInflater.from(this).inflate(R.layout.layout_menu_item, null);
        itemView.setId(viewId);
        TextView nameTv = itemView.findViewById(R.id.item_menu_name_tv);
        ImageView imgView = itemView.findViewById(R.id.item_menu_img_view);
        nameTv.setText(resId);
        imgView.setImageResource(iconRes);
        return itemView;
    }

}
