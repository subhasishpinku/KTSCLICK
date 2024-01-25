package com.kits.kitsclick.fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.BIllPayment;
import com.kits.kitsclick.BalanceEnquiryActivity;
import com.kits.kitsclick.DTHRechargeActivity;
import com.kits.kitsclick.MinistatementActivity;
import com.kits.kitsclick.MinistatementActivityAdher;
import com.kits.kitsclick.Ministatement_seconed;
import com.kits.kitsclick.Ministatementfirst;
import com.kits.kitsclick.MinstatementActivitysecomed;
import com.kits.kitsclick.MobileRechargeActivity;
import com.kits.kitsclick.NotificationActivity;
import com.kits.kitsclick.PayinActivity;
import com.kits.kitsclick.R;
import com.kits.kitsclick.Reportactivity;
import com.kits.kitsclick.ReporttabActivity;
import com.kits.kitsclick.SharedPrefManagerLogin;
import com.kits.kitsclick.adpter.ViewPagerHomeItemAdapter;
import com.kits.kitsclick.setget.Banking_Savings;
import com.kits.kitsclick.setget.Bannerdata;
import com.kits.kitsclick.setget.Bill_payment;
import com.kits.kitsclick.setget.Billpaymentservice;
import com.kits.kitsclick.setget.Blancereload;
import com.kits.kitsclick.setget.Collect_Payment;
import com.kits.kitsclick.setget.Earnmore;
import com.kits.kitsclick.setget.Insurance;
import com.kits.kitsclick.setget.Listbanner;
import com.kits.kitsclick.setget.Listdatabilpayment;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Report;
import com.kits.kitsclick.setget.Reportdataview;
import com.kits.kitsclick.setget.Reportvalue;
import com.kits.kitsclick.setget.Rewards;
import com.kits.kitsclick.setget.Shopowner;
import com.kits.kitsclick.setget.SliderUtils;
import com.kits.kitsclick.setget.Travel;
import com.kits.kitsclick.setget.UtilityEMIEntertainment;
import com.kits.kitsclick.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
public class DasboardFragment extends Fragment implements TabHost.OnTabChangeListener {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    Timer timer;
    int page = 0;
    RecyclerView recyclerView,recycler_banking_saving,recycler_rewards,recycler_entertainment,
            recycler_insurance,recycler_shopowner,recycler_travel,recycler_earnmore;
    HomeItemViewAdapter homeItemViewAdapter;
    HomeItemViewAdapter2 homeItemViewAdapter2;
    HomeItemViewAdapter3 homeItemViewAdapter3;
    HomeItemViewAdapter4 homeItemViewAdapter4;
    HomeItemViewAdapter5 homeItemViewAdapter5;
    HomeItemViewAdapter6 homeItemViewAdapter6;
    HomeItemViewAdapter7 homeItemViewAdapter7;
    HomeItemViewAdapter8 homeItemViewAdapter8;
    List<Collect_Payment> collectPaymentList;
    List<Banking_Savings> banking_savings;
    List<Rewards> rewards;
    List<UtilityEMIEntertainment> utilityEMIEntertainmentList;

    List<Insurance> insurance;
    List<Shopowner> shopowner;
    List<Travel> travel;
    List<Earnmore> earnmore;
    int listview = -1;
    TextView user_name,blanceview;
    ImageView reload_bal;
    TextView tv_statement,tv_payin,tv_payout;
    List<SliderUtils> sliderImg;
    ViewPagerHomeItemAdapter viewPagerAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("TABB","000");
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page, container, false);
         BottomNavigationView navigation = (BottomNavigationView)rootView.findViewById(R.id.navigation);
          navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        user_name = (TextView) rootView.findViewById(R.id.user_name);
        blanceview = (TextView) rootView.findViewById(R.id.blanceview);
        reload_bal = (ImageView)rootView.findViewById(R.id.reload_bal);
        tv_statement = (TextView) rootView.findViewById(R.id.tv_statement);
        tv_payin = (TextView) rootView.findViewById(R.id.tv_payin);
        tv_payout = (TextView) rootView.findViewById(R.id.tv_payout);
        tv_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MinistatementActivity.class);
