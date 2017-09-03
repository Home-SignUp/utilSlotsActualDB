package com.prepare;

import org.apache.commons.lang.ArrayUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * @see https://habrahabr.ru/post/237043/
 *      https://gist.github.com/psayre23/c30a821239f4818b0709
 *
 * Вопросы и ответы на собеседовании по теме Java Collection Framework
 * -------------------------------------------------------------------
 * @see http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-1
 *      http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-2
 *      http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-3
 */

public class Friend {

    public Friend(){
    }

    public Friend(String email){
        this.email = email;
    }

    private Collection<Friend> friends;
    private String email;

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Collection<Friend> friends) {
        this.friends = friends;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        // https://www.programcreek.com/2013/09/top-10-questions-for-java-collections
        // http://info.javarush.ru/translation/2014/06/26/Топ-10-вопросов-о-коллекциях-в-Java.html
        int[] array = {1,2,3,4,5};
        List list = Arrays.asList(ArrayUtils.toObject(array));
        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()) {
            int i = itr.next();
            System.out.print( i + " " );
        }
    }

}
