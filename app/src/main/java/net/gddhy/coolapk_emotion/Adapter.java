package net.gddhy.coolapk_emotion;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import static net.gddhy.coolapk_emotion.MainActivity.bitMapScale;
import static net.gddhy.coolapk_emotion.MainActivity.save;
import static net.gddhy.coolapk_emotion.MainActivity.share;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context context;
    private final List<File> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            imageView = (ImageView) view.findViewById(R.id.item_img);
        }
    }

    public Adapter(List<File> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                File file = list.get(position);
                View root = LayoutInflater.from(context).inflate(R.layout.dialog,null);
                TextView textView = root.findViewById(R.id.dialog_text);
                ImageView img = root.findViewById(R.id.dialog_img);
                ImageButton btnQQ = root.findViewById(R.id.dialog_qq);
                ImageButton btnWechat = root.findViewById(R.id.dialog_wechat);
                ImageButton btnSave = root.findViewById(R.id.dialog_save);
                ImageButton btnShare = root.findViewById(R.id.dialog_share);
                ImageButton btnTim = root.findViewById(R.id.dialog_tim);
                AlertDialog dialog = new AlertDialog.Builder(context).setView(root).create();
                btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        share(context,file,0);
                    }
                });
                btnQQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        share(context,file,1);
                    }
                });
                btnWechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        share(context,file,2);
                    }
                });
                btnTim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        share(context,file,3);
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        save((Activity) context,file,position);
                    }
                });
                textView.setText(file.getName());
                Glide.with(context).load(file).into(img);
                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File file = list.get(position);
        holder.imageView.setImageBitmap(bitMapScale(BitmapFactory.decodeFile(file.getPath()),2.6f));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}