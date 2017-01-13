package com.android.kdpsettings;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.ContentResolver;
import android.content.Context;
import android.os.SystemProperties;
import android.app.Activity;
import android.widget.Toast;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.ListPreference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.app.Fragment;
import android.app.FragmentManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.kdpsettings.R;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
							  FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
								FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
								FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		Context mContext;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {
				case 0:
					// this is just to show it compiles
					return new KangDroidTestFragment();
				case 1:
					return new KangDroidTestFragment();
				case 2:
					return new KangDroidTestFragment();
			}
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
//            Fragment fragment = new DummySectionFragment();
//            Bundle args = new Bundle();
//            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//            fragment.setArguments(args);
			return null;
		}


		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return "NULL";
				case 1:
					return "NULL";
				case 2:
					return "School Info";
			}
			return null;
		}
	}

	//Starting of Fragment Activity(TabActivity) Classes. I can't do separated cuz I don't have any time to doit.

	public static class KangDroidTestFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		private static final String TAG = "KangDroidAnimSettings";
	
		private static final String KEY_TOAST_ANIMATION = "toast_animation";
	    private static final String KEY_LISTVIEW_ANIMATION = "listview_animation";
	    private static final String KEY_LISTVIEW_INTERPOLATOR = "listview_interpolator";
	    private static final String SCROLLINGCACHE_PREF = "pref_scrollingcache";
	    private static final String SCROLLINGCACHE_PERSIST_PROP = "persist.sys.scrollingcache";
	    private static final String SCROLLINGCACHE_DEFAULT = "1";
	
		private Context mContext;
	
		private ListPreference mToastAnimation;
	    private ListPreference mListViewAnimation;
	    private ListPreference mListViewInterpolator;
		private ListPreference mScrollingCachePref;

		public KangDroidTestFragment() {
		}

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static KangDroidTestFragment newInstance(int sectionNumber) {
			KangDroidTestFragment fragment = new KangDroidTestFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			
	        addPreferencesFromResource(R.xml.kangdroid_anim_settings);
			PreferenceScreen prefSet = getPreferenceScreen();
	        ContentResolver resolver = getActivity().getContentResolver();

	        mContext = getActivity().getApplicationContext();

	        // Toast Animations
	        mToastAnimation = (ListPreference) findPreference(KEY_TOAST_ANIMATION);
	        mToastAnimation.setSummary(mToastAnimation.getEntry());
	        int CurrentToastAnimation = Settings.System.getInt(resolver,
	                Settings.System.TOAST_ANIMATION, 1);
	        mToastAnimation.setValueIndex(CurrentToastAnimation); //set to index of default value
	        mToastAnimation.setSummary(mToastAnimation.getEntries()[CurrentToastAnimation]);
	        mToastAnimation.setOnPreferenceChangeListener(this);
		
	        // List view animation
	        mListViewAnimation = (ListPreference) findPreference(KEY_LISTVIEW_ANIMATION);
	        int listviewanimation = Settings.System.getInt(resolver,
	                Settings.System.LISTVIEW_ANIMATION, 0);
	        mListViewAnimation.setValue(String.valueOf(listviewanimation));
	        mListViewAnimation.setSummary(mListViewAnimation.getEntry());
	        mListViewAnimation.setOnPreferenceChangeListener(this);

	        mListViewInterpolator = (ListPreference) findPreference(KEY_LISTVIEW_INTERPOLATOR);
	        int listviewinterpolator = Settings.System.getInt(resolver,
	                Settings.System.LISTVIEW_INTERPOLATOR, 0);
	        mListViewInterpolator.setValue(String.valueOf(listviewinterpolator));
	        mListViewInterpolator.setSummary(mListViewInterpolator.getEntry());
	        mListViewInterpolator.setOnPreferenceChangeListener(this);
	        mListViewInterpolator.setEnabled(listviewanimation > 0);
		
	        // Scrolling cache
	        mScrollingCachePref = (ListPreference) prefSet.findPreference(SCROLLINGCACHE_PREF);
	        mScrollingCachePref.setValue(SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP,
	                SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP, SCROLLINGCACHE_DEFAULT)));
	        mScrollingCachePref.setOnPreferenceChangeListener(this);
		}
		
	    @Override
	    public void onResume() {
	        super.onResume();
	    }
	
	    public boolean onPreferenceChange(Preference preference, Object objValue) {
	        final String key = preference.getKey();
			ContentResolver resolver = getActivity().getContentResolver();
	        if (preference == mToastAnimation) {
	            int index = mToastAnimation.findIndexOfValue((String) objValue);
	            Settings.System.putString(resolver, Settings.System.TOAST_ANIMATION, (String) objValue);
	            mToastAnimation.setSummary(mToastAnimation.getEntries()[index]);
	            Toast.makeText(mContext, "Toast Test", Toast.LENGTH_SHORT).show();
	            return true;
	        }
	        if (KEY_LISTVIEW_ANIMATION.equals(key)) {
	            int value = Integer.parseInt((String) objValue);
	            int index = mListViewAnimation.findIndexOfValue((String) objValue);
	            Settings.System.putInt(resolver,
	                    Settings.System.LISTVIEW_ANIMATION,
	                    value);
	            mListViewAnimation.setSummary(mListViewAnimation.getEntries()[index]);
	            mListViewInterpolator.setEnabled(value > 0);
	        }
	        if (KEY_LISTVIEW_INTERPOLATOR.equals(key)) {
	            int value = Integer.parseInt((String) objValue);
	            int index = mListViewInterpolator.findIndexOfValue((String) objValue);
	            Settings.System.putInt(resolver,
	                    Settings.System.LISTVIEW_INTERPOLATOR,
	                    value);
	            mListViewInterpolator.setSummary(mListViewInterpolator.getEntries()[index]);
	        }
	        if (preference == mScrollingCachePref) {
	            if (objValue != null) {
	                SystemProperties.set(SCROLLINGCACHE_PERSIST_PROP, (String)objValue);
	            return true;
	            }
	        }
	        return false;
	   }
	}

}