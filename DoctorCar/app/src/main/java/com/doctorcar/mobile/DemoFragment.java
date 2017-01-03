package com.doctorcar.mobile;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */
public class DemoFragment extends Fragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private Button button;
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
			View view = inflater.inflate(R.layout.fragment_demo_settings, container, false);
//			initDemoSettings(view);
			return view;
		} else if(arguments == 1){
			View view = inflater.inflate(R.layout.fragment_mine, container, false);
			initDemoMine(view);
			return view;
		}else if(arguments == 2){
			View view = inflater.inflate(R.layout.fragment_mall, container, false);
			return view;
		}else {
			View view = inflater.inflate(R.layout.fragment_demo_list, container, false);
			initDemoList(view);
			return view;
		}
	}

	/**
	 * Init demo settings
	 */
	private void initDemoSettings(View view) {

		final DemoActivity demoActivity = (DemoActivity) getActivity();
		final SwitchCompat switchColored = (SwitchCompat) view.findViewById(R.id.fragment_demo_switch_colored);
		final SwitchCompat switchFiveItems = (SwitchCompat) view.findViewById(R.id.fragment_demo_switch_five_items);
		final SwitchCompat showHideBottomNavigation = (SwitchCompat) view.findViewById(R.id.fragment_demo_show_hide);
		final SwitchCompat showSelectedBackground = (SwitchCompat) view.findViewById(R.id.fragment_demo_selected_background);
		final SwitchCompat switchForceTitleHide = (SwitchCompat) view.findViewById(R.id.fragment_demo_force_title_hide);
		final SwitchCompat switchTranslucentNavigation = (SwitchCompat) view.findViewById(R.id.fragment_demo_translucent_navigation);

		switchColored.setChecked(demoActivity.isBottomNavigationColored());
		switchFiveItems.setChecked(demoActivity.getBottomNavigationNbItems() == 5);
		switchTranslucentNavigation.setChecked(getActivity()
				.getSharedPreferences("shared", Context.MODE_PRIVATE)
				.getBoolean("translucentNavigation", false));
		switchTranslucentNavigation.setVisibility(
				Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? View.VISIBLE : View.GONE);

		switchTranslucentNavigation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getActivity()
						.getSharedPreferences("shared", Context.MODE_PRIVATE)
						.edit()
						.putBoolean("translucentNavigation", isChecked)
						.apply();
				demoActivity.reload();
			}
		});
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

		fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
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

	private void initDemoMine(View view) {
		button = (Button) view.findViewById(R.id.popupMenu);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PopupMenu popup = new PopupMenu(getActivity(), button);
				popup.setGravity(Gravity.RIGHT);
				//Inflating the Popup using xml file
				popup.getMenuInflater()
						.inflate(R.menu.mine_menu, popup.getMenu());

				//registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {

								switch(item.getItemId()){
									case R.id.a1:
										Toast.makeText(getActivity(),"a1",Toast.LENGTH_SHORT).show();break;
									case R.id.a2:Toast.makeText(getActivity(),"a2",Toast.LENGTH_SHORT).show();break;
									case R.id.a3:break;
									case R.id.a4:break;
								}
						return true;
					}
				});

				popup.show(); //showing popup menu
			}
		});
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
}
