package com.outbreak.UseCases.useCase3;

import java.util.ArrayList;

public interface useCase3_DAO {
    public void enterIntoDosesTable(useCase3 uc3);
    public void updateVaccinationStatus(useCase3 uc3);
    public int getCurrentDoseNo(String studentId);
}