//                startActivity(intent);

            }
        });
        tv_payin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayinActivity.class);
                startActivity(intent);
            }
        });
        tv_payout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // tvWeekDayFirstLetter = (TextView) rootView.findViewById(R.id.tvWeekDayFirstLetter);
        Logindata logindata = SharedPrefManagerLogin.getInstance(getContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        String first = String.valueOf(name.charAt(0));
       // tvWeekDayFirstLetter.setText(first);
        user_name.setText(name);
        blanceview.setText(blance);
        viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout)rootView.findViewById(R.id.SliderDots);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
//        viewPager.setAdapter(viewPagerAdapter);
//        dotscount = viewPagerAdapter.getCount();
//        dots = new ImageView[dotscount];
//        for(int i = 0; i < dotscount; i++){
//            dots[i] = new ImageView(getContext());
//            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(8, 0, 8, 0);
//            sliderDotspanel.addView(dots[i], params);
//        }
////
        http://www.sanktips.com/2017/10/15/how-to-fetch-images-from-server-to-image-slider-with-viewpager-in-android-studio/
        ///
        sliderImg = new ArrayList<>();
        sliderImg.clear();
//        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
//        pageSwitcher(5);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//            @Override
//            public void onPageSelected(int position) {
//
//                for(int i = 0; i< dotscount; i++){
//                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
//                }
//
//                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        bannerlist();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        recyclerView = (RecyclerView)rootView.findViewById(R.id.card_recycler_view);
        recycler_banking_saving = (RecyclerView)rootView.findViewById(R.id.recycler_banking_saving);
        recycler_rewards = (RecyclerView)rootView.findViewById(R.id.recycler_rewards);
        recycler_entertainment = (RecyclerView)rootView.findViewById(R.id.recycler_entertainment);
        recycler_insurance  = (RecyclerView)rootView.findViewById(R.id.recycler_insurance);
        recycler_shopowner  = (RecyclerView)rootView.findViewById(R.id.recycler_shopowner);
        recycler_travel  = (RecyclerView)rootView.findViewById(R.id.recycler_travel);
        recycler_earnmore  = (RecyclerView)rootView.findViewById(R.id.recycler_earnmore);
        collectPaymentList = new ArrayList<>();
        banking_savings = new ArrayList<>();
        rewards = new ArrayList<>();
        insurance = new ArrayList<>();
        shopowner = new ArrayList<>();
        travel = new ArrayList<>();
        earnmore = new ArrayList<>();
        utilityEMIEntertainmentList = new ArrayList<>();
        collectPaymentList.add(
                new Collect_Payment(
                        "1",
                       "UPI QR",
                        R.drawable.ic_qr_code));

        collectPaymentList.add(
                new Collect_Payment(
                        "2",
                      "MPOS",
                        R.drawable.mpos));

        collectPaymentList.add(
                new Collect_Payment(
                        "3",
                         "SMS Payment", R.drawable.sms));
