package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kits.kitsclick.fragment.DasboardFragment;
import com.kits.kitsclick.setget.Bill_payment;
import com.kits.kitsclick.setget.Billpaymentservice;
import com.kits.kitsclick.setget.Listdatabilpayment;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Mobleoperatorlist;
import com.kits.kitsclick.setget.UtilityEMIEntertainment;
import com.kits.kitsclick.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BIllPayment extends AppCompatActivity {
    RecyclerView bill_payment;
    BillpaymentItemViewAdapter4 billpaymentItemViewAdapter4;
    List<Bill_payment> bill_paymentList;
   ImageView Img;
   TextView tv,balance;
    GridView gridView;
    private List<String> billimage = new ArrayList<>();
    private List<String> billname = new ArrayList<>();
    ArrayList<Bill_payment> arrlist = new ArrayList<Bill_payment>();
     ImageView Img2;
     ProgressBar progressBar_cyclic;
     LinearLayout lvv;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_payment);
        bill_payment = (RecyclerView) findViewById(R.id.bill_payment);
        Img = (ImageView)findViewById(R.id.Img);
        tv = (TextView)findViewById(R.id.tv);
        balance = (TextView)findViewById(R.id.balance);
        gridView = (GridView)findViewById(R.id.gridView);
        Img2 = (ImageView)findViewById(R.id.Img2);
        progressBar_cyclic =(ProgressBar)findViewById(R.id.progressBar_cyclic);
        lvv = (LinearLayout)findViewById(R.id.lvv);
        progressBar_cyclic.setVisibility(View.VISIBLE);
        lvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ReporttabActivity.class);
                startActivity(intent1);
            }
        });
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        balance.setText(blance);
        bill_paymentList = new ArrayList<>();
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Cable TV",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Electricity",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "DTH",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Credit card",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Associati",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Education",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Fastag",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "LPG Gas",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Housings Ociety",
//                        R.drawable.ic_help));
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Water",
//                        R.drawable.ic_help));
//
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Postpaid",
//                        R.drawable.ic_help));
//
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Piped Gas",
//                        R.drawable.ic_help));
//
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Hospital",
//                        R.drawable.ic_help));
//
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Insurance",
//                        R.drawable.ic_help));
//
//        bill_paymentList.add(
//                new Bill_payment(
//                        "1",
//                        "Loan Repayment",
//                        R.drawable.ic_help));


        loadlist();
    }
     public void loadlist(){
         if (Utility.checkConnectivity(getApplicationContext())) {
             Call<Billpaymentservice> call = ApiClientToken.getInstance().billpaymentlist(
             );
             call.enqueue(new Callback<Billpaymentservice>() {
                 @Override
                 public void onResponse(Call<Billpaymentservice> call, retrofit2.Response<Billpaymentservice> response) {
                     if (response.isSuccessful()){
                         progressBar_cyclic.setVisibility(View.GONE);
                         String error = response.body().getError();
                         Log.e("response",""+error);
                         if (error.equals("false")){
                             for (int i = 0; i <response.body().getData().getList().size(); i++) {
                                 Listdatabilpayment listdata =  response.body().getData().getList().get(i);
                                 String name = listdata.getName();
                                 String image = listdata.getImage();
                                 System.out.println("dataname"+name);
//                                 billimage.add(image);
//                                 billname.add(name);
                                 bill_paymentList.add(new Bill_payment(
                                         name,
                                         image
                                 ));
                                 arrlist.addAll(bill_paymentList);

                             }
                             RecyclerView.LayoutManager mLayoutManager5 = new GridLayoutManager(getApplication(), 3);
                             bill_payment.setLayoutManager(mLayoutManager5);
                             bill_payment.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(5), true));
                             bill_payment.setItemAnimator(new DefaultItemAnimator());
                             bill_payment.setAdapter(billpaymentItemViewAdapter4);
                             billpaymentItemViewAdapter4 = new BillpaymentItemViewAdapter4(getApplication(), bill_paymentList);
                             bill_payment.setAdapter(billpaymentItemViewAdapter4);
//                             MyGridAdapter adapter = new MyGridAdapter(getApplicationContext(), billimage,billname);
//                             gridView.setAdapter(adapter);
                         }


                     }
                     else {
                         Log.e("DATA", " "+response.message());
                     }

                 }

                 @Override
                 public void onFailure(Call<Billpaymentservice> call, Throwable t) {

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


    public class BillpaymentItemViewAdapter4 extends RecyclerView.Adapter<BillpaymentItemViewAdapter4.ViewHolder> {
        private Context mCtx;
        private List<Bill_payment> bill_paymentList;
        int listview;

        public BillpaymentItemViewAdapter4(Context mCtx, List<Bill_payment> bill_paymentList) {
            this.mCtx = mCtx;
            this.bill_paymentList = bill_paymentList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_pay_cardview, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Bill_payment bill_payment = bill_paymentList.get(position);
            listview = position;
            holder.itemname.setText(bill_payment.getBillname());
//            holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(bill_payment.getImg()));
            //holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(homeitemViewCatagorySetGet.getImg()));
            Glide.with(mCtx)
                    .load(bill_payment.getImg())
                    .into(holder.imageItem);
            holder.cardId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Itemname = holder.itemname.getText().toString();
                    String imag = holder.imageItem.toString();
                    Log.e("ViewName",Itemname);
                    Intent intent = new Intent(mCtx, Catagoryactivity.class);
                    Bundle bundle_edit  =   new Bundle();
                    bundle_edit.putString("Itemname",Itemname);
                    bundle_edit.putString("imag",bill_payment.getImg());
                    intent.putExtras(bundle_edit);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mCtx.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return bill_paymentList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemname;
            private ImageView imageItem;
             private CardView cardId;
            //LinearLayout lv;

            public ViewHolder(View view) {
                super(view);
                itemname = (TextView) view.findViewById(R.id.itemname);
                imageItem = (ImageView) view.findViewById(R.id.imageItem);
                 cardId = (CardView) view.findViewById(R.id.cardId);
                // catid = (TextView)view.findViewById(R.id.catid);
                //lv = (LinearLayout) view.findViewById(R.id.lv);
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

    public class MyGridAdapter extends BaseAdapter {

        private Context gContext;
        private List<String> listImages = new ArrayList<>();
        private List<String> listTexts = new ArrayList<>();
        private LayoutInflater inflater;


        public MyGridAdapter(Context gContext, List<String> listImages,List<String> listTexts) {
            this.gContext = gContext;
            this.listImages = listImages;
            this.listTexts = listTexts;
        }

        @Override
        public int getCount() {
            return listImages.size();
        }

        @Override
        public Object getItem(int position) {
            return listImages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if (convertView == null) {
                inflater = (LayoutInflater) gContext.getSystemService(gContext.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.home_item_row_vieww, null);
            }

            Log.e("item_image",listImages.get(position));
            ImageView item_image = view.findViewById(R.id.imageItem);
            TextView item_text = view.findViewById(R.id.itemname);
            item_text.setText(listTexts.get(position));
            //item_image.setImageResource(listImages.get(position));
            Glide.with(gContext)
                    .load(listImages.get(position))
                    .into(item_image);
            return view;
        }
    }

}
