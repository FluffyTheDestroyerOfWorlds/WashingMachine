package com.bigskysolutionsinc.boesen.washingmachine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Integer[] Parts = {R.drawable.cabinet, R.drawable.dispenser, R.drawable.drum};
    ImageView Pic;
    Toast t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Button btnClose = (Button)findViewById(R.id.btnClose);
        final Button button  = (Button)findViewById(R.id.btnPDF);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.searspartsdirect.com/partsdirect/user-manuals/wm3360hvca-lg-parts-washing+machine-manual?manualIndex=0")));
              // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://c.searspartsdirect.com/mmh/lis_pdf/OWNM/1105037L.pdf")));

            }
        });
        final ImageView imgViewBig = (ImageView) findViewById(R.id.iv2);

        imgViewBig.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    String sItem = "";
                System.out.println("here" + x + "----" + y);
                    sItem = WhereAmI(x, y);
                    if (!sItem.isEmpty()) {
                        t1.makeText(getBaseContext(), sItem,
                                Toast.LENGTH_SHORT).show();
                    }


                return false;
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imgViewBig.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                btnClose.setVisibility(View.INVISIBLE);
            }
        });



        GridView grid = (GridView) findViewById(R.id.gridView);
        final ImageView mainPic = (ImageView) findViewById(R.id.iv2);
        assert grid != null;
        grid.setAdapter(new ImageAdapter(this));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainPic.setImageResource(Parts[position]);
                mainPic.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
                btnClose.setVisibility(View.VISIBLE);
            }

        });


    }
    public String WhereAmI(int x, int y) {
        String sText = "";
        Rect board = new Rect();
        Rect soap = new Rect();
        Rect drain = new Rect();
        Rect water = new Rect();

        board.set(380,340,677,579);
        soap.set(133,745,447,959);
        drain.set(577,690,719,893);
        water.set(317,1007,496,1149);

        if (board.contains(x,y)){
            sText = "This is the board";
        }
        if (soap.contains(x,y)){
            sText = "This is the soap dispenser";
        }
        if (drain.contains(x,y)) {
            sText = "This is the drain assembly";
        }
        if (water.contains(x,y)){
            sText = "This is the water input hoses.";
        }

        return sText;
    }


    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }


        @Override
        public int getCount() {
            return Parts.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Pic = new ImageView(context);
            Pic.setImageResource(Parts[position]);
            Pic.setScaleType(ImageView.ScaleType.FIT_XY);
            Pic.setLayoutParams(new GridView.LayoutParams(200,250));
            return Pic;
        }
    }

}




