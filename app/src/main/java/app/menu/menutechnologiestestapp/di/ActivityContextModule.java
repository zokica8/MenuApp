package app.menu.menutechnologiestestapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityContextModule {

    @ActivityScoped
    @Provides
    public Context provideActivityContext(@ActivityContext Context context) {
        return context;
    }
}
