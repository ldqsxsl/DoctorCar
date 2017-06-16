package com.doctorcar.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.commonutils.ImageLoaderUtils;
import com.doctorcar.mobile.module.ask.activity.AskActivity;
import com.doctorcar.mobile.module.ask.activity.AskAlreadyFragment;
import com.doctorcar.mobile.module.ask.activity.MyAskFragment;
import com.doctorcar.mobile.module.ask.activity.MyFocusProblemFragment;
import com.doctorcar.mobile.module.ask.activity.WaitSolveFragment;
import com.doctorcar.mobile.module.ask.fragment.FragmentTab1;
import com.doctorcar.mobile.module.ask.fragment.FragmentTab2;
import com.doctorcar.mobile.module.ask.fragment.FragmentTab3;
import com.doctorcar.mobile.module.ask.fragment.FragmentTab4;
import com.doctorcar.mobile.module.ask.adapter.FragmentAdapter;
import com.doctorcar.mobile.module.blog.activity.WriteBlogActivity;
import com.doctorcar.mobile.module.blog.fragment.DraftBoxFragment;
import com.doctorcar.mobile.module.blog.fragment.MineArticlesFragment;
import com.doctorcar.mobile.module.login.activity.LoginActivity;
import com.doctorcar.mobile.module.mine.activity.AboutUsActivity;
import com.doctorcar.mobile.module.mine.activity.FeedbackActivity;
import com.doctorcar.mobile.utils.SPUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;
import com.doctorcar.mobile.view.layout.EasyIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */
public class DemoFragment extends Fragment {

    private LinearLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;


    RecyclerView recyclerView1;
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Handler handler = new Handler();


    /**
     * Create a new instance of the fragment
     */
    public static DemoFragment newInstance(int index) {
        DemoFragment fragment = new DemoFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int arguments = getArguments().getInt("index", 0);
        if (arguments == 0) {
            View view = inflater.inflate(R.layout.home_fragment, container, false);
            initHomeView(view);
//			initDemoSettings(view);
            return view;
        } else if (arguments == 1) {
            View view = inflater.inflate(R.layout.ask_fragment, container, false);
            FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            TextView title = (TextView) view.findViewById(R.id.toolbar_title);
            title.setText("问问");
            toolbar.inflateMenu(R.menu.menu_toolbar);
            toolbar.setNavigationIcon(R.color.transparent);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AskActivity.class));
                }
            });
            EasyIndicator easyIndicator = (EasyIndicator) view.findViewById(R.id.ask_fg_easy_indicator);
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.ask_fg_view_pager);
            easyIndicator.setTabTitles(new String[]{"已解决", "待解决","我的关注", "我的问答"});
            easyIndicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
                @Override
                public void onTabClick(String title, int position) {

                }
            });
            // 自定义设置
            easyIndicator.setViewPage(viewPager, new FragmentAdapter(getFragmentManager(),
                    new Fragment[]{new AskAlreadyFragment(), new WaitSolveFragment(),new MyFocusProblemFragment(), new MyAskFragment()}));

