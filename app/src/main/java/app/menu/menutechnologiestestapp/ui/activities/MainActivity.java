package app.menu.menutechnologiestestapp.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.R;
import app.menu.menutechnologiestestapp.databinding.ActivityMainBinding;
import app.menu.menutechnologiestestapp.utils.AuthUtils;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    //attributes
    private NavController navController;
    private ActivityMainBinding binding;

    @Inject
    AuthUtils authUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            NavInflater navInflater = navController.getNavInflater();
            NavGraph navGraph = navInflater.inflate(R.navigation.app_navigation);

            if (authUtils.getAccessToken() != null &&
                    !authUtils.getAccessToken().equals("") &&
                    !authUtils.getAccessToken().isEmpty()) {
                navGraph.setStartDestination(R.id.venuesListFragment);
            } else {
                navGraph.setStartDestination(R.id.loginFragment);
            }
            navController.setGraph(navGraph);
        }
        setSupportActionBar(binding.toolbar);
        NavigationUI.setupActionBarWithNavController(this, navController);
        navigationListeners();
        clickListeners();
    }

    private void navigationListeners() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.loginFragment) {
                binding.toolbar.setVisibility(View.GONE);
                binding.logoutBTN.setVisibility(View.GONE);

            } else {
                binding.toolbar.setVisibility(View.VISIBLE);
                binding.logoutBTN.setVisibility(View.VISIBLE);
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
    }

    private void clickListeners() {
        binding.logoutBTN.setOnClickListener(view -> {
            authUtils.setAccessToken("");
            authUtils.setAccessTokenValidTo(-1);
            authUtils.setRefreshTokenValidTo(-1);
            NavOptions.Builder navOptionsBuilder = new NavOptions.Builder();
            navOptionsBuilder.setEnterAnim(R.anim.nav_default_enter_anim);
            navOptionsBuilder.setExitAnim(R.anim.nav_default_exit_anim);
            navOptionsBuilder.setPopEnterAnim(R.anim.nav_default_pop_enter_anim);
            navOptionsBuilder.setPopExitAnim(R.anim.nav_default_pop_exit_anim);
            NavOptions navOptions = navOptionsBuilder.setPopUpTo(R.id.app_navigation, true).build();
            navController.navigate(R.id.loginFragment, null, navOptions);
        });
    }
}