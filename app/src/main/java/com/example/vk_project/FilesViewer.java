package com.example.vk_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FilesViewer extends RecyclerView.Adapter<FilesViewer.ViewHolder>{
    Context context;
    File[] filesAndFolders;
    public FilesViewer(Context context, File[] filesAndFolders){
        this.context = context;
        this.filesAndFolders = filesAndFolders;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(FilesViewer.ViewHolder holder, int position) {
        //Add name of file
        File selectedFile = filesAndFolders[position];
        holder.textViewName.setText(selectedFile.getName());

        long fileSize = selectedFile.length();
        double fileSizeD;
        if ((fileSize / 1024/1024) >= 1){
            fileSizeD = fileSize / 1024.0 / 1024.0;
            String fileSizeStr = "Size: " + String.format("%.2f",fileSizeD) + " " + "Mb";
            holder.textViewSize.setText(fileSizeStr);
        }else if((fileSize / 1024) >= 1){
            fileSizeD = fileSize  / 1024.0;
            String fileSizeStr = "Size: " + String.format("%.2f",fileSizeD) + " " + "Kb";
            holder.textViewSize.setText(fileSizeStr);
        }else{
            String fileSizeStr = "Size: " + fileSize + " " + "Bytes";
            holder.textViewSize.setText(fileSizeStr);
        }

        Date lastModDate = new Date(selectedFile.lastModified());
        holder.textViewDate.setText(lastModDate.toString());

        String fileName = selectedFile.getName();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        //String fileSizeStr = "Size: " + fileSize + " " + "Bytes";
        if(selectedFile.isDirectory()) {
            holder.imageView.setImageResource(R.drawable.folder);
        }else{
            if (fileExt.equals("png") || fileExt.equals("jpeg") || fileExt.equals("jpg") || fileExt.equals("JPG")){
                holder.imageView.setImageResource(R.drawable.image);
            }else if(fileExt.equals("pdf")){
                holder.imageView.setImageResource(R.drawable.pdf_file);
            }else if(fileExt.equals("mp4") || fileExt.equals("gif")){
                holder.imageView.setImageResource(R.drawable.video_image);
            }else{
                holder.imageView.setImageResource(R.drawable.doc_file);
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedFile.isDirectory()){
                    Intent intent = new Intent(context,CheckedActivity.class);
                    String path = selectedFile.getPath();
                    intent.putExtra("path",path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewDate;
        TextView textViewSize;
        ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            textViewDate = itemView.findViewById(R.id.file_date_text_view);
            textViewName = itemView.findViewById(R.id.file_name_text_view);
            textViewSize = itemView.findViewById(R.id.file_size_text_view);
            imageView = itemView.findViewById(R.id.icon_view);
        }
    }
}
