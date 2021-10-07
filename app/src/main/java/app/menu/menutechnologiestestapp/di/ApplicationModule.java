package app.menu.menutechnologiestestapp.di;

import android.content.Context;

import javax.inject.Singleton;

import app.menu.menutechnologiestestapp.ui.MenuTechnologiesTestApplication;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Singleton
    @Provides
    public MenuTechnologiesTestApplication provideApplication(@ApplicationContext Context context) {
        return (MenuTechnologiesTestApplication) context;
    }
}
