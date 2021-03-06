package edu.cmu.webapp.task7.formbean;

import java.util.ArrayList;
import java.util.List;

public class DepositCheckForm extends FormBean {
    private String userName;
    private String dollarAmount;
    private String confirmAmount;
    private String action;

    @Override
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();

        if ((getUserName() == null || getUserName().length() == 0)) {
            errors.add("Username is required");
        }
        if (getDollarAmount() == null || getDollarAmount().length() == 0) {
            errors.add("Amount is required");
        }
        if (getConfirmAmount() == null || getConfirmAmount().length() == 0) {
            errors.add("Confirmed Amount is required");
        }
        if (getAction() == null) {
            errors.add("Button is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (!getAction().equals("Deposit Check")) {
            errors.add("Invalid button");
        }
        if (getUserName().matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }

        try {
            String error = checkNumberFormat(getDollarAmount());
            if (!error.equals("")) {
                errors.add(error);
            }
        } catch (MyException e) {
            errors.add(e.getMessage());
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (Double.parseDouble(getDollarAmount()) != Double.parseDouble(getConfirmAmount())) {
            errors.add("Dollar amount and the confirmed don't match");
        }

        return errors;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(String dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public String getConfirmAmount() {
        return confirmAmount;
    }

    public void setConfirmAmount(String confirmAmount) {
        this.confirmAmount = confirmAmount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}