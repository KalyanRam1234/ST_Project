package com.outbreak.UseCases.useCase2;
import java.util.ArrayList;

import com.outbreak.RTPCR.RTPCR;

public interface useCase2_DAO {
    public useCase2 getMyRTPCR_Status(String rollNo, String Date);
    public ArrayList<useCase2> getAllMyRTPCR_Status(String rollNo);
    public void addMyRTPCR_Status(RTPCR rtpcr);
}
