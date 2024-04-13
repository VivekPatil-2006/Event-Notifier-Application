package com.example.eventapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.eventapp.Fragments.Calls_Fragment;
import com.example.eventapp.Fragments.Chat_Fragment;
import com.example.eventapp.Fragments.Group_Fragment;
import com.example.eventapp.Fragments.Status_Fragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new Group_Fragment();
            default:return new Chat_Fragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
