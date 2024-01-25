package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
public class FragmentDrawer extends Fragment {
    private static String TAG = FragmentDrawer.class.getSimpleName();
    private TextView  dmtId,bill_payment,mobile_recharge,dth_recharge,apesid,addservice,ledgerId,deelsheet,abouts,logout;
    private ImageView profileImageView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private SharedPreferences sp;
    private FragmentDrawerListener drawerListener;
    public FragmentDrawer() {
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("TABB","00");
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
        Log.e("MANU","2");

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp  =   this.getActivity().getSharedPreferences(Consts.SP_NAME, Context.MODE_PRIVATE);
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        profileImageView=   (ImageView) layout.findViewById(R.id.profileImageView);
        dmtId = (TextView)layout.findViewById(R.id.dmtId);
        bill_payment = (TextView)layout.findViewById(R.id.bill_payment);
        mobile_recharge = (TextView)layout.findViewById(R.id.mobile_recharge);
        dth_recharge = (TextView)layout.findViewById(R.id.dth_recharge);
        apesid = (TextView)layout.findViewById(R.id.apesid);
        addservice = (TextView)layout.findViewById(R.id.addservice);
        ledgerId = (TextView)layout.findViewById(R.id.ledgerId);
        deelsheet = (TextView)layout.findViewById(R.id.deelsheet);
        abouts = (TextView)layout.findViewById(R.id.abouts);
        logout = (TextView)layout.findViewById(R.id.logout);

        String imageUrl =   sp.getString("USERPHOTO","http://gshandicraftfashion.com/wp-content/themes/sw_chamy/assets/img/no-thumbnail.png");
        System.out.println("Image Tag- "+imageUrl);
        // ImageLoader imageLoader = Application.getInstance().getImageLoader();
        // profileImageView.setImageUrl(imageUrl,imageLoader);
        dmtId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.DMT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        bill_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.BILLPAYMENT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        mobile_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.MOBILERECHARGE);
                mDrawerLayout.closeDrawer(containerView);
                Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
                startActivity(intent);
            }
        });
        dth_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.DTHRECHARGE);
                mDrawerLayout.closeDrawer(containerView);
                Intent intent = new Intent(getActivity(), DTHRechargeActivity.class);
                startActivity(intent);
            }
        });
        apesid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.AEPS);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.ADDSERVICE);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        ledgerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.LEDGER);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        deelsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.DELSHEET);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        abouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.ABOUT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.LOGOUT);
                mDrawerLayout.closeDrawer(containerView);
                SharedPrefManagerLogin.getInstance(getContext()).logout();
                SharedPrefManagerpin.getInstance(getContext()).logout();
                SharedPrefManagerLogin.getInstance(getContext()).clear();
                SharedPrefManagerpin.getInstance(getContext()).clear();
            }
        });
        return layout;
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        Log.e("MANU","00");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_dashboard_24);
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
                Log.e("MANU","0");
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
                Log.e("MANU","1");

            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 1);
                Log.e("MANU","2");
                toolbar.setCollapseIcon(R.drawable.ic_baseline_dashboard_24);

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);

    }
}

