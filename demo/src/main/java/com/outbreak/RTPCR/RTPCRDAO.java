package com.outbreak.RTPCR;

import java.util.ArrayList;

public interface RTPCRDAO {
    public ArrayList<RTPCR> getRTPCRByStudentId(String id);
    public RTPCR getRTPCRByStudentId_Date(String id, String testDate);
    public ArrayList<RTPCR> getRTPCR_postive();
    public ArrayList<RTPCR> getRTPCR_negative();
    public RTPCR getRTPCRByStudentId_LastDate(String id);
    public int enterRTPCR(RTPCR test);
    public void deleteRTPCR(String testId, String studentId);
}
