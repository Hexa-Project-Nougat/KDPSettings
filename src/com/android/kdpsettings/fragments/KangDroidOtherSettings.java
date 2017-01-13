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
import android.os.Bundle;
import android.os.UserHandle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.provider.SearchIndexableResource;

import com.android.kdpsettings.R;

import java.util.ArrayList;
import java.util.List;

public class KangDroidOtherSettings extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
	
    private static final String FLASHLIGHT_NOTIFICATION = "flashlight_notification";
	
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

    private SwitchPreference mFlashlightNotification;
	
	public KangDroidOtherSettings() {
	}

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static KangDroidOtherSettings newInstance(int sectionNumber) {
		KangDroidOtherSettings fragment = new KangDroidOtherSettings();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.kangdroid_other_settings);
		
		final ContentResolver resolver = getActivity().getContentResolver();

        mFlashlightNotification = (SwitchPreference) findPreference(FLASHLIGHT_NOTIFICATION);
        mFlashlightNotification.setOnPreferenceChangeListener(this);
        mFlashlightNotification.setChecked((Settings.System.getInt(resolver,
                Settings.System.FLASHLIGHT_NOTIFICATION, 0) == 1));
    }
	
    @Override
    public void onResume() {
        super.onResume();
    }
	
	@Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
		final ContentResolver resolver = getActivity().getContentResolver();
        if  (preference == mFlashlightNotification) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            Settings.System.putInt(resolver,
                   Settings.System.FLASHLIGHT_NOTIFICATION, checked ? 1:0);
            return true;
        }
        return false;
     }
}