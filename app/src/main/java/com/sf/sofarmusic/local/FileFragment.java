package com.sf.sofarmusic.local;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sf.sofarmusic.R;
import com.sf.sofarmusic.base.Constant;
import com.sf.sofarmusic.base.LazyLoadBaseFragment;
import com.sf.sofarmusic.enity.FileItem;

import java.util.List;

/**
 * Created by sufan on 16/12/1.
 * 文件夹
 */

public class FileFragment extends LazyLoadBaseFragment implements FileAdapter.OnItemClickListener {

    private View view;

    private RecyclerView file_rv;
    private List<FileItem> mFileList;
    private FileAdapter mAdapter;

    private static final int REQUEST_CODE=102;  //>0的整数即可

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_local_file, container, false);
        return view;
    }


    @Override
    protected void initData() {
        mFileList=MusicLoader.getInstance().getLocalFileList(Constant.sLocalList);
        mAdapter = new FileAdapter(activity, mFileList);
        file_rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

    }



    @Override
    protected void initView() {
        file_rv = (RecyclerView) view.findViewById(R.id.file_rv);
        file_rv.setLayoutManager(new LinearLayoutManager(activity));

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void OnFileItem(int position) {
        FileItem item = mFileList.get(position);


        Intent intent = new Intent(activity, ShowDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Bundle bundle = new Bundle();
        bundle.putString("title", item.name);
        //   bundle.putSerializable("list", playList);
        Constant.sPreList = item.fileList;   //
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUEST_CODE);


    }

    public void refreshData() {
        if (isInit) {
            initData();
        }
    }
}
