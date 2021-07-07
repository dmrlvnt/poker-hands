package com.casino.carddealer.evaluator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Rank generator {@link RankGenerator} test class.
 */
class RankGeneratorTest {

  @DisplayName("Royal Flash Cards generation")
  @Test
  void testGenerateRoyalFlashCards() {
    // When
    List<String> royalFlashCards = RankGenerator.generateRoyalFlashCards();

    // Then
    assertThat(royalFlashCards.isEmpty()).isFalse();
    assertThat(royalFlashCards.size()).isSameAs(4);
    assertThat(String.join("", royalFlashCards))
        .hasToString("TCJCQCKCACTDJDQDKDADTHJHQHKHAHTSJSQSKSAS");
  }

  @DisplayName("Straight Flash Cards generation")
  @Test
  void testGenerateStraightFlashCards() {
    // When
    List<String> straightFlashCards = RankGenerator.generateStraightFlashCards();

    // Then
    assertThat(straightFlashCards.isEmpty()).isFalse();
    assertThat(straightFlashCards.size()).isSameAs(32);
    assertThat(String.join("", straightFlashCards)).hasToString(
        "2C3C4C5C6C2D3D4D5D6D2H3H4H5H6H2S3S4S5S6S3C4C5C6C7C3D4D5D6D7D3H4H5H6H7H3S4S5S6S7S4C5C6C7C8C4D5D6D7D8D4H5H6H7H8H4S5S6S7S8S5C6C7C8C9C5D6D7D8D9D5H6H7H8H9H5S6S7S8S9S6C7C8C9CTC6D7D8D9DTD6H7H8H9HTH6S7S8S9STS7C8C9CTCJC7D8D9DTDJD7H8H9HTHJH7S8S9STSJS8C9CTCJCQC8D9DTDJDQD8H9HTHJHQH8S9STSJSQS9CTCJCQCKC9DTDJDQDKD9HTHJHQHKH9STSJSQSKS");
  }

  @DisplayName("Four Of A Kind Cards generation")
  @Test
  void testGenerateFourOfAKindCards() {
    // When
    List<String> fourOfAKindCards = RankGenerator.generateFourOfAKindCards();

    // Then
    assertThat(fourOfAKindCards.isEmpty()).isFalse();
    assertThat(fourOfAKindCards.size()).isSameAs(13);
    assertThat(String.join("", fourOfAKindCards)).hasToString(
        "2C2D2H2S3C3D3H3S4C4D4H4S5C5D5H5S6C6D6H6S7C7D7H7S8C8D8H8S9C9D9H9STCTDTHTSJCJDJHJSQCQDQHQSKCKDKHKSACADAHAS");
  }

}