//        collectPaymentList.add(
//                new Collect_Payment(
//                        "4",
//                        "Banking", R.drawable.dmt));

        recyclerView.setHasFixedSize(true);
        //////////////////////////////////////////
        banking_savings.add(
                new Banking_Savings(
                        "1",
                        "Addhar Withdraw",
                        R.drawable.ic_aadhar_withdraw));

        banking_savings.add(
                new Banking_Savings(
                        "2",
                        "Balance Enquiry",
                        R.drawable.balanceequary));

        banking_savings.add(
                new Banking_Savings(
                        "3",
                        "Mini Statement", R.drawable.ic_mini_statment));
        banking_savings.add(
                new Banking_Savings(
                        "4",
                        "M Atm", R.drawable.ic_m_atm));

        banking_savings.add(
                new Banking_Savings(
                        "5",
                        "Cash Deposit", R.drawable.cashdeposit));

        banking_savings.add(
                new Banking_Savings(
                        "6",
                        "Money Transfer", R.drawable.moneytransfer));

        banking_savings.add(
                new Banking_Savings(
                        "7",
                        "Account Opening", R.drawable.accountopening));

        banking_savings.add(
                new Banking_Savings(
                        "8",
                        "Claim Refund", R.drawable.ic_claim_refund));

        banking_savings.add(
                new Banking_Savings(
                        "8",
                        "Adhar Pay", R.drawable.ic_aadhar_pay));
        recycler_banking_saving.setHasFixedSize(true);
        /////////////////////////////////////////////////////////////////////////////////
        rewards.add(
                new Rewards(
                        "8",
                        "Rewards", R.drawable.rewards));

        rewards.add(
                new Rewards(
                        "9",
                        "Refer & Earn", R.drawable.refer));
        recycler_rewards.setHasFixedSize(true);
        ////////////////////////////////////////////////
        utilityEMIEntertainmentList.add(
                new UtilityEMIEntertainment(
                        "1",
                        "Bill Payment",
                        R.drawable.ic_bill));

        utilityEMIEntertainmentList.add(
                new UtilityEMIEntertainment(
                        "2",
                        "Mobile Recharge",
                        R.drawable.ic_mobile_recharge));

        utilityEMIEntertainmentList.add(
                new UtilityEMIEntertainment(
                        "3",
                        "DTH Recharge", R.drawable.dthh));
        utilityEMIEntertainmentList.add(
                new UtilityEMIEntertainment(
                        "4",
                        "Data Card", R.drawable.datacard));
        recycler_entertainment.setHasFixedSize(true);
        /////////////////////////////////////////////////////////////////////////////////
        insurance.add(
                new Insurance(
                        "8",
                        "Bike", R.drawable.ic_bike));

        insurance.add(
                new Insurance(
                        "9",
                        "Car", R.drawable.ic_car));
        insurance.add(
                new Insurance(
                        "8",
                        "Personal Accident", R.drawable.accident));

        insurance.add(
                new Insurance(
                        "9",
                        "Travel", R.drawable.ic_travel));
        recycler_insurance.setHasFixedSize(true);
        ////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        shopowner.add(
                new Shopowner(
                        "8",
                        "Shoppind Card", R.drawable.ic_shopping_card));

        shopowner.add(
                new Shopowner(
                        "9",
                        "Business Loans", R.drawable.ic_business_loan));
        shopowner.add(
                new Shopowner(
                        "8",
                        "Income Tax", R.drawable.ic_tax));

        shopowner.add(
                new Shopowner(
                        "9",
                        "Buy", R.drawable.ic_buy));
        recycler_shopowner.setHasFixedSize(true);
        /////////////////////////////////////////////////////////////////////////////////
        travel.add(
                new Travel(
                        "8",
                        "Hotel Booking", R.drawable.ic_hotel));
        travel.add(
                new Travel(
                        "8",
                        "Air Booking", R.drawable.ic_air_booking));
        travel.add(
                new Travel(
                        "9",
                        "Packages Booking", R.drawable.ic_packages));

        recycler_travel.setHasFixedSize(true);
        /////////////////////////////////////////////////////////////////////////////////
        earnmore.add(
                new Earnmore(
                        "8",
                        "Amazon Store", R.drawable.ic_help));

        earnmore.add(
                new Earnmore(
                        "9",
                        "Customer", R.drawable.ic_help));
        earnmore.add(
                new Earnmore(
                        "8",
                        "Jobs", R.drawable.ic_help));
        recycler_earnmore.setHasFixedSize(true);
        ////////////////////////////////////////////////

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
//        recyclerView.setLayoutManager(layoutManager);
     //   GridLayoutManager manager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
     //   recyclerView.setLayoutManager(manager);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeItemViewAdapter);
        homeItemViewAdapter = new HomeItemViewAdapter(getContext(), collectPaymentList);
        recyclerView.setAdapter(homeItemViewAdapter);
        /////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getActivity(), 4);
        recycler_banking_saving.setLayoutManager(mLayoutManager2);
        recycler_banking_saving.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recycler_banking_saving.setItemAnimator(new DefaultItemAnimator());
        recycler_banking_saving.setAdapter(homeItemViewAdapter2);
        homeItemViewAdapter2 = new HomeItemViewAdapter2(getContext(), banking_savings);
        recycler_banking_saving.setAdapter(homeItemViewAdapter2);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager3 = new GridLayoutManager(getActivity(), 2);
        recycler_rewards.setLayoutManager(mLayoutManager3);
        recycler_rewards.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycler_rewards.setItemAnimator(new DefaultItemAnimator());
        recycler_rewards.setAdapter(homeItemViewAdapter3);
        homeItemViewAdapter3 = new HomeItemViewAdapter3(getContext(), rewards);
        recycler_rewards.setAdapter(homeItemViewAdapter3);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager4 = new GridLayoutManager(getActivity(), 4);
        recycler_entertainment.setLayoutManager(mLayoutManager4);
        recycler_entertainment.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recycler_entertainment.setItemAnimator(new DefaultItemAnimator());
        recycler_entertainment.setAdapter(homeItemViewAdapter4);
        homeItemViewAdapter4 = new HomeItemViewAdapter4(getContext(), utilityEMIEntertainmentList);
        recycler_entertainment.setAdapter(homeItemViewAdapter4);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager5 = new GridLayoutManager(getActivity(), 4);
        recycler_insurance.setLayoutManager(mLayoutManager5);
        recycler_insurance.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recycler_insurance.setItemAnimator(new DefaultItemAnimator());
        recycler_insurance.setAdapter(homeItemViewAdapter5);
        homeItemViewAdapter5 = new HomeItemViewAdapter5(getContext(), insurance);
        recycler_insurance.setAdapter(homeItemViewAdapter5);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager6 = new GridLayoutManager(getActivity(), 4);
        recycler_shopowner.setLayoutManager(mLayoutManager6);
        recycler_shopowner.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recycler_shopowner.setItemAnimator(new DefaultItemAnimator());
        recycler_shopowner.setAdapter(homeItemViewAdapter6);
        homeItemViewAdapter6 = new HomeItemViewAdapter6(getContext(), shopowner);
        recycler_shopowner.setAdapter(homeItemViewAdapter6);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager7 = new GridLayoutManager(getActivity(), 4);
        recycler_travel.setLayoutManager(mLayoutManager7);
        recycler_travel.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recycler_travel.setItemAnimator(new DefaultItemAnimator());
        recycler_travel.setAdapter(homeItemViewAdapter7);
        homeItemViewAdapter7 = new HomeItemViewAdapter7(getContext(), travel);
        recycler_travel.setAdapter(homeItemViewAdapter7);
        //////////////////////////////////////////
        RecyclerView.LayoutManager mLayoutManager8 = new GridLayoutManager(getActivity(), 3);
        recycler_earnmore.setLayoutManager(mLayoutManager8);
        recycler_earnmore.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recycler_earnmore.setItemAnimator(new DefaultItemAnimator());
        recycler_earnmore.setAdapter(homeItemViewAdapter8);
        homeItemViewAdapter8 = new HomeItemViewAdapter8(getContext(), earnmore);
        recycler_earnmore.setAdapter(homeItemViewAdapter8);
        reload_bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload_blance();
            }
        });
        reload_blance();
        return rootView;
    }
     public void reload_blance(){
         if (Utility.checkConnectivity(getContext())) {
             Call<Blancereload> call = ApiClientToken.getInstance().blancereload(
             );
             call.enqueue(new Callback<Blancereload>() {
                 @Override
                 public void onResponse(Call<Blancereload> call, retrofit2.Response<Blancereload> response) {
                     if (response.isSuccessful()){
                         String error = response.body().getError();
                         if (error.equals("false")) {
                             String blance = response.body().getData().getBalance();
                             Log.e("blance", "" + blance);
                             blanceview.setText(blance);
                         }
                     }
                     else {
                         Log.e("DATA", " "+response.message());
                     }

                 }

                 @Override
                 public void onFailure(Call<Blancereload> call, Throwable t) {

                 }
             });
         }
         else {
//             PopupClass.showPopUpWithTitleMessageOneButton(KidsphotoshootActivity.this, "Ok", "", "Sorry, No Internet connection found, Please check your network connection", "", new PopupCallBackOneButton() {
//                 @Override
//                 public void onFirstButtonClick() {
//                     finish();
//                 }
//             });
         }
     }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }
    class RemindTask extends TimerTask {
        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 5) { // In my case the number of pages are 5
                        timer.cancel();
                        // Showing a toast for just testing purpose
                        //   Toast.makeText(getApplicationContext(), "Timer stoped",
                        //           Toast.LENGTH_LONG).show();

                        LsttoFirst();
                    } else {
                        viewPager.setCurrentItem(page++);
                    }
