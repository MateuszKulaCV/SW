package com.example.maticzok.sw.Options;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.maticzok.sw.Model.User;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.Response.GetResponse;
import com.example.maticzok.sw.UserInfoDF;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maticzok on 2016-07-07.
 */
public class SearchFragment extends Fragment {
    SearchView searchView;
    ListView userList;
    UsersAdapter usersAdapter;
    SimpleCursorAdapter simpleCursorAdapter;
    HashSet<String> hash = new HashSet<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        searchView = (SearchView) view.findViewById(R.id.searchview);
        userList =(ListView) view.findViewById(R.id.searchlist);

        GetResponse.init();
        Call<List<User>> call = GetResponse.getApi().getResp();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> list = response.body();
                //Setting suggestions
                for(User u: response.body())
                {
                    hash.add(u.getId());
                    hash.add(u.getUsername());
                }

                usersAdapter = new UsersAdapter(getActivity().getApplicationContext(),list);
                userList.setAdapter(usersAdapter);


                searchViewMethod();


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Search Failure:",t.toString());
            }
        });

        //setDialogFragment - user info
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               UserInfoDF userInfoDF = new UserInfoDF();
                User user = (User)usersAdapter.getItem(i);
               userInfoDF.setUser(user);
                userInfoDF.show(getFragmentManager(),"UserInfoDF");


            }
        });



        return view;
    }

    void searchViewMethod()
    {
        //set suggestion "builder"
        final String[] from = new String[] {"bynameuser"};
        final int[] to = new int[] {android.R.id.text1};

        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,null,from,to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        searchView.setSuggestionsAdapter(simpleCursorAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searching depend on number or letters
                if(TextUtils.isDigitsOnly(query)) {
                    usersAdapter.getNOFilter().filter(query);
                }else
                {
                    usersAdapter.getNameFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //searching depend on number or letters
                if(TextUtils.isDigitsOnly(newText)) {
                    usersAdapter.getNOFilter().filter(newText);
                }else
                {
                    usersAdapter.getNameFilter().filter(newText);
                }
                usersAdapter.setUsers((List<User>) usersAdapter.getUsersmem());
                populateAdapter(newText);
                return false;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {

                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
                String sugg = getSuggestion(i);
                searchView.setQuery(sugg,true);
                return true;
            }
        });
    }
    //suggestion Cursor
    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "bynameuser" });
        Iterator<String> it = hash.iterator();
        int i=0;
        while(it.hasNext())
        {
            String temp = it.next();
            if (temp.toLowerCase().contains(query.toLowerCase()))
                c.addRow(new Object[] {i, temp});
            i++;
        }
        simpleCursorAdapter.changeCursor(c);
    }



    String getSuggestion(int position)
    {
        Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
        String Suggest = cursor.getString(cursor.getColumnIndex("bynameuser"));
        return Suggest;
    }

}
