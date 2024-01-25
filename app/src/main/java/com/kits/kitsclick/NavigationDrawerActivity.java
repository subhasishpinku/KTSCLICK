 package com.kits.kitsclick;
 import android.content.Context;
 import android.content.SharedPreferences;
 import android.content.res.Configuration;
 import android.os.Bundle;
 import android.os.PersistableBundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.ImageButton;
 import android.widget.ImageView;
 import androidx.appcompat.app.ActionBar;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;
 import androidx.drawerlayout.widget.DrawerLayout;
 import androidx.fragment.app.Fragment;
 import androidx.fragment.app.FragmentManager;
 import androidx.fragment.app.FragmentTransaction;

 import com.kits.kitsclick.fragment.DasboardFragment;

 import java.lang.ref.WeakReference;
 import java.util.ArrayList;
 import java.util.Iterator;
 public class NavigationDrawerActivity extends AppCompatActivity
         implements FragmentDrawer.FragmentDrawerListener,
         View.OnClickListener,BackButtonHandlerInterface{
     private static String TAG = NavigationDrawerActivity.class.getSimpleName();
     private Toolbar mToolbar;
     private FragmentDrawer drawerFragment;
     private SharedPreferences sp;
     ImageButton profileID;
     ImageView profileID1;
     boolean doubleBackToExitPressedOnce = false;
     private ArrayList<WeakReference<OnBackClickListener>> backClickListenersList = new ArrayList<>();
     @Override
     protected void onCreate(Bundle savedInstanceState){
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//               WindowManager.LayoutParams.FLAG_SECURE);
         super.onCreate(savedInstanceState);
         setContentView(R.layout.acttivity_home);
         System.out.println("Inside onCreate");
         mToolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(mToolbar);
         setSupportActionBar(mToolbar);
         getSupportActionBar().setDisplayShowTitleEnabled(false);
         mToolbar.setNavigationIcon(R.drawable.ic_baseline_dashboard_24);
         profileID  = (ImageButton)findViewById(R.id.profileID);
         profileID1 = (ImageView) findViewById(R.id.profileID1);
         profileID.setOnClickListener(this);
         sp  =   getSharedPreferences(Consts.SP_NAME, Context.MODE_PRIVATE);
         getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_baseline_dashboard_24));
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         getSupportActionBar().setHomeButtonEnabled(true);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_dashboard_24);
         ActionBar actionBar = getSupportActionBar();
         actionBar.setDisplayHomeAsUpEnabled(true);
         actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dashboard_24);
         drawerFragment = (FragmentDrawer)
                 getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
         drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
         drawerFragment.setDrawerListener(this);
         displayView(Consts.DASBOARD);
//        Glide.with(getApplicationContext())
//                .load("https://www.egpaid.com/assets/image/egpaid.svg")
//                .into(profileID1);
     }
     @Override
     public void onClick(View v) {
         switch (v.getId()){
             case R.id.profileID:

                 break;
             case R.id.profileID1:

                 break;
             default:
         }
     }
     @Override
     public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
         super.onSaveInstanceState(outState, outPersistentState);
         outState.putString("PRE_VAL","KITS");
     }
     @Override
     public void onConfigurationChanged(Configuration newConfig) {
         Log.i("tag", "On config changed");
         super.onConfigurationChanged(newConfig);
     }
//     @Override
//     public boolean onCreateOptionsMenu(Menu menu) {
//         getMenuInflater().inflate(R.menu.menu_main, menu);
//         return true;
//     }
//     @Override
//     public boolean onOptionsItemSelected(MenuItem item) {
//         int id = item.getItemId();
//         if (id == R.id.logoutID) {
//             finish();
//             return true;
//         }
//         if (id == R.id.refressId){
//             finish();
//             startActivity(getIntent());
//             return true;
//         }
//         return super.onOptionsItemSelected(item);
//     }
     @Override
     public void onDrawerItemSelected(View view, int position) {
         displayView(position);
     }
     private void displayView(int position) {
         Fragment fragment = null;
         String title = getResources().getString(R.string.app_name);
         switch (position) {
             case Consts.DASBOARD:
                 title = getResources().getString(R.string.app_name);
                 fragment = new DasboardFragment();
                 break;
             default:
                 break;
         }
         if (fragment != null) {
             FragmentManager fragmentManager = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.container_body, fragment);
             fragmentTransaction.commit();
             getSupportActionBar().setTitle("");
         }
     }
//    @Override
//    public void onBackPressed() {
//    }

     @Override
     protected void onStart() {

         super.onStart();
     }

     @Override
     protected void onStop() {
         super.onStop();
     }

     @Override
     public void onResume(){
         super.onResume();
         System.out.println("Inside onResume");
     }

//    @Override
//    public void onStart(){
//        super.onStart();
//        System.out.println("Inside onStart");
//    }

     @Override
     public void onRestart(){
         super.onRestart();
         System.out.println("Inside onReStart");
     }

     @Override
     public void onPause(){
         super.onPause();
         System.out.println("Inside onPause");
     }

//    @Override
//    public void onStop(){
//        super.onStop();
//        System.out.println("Inside onStop");
//    }

     @Override
     public void onDestroy(){
         super.onDestroy();
         System.out.println("Inside onDestroy");
     }

     @Override
     public void addBackClickListener(OnBackClickListener onBackClickListener) {
         backClickListenersList.add(new WeakReference<>(onBackClickListener));
     }

     @Override
     public void removeBackClickListener(OnBackClickListener onBackClickListener) {
         for (Iterator<WeakReference<OnBackClickListener>> iterator = backClickListenersList.iterator();
              iterator.hasNext();){
             WeakReference<OnBackClickListener> weakRef = iterator.next();
             if (weakRef.get() == onBackClickListener){
                 iterator.remove();
             }
         }
     }

     @Override
     public void onBackPressed() {
//        if(!fragmentsBackKeyIntercept()){
//            super.onBackPressed();
//        }
     }

     private boolean fragmentsBackKeyIntercept() {
         boolean isIntercept = false;
         for (WeakReference<OnBackClickListener> weakRef : backClickListenersList) {
             OnBackClickListener onBackClickListener = weakRef.get();
             if (onBackClickListener != null) {
                 boolean isFragmIntercept = onBackClickListener.onBackClick();
                 if (!isIntercept) isIntercept = isFragmIntercept;
             }
         }
         return isIntercept;
     }
 }

