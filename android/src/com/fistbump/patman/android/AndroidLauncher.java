package com.fistbump.patman.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fistbump.patman.MyGdxGame;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);

		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "jxrQbwQawagYASoMXaGExoi3CoB6gGvur53BTvjl", "7zwno6FC9ODvYZJZTFVEHQ3gv4QMswJzb21RmeMv");
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
