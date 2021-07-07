package com.casino.carddealer.evaluator;

import static com.casino.carddealer.evaluator.RankGenerator.generateFourOfAKindCards;
import static com.casino.carddealer.evaluator.RankGenerator.generateRoyalFlashCards;
import static com.casino.carddealer.evaluator.RankGenerator.generateStraightFlashCards;

import com.casino.carddealer.comparator.CardComparator;
import com.casino.carddealer.utility.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Rank finder is used to find the correct rank, the hand of the rank, and the highest values of the
 * hand by searching in the card groups or using regex. Finally, it returns a full Evaluation
 * instance for decision making.
 */
public interface RankFinder {

  List<String> royalFlashes = Collections.unmodifiableList(generateRoyalFlashCards());
  List<String> straightFlashes = Collections.unmodifiableList(generateStraightFlashCards());
  List<String> fourOfAKinds = Collections.unmodifiableList(generateFourOfAKindCards());

  static Evaluation find(Entry<String, List<String>> hand) {
    String player = hand.getKey();
    hand.getValue().sort(new CardComparator());
    var playerCards = String.join("", hand.getValue());

    // start searching Royal Flush
    return royalFlush(playerCards, player);
  }

  static Evaluation royalFlush(String playerCards, String player) {
    int index = Collections.binarySearch(royalFlashes, playerCards);
    if (index >= 0) {
      return new Evaluation.Builder(player).withRank(Ranks.ROYAL_FLUSH)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.ROYAL_FLUSH, 0))
          .withHighestValue(getHighestCards(playerCards, Ranks.ROYAL_FLUSH, 0)).build();
    }
    // no Royal Flush, start searching Straight Flush
    return straightFlush(playerCards, player);
  }

  static Evaluation straightFlush(String playerCards, String player) {
    int index = Collections.binarySearch(straightFlashes, playerCards);
    if (index >= 0) {
      return new Evaluation.Builder(player).withRank(Ranks.STRAIGHT_FLUSH)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.STRAIGHT_FLUSH, 0))
          .withHighestValue(getHighestCards(playerCards, Ranks.STRAIGHT_FLUSH, 0)).build();
    }
    // no Straight Flush, start searching Four of a Kind
    return fourOfAKind(playerCards, player);
  }

  static Evaluation fourOfAKind(String playerCards, String player) {
    for (String four : fourOfAKinds) {
      if (playerCards.contains(four)) {
        return new Evaluation.Builder(player).withRank(Ranks.FOUR_OF_A_KIND)
            .withHand(playerCards)
            .withHandOf(getHandsOf(playerCards, Ranks.FOUR_OF_A_KIND, playerCards.indexOf(four)))
            .withHighestValue(
                getHighestCards(playerCards, Ranks.FOUR_OF_A_KIND, playerCards.indexOf(four)))
            .build();
      }
    }
    // no Four of a Kind, start searching Full House
    return fullHouse(playerCards, player);
  }

  static Evaluation fullHouse(String playerCards, String player) {
    var matched = false;
    var index = 0;
    if (Pattern.matches("([2-9|TJQKA])[CDHS]\\1[CDHS]\\1[CDHS]([2-9|TJQKA])[CDHS]\\2[CDHS]",
        playerCards)) {
      matched = true;
    }
    if (Pattern.matches("([2-9|TJQKA])[CDHS]\\1[CDHS]([2-9|TJQKA])[CDHS]\\2[CDHS]\\2[CDHS]",
        playerCards)) {
      matched = true;
      index = 4;
    }
    if (matched) {
      return new Evaluation.Builder(player).withRank(Ranks.FULL_HOUSE)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.FULL_HOUSE, index))
          .withHighestValue(
              getHighestCards(playerCards, Ranks.FULL_HOUSE, index)).build();
    } else {
      // no Full House, start searching Flush
      return flush(playerCards, player);
    }

  }

  static Evaluation flush(String playerCards, String player) {
    if (Pattern
        .matches("[2-9|TJQKA]([CDHS])[2-9|TJQKA]\\1[2-9|TJQKA]\\1[2-9|TJQKA]\\1([2-9|TJQKA])\\1",
            playerCards)) {
      return new Evaluation.Builder(player).withRank(Ranks.FLUSH)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.FLUSH, 0))
          .withHighestValue(
              getHighestCards(playerCards, Ranks.FLUSH, 0)).build();
    }
    // no Flush, start searching Straight
    return straight(playerCards, player);
  }

  static Evaluation straight(String playerCards, String player) {
    if (Character.getNumericValue(playerCards.charAt(8))
        == Character.getNumericValue(playerCards.charAt(6) + 1) &&
        Character.getNumericValue(playerCards.charAt(6))
            == Character.getNumericValue(playerCards.charAt(4)) + 1 &&
        Character.getNumericValue(playerCards.charAt(4))
            == Character.getNumericValue(playerCards.charAt(2)) + 1 &&
        Character.getNumericValue(playerCards.charAt(2))
            == Character.getNumericValue(playerCards.charAt(0)) + 1) {
      return new Evaluation.Builder(player).withRank(Ranks.STRAIGHT)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.STRAIGHT, 0))
          .withHighestValue(
              getHighestCards(playerCards, Ranks.STRAIGHT, 0)).build();
    }
    // no Straight, start searching Three of a Kind
    return threeOfAKind(playerCards, player);
  }

  static Evaluation threeOfAKind(String playerCards, String player) {
    var matched = false;
    var index = 0;
    if (Pattern
        .matches("([2-9|TJQKA])[CDHS]\\1[CDHS]\\1[CDHS][2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS]\\1[CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
      index = 2;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS]\\1[CDHS]",
            playerCards)) {
      matched = true;
      index = 4;
    }
    if (matched) {
      return new Evaluation.Builder(player).withRank(Ranks.THREE_OF_A_KIND)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.THREE_OF_A_KIND, index))
          .withHighestValue(
              getHighestCards(playerCards, Ranks.THREE_OF_A_KIND, index)).build();
    } else {
      // no Three of a Kind, start searching Two Pairs
      return twoPairs(playerCards, player);
    }
  }

  static Evaluation twoPairs(String playerCards, String player) {
    var matched = false;
    var index = 0;
    if (Pattern
        .matches("([2-9|TJQKA])[CDHS]\\1[CDHS]([2-9|TJQKA])[CDHS]\\2[CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
      index = 8;
    }
    if (Pattern
        .matches("([2-9|TJQKA])[CDHS]\\1[CDHS][2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\2[CDHS]",
            playerCards)) {
      matched = true;
      index = 4;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS]([2-9|TJQKA])[CDHS]\\2[CDHS]",
            playerCards)) {
      matched = true;
    }

    if (matched) {
      return new Evaluation.Builder(player).withRank(Ranks.TWO_PAIRS)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.TWO_PAIRS, index))
          .withHighestValue(
              getHighestCards(playerCards, Ranks.TWO_PAIRS, index)).build();
    } else {
      // no Two Pairs, start searching One Pair
      return onePair(playerCards, player);
    }
  }

  static Evaluation onePair(String playerCards, String player) {
    var matched = false;
    var index = 0;
    if (Pattern
        .matches("([2-9|TJQKA])[CDHS]\\1[CDHS][2-9|TJQKA][CDHS][2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS][2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
      index = 2;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS][2-9|TJQKA][CDHS]",
            playerCards)) {
      matched = true;
      index = 4;
    }
    if (Pattern
        .matches("[2-9|TJQKA][CDHS][2-9|TJQKA][CDHS][2-9|TJQKA][CDHS]([2-9|TJQKA])[CDHS]\\1[CDHS]",
            playerCards)) {
      matched = true;
      index = 6;
    }
    if (matched) {
      return new Evaluation.Builder(player).withRank(Ranks.ONE_PAIR)
          .withHand(playerCards)
          .withHandOf(getHandsOf(playerCards, Ranks.ONE_PAIR, index))
          .withHighestValue(getHighestCards(playerCards, Ranks.ONE_PAIR, index)).build();
    } else {
      // no One Pair, bring High Card
      return highCard(playerCards, player);
    }
  }

  static Evaluation highCard(String playerCards, String player) {
    return new Evaluation.Builder(player).withRank(Ranks.HIGH_CARD)
        .withHand(playerCards)
        .withHandOf(getHandsOf(playerCards, Ranks.HIGH_CARD, 0))
        .withHighestValue(getHighestCards(playerCards, Ranks.HIGH_CARD, 0))
        .build();
  }

  static String getHandsOf(String playerCards, Ranks rank, int index) {
    String handOf;
    switch (rank) {
      case ROYAL_FLUSH:
      case STRAIGHT_FLUSH:
      case FLUSH:
        handOf = Cards.findBySymbol(playerCards.substring(1, 2));
        break;
      case FOUR_OF_A_KIND:
      case FULL_HOUSE:
      case THREE_OF_A_KIND:
      case ONE_PAIR:
        handOf = Cards.findBySymbol(playerCards.substring(index, index + 1));
        break;
      case TWO_PAIRS:
        handOf = Cards.findBySymbol(
            playerCards.replace(playerCards.substring(index, index + 2), "").substring(4, 5));
        break;
      case STRAIGHT:
      default:
        handOf = Cards.findBySymbol(playerCards.substring(8, 9));
    }

    return handOf;
  }

  static List<Integer> getHighestCards(String playerCards, Ranks rank, int index) {
    List<Integer> highestCards = new ArrayList<>();
    switch (rank) {
      case ROYAL_FLUSH:
        break;
      case STRAIGHT_FLUSH:
        highestCards = cardValues(playerCards.substring(playerCards.length() - 2));
        break;
      case FOUR_OF_A_KIND:
        highestCards.add(cardValues(playerCards.substring(index, index + 2)).get(0));
        highestCards.add(
            cardValues(playerCards.replace(playerCards.substring(index, index + 8), "")).get(0));
        break;
      case FULL_HOUSE:
      case THREE_OF_A_KIND:
        highestCards.add(cardValues(playerCards.substring(index, index + 2)).get(0));
        highestCards.add(
            cardValues(playerCards.replace(playerCards.substring(index, index + 6), "")).get(0));
        break;
      case TWO_PAIRS:
        List<Integer> values = cardValues(
            playerCards.replace(playerCards.substring(index, index + 2), ""));
        highestCards.add(values.get(0));
        highestCards.add(values.get(2));
        highestCards.add(cardValues(playerCards.substring(index, index + 2)).get(0));
        break;
      case ONE_PAIR:
        highestCards.add(cardValues(playerCards.substring(index, index + 2)).get(0));
        highestCards.addAll(
            cardValues(playerCards.replace(playerCards.substring(index, index + 4), "")));
        break;
      case FLUSH:
      case STRAIGHT:
      default:
        highestCards = cardValues(playerCards);
    }

    return highestCards;
  }

  static List<Integer> cardValues(String playerCards) {
    List<Integer> values = new ArrayList<>();
    IntStream.iterate(0, i -> i + 2).limit(playerCards.length() / 2).forEach(i -> {
      switch (playerCards.charAt(i)) {
        case 'T':
          values.add(10);
          break;
        case 'J':
          values.add(11);
          break;
        case 'Q':
          values.add(12);
          break;
        case 'K':
          values.add(13);
          break;
        case 'A':
          values.add(14);
          break;
        default:
          values.add(Character.getNumericValue(playerCards.charAt(i)));
      }
    });
    Collections.reverse(values);

    return values;
  }

}
