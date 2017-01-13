/*
 * Copyright (C) 2014 The KangDroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.kdpsettings.fragments;

import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.provider.SearchIndexableResource;
import com.android.kdpsettings.KangDroidSeekBarPreference;

import com.android.kdpsettings.R;

import java.util.ArrayList;
import java.util.List;

public class KangDroidLockscreenSettings extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
	
	private static final String LOCKSCREEN_MAX_NOTIF_CONFIG = "lockscreen_max_notif_cofig";
	private static final String LOCK_CLOCK_FONTS = "lock_clock_fonts";
	
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private KangDroidSeekBarPreference mMaxKeyguardNotifConfig;
	private ListPreference mLockClockFonts;
	
	public KangDroidLockscreenSettings() {
	}

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static KangDroidLockscreenSettings newInstance(int sectionNumber) {
		KangDroidLockscreenSettings fragment = new KangDroidLockscreenSettings();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.kangdroid_lockscreen_settings);
		ContentResolver resolver = getActivity().getContentResolver();
		
        mMaxKeyguardNotifConfig = (KangDroidSeekBarPreference) findPreference(LOCKSCREEN_MAX_NOTIF_CONFIG);
		mMaxKeyguardNotifConfig.setValue(Settings.System.getInt(resolver,
			Settings.System.LOCKSCREEN_MAX_NOTIF_CONFIG, 5));
        mMaxKeyguardNotifConfig.setOnPreferenceChangeListener(this);
		
        mLockClockFonts = (ListPreference) findPreference(LOCK_CLOCK_FONTS);
        mLockClockFonts.setValue(String.valueOf(Settings.System.getInt(
                resolver, Settings.System.LOCK_CLOCK_FONTS, 0)));
        mLockClockFonts.setSummary(mLockClockFonts.getEntry());
        mLockClockFonts.setOnPreferenceChangeListener(this);
    }
	
    @Override
    public void onResume() {
        super.onResume();
    }
	
    public boolean onPreferenceChange(Preference preference, Object newValue) {
		ContentResolver resolver = getActivity().getContentResolver();
		if (preference == mMaxKeyguardNotifConfig) {
            int kgconf = (Integer) newValue;
            Settings.System.putInt(resolver,
                    Settings.System.LOCKSCREEN_MAX_NOTIF_CONFIG, kgconf);
            return true;
		} else if (preference == mLockClockFonts) {
            Settings.System.putInt(resolver, Settings.System.LOCK_CLOCK_FONTS,
                    Integer.valueOf((String) newValue));
            mLockClockFonts.setValue(String.valueOf(newValue));
            mLockClockFonts.setSummary(mLockClockFonts.getEntry());
            return true;
		}
        return false;
    }
}