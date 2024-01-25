package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kits.kitsclick.setget.Deviceadddata;
import com.kits.kitsclick.setget.Listdatadevice;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Ministatementsetget;
import com.kits.kitsclick.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BalanceEnquiryActivity extends AppCompatActivity {
    RecyclerView rcv_mechine;
    List<Ministatementsetget> ministatementsetgetList;
    MinistatementItemViewAdapter4 ministatementItemViewAdapter4;
    FloatingActionButton fab;
    ArrayList<Ministatementsetget> arrlist = new ArrayList<Ministatementsetget>();
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ministatement_activitybalance);
        rcv_mechine = (RecyclerView) findViewById(R.id.rcv_mechine);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        balance = (TextView) findViewById(R.id.balance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        balance.setText(blance);
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Deviceadd_activitybalance.class);
                startActivity(intent);
            }
        });
        ministatementsetgetList =  new ArrayList<>();
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Morpho",
//                        R.drawable.morfo));
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Startek",
//                        R.drawable.startek));
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Mantra",
//                        R.drawable.mantra));
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Precision",
//                        R.drawable.precsion));
//
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Evolute",
//                        R.drawable.sf));
//        ministatementsetgetList.add(
//                new Ministatementsetget(
//                        "Secugen",
//                        R.drawable.secugen));

        loadlist();
    }
    public void loadlist(){
        if (Utility.checkConnectivity(getApplicationContext())) {
            Call<Deviceadddata> call = ApiClientToken.getInstance().deviceadd(
            );
            call.enqueue(new Callback<Deviceadddata>() {
                @Override
                public void onResponse(Call<Deviceadddata> call, retrofit2.Response<Deviceadddata> response) {
                    if (response.isSuccessful()){
                        String error = response.body().getError();
                        Log.e("response",""+error);
                        if (error.equals("false")){
                            for (int i = 0; i <response.body().getData().getList().size(); i++) {
                                Listdatadevice listdata =  response.body().getData().getList().get(i);
                                String company = listdata.getCompany();
                                String devicetype = listdata.getDivtype();
                                String image = listdata.getImg();
                                String model = listdata.getModel();
                                String serial = listdata.getSerial();
                                System.out.println("device_name"+company+" "
                                        +devicetype+" "+image+" "+model+" "+serial);
//                                 billimage.add(image);
//                                 billname.add(name);
                                ministatementsetgetList.add(new Ministatementsetget(
                                        company,
                                        image,devicetype,model,serial
                                ));
                                arrlist.addAll(ministatementsetgetList);

                            }
                            RecyclerView.LayoutManager mLayoutManager5 = new GridLayoutManager(getApplication(), 2);
                            rcv_mechine.setLayoutManager(mLayoutManager5);
                            rcv_mechine.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
                            rcv_mechine.setItemAnimator(new DefaultItemAnimator());
                            rcv_mechine.setAdapter(ministatementItemViewAdapter4);
                            ministatementItemViewAdapter4 = new MinistatementItemViewAdapter4(getApplication(), ministatementsetgetList);
                            rcv_mechine.setAdapter(ministatementItemViewAdapter4);
                        }


                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<Deviceadddata> call, Throwable t) {

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




    public class MinistatementItemViewAdapter4 extends RecyclerView.Adapter<MinistatementItemViewAdapter4.ViewHolder> {
        private Context mCtx;
        private List<Ministatementsetget> ministatementsetgetList;
        int listview;
        public MinistatementItemViewAdapter4(Context mCtx, List<Ministatementsetget> ministatementsetgetList) {
            this.mCtx = mCtx;
            this.ministatementsetgetList = ministatementsetgetList;
        }
        @Override
        public MinistatementItemViewAdapter4.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_statement, parent, false);
            return new MinistatementItemViewAdapter4.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final MinistatementItemViewAdapter4.ViewHolder holder, int position) {
            Ministatementsetget ministatementsetget = ministatementsetgetList.get(position);
            listview = position;
            holder.tv_min_name.setText(ministatementsetget.getStname());
//            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(ministatementsetget.getImg()));
            Glide.with(mCtx)
                    .load(ministatementsetget.getImg())
                    .into(holder.imageItem);
            holder.cardId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, Balanceenquary_seconedAdher.class);
                    startActivity(intent);

                }
            });
        }
        @Override
        public int getItemCount() {
            return ministatementsetgetList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageItem;
            private TextView tv_min_name;
            private CardView cardId;
            public ViewHolder(View view) {
                super(view);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                tv_min_name = (TextView) view.findViewById(R.id.tv_min_name);
                cardId = (CardView)view.findViewById(R.id.cardId);

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

