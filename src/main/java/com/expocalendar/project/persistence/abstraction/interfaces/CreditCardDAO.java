package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.CreditCard;

public interface CreditCardDAO {

    CreditCard findCard(int id);

    void addFunds(int id, double sum);

}
