package com.casino.carddealer.comparator;

import com.casino.carddealer.utility.Cards;
import java.util.Comparator;

/**
 * Card comparator is used to sort a hand for evaluation preparation. The order of the sorting as
 * follows ranks => 2, 3, 4, 5, 6, 7, 8, 9, 10(T), Jack(J), Queen(Q), King(K), Ace(A) and suits =>
 * Clubs (C), Diamonds (D), Hearts (H) or Spades (S). A given hand e.g. "8S", "TD", "2C", "3S", "8D"
 * is sorted to "2C" "3S" "8D" "8S" "TD".
 */
public class CardComparator implements Comparator<String> {

  @Override
  public int compare(String o1, String o2) {
    int tjqka = checkTJQKA(o1, o2);
    if (tjqka != 0) {
      return tjqka;
    }

    int jqka = checkJQKA(o1, o2);
    if (jqka != 0) {
      return jqka;
    }

    int qka = checkQKA(o1, o2);
    if (qka != 0) {
      return qka;
    }

    int ka = checkKA(o1, o2);
    if (ka != 0) {
      return ka;
    }

    return o1.compareTo(o2);
  }

  private int checkTJQKA(String o1, String o2) {
    if (o1.startsWith(Cards.TEN.toString()) && (o2.startsWith(Cards.JACK.toString()) || o2
        .startsWith(Cards.QUEEN.toString()) || o2.startsWith(Cards.KING.toString())
        || o2.startsWith(Cards.ACE.toString()))) {
      return -1;
    } else if ((o1.startsWith(Cards.JACK.toString()) || o1.startsWith(Cards.QUEEN.toString()) || o1
        .startsWith(Cards.KING.toString())
        || o1.startsWith(Cards.ACE.toString())) && o2.startsWith(Cards.TEN.toString())) {
      return 1;
    }

    return 0;
  }

  private int checkJQKA(String o1, String o2) {
    if (o1.startsWith(Cards.JACK.toString()) && (o2.startsWith(Cards.QUEEN.toString()) || o2
        .startsWith(Cards.KING.toString())
        || o2.startsWith(Cards.ACE.toString()))) {
      return -1;
    } else if ((o1.startsWith(Cards.QUEEN.toString()) || o1.startsWith(Cards.KING.toString())
        || o1.startsWith(Cards.ACE.toString())) && o2.startsWith(Cards.JACK.toString())) {
      return 1;
    }

    return 0;
  }

  private int checkQKA(String o1, String o2) {
    if (o1.startsWith(Cards.QUEEN.toString()) && (o2.startsWith(Cards.KING.toString()) || o2
        .startsWith(Cards.ACE.toString()))) {
      return -1;
    } else if ((o1.startsWith(Cards.KING.toString()) || o1.startsWith(Cards.ACE.toString())) && o2
        .startsWith(Cards.QUEEN.toString())) {
      return 1;
    }

    return 0;
  }

  private int checkKA(String o1, String o2) {
    if (o1.startsWith(Cards.KING.toString()) && o2.startsWith(Cards.ACE.toString())) {
      return -1;
    } else if (o1.startsWith(Cards.ACE.toString()) && o2.startsWith(Cards.KING.toString())) {
      return 1;
    }

    return 0;
  }

}
