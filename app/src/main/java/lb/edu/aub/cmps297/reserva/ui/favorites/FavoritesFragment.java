package lb.edu.aub.cmps297.reserva.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.FavoriteRestaurantsByClientsViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentFavoritesBinding;
import lb.edu.aub.cmps297.reserva.adapters.RestaurantAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private RecyclerView restaurantRV;
    private ArrayList<Restaurant> favRestaurantArrayList;
    private TextView favRestaurantCounter;

    private FavoriteRestaurantsByClientsViewModel favoriteRestaurantsByClientsViewModel;
    private LoggedInUserViewModel loggedInUserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        favRestaurantCounter = root.findViewById(R.id.idFavResCounter);
        favoriteRestaurantsByClientsViewModel = new ViewModelProvider(this).get(FavoriteRestaurantsByClientsViewModel.class);
        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        String client_email = loggedInUserViewModel.getUser().email;
        favRestaurantArrayList = favoriteRestaurantsByClientsViewModel.getAllFavoriteRestaurants(client_email);
        favRestaurantCounter.setText(favRestaurantArrayList.size() + " restaurants found");
        restaurantRV = root.findViewById(R.id.idRVRestaurant);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this.getContext(), favRestaurantArrayList, favoriteRestaurantsByClientsViewModel, client_email);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        restaurantRV.setLayoutManager(linearLayoutManager);
        restaurantRV.setAdapter(restaurantAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}