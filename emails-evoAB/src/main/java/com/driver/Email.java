package com.driver;

public class Email {

    private final String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(this.password))
        {
            char []ch = newPassword.toCharArray();
            boolean isEight=false;
            boolean isOneUpper=false;
            boolean isOneLower=false;
            boolean isOneDigit=false;
            boolean isOneSpecial=false;

            if(newPassword.length()>=8) isEight=true;

            for(char c : ch)
            {
                if(c>='A' && c<='Z') isOneUpper=true;
                else
                if(c>='a' && c<='z') isOneLower=true;
                else
                if(c>='0' && c<='9') isOneDigit=true;
                else
                    isOneSpecial=true;
            }

            if(isEight && isOneDigit && isOneUpper && isOneLower && isOneSpecial)
                this.password=newPassword;
        }

    }
}