//			initDemoList(view);
            return view;
        } else if (arguments == 2) {
            View view = inflater.inflate(R.layout.blog_fragment, container, false);
            initBlogView(view);
//            initView(view);
//            initData();
            return view;
        } else if (arguments == 3) {
            View view = inflater.inflate(R.layout.friend_fragment, container, false);
            initFriendView(view);
            return view;
        } else {
            View view = inflater.inflate(R.layout.my_fragment, container, false);
            initDemoMine(view);
            return view;
        }
    }

    public void initBlogView(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        title.setText("Blog");

        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setNavigationIcon(R.color.transparent);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WriteBlogActivity.class));
            }
        });
        EasyIndicator easyIndicator = (EasyIndicator) view.findViewById(R.id.blog_fg_easy_indicator);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.blog_fg_view_pager);
        easyIndicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {

            }
        });
        easyIndicator.setTabTitles(new String[]{"我的文章", "草稿箱"});

        // 自定义设置
        easyIndicator.setViewPage(viewPager, new FragmentAdapter(getFragmentManager(),
                new Fragment[]{new MineArticlesFragment(), new DraftBoxFragment()}));


    }

    public void initFriendView(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        title.setText("Friend");
    }

    public void initHomeView(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        EasyIndicator easyIndicator = (EasyIndicator) view.findViewById(R.id.home_fg_easy_indicator);
        title.setText("首页");
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setNavigationIcon(R.color.transparent);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_fg_view_pager);
        easyIndicator.setTabTitles(new String[]{"技术博客", "汽车资讯", "技术问答", "案例分享"});
        easyIndicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {

            }
        });
        // 自定义设置
        easyIndicator.setViewPage(viewPager, new FragmentAdapter(getFragmentManager(),
                new Fragment[]{new FragmentTab1(), new FragmentTab2(),
                        new FragmentTab3(), new FragmentTab4()}));

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_fg_recycler_view);
//        easyIndicator.setTabTitles(new String[]{"已完成", "已评价"});
//        easyIndicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
//            @Override
//            public void onTabClick(String title, int position) {
//
//            }
//        });
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.HORIZONTAL, dip2px(mContext, 0.5f), ContextCompat.getColor(mContext, R.color.line_color)));
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(new MyAdapter(mContext));

    }


    public void addView(LinearLayout view, List<String> drawable) {
        List<RelativeLayout> viewList = new ArrayList<RelativeLayout>();
        for (int i = 0; i < 10; i++) {
            ImageView iv1 = new ImageView(getActivity());
            ImageView iv2 = new ImageView(getActivity());
            iv1.setImageResource(R.drawable.ic_image2vector);
            iv2.setImageResource(R.drawable.circle_orange);
            iv1.setId(R.id.iv1);
            iv2.setId(R.id.iv2);

            RelativeLayout rl = new RelativeLayout(getActivity());
            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(160, 160);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv1.setLayoutParams(layoutParams2);
            iv2.setLayoutParams(layoutParams3);
            layoutParams2.leftMargin = 30;
            layoutParams3.leftMargin = 150;
            rl.addView(iv1);
            rl.addView(iv2);
            rl.setLayoutParams(layoutParams1);
            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TLUtil.showLog("111111111111111");
                }
            });
            iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TLUtil.showLog("2222222222222222");
                }
            });
            view.addView(rl);
        }
    }

    public void initView(View view) {
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        adapter = new RecyclerViewAdapter(getContext(), data);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_tab_4);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        getData();
                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(adapter);
        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
                ((DemoActivity) getActivity()).showOrHideBottomNavigation(true);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * Init demo settings
     */
    private void initDemoSettings(View view) {

//        final DemoActivity demoActivity = (DemoActivity) getActivity();
//        final SwitchCompat switchColored = (SwitchCompat) view.findViewById(R.id.fragment_demo_switch_colored);
//        final SwitchCompat switchFiveItems = (SwitchCompat) view.findViewById(R.id.fragment_demo_switch_five_items);
//        final SwitchCompat showHideBottomNavigation = (SwitchCompat) view.findViewById(R.id.fragment_demo_show_hide);
//        final SwitchCompat showSelectedBackground = (SwitchCompat) view.findViewById(R.id.fragment_demo_selected_background);
//        final SwitchCompat switchForceTitleHide = (SwitchCompat) view.findViewById(R.id.fragment_demo_force_title_hide);
//        final SwitchCompat switchTranslucentNavigation = (SwitchCompat) view.findViewById(R.id.fragment_demo_translucent_navigation);

//        switchColored.setChecked(demoActivity.isBottomNavigationColored());
//        switchFiveItems.setChecked(demoActivity.getBottomNavigationNbItems() == 5);
//        switchTranslucentNavigation.setChecked(getActivity()
//                .getSharedPreferences("shared", Context.MODE_PRIVATE)
//                .getBoolean("translucentNavigation", false));
//        switchTranslucentNavigation.setVisibility(
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? View.VISIBLE : View.GONE);
//
//        switchTranslucentNavigation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                getActivity()
//                        .getSharedPreferences("shared", Context.MODE_PRIVATE)
//                        .edit()
//                        .putBoolean("translucentNavigation", isChecked)
//                        .apply();
//                demoActivity.reload();
//            }
//        });
//		switchColored.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				demoActivity.updateBottomNavigationColor(isChecked);
//			}
//		});
//		switchFiveItems.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				demoActivity.updateBottomNavigationItems(isChecked);
//			}
//		});
//		showHideBottomNavigation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				demoActivity.showOrHideBottomNavigation(true);
//			}
//		});
//		showSelectedBackground.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				demoActivity.updateSelectedBackgroundVisibility(isChecked);
//			}
//		});
//		switchForceTitleHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				Toast.makeText(getActivity(),isChecked+"",Toast.LENGTH_SHORT).show();
//				demoActivity.setForceTitleHide(false);
//			}
//		});
    }

    /**
     * Init the fragment
     */
    private void initDemoList(View view) {

        fragmentContainer = (LinearLayout) view.findViewById(R.id.fragment_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_demo_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemsData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
        }

        DemoAdapter adapter = new DemoAdapter(itemsData);
        recyclerView.setAdapter(adapter);
    }

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {


        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }


    private void initDemoMine(View view) {
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        final TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        toolbar.setNavigationIcon(R.color.transparent);
        toolbar.inflateMenu(R.menu.toobar);
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                TextView textView = (TextView) toolbar.getMenu().getItem(0).getActionView();
                if (state == State.EXPANDED) {
                    textView.setBackgroundResource(R.drawable.ic_equalizer_black_24dp);
                    textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    toolbar.setOverflowIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_notifications_none_white_24dp));
                    title.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    //展开状态
                } else if (state == State.COLLAPSED) {
                    toolbar.setOverflowIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_notifications_none_black_24dp));
                    title.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    textView.setBackgroundResource(R.drawable.ic_equalizer_black_24dp);
                    //折叠状态
                } else {
                    //中间状态
                }
            }
        });

        CircleImageView headIv = (CircleImageView) view.findViewById(R.id.my_fg_head_iv);
        TextView headNameTv = (TextView) view.findViewById(R.id.my_fg_head_name_tv);
        TextView headDescriptionTv = (TextView) view.findViewById(R.id.my_fg_head_description_tv);
        ImageLoaderUtils.display(getActivity(), headIv, "http://192.168.20.87:8080/images/head.jpg");
        TextView signTv = (TextView) view.findViewById(R.id.my_fg_head_sign_tv);

        User user = UserUtils.getUser();
        if (user != null) {
            headNameTv.setText(user.getUser_phone());
        }

        headNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });




        view.findViewById(R.id.my_feedback_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });

        view.findViewById(R.id.my_check_version_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TLUtil.showToast("已经是最新版本");
            }
        });

        view.findViewById(R.id.my_about_us_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });

//		button = (Button) view.findViewById(R.id.popupMenu);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
////              startActivity(new Intent(getActivity(), ViewActivity.class));
//              startActivity(new Intent(getActivity(), BrandAndModelActivity.class));
//			}
//		});
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				PopupMenu popup = new PopupMenu(getActivity(), button);
//				popup.setGravity(Gravity.RI GHT);
//				//Inflating the Popup using xml file
//				popup.getMenuInflater()
//						.inflate(R.menu.mine_menu, popup.getMenu());
//
//				//registering popup with OnMenuItemClickListener
//				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//					public boolean onMenuItemClick(MenuItem item) {
//
//								switch(item.getItemId()){
//									case R.id.a1:
//										Toast.makeText(getActivity(),"a1",Toast.LENGTH_SHORT).show();break;
//									case R.id.a2:Toast.makeText(getActivity(),"a2",Toast.LENGTH_SHORT).show();break;
//									case R.id.a3:break;
//									case R.id.a4:break;
//								}
//						return true;
//					}
//				});
//				popup.show(); //showing popup menu
//			}
//		});
    }

    /**
     * Refresh
     */
    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }


    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }


}
