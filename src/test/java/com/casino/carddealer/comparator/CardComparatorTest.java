package com.casino.carddealer.comparator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Card comparator {@link CardComparator} test class.
 */
class CardComparatorTest {

  @DisplayName("One hand sorted with comparator")
  @Test
  void testOneHand() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("8S", "TD", "2C", "3S", "8D"));

    // When
    hands.sort(new CardComparator());

    // Then
    assertThat(hands.size()).isSameAs(5);
    assertThat(String.join("", hands)).isEqualTo("2C3S8D8STD");
  }

  @DisplayName("One invalid hand sorted with comparator")
  @Test
  void testOneInvalidHand() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays.asList("8S", "TD", "2C", "3S", "3S"));

    // When
    hands.sort(new CardComparator());

    // Then
    assertThat(hands.size()).isSameAs(5);
    assertThat(String.join("", hands)).isEqualTo("2C3S3S8STD");
  }

  @DisplayName("Two valid hands sorted with comparator")
  @Test
  void testTwoHand() {
    // Given
    final List<String> hands = new ArrayList<>(
        Arrays.asList("8S", "TD", "2C", "3S", "8D", "5D", "8C", "9S", "JS", "AC"));

    // When
    hands.sort(new CardComparator());

    // Then
    assertThat(hands.size()).isSameAs(10);
    assertThat(String.join("", hands)).isEqualTo("2C3S5D8C8D8S9STDJSAC");
  }

}
