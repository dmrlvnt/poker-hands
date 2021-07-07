package com.casino.carddealer.evaluator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Rank finder {@link RankFinder} test class.
 */
class RankFinderTest {

  @DisplayName("Royal Flush")
  @Test
  void testRoyalFlush() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("TC", "JC", "QC", "KC", "AC"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 1", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 1");
    assertThat(ev.getHand()).hasToString("TCJCQCKCAC");
    assertThat(ev.getHandOf()).hasToString("Clubs");
    assertThat(ev.getRank()).isEqualTo(Ranks.ROYAL_FLUSH);
    assertThat(ev.getHighestValue().isEmpty()).isTrue();
  }

  @DisplayName("Straight Flush")
  @Test
  void testStraightFlush() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "3C", "4C", "5C", "6C"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 2", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 2");
    assertThat(ev.getHand()).hasToString("2C3C4C5C6C");
    assertThat(ev.getHandOf()).hasToString("Clubs");
    assertThat(ev.getRank()).isEqualTo(Ranks.STRAIGHT_FLUSH);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(6);
  }

  @DisplayName("Four of A Kind")
  @Test
  void testFourOfAKind() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "6C", "6D", "6H", "6S"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 3", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 3");
    assertThat(ev.getHand()).hasToString("2C6C6D6H6S");
    assertThat(ev.getHandOf()).hasToString("Six");
    assertThat(ev.getRank()).isEqualTo(Ranks.FOUR_OF_A_KIND);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(6);
  }

  @DisplayName("Full House")
  @Test
  void testFullHouse() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "2D", "6D", "6H", "6S"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 4", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 4");
    assertThat(ev.getHand()).hasToString("2C2D6D6H6S");
    assertThat(ev.getHandOf()).hasToString("Six");
    assertThat(ev.getRank()).isEqualTo(Ranks.FULL_HOUSE);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(6);
  }

  @DisplayName("Flush")
  @Test
  void testFlush() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2D", "4D", "6D", "8D", "QD"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 5", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 5");
    assertThat(ev.getHand()).hasToString("2D4D6D8DQD");
    assertThat(ev.getHandOf()).hasToString("Diamonds");
    assertThat(ev.getRank()).isEqualTo(Ranks.FLUSH);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(12);
  }

  @DisplayName("Straight")
  @Test
  void testStraight() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "3D", "4D", "5S", "6H"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 6", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 6");
    assertThat(ev.getHand()).hasToString("2C3D4D5S6H");
    assertThat(ev.getHandOf()).hasToString("Six");
    assertThat(ev.getRank()).isEqualTo(Ranks.STRAIGHT);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(6);
  }

  @DisplayName("Three of A Kind")
  @Test
  void testThreeOfAKind() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("3C", "3D", "3H", "5S", "6H"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 7", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 7");
    assertThat(ev.getHand()).hasToString("3C3D3H5S6H");
    assertThat(ev.getHandOf()).hasToString("Three");
    assertThat(ev.getRank()).isEqualTo(Ranks.THREE_OF_A_KIND);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(3);
  }

  @DisplayName("Two Pairs")
  @Test
  void testTwoPairs() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("3C", "3D", "5H", "5S", "6H"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 8", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 8");
    assertThat(ev.getHand()).hasToString("3C3D5H5S6H");
    assertThat(ev.getHandOf()).hasToString("Five");
    assertThat(ev.getRank()).isEqualTo(Ranks.TWO_PAIRS);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(5);
  }

  @DisplayName("One Pair")
  @Test
  void testOnePair() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "4D", "7H", "7S", "AH"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 9", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 9");
    assertThat(ev.getHand()).hasToString("2C4D7H7SAH");
    assertThat(ev.getHandOf()).hasToString("Seven");
    assertThat(ev.getRank()).isEqualTo(Ranks.ONE_PAIR);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(7);
  }

  @DisplayName("High Card")
  @Test
  void testHighCard() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("2C", "5C", "7D", "8S", "QH"));
    final Entry<String, List<String>> entry = new AbstractMap.SimpleEntry<>("Player 10", hands);

    // When
    Evaluation ev = RankFinder.find(entry);

    // Then
    assertThat(ev).isNotNull();
    assertThat(ev.getPlayer()).isEqualTo("Player 10");
    assertThat(ev.getHand()).hasToString("2C5C7D8SQH");
    assertThat(ev.getHandOf()).hasToString("Queen");
    assertThat(ev.getRank()).isEqualTo(Ranks.HIGH_CARD);
    assertThat(ev.getHighestValue().get(0)).isEqualTo(12);
  }

}
