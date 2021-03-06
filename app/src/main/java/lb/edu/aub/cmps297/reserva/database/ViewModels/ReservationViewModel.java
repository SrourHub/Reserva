package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;
import lb.edu.aub.cmps297.reserva.database.Repositories.ReservationRepository;

public class ReservationViewModel extends AndroidViewModel {
    private final ReservationRepository reservationRepository;

    public ReservationViewModel(@NonNull Application application) {
        super(application);
        reservationRepository = new ReservationRepository(application);
    }

    public ArrayList<Reservation> getRestaurantReservations(String restaurant_email) {
        return reservationRepository.getRestaurantReservations(restaurant_email);
    }

    public ArrayList<Reservation> getClientReservations(String client_email) {
        return reservationRepository.getClientReservations(client_email);
    }

    public void updateReservation(String id, String status) {
        reservationRepository.updateReservation(id, status);
    }

    public void insert(String client_email, String restaurant_email, String seats_requested, String status) {
        reservationRepository.insert(client_email, restaurant_email, seats_requested, status);
    }
}