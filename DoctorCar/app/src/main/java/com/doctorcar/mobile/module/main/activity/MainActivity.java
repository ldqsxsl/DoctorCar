package com.doctorcar.mobile.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.doctorcar.mobile.DemoActivity;
import com.doctorcar.mobile.DemoFragment;
import com.doctorcar.mobile.DemoViewPagerAdapter;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.module.main.adapter.MainViewPagerAdapter;
import com.doctorcar.mobile.module.main.fragment.MainFragment;
import com.doctorcar.mobile.utils.PermissionUtils;

import java.util.ArrayList;

/**
 * Created by dd on 2017/5/19.
 */

public class MainActivity  extends AppCompatActivity {

    private MainFragment currentFragment;
    private MainViewPagerAdapter adapter;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private boolean useMenuResource = false;
    private int[] tabColors;
    private Handler handler = new Handler();

    // UI
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
//	private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
                .getBoolean("translucentNavigation", false);
////		setTheme(enabledTranslucentNavigation ? android.R.style.Theme_Light_NoTitleBar_Fullscreen : R.style.AppTheme);
//		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        setContentView(R.layout.activity_home);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * Init UI
     */
    private void initUI() {
        readExternalStorage();
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
//		floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        updateBottomNavigationColor(false);
//		showOrHideBottomNavigation(true);//设置显示bar
        updateSelectedBackgroundVisibility(false);
        setForceTitleHide(false);
        if (useMenuResource) {
            tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
//			Toast.makeText(DemoActivity.this,"1111111111111111",Toast.LENGTH_SHORT).show();
        } else {
//			Toast.makeText(DemoActivity.this,"2222222222222222",Toast.LENGTH_SHORT).show();
            AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_apps_black_24dp, R.color.color_tab_1);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_help_24dp, R.color.color_tab_2);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_local_library_black_24dp, R.color.color_tab_3);
            AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4,  R.drawable.ic_language_black_24dp, R.color.color_tab_4);
            AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_group_black_24dp, R.color.color_tab_5);

            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);
            bottomNavigationItems.add(item3);
            bottomNavigationItems.add(item4);
            bottomNavigationItems.add(item5);

            bottomNavigation.addItems(bottomNavigationItems);
        }

//		bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

//				if (position == 2){
//					startActivity(new Intent(DemoActivity.this, WriteBlogActivity.class));
//				}


                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

                if (position == 1) {
                    bottomNavigation.setNotification("", 1);
//					floatingActionButton.setVisibility(View.VISIBLE);
//					floatingActionButton.setAlpha(0f);
//					floatingActionButton.setScaleX(0f);
//					floatingActionButton.setScaleY(0f);
//					floatingActionButton.animate()
//							.alpha(1)
//							.scaleX(1)
//							.scaleY(1)
//							.setDuration(300)
//							.setInterpolator(new OvershootInterpolator())
//							.setListener(new Animator.AnimatorListener() {
//								@Override
//								public void onAnimationStart(Animator animation) {
//
//								}
//
//								@Override
//								public void onAnimationEnd(Animator animation) {
//									floatingActionButton.animate()
//											.setInterpolator(new LinearOutSlowInInterpolator())
//											.start();
//								}
//
//								@Override
//								public void onAnimationCancel(Animator animation) {
//
//								}
//
//								@Override
//								public void onAnimationRepeat(Animator animation) {
//
//								}
//							})
//							.start();

                } else {
//					if (floatingActionButton.getVisibility() == View.VISIBLE) {
//						floatingActionButton.animate()
//								.alpha(0)
//								.scaleX(0)
//								.scaleY(0)
//								.setDuration(300)
//								.setInterpolator(new LinearOutSlowInInterpolator())
//								.setListener(new Animator.AnimatorListener() {
//									@Override
//									public void onAnimationStart(Animator animation) {
//
//									}
//
//									@Override
//									public void onAnimationEnd(Animator animation) {
//										floatingActionButton.setVisibility(View.GONE);
//									}
//
//									@Override
//									public void onAnimationCancel(Animator animation) {
//										floatingActionButton.setVisibility(View.GONE);
//									}
//
//									@Override
//									public void onAnimationRepeat(Animator animation) {
//
//									}
//								})
//								.start();
//					}
                }

                return true;
            }
        });

		/*
		bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
			@Override public void onPositionChange(int y) {
				Log.d("DemoActivity", "BottomNavigation Position: " + y);
			}
		});
		*/

        viewPager.setOffscreenPageLimit(4);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();

//		handler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				// Setting custom colors for notification
//				AHNotification notification = new AHNotification.Builder()
//						.setText("5")
//						.setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.red))
//						.setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.white))
//						.build();
//				bottomNavigation.setNotification(notification, 1);
//				Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
//						Snackbar.LENGTH_SHORT).show();
//
//			}
//		}, 3000);

        //bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_navigation_background);
    }

    /**
     * Update the bottom navigation colored param
     */
    public void updateBottomNavigationColor(boolean isColored) {
        bottomNavigation.setColored(isColored);
    }

    /**
     * Return if the bottom navigation is colored
     */
    public boolean isBottomNavigationColored() {
        return bottomNavigation.isColored();
    }

    /**
     * Add or remove items of the bottom navigation
     */
//	public void updateBottomNavigationItems(boolean addItems) {
//
//		if (useMenuResource) {
//			if (addItems) {
//				navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_5);
//				navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
//				bottomNavigation.setNotification("1", 3);
//			} else {
//				navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
//				navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
//			}
//
//		} else {
//			if (addItems) {
//				AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.tab_4),
//						ContextCompat.getDrawable(this, R.mipmap.ic_maps_local_bar),
//						ContextCompat.getColor(this, R.color.color_tab_4));
//				AHBottomNavigationItem item5 = new AHBottomNavigationItem(getString(R.string.tab_5),
//						ContextCompat.getDrawable(this, R.mipmap.ic_maps_place),
//						ContextCompat.getColor(this, R.color.color_tab_5));
//
//				bottomNavigation.addItem(item4);
//				bottomNavigation.addItem(item5);
//				bottomNavigation.setNotification("1", 3);
//			} else {
//				bottomNavigation.removeAllItems();
//				bottomNavigation.addItems(bottomNavigationItems);
//			}
//		}
//	}

    /**
     * Show or hide the bottom navigation with animation
     */
    public void showOrHideBottomNavigation(boolean show) {
        if (show) {
            bottomNavigation.restoreBottomNavigation(true);
        } else {
            bottomNavigation.hideBottomNavigation(true);
        }
    }

    /**
     * Show or hide selected item background
     */
    public void updateSelectedBackgroundVisibility(boolean isVisible) {
        bottomNavigation.setSelectedBackgroundVisible(isVisible);
    }

    /**
     * Show or hide selected item background
     */
    public void setForceTitleHide(boolean forceTitleHide) {
        AHBottomNavigation.TitleState state = forceTitleHide ? AHBottomNavigation.TitleState.ALWAYS_HIDE : AHBottomNavigation.TitleState.ALWAYS_SHOW;
        bottomNavigation.setTitleState(state);
    }

    /**
     * Reload activity
     */
    public void reload() {
        startActivity(new Intent(this, DemoActivity.class));
        finish();
    }

    /**
     * Return the number of items in the bottom navigation
     */
    public int getBottomNavigationNbItems() {
        return bottomNavigation.getItemsCount();
    }

    public void readExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

}