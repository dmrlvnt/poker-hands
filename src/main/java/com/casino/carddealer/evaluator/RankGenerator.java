package com.casino.carddealer.evaluator;

import static com.casino.carddealer.utility.Constants.CARD_RANK;
import static com.casino.carddealer.utility.Constants.CARD_SUIT;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Rank generator is used to populate 3 different card groups to simplify search for a rank. 1)
 * Royal Flash cards 2) Straight Flash cards 3) Four Of A Kind cards
 */
public interface RankGenerator {

  static ArrayList<String> generateRoyalFlashCards() {
    var generatedList = new ArrayList<String>();

    CARD_SUIT.stream().forEach(cs -> {
      var sb = new StringBuilder();
      CARD_RANK.stream()
          .filter(cr -> "T".equals(cr) || "J".equals(cr) || "Q".equals(cr) || "K".equals(cr) || "A"
              .equals(cr))
          .forEach(cr -> sb.append(cr + cs));
      generatedList.add(sb.toString());
    });

    return generatedList;
  }

  static ArrayList<String> generateStraightFlashCards() {
    var generatedList = new ArrayList<String>();

    CARD_SUIT.forEach(cs -> CARD_RANK.stream()
        .filter(
            cr -> !"T".equals(cr) && !"J".equals(cr) && !"Q".equals(cr) && !"K".equals(cr) && !"A"
                .equals(cr))
        .forEach(cr -> {
          var sb = new StringBuilder();
          sb.append(cr + cs);
          sb.append(CARD_RANK.get(CARD_RANK.indexOf(cr) + 1) + cs);
          sb.append(CARD_RANK.get(CARD_RANK.indexOf(cr) + 2) + cs);
          sb.append(CARD_RANK.get(CARD_RANK.indexOf(cr) + 3) + cs);
          sb.append(CARD_RANK.get(CARD_RANK.indexOf(cr) + 4) + cs);
          generatedList.add(sb.toString());
        }));
    Collections.sort(generatedList);

    return generatedList;
  }

  static ArrayList<String> generateFourOfAKindCards() {
    var generatedList = new ArrayList<String>();

    CARD_RANK.stream().forEach(cr -> {
      var sb = new StringBuilder();
      CARD_SUIT.forEach(cs -> sb.append(cr + cs));
      generatedList.add(sb.toString());
    });

    return generatedList;
  }

}
