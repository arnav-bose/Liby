package com.example.arnavbose.liby;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWishlist extends Fragment {

    EditText editTextWishlistTitle, editTextWishlistAuthor;
    Button buttonWishlist;
    RecyclerView recyclerViewWishlist;


    public FragmentWishlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        editTextWishlistTitle = (EditText) view.findViewById(R.id.editTextWishlistTitle);
        editTextWishlistAuthor = (EditText)view.findViewById(R.id.editTextWishlistAuthor);
        buttonWishlist = (Button)view.findViewById(R.id.buttonWishlist);
        recyclerViewWishlist = (RecyclerView)view.findViewById(R.id.recyclerViewWishlist);

        String methodGet = "Wishlist_Get";
        AsyncTaskWishlistGet asyncTaskWishlistGet = new AsyncTaskWishlistGet(getContext(), recyclerViewWishlist);
        asyncTaskWishlistGet.execute(methodGet);

        buttonWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextWishlistTitle.getText().toString().equals("") && !editTextWishlistAuthor.getText().toString().equals("")){
                    String methodAdd = "Wishlist_Add";
                    String title = editTextWishlistTitle.getText().toString();
                    String author = editTextWishlistAuthor.getText().toString();
                    AsyncTaskWishlistAdd asyncTaskWishlistAdd = new AsyncTaskWishlistAdd(getContext());
                    asyncTaskWishlistAdd.execute(methodAdd, title, author);
                }
                else
                    Toast.makeText(getContext(), "Enter the book details", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
