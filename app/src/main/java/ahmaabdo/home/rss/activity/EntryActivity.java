/**
 * spaRSS
 * <p/>
 * Copyright (c) 2015-2016 Arnaud Renaud-Goud
 * Copyright (c) 2012-2015 Frederic Julian
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ahmaabdo.home.rss.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ahmaabdo.home.rss.Constants;
import ahmaabdo.home.rss.R;
import ahmaabdo.home.rss.fragment.EntryFragment;
import ahmaabdo.home.rss.utils.PrefUtils;
import ahmaabdo.home.rss.utils.UiUtils;

public class EntryActivity extends BaseActivity {

    private EntryFragment mEntryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiUtils.setPreferenceTheme(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry);

        mEntryFragment = (EntryFragment) getFragmentManager().findFragmentById(R.id.entry_fragment);
        if (savedInstanceState == null) { // Put the data only the first time (the fragment will save its state)
            mEntryFragment.setData(getIntent().getData());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_clear);
        toolbar.setBackgroundColor(Color.BLACK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        if (PrefUtils.getBoolean(PrefUtils.DISPLAY_ENTRIES_FULLSCREEN, false)) {
            setImmersiveFullScreen(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Bundle b = getIntent().getExtras();
            if (b != null && b.getBoolean(Constants.INTENT_FROM_WIDGET, false)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
            return true;
        }

        return false;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mEntryFragment.setData(intent.getData());
    }
}