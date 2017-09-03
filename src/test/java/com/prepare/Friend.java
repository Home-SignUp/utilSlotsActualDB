package com.prepare;

import java.util.ArrayList;
import java.util.Collection;


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

    private Collection<Friend> friends = new ArrayList<>();
    private String email;

    public Friend(String email){
        this.email = email;
    }

    public void addFriends(Friend friend) {
        this.friends.add(friend);
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public String getEmail() {
        return email;
    }

}
