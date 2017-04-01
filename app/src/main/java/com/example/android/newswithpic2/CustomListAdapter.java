package com.example.android.newswithpic2;

/**
 * Created by Pratik saxsena on 29-03-2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik saxsena on 26-03-2017.
 */

public class CustomListAdapter extends ArrayAdapter<NewsElements> implements View.OnClickListener {


    ArrayList<NewsElements> newselements;
    int resource;
    Context context;
    String articleurl;

    public CustomListAdapter(Context context, int resource, ArrayList<NewsElements> newselements)
    {
        super(context,resource,newselements);
        this.newselements = newselements;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list, null, true);

        }
        NewsElements newselement = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.pic);
        Picasso.with(context).load(newselement.getImage()).into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText(newselement.getHeadlines());
        articleurl = newselement.getArticleurl();
        convertView.setOnClickListener(this);

        return convertView;
    }

    public void onClick(View view){
        Intent browserintent = new Intent("android.intent.action.VIEW", Uri.parse(articleurl));
        browserintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browserintent);

    }
}