//                    if (page1 < 6) { // In my case the number of pages are 5
//                        timer.cancel();
//                        // Showing a toast for just testing purpose
//                        Toast.makeText(getApplicationContext(), "Timer stoped1",
//                                Toast.LENGTH_LONG).show();
//                    } else {
//                        viewPager.setCurrentItem(page++);
//                    }
                }
            });

        }
    }
    private void LsttoFirst(){
//        Toast.makeText(getApplicationContext(), "Timer Next",
//                Toast.LENGTH_LONG).show();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                int currentPage = viewPager.getCurrentItem();
                if (currentPage == page-6) {

                    currentPage = 0;

                } else {
                    currentPage--;

                }
                viewPager.setCurrentItem(currentPage, true);

                handler.postDelayed(this, 5000);

            }
        };
        handler.postDelayed(Update, 500);
    }
     public void bannerlist(){
         Call<Bannerdata> call = ApiClientToken.getInstance().getbanner(
         );
         call.enqueue(new Callback<Bannerdata>() {
             @Override
             public void onResponse(Call<Bannerdata> call, retrofit2.Response<Bannerdata> response) {
                 if (response.isSuccessful()){
                     String error = response.body().getError();
                     Log.e("response",""+response.body());
                     if (error.equals("false")){
                         for (int j = 0; j <response.body().getData().getList().size(); j++) {
                             Listbanner listdata =  response.body().getData().getList().get(j);
                             String img= listdata.getImg();
                             SliderUtils sliderUtils = new SliderUtils();
                             sliderUtils.setSliderImageUrl(img);
                             sliderImg.add(sliderUtils);

                         }
                         viewPagerAdapter = new ViewPagerHomeItemAdapter(sliderImg, getContext());
                         viewPager.setAdapter(viewPagerAdapter);
                         dotscount = viewPagerAdapter.getCount();
                         dots = new ImageView[dotscount];
                         for(int i = 0; i < dotscount; i++){
                             dots[i] = new ImageView(getContext());
                             dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                             params.setMargins(8, 0, 8, 0);
                             sliderDotspanel.addView(dots[i], params);
                         }
                         dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

                     }
                 }
                 else {
                     Log.e("DATA", " "+response.message());
                 }

             }

             @Override
             public void onFailure(Call<Bannerdata> call, Throwable t) {

             }
         });

     }

        private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.ic_notification:
                    //fragment = new NotificationActivity();
                    Intent intent = new Intent(getActivity(), NotificationActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.ic_reports:
//                    Intent intent1 = new Intent(getActivity(), Reportactivity.class);
                    Intent intent1 = new Intent(getActivity(), ReporttabActivity.class);
                    startActivity(intent1);
                    return true;
            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void onTabChanged(String tabId) {

    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
//    public class ViewPagerAdapter extends PagerAdapter {
//        private Context context;
//        private LayoutInflater layoutInflater;
//        private Integer [] images = {R.drawable.banner, R.drawable.banner,R.drawable.banner,R.drawable.banner,R.drawable.banner};
//        public ViewPagerAdapter(Context context) {
//            this.context = context;
//        }
//        @Override
//        public int getCount() {
//            return images.length;
//        }
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//
//            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(R.layout.custom_layout, null);
//            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//            imageView.setImageResource(images[position]);
//            ViewPager vp = (ViewPager) container;
//            vp.addView(view, 0);
//            return view;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            ViewPager vp = (ViewPager) container;
//            View view = (View) object;
//            vp.removeView(view);
//        }
//    }


    public class HomeItemViewAdapter extends RecyclerView.Adapter<HomeItemViewAdapter.ViewHolder> {
        private Context mCtx;
        private List<Collect_Payment> collectPaymentList;
        int listview;
        public HomeItemViewAdapter(Context mCtx, List<Collect_Payment> collectPaymentList) {
            this.mCtx = mCtx;
            this.collectPaymentList = collectPaymentList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view  , parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Collect_Payment homeitemViewCatagorySetGet = collectPaymentList.get(position);
            listview = position;
            holder.itemname.setText(homeitemViewCatagorySetGet.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(homeitemViewCatagorySetGet.getImg()));
//            holder.cardId.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (homeitemViewCatagorySetGet.getItemname().equals("Mobile")){
//                        Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
//                        startActivity(intent);
//                    }
//                    if (homeitemViewCatagorySetGet.getItemname().equals("DTH")){
//                        Intent intent = new Intent(getActivity(), DTHRechargeActivity.class);
//                        startActivity(intent);
//                    }
//                }
//            });
            holder.lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (homeitemViewCatagorySetGet.getItemname().equals("Mobile")){
                        Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
                        startActivity(intent);
                    }
                    if (homeitemViewCatagorySetGet.getItemname().equals("DTH")){
                        Intent intent = new Intent(getActivity(), DTHRechargeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
        @Override
        public int getItemCount() {
            return collectPaymentList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname;
            private ImageView imageItem;
           // private CardView cardId;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
              //  cardId = (CardView) view.findViewById(R.id.cardId);
               // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class HomeItemViewAdapter2 extends RecyclerView.Adapter<HomeItemViewAdapter2.ViewHolder> {
        private Context mCtx;
        private List<Banking_Savings> banking_savings;
        int listview;
        public HomeItemViewAdapter2(Context mCtx, List<Banking_Savings> banking_savings) {
            this.mCtx = mCtx;
            this.banking_savings = banking_savings;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Banking_Savings banking_savingss = banking_savings.get(position);
            listview = position;
            holder.itemname.setText(banking_savingss.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(banking_savingss.getImg()));
            holder.lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(banking_savingss.getItemname().equals("Addhar Withdraw")){
                        Intent intent = new Intent(getActivity(), MinistatementActivityAdher.class);
                        startActivity(intent);
                    }
                    if(banking_savingss.getItemname().equals("Balance Enquiry")){
                        Intent intent = new Intent(getActivity(), BalanceEnquiryActivity.class);
                        startActivity(intent);
                    }
                    if(banking_savingss.getItemname().equals("Mini Statement")){
//                        Intent intent = new Intent(getActivity(), MinstatementActivitysecomed.class);
                        Intent intent = new Intent(getActivity(), Ministatementfirst.class);
                        startActivity(intent);

                    }
                    if(banking_savingss.getItemname().equals("M Atm")){

                    }
                    if(banking_savingss.getItemname().equals("Cash Deposit")){

                    }
                    if(banking_savingss.getItemname().equals("Money Transfer")){

                    }
                    if(banking_savingss.getItemname().equals("Account Opening")){

                    }
                    if(banking_savingss.getItemname().equals("Claim Refund")){

                    }
                    if(banking_savingss.getItemname().equals("Adhar Pay")){

                    }
                }
            });

        }
        @Override
        public int getItemCount() {
            return banking_savings.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class HomeItemViewAdapter3 extends RecyclerView.Adapter<HomeItemViewAdapter3.ViewHolder> {
        private Context mCtx;
        private List<Rewards> rewards;
        int listview;
        public HomeItemViewAdapter3(Context mCtx, List<Rewards> rewards) {
            this.mCtx = mCtx;
            this.rewards = rewards;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view4, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Rewards rewardss = rewards.get(position);
            listview = position;
            holder.itemname.setText(rewardss.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(rewardss.getImg()));

        }
        @Override
        public int getItemCount() {
            return rewards.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
//                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }

    public class HomeItemViewAdapter4 extends RecyclerView.Adapter<HomeItemViewAdapter4.ViewHolder> {
        private Context mCtx;
        private List<UtilityEMIEntertainment> utilityEMIEntertainmentList;
        int listview;
        public HomeItemViewAdapter4(Context mCtx, List<UtilityEMIEntertainment> utilityEMIEntertainmentList) {
            this.mCtx = mCtx;
            this.utilityEMIEntertainmentList = utilityEMIEntertainmentList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            UtilityEMIEntertainment utilityEMIEntertainmentListt = utilityEMIEntertainmentList.get(position);
            listview = position;
            holder.itemname.setText(utilityEMIEntertainmentListt.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(utilityEMIEntertainmentListt.getImg()));
            holder.lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (utilityEMIEntertainmentListt.getItemname().equals("Mobile Recharge")){
                     Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
                     startActivity(intent);
                    }
                    if (utilityEMIEntertainmentListt.getItemname().equals("DTH Recharge")){
                        Intent intent = new Intent(getActivity(), DTHRechargeActivity.class);
                        startActivity(intent);
                    }
                    if (utilityEMIEntertainmentListt.getItemname().equals("Bill Payment")){
                        Intent intent = new Intent(getActivity(), BIllPayment.class);
                        startActivity(intent);
                    }
                }
            });

        }
        @Override
        public int getItemCount() {
            return utilityEMIEntertainmentList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }

    public class HomeItemViewAdapter5 extends RecyclerView.Adapter<HomeItemViewAdapter5.ViewHolder> {
        private Context mCtx;
        private List<Insurance> insurance;
        int listview;
        public HomeItemViewAdapter5(Context mCtx, List<Insurance> insurance) {
            this.mCtx = mCtx;
            this.insurance = insurance;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Insurance insurancee = insurance.get(position);
            listview = position;
            holder.itemname.setText(insurancee.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(insurancee.getImg()));

        }
        @Override
        public int getItemCount() {
            return insurance.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class HomeItemViewAdapter6 extends RecyclerView.Adapter<HomeItemViewAdapter6.ViewHolder> {
        private Context mCtx;
        private List<Shopowner> shopowner;
        int listview;
        public HomeItemViewAdapter6(Context mCtx, List<Shopowner> shopowner) {
            this.mCtx = mCtx;
            this.shopowner = shopowner;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Shopowner shopownerr = shopowner.get(position);
            listview = position;
            holder.itemname.setText(shopownerr.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(shopownerr.getImg()));

        }
        @Override
        public int getItemCount() {
            return shopowner.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class HomeItemViewAdapter7 extends RecyclerView.Adapter<HomeItemViewAdapter7.ViewHolder> {
        private Context mCtx;
        private List<Travel> travel;
        int listview;
        public HomeItemViewAdapter7(Context mCtx, List<Travel> travel) {
            this.mCtx = mCtx;
            this.travel = travel;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Travel travel1 = travel.get(position);
            listview = position;
            holder.itemname.setText(travel1.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(travel1.getImg()));

        }
        @Override
        public int getItemCount() {
            return travel.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class HomeItemViewAdapter8 extends RecyclerView.Adapter<HomeItemViewAdapter8.ViewHolder> {
        private Context mCtx;
        private  List<Earnmore> earnmore;
        int listview;
        public HomeItemViewAdapter8(Context mCtx,  List<Earnmore> earnmore) {
            this.mCtx = mCtx;
            this.earnmore = earnmore;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row_view1, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Earnmore earnmore1 = earnmore.get(position);
            listview = position;
            holder.itemname.setText(earnmore1.getItemname());
            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(earnmore1.getImg()));

        }
        @Override
        public int getItemCount() {
            return travel.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname,catid;
            private ImageView imageItem;
            LinearLayout lv;
            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                // catid = (TextView)view.findViewById(R.id.catid);
                lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {

            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

