package com.outbreak.UseCases.useCase8;

import java.util.ArrayList;

public interface useCase8DAO {
    public useCase8 getRoomDetails(String roomNo);
    public ArrayList<useCase8> getAllRoomDetails(String hostelType);
}
