package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Date;
class Container{
    Date date;
    String sender;
    String message;
    Container(Date date, String sender, String message){
        this.date=date;
        this.sender=sender;
        this.message=message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    private ArrayList<Container> inbox;//Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    private ArrayList<Container> trash;//Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.inbox=new ArrayList<>();
        this.trash=new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()==this.inboxCapacity)
        {
            Container old = inbox.get(0);
            inbox.remove(0);
            trash.add(old);
        }
        Container c = new Container(date, sender, message);
        inbox.add(c);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int idx=-1;
        for(int i=0;i<inbox.size();i++)
        {
            if((inbox.get(i).message).equals(message))
            {
                idx=i;
                break;
            }
        }

        if(idx!=-1)
        {
            Container c = inbox.get(idx);
            inbox.remove(idx);
            trash.add(c);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()) return null;
        return inbox.get(inbox.size()-1).message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()) return null;
        return inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int cnt=0;
        for (Container container : inbox) {
            if ((start.compareTo(container.date)) <= 0 && end.compareTo(container.date) <= 0)
                cnt++;
        }
        return cnt;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
