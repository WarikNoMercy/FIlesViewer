package com.example.vk_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class CheckedActivity extends AppCompatActivity {

    private Boolean [] selectedSort = {true,false,false,false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked);
        ImageView sortIcon = findViewById(R.id.sorted_icon);
        sortIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopunMenu(view);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TextView filesNotFound = findViewById(R.id.files_not_found);

        String path = getIntent().getStringExtra("path");
        File root = new File(path);
        File [] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length==0){
            filesNotFound.setVisibility(View.VISIBLE);
            return;
        }
        if (filesAndFolders != null && filesAndFolders.length > 1){
            Arrays.sort(filesAndFolders, new Comparator<File>() {
                @Override
                public int compare(File file1, File file2) {
                    return file1.getName().compareTo(file2.getName());
                }
            });
        }
        filesNotFound.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FilesViewer(getApplicationContext(),filesAndFolders));
    }
    public void setSort(int id){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        String path = getIntent().getStringExtra("path");
        File root = new File(path);
        File [] filesAndFolders = root.listFiles();
        if (selectedSort[id]){
            Toast.makeText(CheckedActivity.this,"This sort've already selected",Toast.LENGTH_SHORT).show();
        }else {
            if (id == 0) {
                Toast.makeText(CheckedActivity.this, "First sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            return file1.getName().compareTo(file2.getName());
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            } else if (id == 1) {
                Toast.makeText(CheckedActivity.this, "Second sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            if (file1.length() > file2.length())
                                return 1;
                            if (file1.length() < file2.length())
                                return -1;
                            return 0;
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            } else if(id==2) {
                Toast.makeText(CheckedActivity.this, "Third sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            if (file1.length() < file2.length())
                                return 1;
                            if (file1.length() > file2.length())
                                return -1;
                            return 0;
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            }else if (id == 3) {
                Toast.makeText(CheckedActivity.this, "Fourth sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            if (file1.lastModified() > file2.lastModified())
                                return 1;
                            if (file1.lastModified() < file2.lastModified())
                                return -1;
                            return 0;
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            } else if(id==4) {
                Toast.makeText(CheckedActivity.this, "Fifth sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            if (file1.lastModified() < file2.lastModified())
                                return 1;
                            if (file1.lastModified() > file2.lastModified())
                                return -1;
                            return 0;
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            }else if (id == 5) {
                Toast.makeText(CheckedActivity.this, "Sixth sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            String fileName1 = file1.getName();
                            String fileExt1 = fileName1.substring(fileName1.lastIndexOf(".") + 1);
                            String fileName2 = file2.getName();
                            String fileExt2 = fileName1.substring(fileName2.lastIndexOf(".") + 1);
                            return fileExt1.compareTo(fileExt2);
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            } else if(id==6) {
                Toast.makeText(CheckedActivity.this, "Seventh sort selected", Toast.LENGTH_SHORT).show();
                if (filesAndFolders != null && filesAndFolders.length > 1) {
                    Arrays.sort(filesAndFolders, new Comparator<File>() {
                        @Override
                        public int compare(File file1, File file2) {
                            String fileName1 = file1.getName();
                            String fileExt1 = fileName1.substring(fileName1.lastIndexOf(".") + 1);
                            String fileName2 = file2.getName();
                            String fileExt2 = fileName1.substring(fileName2.lastIndexOf(".") + 1);
                            return fileExt2.compareTo(fileExt1);
                        }
                    });
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new FilesViewer(getApplicationContext(), filesAndFolders));
            }
        }
        for (int i =0; i < selectedSort.length;i++){
            selectedSort[i] = false;
        }
        selectedSort[id] = true;
    }
    private void showPopunMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        //Toast.makeText(CheckedActivity.this,"First sort selected",Toast.LENGTH_SHORT).show();
                        setSort(0);
                        return true;
                    case R.id.item2:
                        //Toast.makeText(CheckedActivity.this,"Secon sort selected",Toast.LENGTH_SHORT).show();
                        setSort(1);
                        return true;
                    case R.id.item3:
                        setSort(2);
                        return true;
                    case R.id.item4:
                        setSort(3);
                        return true;
                    case R.id.item5:
                        setSort(4);
                        return true;
                    case R.id.item6:
                        setSort(5);
                        return true;
                    case R.id.item7:
                        setSort(6);
                        return true;
                }
            return true;
            }
        });
    }
}