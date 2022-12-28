/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
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
package au.mymetro.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;

import org.onebusaway.android.R;
import org.onebusaway.android.ui.MyRoutesActivity;
import org.onebusaway.android.ui.MyStarredStopsFragment;
import org.onebusaway.android.ui.MyStopsActivity;
import org.onebusaway.android.ui.MyTabActivityBase;
import org.onebusaway.android.util.UIUtils;

public class MyStarredRoutesActivity extends AppCompatActivity {

    //
    // The only thing this is used for anymore is to create
    // a shortcut to the starred stops list.
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIUtils.setupActionBar(this);

        Intent myIntent = getIntent();
        if (Intent.ACTION_CREATE_SHORTCUT.equals(myIntent.getAction())) {
            ShortcutInfoCompat shortcut = getShortcut();
            ShortcutManagerCompat.requestPinShortcut(this, shortcut, null);
            setResult(RESULT_OK, shortcut.getIntent());
        }
        finish();
    }

    private ShortcutInfoCompat getShortcut() {
        final Uri uri = MyTabActivityBase.getDefaultTabUri(MyStarredRoutesFragment.TAB_NAME);
        return UIUtils.makeShortcutInfo(this,
                getString(R.string.starred_routes_shortcut),
                new Intent(this, MyRoutesActivity.class).setData(uri),
                R.drawable.ic_drawer_star);
    }
}
